build:
	./gradlew build

env-start:
	@docker-compose up -d

run:
	@docker-compose exec -T app gradle run

clean:
	./gradlew clean
	docker-compose down -v

test:
	@docker-compose run --rm --env-file .env.test billing_backend --no-deps /app/run_tests.sh

bash:
	@docker-compose exec billing_backend bash

env-stop:
	@docker-compose down
