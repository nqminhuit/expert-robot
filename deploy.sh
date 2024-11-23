#!/usr/bin/env sh

mvn install || return 1

podman pod rm -f art
podman pod create -p 5005:5005 -p 5006:5006 art

podman run --replace -d --name mq --pod art docker.io/rabbitmq:4.0.3-alpine || return 1
podman run --replace -d --name pgdb --pod art \
       -e POSTGRES_PASSWORD=dev \
       -e POSTGRES_USER=dev \
       -e POSTGRES_DB=art \
       -v /opt/art/db:/var/lib/postgresql/data \
       docker.io/postgres:17.1-alpine3.20 || return 1

sleep 1
while [[ ! $(podman exec mq rabbitmq-diagnostics -q ping) ]]
do
    sleep 1
done

echo rabbitmq is ready!

podman run --replace -d --name processor --pod art processor:latest || return 1
podman run --replace -d --name crawler --pod art crawler:latest || return 1
