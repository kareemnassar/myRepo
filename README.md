
# Project Title

Using drones functions to deliver small items that are (urgently) 
needed in locations with difficult access.


## API Reference

#### Get all drones

```http
  GET http://localhost:8080/api/v1/drone/findAll
```
#### Get all available drones

```http
  GET http://localhost:8080/api/v1/drone/findAvailableDrones
```

#### Add drone

```http
  POST http://localhost:8080/api/v1/drone/add
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `drone`      | `json object` | **Required**.  |


#### Load drone with medications

```http
  POST http://localhost:8080/api/v1/drone/load
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `drone`      | `json object` | **Required**.  |

### edit drone 

```http
  PUT http://localhost:8080/api/v1/drone/edit
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `drone`      | `json object` | **Required**.  |

### get loaded medications for  drone 

```http
  GET http://localhost:8080/api/v1/drone/loadedMedications/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**.  |


### check battery level for drone 

```http
  GET http://localhost:8080/api/v1/drone/battery/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**.  |

### add medication 

```http
  POST http://localhost:8080/api/v1/medication/add
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `medication`      | `json object` | **Required**.  |


### upload image for  medication 

```http
  POST http://localhost:8080/api/v1/medication/uploadimage
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**.  |
| `image`      | `image` | **Required**.  |



## Payload examples
```
  POST http://localhost:8080/api/v1/drone/add
    Accept: application/json
    Content-Type: application/json
```
```
{
"serialNumber":"abcd",
"model":"CruiserweighT",
"weightLimit":500,
"batteryCapacity":50
}
```
```
  POST http://localhost:8080/api/v1/drone/edit
    Accept: application/json
    Content-Type: application/json
```
```
{
"id",1,
"serialNumber":"abcd",
"model":"CruiserweighT",
"weightLimit":500,
"batteryCapacity":50
}
```
```
  POST http://localhost:8080/api/v1/drone/load
    Accept: application/json
    Content-Type: application/json
```
```
{
"id":1,
"medications":[
    {
"name":"asdsaf-",
"code":"DAADA56547_-",
"weight":500
}
]
}
```
```
To view your H2 in-memory datbase
The 'mem' profile runs on H2 in-memory database.
 To view and query the database you can browse to
  http://localhost:8080/h2. Default username is 'sa' with a blank password. 
  ```
   
## Run Locally

There are several ways to run a Spring Boot application on your local machine.
 One way is to execute the main method in the 
 org.springframework.boot.SpringApplication class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:
mvn spring-boot:run



