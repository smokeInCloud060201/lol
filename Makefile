




setup: db-setup services-setup



db-setup:
	docker-compose -p lol-base -f ./infra-build/docker-composes/base.docker-compose.yml up -d

services-setup:
	docker-compose -p lol-services -f ./infra-build/docker-composes/service.docker-compose.yml up -d
