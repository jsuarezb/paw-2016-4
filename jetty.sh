#!/bin/bash

ENV=${ENV:-development}
java -Dspring.profiles.active=$ENV -jar webapp/target/dependency/jetty-runner.jar --path /grupo4 webapp/target/webapp
