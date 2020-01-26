# Money Transfer Demo

## How to start the application

```
./mvnw exec:java
```
The application should start on http://localhost:4567

## API

In order to create account send this POST request
```
curl -v --header "Content-Type: application/json" \
                                 --request POST \
                                 --data '{
                                          "accountId": 1,
                                          "firstName": "Max", 
                                          "lastName": "Musterman"
                                          }'\
                                 http://localhost:4567/account

```
To simplified the use case every new account has 10 units balance by default.
Creating transaction from one account to another can be done by invoking this POST request

```
curl -v --header "Content-Type: application/json" \
                                 --request POST \
                                 --data '{
                                          "fromAccount": 1,
                                          "toAccount": 2, 
                                          "amuont": 5
                                          }'\
                                 http://localhost:4567/transactions
```