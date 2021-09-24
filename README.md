# dio-person-api

## Running the project

If you are using an IDE, use it to run the project because you will get Live Reload working.

If you wanna use your terminal, just run:

```sh
./mvnw spring-boot:run
```

## Testing the project

## Automated tests:

```sh
./mvnw test
```

## Manual tests:

### First, run the application and check if service is up:

```sh
./mvnw spring-boot:run

# In another terminal, run:
curl http://localhost:8080/actuator/health
# Output should be {"status":"UP"}
```

### Insert a person

```sh
curl http://localhost:8080/api/v1/person \
    -X POST \
    -H "Content-Type: application/json" \
    -d '{
    "firstName": "Axell",
    "lastName": "Brendow",
    "cpf": "23108762087",
    "birthDate": "15-09-2021",
    "phones": [
        {
            "type": "MOBILE",
            "number": "(11)999999999"
        }
    ]
}' | json_pp
```

### Get all persons

```sh
curl http://localhost:8080/api/v1/person | json_pp
```

### Get person by id

```sh
curl http://localhost:8080/api/v1/person/1 | json_pp
```

### Delete person by id

```sh
curl http://localhost:8080/api/v1/person/1 -X DELETE
```

### Update person by id

```sh
curl http://localhost:8080/api/v1/person/1 \
    -X PUT \
    -H "Content-Type: application/json" \
    -d '{
    "firstName": "Axell_updated",
    "lastName": "Brendow_updated",
    "cpf": "23108762087",
    "birthDate": "15-09-2021",
    "phones": [
        {
            "type": "MOBILE",
            "number": "(11)999999999"
        }
    ]
}' | json_pp
```
