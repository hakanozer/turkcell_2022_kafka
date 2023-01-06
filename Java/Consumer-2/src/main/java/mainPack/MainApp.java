package mainPack;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public class MainApp {
    public static void main(String[] args) {

        ConsumerFactory factory = new ConsumerFactory();
        KafkaConsumer<String, String> consumer = factory.kafkaConsumer();
        factory.messageListener(consumer);


    }
}
