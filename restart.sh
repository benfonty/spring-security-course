
DOCKER_COMPOSE_FILE=docker-compose.yml

docker-compose -f ${DOCKER_COMPOSE_FILE} down
docker-compose -f ${DOCKER_COMPOSE_FILE} up -d
