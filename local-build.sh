JAVA_HOME=/home/rishabh/software/jdk-18.0.2.1_linux-x64_bin/jdk-18.0.2.1
cd product-service
mvn clean package
java -jar \
-Dspring.profiles.active=local \
-Drss.postgres.host=localhost:5432 \
target/product-service-0.0.1-SNAPSHOT.jar

# mvn -f ./gatling-load-test/pom.xml gatling:test

# DOCKER_BUILDKIT=1 docker build -f ./Dockerfile -t 561375666658.dkr.ecr.us-east-1.amazonaws.com/rish-spring-shop-product-service:0.0.1-SNAPSHOT .

# cd ./cdk-deploy/; cdk synth; cd..
# cd ./cdk-deploy/; cdk deploy; cd..
# cd ./cdk-deploy/; cdk destroy; cd..
