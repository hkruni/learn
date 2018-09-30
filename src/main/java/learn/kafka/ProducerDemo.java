package learn.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

public class ProducerDemo {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "112.35.79.103:9092,112.35.79.103:9093,112.35.79.103:9094");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);//16KB
        props.put("linger.ms", 1000);
        props.put("buffer.memory", 33554432);//32M
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("请输入key和value的格式");
            producer.send(new ProducerRecord<String, String>("topic1", input.nextLine(), input.nextLine()));
        }while(flag);
        producer.close();
    }
}