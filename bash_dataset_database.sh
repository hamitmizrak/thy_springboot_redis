#!/bin/bash

echo -e "Spring Boot & Redis & Docker kurulumları başlıyor..."


curl -X 'POST' \
  'http://localhost:4444/api/order/create' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{

  "name": "string",
  "code": "string",
  "systemCreatedDate": "2024-10-18T11:38:45.589Z",
  "customerDto": {
    "createdBy": "string",
    "createdDate": "string",
    "lastModifiedBy": "string",
    "lastModifiedDate": "string",

    "name": "string",
    "surname": "string",
    "systemCreatedDate": "2024-10-18T11:38:45.589Z",
    "addressDto": {
      "createdBy": "string",
      "createdDate": "string",
      "lastModifiedBy": "string",
      "lastModifiedDate": "string",

      "doorNumber": "string",
      "street": "string",
      "avenue": "string",
      "city": "string",
      "zipCode": "string",
      "addressQrCode": "string",
      "state": "string",
      "description": "string",
      "systemCreatedDate": "2024-10-18T11:38:45.589Z"
    },
    "orderDtoList": [
      "string"
    ]
  },
  "productDtoList": [
    {
      "createdBy": "string",
      "createdDate": "string",
      "lastModifiedBy": "string",
      "lastModifiedDate": "string",
      "name": "string",
      "price": "string",
      "systemCreatedDate": "2024-10-18T11:38:45.589Z"
    }
  ]
}'