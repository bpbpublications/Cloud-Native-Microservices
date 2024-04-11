#!/bin/sh

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
	}
	
	execution_handler
	while true
	do 
	 tail -f log/nohup.out & wait ${!}
	done