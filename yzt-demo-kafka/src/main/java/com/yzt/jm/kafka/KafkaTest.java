package com.yzt.jm.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description:
 * @Author: min
 * @Date: 2021-06-23 13:42
 */

public class KafkaTest {

    public static Properties init() {
        Properties conf = new Properties();
        conf.setProperty(ProducerConfig.ACKS_CONFIG, "0");
        conf.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        conf.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        conf.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "49.232.160.85:9092");
        conf.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, DefaultPartitioner.class.getName());
        conf.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "10");
        conf.setProperty(ProducerConfig.LINGER_MS_CONFIG, "0");
        return conf;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties conf = init();
        KafkaProducer<String, String> producer = new KafkaProducer<>(conf);
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 2; j++) {
                ProducerRecord<String, String> record = new ProducerRecord<>("ox", j + "key", "ox" + i);
                Future<RecordMetadata> send = producer.send(record);
                RecordMetadata recordMetadata = send.get();
                System.out.println("offset: " + recordMetadata.offset() + " topic: " + recordMetadata.topic() + " partition: " + recordMetadata.partition());
            }
        }
    }

    public void consumer() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "49.232.160.85:9092");

        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        /**
         *         "What to do when there is no initial offset in Kafka or if the current offset
         *         does not exist any more on the server
         *         (e.g. because that data has been deleted):
         *         <ul>
         *             <li>earliest: automatically reset the offset to the earliest offset
         *             <li>latest: automatically reset the offset to the latest offset</li>
         *             <li>none: throw exception to the consumer if no previous offset is found for the consumer's group</li><li>anything else: throw exception to the consumer.</li>
         *         </ul>";
         */
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earlist");
        //自动提交时异步提交，丢数据&&重复数据
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList("ox"), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                System.out.println("---onPartitionsRevoked:");
                Iterator<TopicPartition> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next().partition());
                }
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                System.out.println("---onPartitionsAssigned:");
                Iterator<TopicPartition> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next().partition());
                }
            }
        });

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ZERO);
            if (!records.isEmpty()) {
                records.partitions().forEach(topicPartition -> {
                    List<ConsumerRecord<String, String>> parRecords = records.records(topicPartition);
                    Iterator<ConsumerRecord<String, String>> iterator = parRecords.iterator();
                    while (iterator.hasNext()) {
                        ConsumerRecord<String, String> record = iterator.next();
                        String key = record.key();
                        String value = record.value();
                        int partition = record.partition();
                        long offset = record.offset();
                        System.out.println("key: " + key + " val: " + value + " partition: " + partition + " offset: " + offset);

                        //按记录提交offset
                        Map<TopicPartition, OffsetAndMetadata> map = new HashMap<>();
                        map.put(new TopicPartition("ox", partition), new OffsetAndMetadata(offset));
                        consumer.commitSync(map);
                    }
                    //按分区提交offset
//                    Map<TopicPartition, OffsetAndMetadata> map = new HashMap<>();
//                    map.put(new TopicPartition("ox", topicPartition.partition()), new OffsetAndMetadata(records.records(topicPartition).size() - 1));
//                    consumer.commitSync(map);

                });
                //按批提交offset
//                consumer.commitSync();
            }

        }


    }
}
