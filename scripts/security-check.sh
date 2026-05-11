#!/bin/bash

set -e

IMAGE_NAME="secure-api"

mkdir -p reports

mvn clean package -DskipTests

mvn checkstyle:checkstyle || true

docker build -t $IMAGE_NAME .

docker save $IMAGE_NAME -o reports/secure-api.tar

trivy image --input reports/secure-api.tar \
  --format table \
  --output reports/trivy-report.txt || true

trivy image --input reports/secure-api.tar \
  --format json \
  --output reports/trivy-report.json || true
