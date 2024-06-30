
setup: docker-network-setup db-setup services-setup monitor-setup

docker-network-setup:
	docker network create --driver bridge lol-network || true

db-setup:
	docker-compose -p lol-base -f ./infra/Ibuild/docker-composes/base.docker-compose.yml up -d

services-setup:
	docker-compose -p lol-services -f ./infra/Ibuild/docker-composes/service.docker-compose.yml up -d

monitor-setup:
	docker-compose -p lol-moniter -f ./infra/Ibuild/docker-composes/moniter.docker-compose.yml up -d
