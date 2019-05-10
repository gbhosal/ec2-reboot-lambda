# newrelic-webhook-gateway-lambda
Receives the webhook request caused by violations to the alert policies setup in the New Relic and take automated action on AWS resources if needed.

# Command to deploy

## Staging environment
$serverless deploy --aws-profile jenkin-role --awsAccountNo 691339910992 --securityGroupId1 sg-f23f8aba --subnetId1 subnet-ab92b7f3 --subnetId2 subnet-4197cc6b

## Production environment
TBD