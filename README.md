<<<<<<< HEAD
# Lab05prin
=======
# Coffee Shop REST API (Spring Boot)

A minimal Spring Boot project that demonstrates:

- HTTP methods: GET / POST / PUT / DELETE
- Layered design: Controller / Service / Model
- JSON request/response over REST

No database — data is kept in memory in a `List` inside `CoffeeService`.

Run locally:

```powershell
cd d:\labprin
mvn spring-boot:run
```

Default server: `http://localhost:8080`

Endpoints:

- GET `/coffees` — list all
- GET `/coffees/{id}` — get one (404 if not found)
- GET `/coffees/search?name=...` — search by name (case-insensitive, contains)
- POST `/coffees` — create (JSON body without `id`)
- PUT `/coffees/{id}` — update (JSON body)
- DELETE `/coffees/{id}` — delete

Sample curl usage and expected results (matches exercise examples):

```bash
# Examples (curl) for every endpoint

1) GET all

```bash
curl -i http://localhost:8080/coffees
```

Expected body (initial state):

```json
[
	{"id":1,"name":"Espresso","price":45.0},
	{"id":2,"name":"Latte","price":55.0}
]
```

2) GET by id

```bash
curl -i http://localhost:8080/coffees/1
```

200 OK example body:

```json
{"id":1,"name":"Espresso","price":45.0}
```

If id not found:

```bash
curl -i http://localhost:8080/coffees/999
```

Response status: `404 Not Found` (no body)

3) Search by name

```bash
curl -i "http://localhost:8080/coffees/search?name=Esp"
```

Returns JSON array of matches (case-insensitive, contains):

```json
[ {"id":1,"name":"Espresso","price":45.0} ]
```

4) POST create

```bash
curl -i -X POST http://localhost:8080/coffees \
	-H "Content-Type: application/json" \
	-d '{"name":"Cappuccino","price":60.0}'
```

Expected response: `201 Created` with body of created object and `Location: /coffees/{id}` header

5) PUT update

```bash
curl -i -X PUT http://localhost:8080/coffees/2 \
	-H "Content-Type: application/json" \
	-d '{"name":"Latte","price":50.0}'
```

Expected `200 OK` and body of updated object. If id not found -> `404 Not Found`.

6) DELETE

```bash
curl -i -X DELETE http://localhost:8080/coffees/3
```

Expected `200 OK` when deleted; if id not found -> `404 Not Found`.
```

Files created:

- [pom.xml](pom.xml)
- [src/main/java/com/example/coffeeshop/CoffeeShopApplication.java](src/main/java/com/example/coffeeshop/CoffeeShopApplication.java)
- [src/main/java/com/example/coffeeshop/model/Coffee.java](src/main/java/com/example/coffeeshop/model/Coffee.java)
- [src/main/java/com/example/coffeeshop/service/CoffeeService.java](src/main/java/com/example/coffeeshop/service/CoffeeService.java)
- [src/main/java/com/example/coffeeshop/controller/CoffeeController.java](src/main/java/com/example/coffeeshop/controller/CoffeeController.java)

Notes:

- This follows the layered design: the controller handles HTTP, the service holds logic + in-memory data, and the model represents the data structure.
- IDs are managed in memory with an `AtomicLong`.
>>>>>>> fab650f (lab05 finish and Discussion)
