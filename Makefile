run-in-dev:
	mvn clean install -Dmaven.test.skip && java -jar -Dspring.profiles.active=dev target/verly-service-1.0.jar

run-in-prod:
	mvn clean install -Dmaven.test.skip && java -jar -Dspring.profiles.active=prod target/verly-service*.jar

tests:
	mvn clean verify
