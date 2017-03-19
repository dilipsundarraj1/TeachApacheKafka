# Kafka Commands

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

## How to display the list of topics in a broker ?

```
./kafka-topics.sh --describe --zookeeper localhost:2181
```

## How to create a topic ?

**MAC:**  
```
./kafka-topics.sh --create --topic <topic-name> -zookeeper localhost:2181 --replication-factor 1 --partitions 1

```

Example:  
The below command creates a topic called **mytopic**.
```
./kafka-topics.sh --create --topic mytopic -zookeeper localhost:2181 --replication-factor 1 --partitions 1
```

## How to instantiate a Console Producer?

**MAC:**  
```
./kafka-console-producer.sh --broker-list localhost:9092 --topic <topic-name>
```

Example :

```
./kafka-console-producer.sh --broker-list localhost:9092 --topic mytopic
```

## How to instantiate a Console Consumer?

**MAC**  
```
./kafka-console-consumer.sh --zookeeper localhost:2181 --topic <topic-name> --from-beginning
```

Example:  
```
./kafka-console-consumer.sh --zookeeper localhost:2181 --topic mytopic --from-beginning
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
