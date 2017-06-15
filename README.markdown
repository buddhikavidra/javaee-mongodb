# Java EE with MongoDB
Simple application to show how configure and use MongoDB in Java EE 7 Project.

## Requirements
* [MongoDB version 3](https://www.mongodb.com/download-center)
* [Mongo Java Driver version 3](https://mongodb.github.io/mongo-java-driver/)

## Document and Collection Structure
* Create Document (Database) with name `demo`.
* Create Collection (Table) with name`students`.
* The structure of Collection will be look like this:

```
{
    "_id": {"$oid": "generated id"},
    "name": "name of students",
    "gender": "gender of the students",
    "address": {
        "address1": "...",
        "address2": "...",
        "city": "...",
        "state": "...",
    }
}
```
