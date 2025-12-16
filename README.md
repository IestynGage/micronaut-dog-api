# Overview

I used Micronaut framework for this project.
I'm not familiar with Micronaut framework,
so it was a bit of a learning curve.
I'm still not experience with it to make a comparison with Spring boot.
In total, I would say I spend around 3/4 hours on this.
I filled all the requirements apart from using mapstruct and filtering.

I went with Micronaut over as I thought it would be more of an interesting challenge.
I've used chatGPT to speed up learning the basics,
I asked questions such as:
* "Create a basic Micronaut framework project that interacts with a H2 database and migrates the database automatically"
* "Micronaut repository how do I add a where to a findAll method"

If I had more time I would:

* Rework the DB design. 
  The DB is all in one table.
  This should be re-designed,
  I've given the schema design below.

* Use Swagger to generate the controllers.
  This would "remove" the boilerplate of the annotations,
  and add better checks for the arguments given in the PUT/POST method

* Add tests.

* Add the Kenneling characteristics as a string frield of dog
```
Dog(
    internal_id: BIGINT, 
    external_id: UUID,
    name: string, 
    breed: MANY-TO-ONE with breed, 
    supplier: MANY-TO-ONE with supplier table,
    badge: ONE-TO-ONE with badge table
    gender: enum
    birth_date: Date
    date_acquired: Date,
    current_status: enum
    Leaving: ONE-TO-ONE with leaving table,
    kennel-characteristics: String
)

Leaving(
    internal_id: BIGINT
    date: Date
    Reason: status
)

Supplier(
    internal_id: BIGINT
    name: string
    // other fields e.g. phone number, address, still open etc
)

Badge(
    internal_id: bigint
    badge_id: string
)

breed(
    internal_id: bigint
    name: string
)
```

## Example HTTP requests

```
POST http://localhost:8080/api/dogs/dogs
{
    "name": "bob",
    "breed": "dog",
    "supplier": "me",
    "gender": "MALE",
    "birthDate": "2024-10-10"
}
```

```
PUT http://localhost:8080/api/dogs/dogs
{
    "id": "1",
    "name": "bob",
    "breed": "dog",
    "supplier": "me",
    "gender": "MALE",
    "birthDate": "2024-10-10"
}
```

```
GET http://localhost:8080/api/dogs/dogs/1
```

```
GET http://localhost:8080/api/dogs/dogs?page=0&size=1
```

```
DELETE http://localhost:8080/api/dogs/dogs/1
```
