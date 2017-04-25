## How to build the docker image ?

Maven command :  

```
clean package docker:build -t bootkafka:1.0
```

## How to run the Docker Image?

```
docker run -it -p 8080:8080 --name bootkafka bootkafka/learnbootkafka-manual-offset
```

## How to kill a java process running on a particular port?

```
sudo lsof -i :8080

kill -9 [PID]

```
