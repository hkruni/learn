package learn.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo {

    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "112.35.79.103:9092,112.35.79.103:9093,112.35.79.103:9094");
        properties.put("group.id", "group-1");
        properties.put("enable.auto.commit", "true");//设置自动提交偏移量
        properties.put("auto.commit.interval.ms", "1000"); //自动提交偏移的时间间隔是1秒
        properties.put("auto.offset.reset", "earliest");
        properties.put("session.timeout.ms", "30000");//消费者如果30秒没有和服务器联系就被认为断开
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("topic1"));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, partition = %d value = %s ", record.offset(), record.partition() ,record.value());
                System.out.println();
            }
        }

    }
}