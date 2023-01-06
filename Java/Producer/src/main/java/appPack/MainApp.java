package appPack;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {

    public static void main(String[] args) {

        ProducerFactory factory = new ProducerFactory();
        KafkaProducer<String, String> producer = factory.kafkaProducer();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            factory.sendMessage("New Message : " + i, producer);
        }
        long end = System.currentTimeMillis();
        long bettween = end - start;
        System.out.println(bettween);


        producer.flush();
        producer.close();

    }
}
