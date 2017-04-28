## How to build the docker image ?

Maven command :  

```
clean package docker:build -t bootkafka:1.0
```

## How to check the docker images ?

```
docker images
```

## How to push a docker Image:

Step 1:  

-  Create a docker hub account using the following link https://hub.docker.com/.

Step 2:  

Login in to docker and push the image. Follow the below commands.

```
docker login

docker push <image Name>
```

## How to run the Docker Image?

```
docker run -it -p 8080:8080 --name bootkafka dilipthelip/learnbootkafka-manual-offset
```

## Command  to run the Docker Image to connect to Kafka running in docker
```
docker run --name dockerboot -p 8080:8080 -e ENVIRONMENT=stage -e KAFKABROKER=172.17.0.3:9092 dilipthelip/learnbootkafka-manual-offset
```

## Setting needs to be done in VirtualBox

Please have this settings done in your VirtualBox to connect to the docker container using the local host.

```
http://stackoverflow.com/questions/27471688/how-to-access-tomcat-running-in-docker-container-from-browser
```


## How to kill a java process running on a particular port?

```
sudo lsof -i :8080

kill -9 [PID]

```
