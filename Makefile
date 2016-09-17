VERSION=1.0.0
PROJECT=lcmap-event
STANDALONE=target/$(PROJECT)-$(VERSION)-SNAPSHOT-standalone.jar
ROOT_DIR = $(shell pwd)

include resources/make/code.mk
include resources/make/docs.mk
include resources/make/docker.mk

