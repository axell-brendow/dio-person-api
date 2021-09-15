# dio-person-api

## Running the project

If you are using an IDE, use it to run the project because you will get Live Reload working.

If you wanna use the terminal, compile and run the jar with:

```sh
mvn install
java -jar target/dio-person-api-0.0.1-SNAPSHOT.jar
```

## Testing the project

First, check if service is up:

```sh
curl http://localhost:8080/actuator/health
# Output should be {"status":"UP"}
```

```sh
curl http://localhost:8080/api/v1/person \
    -X POST \
    -H "Content-Type: application/json" \
    -d '{
    "firstName": "Axell",
    "lastName": "Brendow",
    "cpf": "11111111111",
    "phones": [
        {
            "type": "MOBILE",
            "number": "(11)999999999"
        }
    ]
}' | json_pp
```
