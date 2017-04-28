## How to build the docker image ?

Maven command :  

```
clean package docker:build
```

## How to run the Docker Image?

```
docker run --name dockerboot -p 8080:8080 -e ENVIRONMENT=stage -e KAFKABROKER=172.17.0.3:9092 dilipthelip/learnbootkafka-manual-offset
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

## How to kill a java process running on a particular port?

```
sudo lsof -i :8080

kill -9 [PID]

```
