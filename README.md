### Key Technologies

* Spring-Boot
* Spring-Web
* Spring Data Jpa
* Spring Test
* MySql
* Flyway

### JDK Version
* JDK 17

### Environment Setup
* Install mysql database server
* Create database user with **selise/selise123**
* Give database permission to the user
* Create new database selise_banking
* Run flyway Script

>`./mvnw flyway:migrate`

### Database Script File directory
> filesystem:src/main/resources/db/mig

## Project Build command
>`./mvnw spring-boot:run`

OR

>`./mvnw clean package`

>`java -jar target/selise_banking-0.0.1-SNAPSHOT.jar`


### API detail

#### 1. Get Client Account

- **Endpoint**: `http://localhost:8080/selise-banking/api/v1/accounts/{accountNumber}`
- **Method**: `GET`
- **Description**: Retrieve details of a client account by ID.

##### Request

- **URL Parameters**:
  - `accountNumber` (required): The ID of the client account.

##### Example Request
> http://localhost:8080/selise-banking/api/v1/accounts/1234567890

##### Response

- **Status Code**: `200 OK`
- **Response Body**:

```json
{
  "status": "OK",
  "message": "SUCCESS",
  "success": {
    "id": 1,
    "accountNumber": "1234567890",
    "fullName": "John Doe",
    "dateOfBirth": "1980-05-12",
    "accountType": "Savings",
    "accountStatus": "Active",
    "balance": 3600.00,
    "lastTransactionDate": "2024-10-11T22:25:51"
  }
}
```

#### 1. Bank Balance Transfer

- **Endpoint**: `http://localhost:8080/selise-banking/api/v1/balance/transfer`
- **Method**: `POST`
- **Description**: Bank Balance transfer Request

##### Request

- **JSON body**:

##### Example Request
> http://localhost:8080/selise-banking/api/v1/balance/transfer
```Json
{
"sender" : "1234567890",
"receiver" : "9876543210",
"amount" : "200"
}
```
##### Response

- **Status Code**: `200 OK`
- **Response Body**:
  ```Transfer successful```
  

### Following are some API error response
```
{
    "status": "BAD_REQUEST",
    "message": "400 BAD_REQUEST \"Sender must not be null\""
}

{
    "status": "NOT_FOUND",
    "message": "404 NOT_FOUND \"Account Record not found with No [12345678900]\""
}

{
    "status": "BAD_REQUEST",
    "message": "400 BAD_REQUEST \"Amount must not be negative\""
```