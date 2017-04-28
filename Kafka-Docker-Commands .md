## Download Docker Images from github:

Clone the following repo to your local.  

```
git clone https://github.com/ches/docker-kafka.git
```
## Run the Zookeeper Docker Image:

```
docker run -d --name zookeeper jplock/zookeeper:3.4.6
```

Check the docker zookeeper image is up by running the following command.

```
docker ps -a
```
## Run the Kafka Broker Docker Image:

```
docker run -d --name kafka --link zookeeper:zookeeper ches/kafka
```

Check the docker kafka image is up by running the following command.  

```
docker ps -a
```

## Create a topic to the docker Kafka

### Check the port of zookeeper Docker instance

```
docker inspect --format '{{ .NetworkSettings.IPAddress }}' zookeeper
```

### Check the port of kafka Docker instance

```
docker inspect --format '{{ .NetworkSettings.IPAddress }}' kafka
```

### Set the env variables:

```
ZK_IP=$(docker inspect --format '{{ .NetworkSettings.IPAddress }}' zookeeper)
KAFKA_IP=$(docker inspect --format '{{ .NetworkSettings.IPAddress }}' kafka)
```

```
docker run --rm ches/kafka kafka-topics.sh --create --topic my-topic --replication-factor 1 --partitions 1 --zookeeper $ZK_IP:2181
```
