@echo off
echo Creating namespace...
kubectl create namespace kafka

echo Applying Zookeeper deployment...
kubectl apply -f 01-zookeeper.yaml -n kafka

echo Applying Kafka deployment...
kubectl apply -f 02-kafka.yaml -n kafka

echo All Zookeeper and Kafka resources have been applied successfully!
pause
