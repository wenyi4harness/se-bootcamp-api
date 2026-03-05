# Office Supply API

A simple Spring Boot REST API for an office supply store with 15 products.

## Requirements

- Java 17+
- Maven

## Getting Started

```bash
mvn spring-boot:run
```

Server runs on `http://localhost:8080`.

## Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get product by ID |
| GET | `/api/products/category/{category}` | Filter by category |
| POST | `/api/products` | Add a product |
| PUT | `/api/products/{id}` | Update a product |
| DELETE | `/api/products/{id}` | Delete a product |

## Product Fields

| Field | Type | Description |
|-------|------|-------------|
| id | int | Unique identifier |
| name | String | Product name |
| category | String | Product category |
| price | double | Price in USD |
| stock | int | Units available |

## Categories

`Writing` `Paper` `Fastening` `Cutting` `Adhesives` `Organization`

## Example Requests

```bash
# Get all products
curl http://localhost:8080/api/products

# Get product by ID
curl http://localhost:8080/api/products/1

# Get by category
curl http://localhost:8080/api/products/category/Writing

# Add a product
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"id":16,"name":"Ruler","category":"Tools","price":2.99,"stock":100}'

# Delete a product
curl -X DELETE http://localhost:8080/api/products/1
```
