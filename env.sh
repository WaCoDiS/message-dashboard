#/bin/sh

if [[ -z $PORT ]]; then
  echo using default port
else
  export SERVER_PORT=$PORT
  echo App will listen on port $PORT
fi

java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
