JAVA_HOME=/home/rishabh/software/jdk-18.0.2.1_linux-x64_bin/jdk-18.0.2.1
mvn clean install -DskipTests
mvn clean package -DskipTests
java -jar -Dspring.profiles.active=local target/rishShop-0.0.1-SNAPSHOT.jar