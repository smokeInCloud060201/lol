.PHONY: setup db-setup services-setup docker-network-setup monitor-setup

setup: docker-network-setup db-setup services-setup monitor-setup

docker-network-setup:
	docker network create --driver bridge lol-network || true

db-setup:
	docker-compose -p lol-base -f ./infra/build/docker-composes/base.docker-compose.yml up -d

services-setup:
	docker-compose -p lol-services -f ./infra/build/docker-composes/service.docker-compose.yml up -d

monitor-setup:
	docker-compose -p lol-moniter -f ./infra/build/docker-composes/moniter.docker-compose.yml up -d
