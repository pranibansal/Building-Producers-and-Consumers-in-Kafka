# Building-Producers-and-Consumers-in-Kafka
Basic example of Building Producers and Consumers in Kafka



Some basic commands needs to be followed 

#To start Zookeeper server

zookeeper-server-start.sh ~/kafka_2.13-3.4.0/config/zookeeper.properties

#To start Kafka server

kafka-server-start.sh ~/kafka_2.13-3.4.0/config/server.properties

#To create Kafka topic (Example - prani2 with certain no of partitions)

kafka-topics.sh --bootstrap-server localhost:9092 --topic prani2 --create --partitions 4 --replication-factor 1

#To view Kafka producer output

kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic prani2
