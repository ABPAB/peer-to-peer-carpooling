@echo off

echo Creating Kubernetes secret for GCP credentials...
kubectl create secret generic gcp-credentials --from-file=gcp-credentials.json=gcp-credentials.json
if %ERRORLEVEL% NEQ 0 (
    echo Failed to create secret. Exiting...
    exit /b %ERRORLEVEL%
)

echo Applying authserver deployment...
kubectl apply -f authserver-deployment.yaml
if %ERRORLEVEL% NEQ 0 (
    echo Failed to apply authserver deployment. Exiting...
    exit /b %ERRORLEVEL%
)

echo Applying authserver service...
kubectl apply -f authserver-service.yaml
if %ERRORLEVEL% NEQ 0 (
    echo Failed to apply authserver service. Exiting...
    exit /b %ERRORLEVEL%
)

echo Authserver setup completed successfully!
pause
