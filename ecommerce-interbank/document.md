# Category:
## List Categories
GET
localhost:8080/api/category/list

Example output

```Json
[
  {
    "id": "814fc71a-790f-4ce8-8b22-6f93f61c50a6",
    "name": "dulces",
    "description": "dulces regionales de diversos paises",
    "number": "d001",
    "state": true
  }
]
```

## Create a new category
POST
localhost:8080/api/category/create
Example input:
```Json
{
  "name":"bebidas",
  "description":"bebidas regionales de diversos paises",
  "number":"b001",
  "state":true
}
```
Example output:
```Json
{
  "mensaje": "category created successfully"
}
```

## Edit a category
PUT
localhost:8080/api/category/update/{id}

Example input:


```Json
{
  "name": "dulces",
  "description": "dulces regionales de diversos paises-lo que cambie",
  "number": "d001",
  "state": true
}
```
Example output:
```Json
{
  "mensaje": "category updated successfully"
}
```

## Delete a category
localhost:8080/api/category/delete/{id}
Example input:


Example output:
```Json
{
  "mensaje": "category deleted successfully"
}
```

## Get by name
GET


Example input:

localhost:8080/api/category/detail/{categoryName}

Example output:
```Json
{
  "id": "814fc71a-790f-4ce8-8b22-6f93f61c50a6",
  "name": "dulces",
  "description": "dulces regionales de diversos paises",
  "number": "d001",
  "state": true
}
```

# Customers

## Register
POST
localhost:8080/api/authentication/sign-up
Example input:
```Json
{
  "email": "example@example.com",
  "password": "password",
  "name": "Juan",
  "lastName": "Peréz"
}
```
Example output:
```Json
{
  "email": "example@example.com",
  "password": "$2a$10$gBAtBVF8zx5dtPRt0X9bru.bpKKnWeuDgpwUvyIbwPLpydo1yWuq2",
  "verificationCode": "BFhUIMZCvLaRGRKAC9g9LqltlXLqzqopJiSY1G2BevotUt6YsfjKtigeUI2mqzSh",
  "fullName": "Juan Peréz",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlQGV4YW1wbGUuY29tIiwicm9sZXMiOiJVU0VSIiwidXNlcklkIjoiNzg1YjI5NWYtODJmMi00NWUzLWE0NTUtYWU5MTg4OGRlNWZkIiwiZXhwIjoxNjg4NTc1OTgxfQ.Ql8DQ9xQaepdNng39qtnnUKjQ1dOKFuSvthUsHzClmLDtwKPiI0ejLqCXRrIPnuy4orpm93wbeXDB0b-BiHQ0w"
}
```


## Account verification
GET
https://delatinos.up.railway.app/api/authentication/verify/{verificationCode}

Example input:

https://delatinos.up.railway.app/api/authentication/verify/BFhUIMZCvLaRGRKAC9g9LqltlXLqzqopJiSY1G2BevotUt6YsfjKtigeUI2mqzSh

Example output:
```Text
Verification Succeeded
```

## Log in
POST
localhost:8080/api/authentication/sign-in
Example input:
```Json
{
  "email": "example@example.com",
  "password": "password"
}
```
Example Output
```Json
{
  "email": "example@example.com",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJleGFtcGxlQGV4YW1wbGUuY29tIiwicm9sZXMiOiJST0xFX1VTRVIiLCJ1c2VySWQiOiI3ODViMjk1Zi04MmYyLTQ1ZTMtYTQ1NS1hZTkxODg4ZGU1ZmQiLCJleHAiOjE2ODg1NzYzMTd9.yG6xBV4mjCM6hqAB0iVTAkONZJZGf2pf9bZlI1jpZq0L3NOvWvdxLMfphckaLBlUOFIWFKWFFG_dxtIJnffjrw",
  "active": true
}
```

## Update account
PUT
localhost:8080/api/authentication/update/{id}

Example input:

```Json
{
  "name": "Fulano",
  "lastName": "Peréz",
  "country":"Serbia",
  "address":"Calle siempre viva"
}
```
Example output:
```Json
{
  "name": "Fulano",
  "lastName": "Peréz",
  "country": "Serbia",
  "address": "Calle siempre viva"
}

```



# Products

## Save Product
POST
localhost:8080/api/products/add

Example input:
```Json
{
  "name": "product4",
  "description": "description4",
  "stock": 150,
  "image": "imagen4.jpg",
  "price": 2.5,
  "weight": 10.0,
  "country": "country2",
  "minStock": 20,
  "category": "bebidas"
}
```

Example output:
```Json
{
  "id": "b8ab31b0-5bd5-45b1-8abd-b20c9bec3969",
  "name": "product4",
  "description": "description4",
  "stock": 150,
  "image": "imagen4.jpg",
  "price": 2.5,
  "weight": 10.0,
  "country": "country2",
  "minStock": 20,
  "state": true,
  "category": {
    "id": "814fc71a-790f-4ce8-8b22-6f93f61c50a6",
    "name": "bebidas",
    "description": "bebidas regionales de diversos paises",
    "number": "b001",
    "state": true
  }
}
```

## Update Product
PUT
localhost:8080/api/products/update/{id}

Example input:


```Json
{
  "name": "azucar",
  "description": "description",
  "stock": 100,
  "image": "imagen.jpg",
  "price": 2.5,
  "weight": 10.0,
  "country": "country",
  "minStock": 10,
  "category": "bebidas"
}

```

## Delete product
PATCH

Example input:


Example output:
```Text
The product state changed
```





Example output

```Json
[
  {
    "id": "b8ab31b0-5bd5-45b1-8abd-b20c9bec3969",
    "name": "azucar",
    "description": "description",
    "stock": 100,
    "image": "imagen.jpg",
    "price": 2.5,
    "weight": 10.0,
    "country": "country",
    "minStock": 10,
    "state": true,
    "category": {
      "id": "814fc71a-790f-4ce8-8b22-6f93f61c50a6",
      "name": "bebidas",
      "description": "bebidas regionales de diversos paises",
      "number": "b001",
      "state": true
    }
  }
]
 




```

# Orders
## Create Order

POST

Example input


```JSON
{
  "customers": {
    "accountUuid":"45f60e43-619d-4031-a3c7-aacca8ed26e0"
  },
  "shippingCost": 15,
  "orderDetailsList": [
    {
      "product": {
        "id": "f0bcc4f4-d8f7-44c4-92d9-4c066dfe742e"
      },
      "quantity": 2
    },
    {
      "product": {
        "id": "99968e9d-725c-4951-bf00-8275f0a8d266"
      },
      "quantity": 4
    }
  ],
  "shippingDetails": {
    "name": "Ana",
    "lastName": "Reyes",
    "company": "",
    "address1": "Calle Mercedes No. 24",
    "address2": "Zona Colonial",
    "postalCode": "22243",
    "provincia": "Santo Domingo",
    "city": "Distrito Nacional",
    "country": "Republica Dominicana",
    "gift": false
  }
}
```

Example output
```JSON
{
  "number": "2023-2",
  "fullName": "Felipe Castro",
  "shippingCost": null,
  "amountTaxes": 2.85,
  "amountTotal": 15.0,
  "total": 17.85,
  "createdDate": "2023-07-11T15:05:24.496416873",
  "transactionState": "ON_HOLD",
  "orderDetailsRegistrationList": [
    {
      "productName": "azucar",
      "quantity": 2,
      "price": 2.5,
      "totalAmount": 5.0,
      "taxesAmount": 0.95,
      "total": 5.95
    },
    {
      "productName": "product2",
      "quantity": 4,
      "price": 2.5,
      "totalAmount": 10.0,
      "taxesAmount": 1.9,
      "total": 11.9
    }
  ],
  "shippingDetailsRegistration": {
    "fullName": "Ana Reyes",
    "company": "",
    "address": "Calle Mercedes No. 24",
    "address2": "Zona Colonial",
    "postalCode": "22243",
    "provincia": "Santo Domingo",
    "city": "Distrito Nacional",
    "country": "Republica Dominicana"
  }
}

```
# Shipping Details

## List shipping detail
GET
http://delatinos.up.railway.app/api/shipping-details/customer/%7Bcustomer_uuid%7D

Example input
http://delatinos.up.railway.app/api/shipping-details/customer/%7Bcustomer_uuid%7D
```JSON
{
  "shippingDetailsName":"Direccion ",
  "name":"Alfonso",
  "lastName":"Almonte",
  "address1":"Carretera  Mella 7",
  "address2":"Lucerna",
  "postalCode":"11516",
  "provincia":"Santo Domingo",
  "city": "Santo Domingo Este",
  "country": "República Dominicanna",
  "active": true,
  "primaryAddress":true
}

```
Example output
```JSON
[
  {
    "shippingDetailsCustomerName": "Direccion 1"
  },
  {
    "shippingDetailsCustomerName": "Direccion 3"
  },
  {
    "shippingDetailsCustomerName": "Direccion 2"
  }
]
```

## Add shipping detail
POST
https://delatinos.up.railway.app/api/shipping-details/65d5e792-b16f-498b-bf1a-41e4442d91c0
Example input
https://delatinos.up.railway.app/api/shipping-details/65d5e792-b16f-498b-bf1a-41e4442d91c0

```JSON
{
  "shippingDetailsName":"Direccion 3",
  "name":"Alfonso",
  "lastName":"Almonte",
  "address1":"Carretera Mella",
  "address2":"Lucerna",
  "postalCode":"11516",
  "provincia":"Santo Domingo",
  "city": "Santo Domingo Este",
  "country": "República Dominicanna",
  "active": true,
  "primaryAddress":true
}

```
Example output
```JSON
{
  "name": "Alfonso",
  "lastName": "Almonte",
  "shippingDetailsName": "Direccion 3",
  "address1": "Carretera Mella",
  "address2": "Lucerna",
  "postalCode": "11516",
  "provincia": "Santo Domingo",
  "city": "Santo Domingo Este",
  "country": "República Dominicanna",
  "active": true,
  "primaryAddress": true,
  "gift": false
}
```

## Change shipping detail primary address
POST
https://delatinos.up.railway.app/api/shipping-details/change-primary/{accountUuid}/{shippingDetailUuid}

Example input
https://delatinos.up.railway.app/api/shipping-details/change-primary/ad5e8b88-315b-4864-bf41-e473c0c7cb54/f5a282f6-8ceb-4123-8a77-793857f4e2d5

Example output
```Text
    Address changed to primary successfully.
```

# PAY
## Create payment
POST
Example input
https://delatinos.up.railway.app/api/pay

```JSON
{
    "cardNumber": "4242424242424242",
    "expirationDate": "0823",
    "cardCode": "900",
    "orders": {
        "transactionUuid": ""
    },
    "transaction_type": "PAID"
}

```
Example output
```JSON