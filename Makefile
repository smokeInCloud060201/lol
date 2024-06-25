

setup: db-setup services-setup docker-network-setup monitor-setup

docker-network-setup:
	@if [ -z "$$(docker network ls --filter name=^common-network$$ --format '{{.Name}}')" ]; then \
		echo "Creating common-network..."; \
		docker network create common-network; \
	else \
		echo "common-network already exists."; \
	fi

db-setup:
	docker-compose -p lol-base -f ./infra-build/docker-composes/base.docker-compose.yml up -d

services-setup:
	docker-compose -p lol-services -f ./infra-build/docker-composes/service.docker-compose.yml up -d

monitor-setup:
	docker-compose -p lol-moniter -f ./infra-build/docker-composes/moniter.docker-compose.yml up -d
