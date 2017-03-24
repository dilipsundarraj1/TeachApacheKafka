# Kafka Commands

## How to download Kafka ?

```
curl "http://apache.mirrors.tds.net/kafka/0.10.2.0/kafka_2.11-0.10.2.0.tgz" | tar xz
```

## How to start a zookeeper ?

**Windows:**

```
zookeeper-server-start.bat ..\..\config\zookeeper.properties
```

**MAC/Unix:**

```
./zookeeper-server-start.sh ../config/zookeeper.properties
```
## How to start a Kafka Broker ?

**Windows:**

```
kafka-server-start.bat ..\..\config\server.properties
```

**MAC/Unix :**

```
./kafka-server-start.sh ../config/server.properties
```

## How to check the configuration of all the topics in a broker ?

```
./kafka-topics.sh --describe --zookeeper localhost:2181
```

## How to create a topic ?
**Windows**
```
kafka-topics.bat --create --topic <topic-name> -zookeeper localhost:2181 --replication-factor 1 --partitions 1.
```
Example:  

```
kafka-topics.bat --create --topic my-first-topic -zookeeper localhost:2181 --replication-factor 1 --partitions 1.
```

**MAC:**  
```
./kafka-topics.sh --create --topic <topic-name> -zookeeper localhost:2181 --replication-factor 1 --partitions 1

```

Example:  
The below command creates a topic called **my-first-topic**.
```
./kafka-topics.sh --create --topic my-first-topic -zookeeper localhost:2181 --replication-factor 1 --partitions 1
```

## How to instantiate a Console Producer?

**Windows:**
```
kafka-console-producer.bat --broker-list localhost:9092 --topic <topic-name>
```

Example:  
```
kafka-console-producer.bat --broker-list localhost:9092 --topic my-first-topic
```

**MAC:**  
```
./kafka-console-producer.sh --broker-list localhost:9092 --topic <topic-name>
```

Example :

```
./kafka-console-producer.sh --broker-list localhost:9092 --topic my-first-topic
```

## How to instantiate a Console Consumer?

**Windows:**
```
kafka-console-producer.bat --broker-list localhost:9092 --topic <topic-name>
```

Example:  
```
kafka-console-consumer.bat --zookeeper localhost:2181 --topic my-first-topic --from-beginning.

```

**MAC**  
```
./kafka-console-consumer.sh --zookeeper localhost:2181 --topic <topic-name> --from-beginning
```

Example:  
```
./kafka-console-consumer.sh --zookeeper localhost:2181 --topic my-first-topic --from-beginning
```

## How to delete a topic?

```
./kafka-topics.sh --delete --zookeeper localhost:2181 --topic your_topic_name
```

## How to kill the Broker Process?

Step 1:   
```
ps ax | grep -i 'kafka\.Kafka'
```

Step 2:  

```
kill -9 <processId>
```
