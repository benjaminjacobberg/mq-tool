#!/bin/sh

docker exec -it localstack_main awslocal sqs create-queue --queue-name test-queue
docker exec -it localstack_main awslocal sqs create-queue --queue-name test-queue-dlq

docker exec -it localstack_main awslocal sqs create-queue --queue-name test-queue-sns
docker exec -it localstack_main awslocal sns create-topic --name test-topic
docker exec -it localstack_main awslocal sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:test-topic --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:queue:test-queue-sns