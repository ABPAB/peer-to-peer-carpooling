#!/bin/bash

echo "Creating Kubernetes secret for GCP credentials..."
kubectl create secret generic gcp-credentials --from-file=gcp-credentials.json=gcp-credentials.json

if [ $? -ne 0 ]; then
  echo "Failed to create secret. Exiting..."
  exit 1
fi

echo "Applying authserver deployment..."
kubectl apply -f authserver-deployment.yaml

if [ $? -ne 0 ]; then
  echo "Failed to apply authserver deployment. Exiting..."
  exit 1
fi

echo "Applying authserver service..."
kubectl apply -f authserver-service.yaml

if [ $? -ne 0 ]; then
  echo "Failed to apply authserver service. Exiting..."
  exit 1
fi

echo "Authserver setup completed successfully!"
