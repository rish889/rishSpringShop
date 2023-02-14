# sh ./docker-build.sh
ecr_repo="561375666658.dkr.ecr.us-east-1.amazonaws.com/rish-spring-shop-product-service"
echo $ecr_repo
DOCKER_BUILDKIT=1 docker build -f ./Dockerfile -t $ecr_repo:0.0.1-SNAPSHOT .
docker push $ecr_repo:0.0.1-SNAPSHOT
docker tag $ecr_repo:0.0.1-SNAPSHOT $ecr_repo:latest
docker push $ecr_repo:latest

# aws-vault exec rish889IAM --duration=12h
# $(aws ecr get-login --no-include-email --region us-east-1)
# docker run -p 8080:80 -t 561375666658.dkr.ecr.us-east-1.amazonaws.com/rish-spring-shop-product-service:0.0.1-SNAPSHOT

