# sh ./docker-build.sh
DOCKER_BUILDKIT=1 docker build -f ./DockerfileMain -t 561375666658.dkr.ecr.us-east-1.amazonaws.com/rish-spring-shop:0.0.1-SNAPSHOT .
docker push 561375666658.dkr.ecr.us-east-1.amazonaws.com/rish-spring-shop:0.0.1-SNAPSHOT
docker tag 561375666658.dkr.ecr.us-east-1.amazonaws.com/rish-spring-shop:0.0.1-SNAPSHOT 561375666658.dkr.ecr.us-east-1.amazonaws.com/rish-spring-shop:latest
docker push 561375666658.dkr.ecr.us-east-1.amazonaws.com/rish-spring-shop:latest

# aws-vault exec rish889IAM --duration=12h
# $(aws ecr get-login --no-include-email --region us-east-1)
# docker run -p 8080:80 -t 561375666658.dkr.ecr.us-east-1.amazonaws.com/rish-spring-shop:0.0.1-SNAPSHOT

