
rabbit-up:
	@docker-compose -f docker/dev-compose.yml up

rabbit-down:
	@docker-compose -f docker/dev-compose.yml down
