#!/bin/sh

set -e

if [ "$1" = 'start' ]; then

    # Check to see if the environment variable ENVIRONMENT is set. If it is, we can set our Spring Boot active profile
    # based on it. If it isn't, the default Spring Profile will be used.
    [ -z "$ENVIRONMENT" ] && printf "WARN:: No environment specified, relying on default Spring profile. To change the \
    environment, please supply a Docker environment variable. Currently available environments are:\n\n\
    stage (default)\n\
    prod\n\n\
    Example: docker run -e ENVIRONMENT=prod bootkafka bootkafka/learnbootkafka-manual-offset\n";

    java_opts="-Djava.security.egd=file:/dev/./urandom"

    # If an environment variable is set to provide a different Spring Profile, use it.
	if [ $ENVIRONMENT ]; then 
		java_opts="$java_opts -Dspring.profiles.active=$ENVIRONMENT"
	fi
	
		java_opts="$java_opts -Dkafka.broker=$KAFKABROKER"
	
	printf "java options are : $java_opts"
    # Launch the app with whatever Java options are required to execute.
    exec java -jar ${java_opts} /app.jar
fi

exec "$@"