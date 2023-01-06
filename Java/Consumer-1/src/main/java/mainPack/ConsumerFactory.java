package mainPack;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerFactory {

    private final String serverUrl = "localhost:9092";
    private final String topicName = "projectTopic";
    private final String groupId = "groupId";

    public KafkaConsumer<String, String> kafkaConsumer() {
        Properties prop = new Properties();
        prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serverUrl);
        prop.setProperty( ConsumerConfig.GROUP_ID_CONFIG, groupId );
        prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);
        return consumer;
    }

    public void messageListener(KafkaConsumer<String, String> consumer) {
        consumer.subscribe(Collections.singletonList(topicName));
        Gson gson = new Gson();
        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for(ConsumerRecord record : records) {
                String item = gson.toJson(record);
                System.out.println( item );
            }
        }
    }

}
