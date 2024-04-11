#!/bin/sh

## Sigterm Handler
sigterm_handler() {
  echo "Gracefully stopping the process with Id = $pid"
  if [ "$pid" -ne 0 ]; then
    # the above if statement is important because it ensures 
    # that the application has already started. without it you
    # could attempt cleanup steps if the application failed to
    # start, causing errors.
	kill -15 "$pid"
    wait "$pid"
  fi
  exit 0;
}

## Setup signal trap
# on callback execute the specified handler
trap 'sigterm_handler' SIGTERM

execution_handler(){
	HEAP_OPTS="-Xms${MIN_HEAP_SIZE} -Xmx${MAX_HEAP_SIZE}"
	EXTERNAL_PARAMS="--spring.profiles.active=${PROFILE} --spring.security.user.name=${CONFIG_SERVER_USERNAME} --spring.security.user.password=${CONFIG_SERVER_PASSWORD} --spring.cloud.config.server.git.password=${GIT_PASSWORD}"
	
	if [ ! -d "log" ]; then # If log folder does not exist
		echo "Log folder does not exist, creating it";		
		mkdir -p log
	else
		echo "Log folder already exists, deleting contents";
		rm -rf log
		mkdir -p log
	fi	
		
	nohup java -jar config-server.jar ${EXTERNAL_PARAMS} ${HEAP_OPTS} > log/nohup.out &		
	pid="$!"
}

execution_handler
while true
do 
	tail -f log/nohup.out & wait ${!}
done	