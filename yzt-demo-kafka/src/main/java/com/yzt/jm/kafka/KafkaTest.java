package com.yzt.jm.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
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

}
