package appPack;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerFactory {

    public static final Logger log = LoggerFactory.getLogger(MainApp.class.getSimpleName());
    Gson gson = new Gson();

    private final String serverUrl = "localhost:9092";
    private final String topicName = "projectTopic";

    public KafkaProducer<String, String> kafkaProducer() {
        log.info("Starting Kafka Producer Client");
        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serverUrl);
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);
        return producer;
    }

    public void sendMessage( String message, KafkaProducer<String, String> producer ) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, message);
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if ( e == null ) {
                    String metaData = gson.toJson(recordMetadata);
                    metaData = "message : " + message + " - " + metaData;
                    System.out.println( metaData );
                }else {
                    System.err.println(e.getMessage());
                }
            }
        });
    }

}
