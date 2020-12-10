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

- `POST /application/usuarios/login`

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

- `POST /application/usuarios `

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

- `GET /application/usuarios/<email>`

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

- `PUT /application/usuarios/<email>/forgot`

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

- `PUT /application/usuarios/<email>`

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

- `DELETE /application/usuarios/<email>`

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

- `POST /application/usuarios/<email>/mascotas`

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

- `GET /application/usuarios/<email>/mascotas/<nombre>`

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

- `GET /application/usuarios/<email>/mascotas`

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

- `PUT /application/usuarios/<email>/mascotas/<nombre>`

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

- `DELETE /application/usuarios/<email>/mascotas/<nombre>`

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

- `POST /application/quedadas`

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

- `GET /application/quedadas/<id>`

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

- `GET /application/quedadas?admin=<emailAdmin>` ✔
- `GET /application/quedadas?participante=<emailPart>` ✔
- `GET /application/quedadas?ubicacion=<ubicacion>` ❌
- `GET /application/quedadas?order=time` ✔
- `GET /application/quedadas`

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

- `PUT /application/quedadas/<id>`

##### Delete ✔

- `DELETE /application/quedadas/<id>`

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

- `POST /application/calendario/<email>/eventos`

**Request body:**
```json
{
    "titulo": "cortar pelo a jumanji",
    "usuario": "joan@gmail.com",
    "fecha": "2020-06-01",
    "fechaFin": "2020-06-02",
    "descripcion": "cortar pelo",
    "notificaciones": 1
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
##### Read (all) ✔

- `GET /application/calendario/<email>/mascotas`

**Response body:**
Successful:
```json
[
   {
       "id": 6,
       "titulo": "cortar pelo a jumanji",
       "usuario": "joan@gmail.com",
       "fecha": "2020-06-01T00:00:00.000+0000",
       "fechaFin": "2020-06-02T00:00:00.000+0000",
       "descripcion": "cortar pelo",
       "notificaciones": 1
   }
]
HTTP 200
```
User not found:
```
HTTP 404
```

##### Delete ✔

- `DELETE /application/calendario/<email>/eventos/<id>`

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

##### UPDATE ✔

- `PUT /application/calendario/<email>/eventos/<id>`

**Request body:**
```json
{
    "titulo": "cortar pelo a jumanji",
    "fecha": "2020-06-01",
    "fechaFin": "2020-06-02",
    "descripcion": "cortar pelo",
    "notificaciones": 1
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

#### Consejos

- `POST /application/consejos`

**Request body:**
```json
{
  "consejo": "Recuerda asear y peinar a tu perro con frecuencia",
}
```
**Response body:**
Successful:
```json
[
   {
       "id": 6,
       "consejo": "Recuerda asear y peinar a tu perro con frecuencia"
   }
]

HTTP 201
```
Sin permiso:
```
HTTP 403
```

- `GET /application/consejos`

**Response body:**
Successful:
```json
[
   {
       "id": 6,
       "consejo": "Recuerda asear y peinar a tu perro con frecuencia"
   }
]
HTTP 200
```
Consejo not found:
```
HTTP 404
```

- `GET /application/consejos/one`

**Response body:**
Successful:
```json
[
   {
       "id": 6,
       "consejo": "Recuerda asear y peinar a tu perro con frecuencia"
   }
]
HTTP 200
```

- `DELETE /application/consejos/<id>`

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
#### avatares

- `POST /application/avatares`

**Request body:**
```json
{
    "niveldesbloqueo": "1",
    "avatar": "https://i.imgur.com/2mWlaET.png"
}
```
**Response body:**
Successful: HTTP 201
```json
[
   {
           "id": 1,
           "niveldesbloqueo": 3,
           "avatar": "https://i.imgur.com/8WOOHSW.png"
    }
]
```
Sin permiso:
```
HTTP 403
```

- `GET /application/avatares`

**Response body:**
Successful: HTTP 200
```json
[
   {
           "id": 1,
           "niveldesbloqueo": 3,
           "avatar": "https://i.imgur.com/8WOOHSW.png"
    }
]
```
- `PUT /application/avatares/{email}/avatar`

**Request body:**
```text
https://i.imgur.com/2mWlaET.png
```
Successful
```
HTTP 200
```
