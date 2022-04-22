CORES ?= $(shell sysctl -n hw.ncpu || echo 1)
all:; @$(MAKE) _all -j$(CORES)

_all: store management game user mongo

store:
	./mvnw spring-boot:run -pl store

management:
	./mvnw spring-boot:run -pl management

game:
	./mvnw spring-boot:run -pl game

user:
	./mvnw spring-boot:run -pl user

mongo:
	mongod --dbpath=./data

reset_mongo:
	rm -rf ./data
	mkdir ./data

lint:
	./mvnw spring-javaformat:apply

.PHONY: all _all store management game user mongo lint