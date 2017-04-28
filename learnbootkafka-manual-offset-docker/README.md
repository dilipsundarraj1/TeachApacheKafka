# Learn Spring Boot Kafka

## Java Command to launch the jar file

The below command will take the stage profile configuration and launch the job.  

```
java -jar -Dspring.profiles.active=stage <executable>.jar
```

## Launching multiple instances:

```
java -jar -Dspring.profiles.active=stage -Dserver.port=8081 <executable>.jar
```
