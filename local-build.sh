JAVA_HOME=/home/rishabh/software/jdk-18.0.2.1_linux-x64_bin/jdk-18.0.2.1
cd product-service
mvn clean package
java -jar \
-Dspring.profiles.active=local \
-Drss.postgres.host=localhost:5432 \
target/product-service-0.0.1-SNAPSHOT.jar