# Money Transfer Demo

The application is designed to have 2 domains which communicate via events. One domain is account domain which is managing accounts and balances on accounts.
The other one is transaction domain which is managing transactions and its statuses.
Each domain has events which are related to the changes inside domain. 
The separation/decoupling of those 2 domains was  implemented by using Java Reactive Streams API from JDK 11. 


## How to start the application

To start using maven wrapper
```
./mvnw exec:java
```
The application should start on http://localhost:4567

It is possible to build the executable jar

```
./mvnw clean install
```

and run it as 

```
java -jar target/money-transfer-demo-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

## Basic use case

### Creating account
In order to transfer money from one account to another, at least 2 accounts should be created. Account creation can be done
by calling this POST request

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
The request creates account with accountId 1. To create another account, just send request with different id.
To simplified the use case every new account has 10 units balance by default.
Account balance can be checked by
```
curl -v http://localhost:4567/accounts/1/balance
```
which returns

```
{"accountId":1,"amount":10.0}
```

Account details can be return by calling 
```
curl -v http://localhost:4567/accounts/1
```

### Creating transaction and retrieving transaction status

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

The response contains transaction id
```
{"transactionId":"bb12e724-0421-4ce0-85d7-345de093ab6e"}
```

Every transaction can have following statuses

- PENDING - which is status of newly created transaction which is not yet processed
- SUCCESSFUL - which is status of committed/processed transaction
- DISCARD - status of discard transaction

Transaction status can be retrieved by
```
curl -v http://localhost:4567/transactions/bb12e724-0421-4ce0-85d7-345de093ab6e

```
 