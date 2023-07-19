###run docker image


1. start docker desktop
2. Goto mdts-asset root folder
3. use command below to start the docker mdts-asset-api

```
docker-compose up
```

### test

```
curl --location --request POST 'http://localhost:6868/api/v1/asset' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Dell laptop",
    "type": "computer",
    "description": "dell precision"
    
}'
```

###postman collection 

use attached postman collection for test