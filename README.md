# Building-Producers-and-Consumers-in-Kafka
Basic example of Building Producers and Consumers in Kafka
Some basic commands needs to be followed 

zookeeper-server-start.sh ~/kafka_2.13-3.4.0/config/zookeeper.properties

kafka-server-start.sh ~/kafka_2.13-3.4.0/config/server.properties

kafka-topics.sh --bootstrap-server localhost:9092 --topic prani2 --create --partitions 4 --replication-factor 1

kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic prani2
