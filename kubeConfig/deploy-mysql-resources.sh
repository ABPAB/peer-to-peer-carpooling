#!/bin/bash

# Deploy MySQL resources to the 'mysql' namespace
echo "Creating namespace..."
kubectl create namespace mysql

echo "Applying MySQL Secret..."
kubectl apply -f mysql-secret.yaml -n mysql

echo "Applying MySQL Storage..."
kubectl apply -f mysql-storage.yaml -n mysql

echo "Applying MySQL Deployment..."
kubectl apply -f mysql-deployment.yaml -n mysql

echo "Applying MySQL Service..."
kubectl apply -f mysql-service.yaml -n mysql

echo "All MySQL resources have been applied successfully!"
