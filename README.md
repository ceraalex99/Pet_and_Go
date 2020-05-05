#  Pet_and_Go

[![Build status](https://travis-ci.com/ceraalex99/Pet_and_Go.svg?branch=staging)](https://travis-ci.com/ceraalex99/Pet_and_Go)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=ceraalex99_Pet_and_Go&metric=alert_status&branch=staging)](https://sonarcloud.io/dashboard?branch=staging&id=ceraalex99_Pet_and_Go)
[![Code smells](https://sonarcloud.io/api/project_badges/measure?project=ceraalex99_Pet_and_Go&metric=code_smells&branch=staging)](https://sonarcloud.io/project/issues?branch=staging&id=ceraalex99_Pet_and_Go&resolved=false&types=CODE_SMELL)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ceraalex99_Pet_and_Go&metric=coverage&branch=staging)](https://sonarcloud.io/component_measures?branch=staging&id=ceraalex99_Pet_and_Go&metric=coverage)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=ceraalex99_Pet_and_Go&metric=bugs&branch=staging)](https://sonarcloud.io/project/issues?branch=staging&id=ceraalex99_Pet_and_Go&resolved=false&types=BUG)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=ceraalex99_Pet_and_Go&metric=vulnerabilities&branch=staging)](https://sonarcloud.io/project/issues?branch=staging&id=ceraalex99_Pet_and_Go&resolved=false&types=VULNERABILITY)
[![Duplications](https://sonarcloud.io/api/project_badges/measure?project=ceraalex99_Pet_and_Go&metric=duplicated_lines_density&branch=staging)](https://sonarcloud.io/component_measures?branch=staging&id=ceraalex99_Pet_and_Go&metric=duplicated_lines_density&view=list)

## API REFERENCE

### Resources

#### Usuarios

##### Login ✔

- `POST /api/usuarios/login`

**Request body:**
```json
{
"email": "antoniogp68@gmail.com",
"password": "123456abc"
}
```

**Response body and status:**

Successful:
```json
{
  "nombre": "Antonio Garcia Perez",
  "username": "antonio68",
  "email": "antoniogp68@gmail.com"
}
HTTP 200
```
Bad login:
```
HTTP 400
```

##### Create ✔

- `POST /api/usuarios `

**Request body:**
```json
{
  "nombre": "Antonio Ruiz",
  "username": "antonioruiz83",
  "email": "antonio@ruiz.com",
  "password": "Passw0rd!"
}
```

**Response body and status:**

Successful:
```
HTTP 201
```
Username repetido:
```
username
HTTP 400
```
Email repetido:
```
email
HTTP 400
```


##### Read ✔

- `GET /api/usuarios/<email>`

**Response body and status:**

Successful:
```json
{
  "nombre": "Antonio Ruiz",
  "username": "antonioruiz83",
  "email": "antonio@ruiz.com"
}
HTTP 200
``` 
User not found:
```
HTTP 404
```

##### Update Password ✔ 

- `PUT /api/usuarios/<email>/forgot`

**Response body and status:**

Successful:
```json
{
  "newPassword": "P4ssw0rd?",
  "oldPassword": "Passw0rd!"
}
HTTP 200
```
Incorrect Old Password:
```
HTTP 400
```
Usuario not found:
```
HTTP 404
```
No autorizado:
```
HTTP 403
```

##### Update Campos ✔ 

- `PUT /api/usuarios/<email>`

**Response body and status:**

Successful:
```json
{
  "nombre": "Antonio Ruiz",
  "username": "antonioruiz83"
}
HTTP 200
```
Usuario not found:
```
HTTP 404
```
No autorizado:
```
HTTP 403
```

##### Delete ✔

- `DELETE /api/usuarios/<email>`

**Response body and status:**

Successful:
```
HTTP 200
```
User not found:
```
HTTP 404
```
Error BD:
```
HTTP 500
```
No autorizado:
```
HTTP 403
```

#### Mascotas

##### Create ✔

- `POST /api/usuarios/<email>/mascotas`

**Request body:**
```json
{"id": {
	"nombre":"Pepito",
	"amo":"antoniogp68@gmail.com"
	},
"fechaNacimiento":"2020-01-30"}
```

**Response body and status:**
```
HTTP 201
```
Sin permiso:
```
HTTP 403
```

##### Read ✔

- `GET /api/usuarios/<email>/mascotas/<nombre>`

**Response body:**
Successful:
```json
{"id": {
	"nombre":"Pepito",
	"amo":"antoniogp68@gmail.com"
	},
"fechaNacimiento":"2020-01-30"}
HTTP 200
```

Mascota not found:
```
HTTP 404
```

##### Read (all) ✔

- `GET /api/usuarios/<email>/mascotas`

**Response body:**
Successful:
```json
[{"id": {
	"nombre":"Pepito",
	"amo":"antoniogp68@gmail.com"
	},
"fechaNacimiento":"2020-01-30"},
  {"id": {
    "nombre":"Manolito",
    "amo":"antoniogp68@gmail.com"
    },
"fechaNacimiento":"2020-01-02"}]
HTTP 200
```
User not found:
```
HTTP 404
```


##### Update ✔

- `PUT /api/usuarios/<email>/mascotas/<nombre>`

**Request body:**
```json
{"id": {
	"nombre":"Pepito",
	"amo":"antoniogp68@gmail.com"
	},
"fechaNacimiento":"2020-01-30"}
```

**Response body:**
Successful:
```
HTTP 200
```
Mascota not found:
```
HTTP 404
```
No coinciden URL y body:
```
HTTP 400
```
Sin permiso:
```
HTTP 403
```

##### Delete ✔

- `DELETE /api/usuarios/<email>/mascotas/<nombre>`

**Response body and status:**

Successful:
```
HTTP 200
```
Mascota not found:
```
HTTP 404
```
Error BD:
```
HTTP 500
```
Sin permiso:
```
HTTP 403
```

#### Quedadas

##### Create ✔

- `POST /api/quedadas`

**Request body:**
```json
{
"admin": "antoniogp68@gmail.com",
"createdAt": "2020-04-20 11:23:33",
"fechaQuedada": "2020-05-12 19:20:00",
"lugarInicio": "F4VX+22 Cerdanyola",
"lugarFin": "F4RR+C7 Cerdanyola"
}
```

**Response body and status:**
```
HTTP 201
```

Sin permiso:
```
HTTP 403
```

##### Read ✔

- `GET /api/quedadas/<id>`

**Response body:**
Successful:
```json
{
"id": 6,
"admin": "antoniogp68@gmail.com",
"createdAt": "2020-04-20 11:23:33",
"fechaQuedada": "2020-05-12 19:20:00",
"lugarInicio": "F4VX+22 Cerdanyola",
"lugarFin": "F4RR+C7 Cerdanyola"
}
HTTP 200
```

Quedada not found:
```
HTTP 404
```

##### Read (all) ✔

- `GET /api/quedadas?admin=<emailAdmin>` ✔
- `GET /api/quedadas?participante=<emailPart>` ✔
- `GET /api/quedadas?ubicacion=<ubicacion>` ❌
- `GET /api/quedadas?order=time` ✔
- `GET /api/quedadas`

**Response body:**
Successful:
```json
[{"id": {
	"nombre":"Pepito",
	"amo":"antoniogp68@gmail.com"
	},
"fechaNacimiento":"2020-01-30"},
  {"id": {
    "nombre":"Manolito",
    "amo":"antoniogp68@gmail.com"
    },
"fechaNacimiento":"2020-01-02"}]
HTTP 200
```
No hay quedadas:
```
HTTP 204
```

##### Update ❌

- `PUT /api/quedadas/<id>`

##### Delete ✔

- `DELETE /api/quedadas/<id>`

**Response body and status:**

Successful:
```
HTTP 200
```
Quedada not found:
```
HTTP 404
```
Error BD:
```
HTTP 500
```
Sin permiso:
```
HTTP 403
```


#### Eventos

##### Create ✔

- `POST /api/usuarios/<email>/eventos`

**Request body:**
```json
{"id": {
	"titulo":"cortar pelo a jumanji",
	"usuario":"joan@gmail.com",
	"fecha": "2020-06-01"
	},
"descripcion":"cortar pelo"}
```

**Response body and status:**
```
HTTP 201
```
Sin permiso:
```
HTTP 403
```
##### Read (all) ✔

- `GET /api/usuarios/<email>/mascotas`

**Response body:**
Successful:
```json
[
    {
        "id": {
            "titulo": "cortar pelo a jumanji",
            "user": "joan@gmail.com",
            "fecha": "2020-06-01T00:00:00.000+0000"
        },
        "descripcion": "cortar pelo"
    }
]
HTTP 200
```
User not found:
```
HTTP 404
```

##### Delete ✔

- `DELETE /api/usuarios/<email>/eventos`

**Request body:**
```json
{
    "titulo": "cortar pelo a jumanji",
    "usuario": "joan@gmail.com",
    "fecha": "2020-06-01"
}
```

**Response body and status:**

Successful:
```
HTTP 200
```
Mascota not found:
```
HTTP 404
```
Error BD:
```
HTTP 500
```
Sin permiso:
```
HTTP 403
```