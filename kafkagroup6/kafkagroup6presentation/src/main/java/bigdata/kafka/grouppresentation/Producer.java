package bigdata.kafka.grouppresentation;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class.getSimpleName());

    public static void main(String[] args) {
        log.info("Kafka Producer!");

        // create Producer Properties
        Properties properties = new Properties();

        // Unsecurely connect to Localhost - 127.0.0.9092
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");



        // set producer properties - how producer producing data and datatype is string
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        //Sticky partitioner - In a single batch multiple records are send which enhances performance

        //You can use Round robin Partitioner also but it would degrade performance


        // create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);


        for (int j=0; j<2; j++){
            //no of messages per batch
            //same keys goes to same partition
            for (int i=0; i<10; i++){

                String topic = "prani2";
                String key = "id_" + i;
                String value = "demokafkapresentation " + i;

                // create a Producer Record
                ProducerRecord<String, String> producerRecord =
                        new ProducerRecord<>(topic, key, value);

                // send data
                producer.send(producerRecord, new Callback() {
                    @Override
                    //Callback function to provided to receive acknowledgement or exception. Happens in Asynchronous Producer
                    //Performance is equivalent to Fire and Forget Producer but if exception occurs we can retrieve lost messages
                    //Callback interface is called and only 1 method is override
                    public void onCompletion(RecordMetadata metadata, Exception e) {
                        // executes every time a record successfully sent or an exception is thrown
                        if (e == null) {
                            // the record was successfully sent
                            log.info( "Topic: " + metadata.topic() +
                                    "| Key: " + key +
                                    "| Partition: " + metadata.partition() +
                                    "| Offset: " + metadata.offset() +
                                    "| Timestamp: " + metadata.timestamp());
                        } else {
                            log.error("Error while producing", e);
                        }
                    }
                });
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        // tell the producer to send all data and block until done -- synchronous
        producer.flush();

        // flush and close the producer
        producer.close();
    }
}
