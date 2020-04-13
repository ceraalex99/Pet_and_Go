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

**Response body:**
```
Usuario creado con exito
```

##### Read ✔

- `GET /api/usuarios/<email>`

**Response body:**
```json
{
  "nombre": "Antonio Ruiz",
  "username": "antonioruiz83",
  "email": "antonio@ruiz.com"
}
```

##### Update ❌

- `PUT /api/usuarios/<email>`

**Request body:**
```json
{
  "nombre": "Antonio Ruiz",
  "username": "antonioruiz83",
  "email": "antonio@ruiz.com"
}
```

**Response body:**
```
Usuario modificado con exito
```

##### Delete ✔

- `DELETE /api/usuarios/<email>`

**Response body:**
```
true
```

#### Mascotas

##### Create

- `POST /api/usuarios/<email>/mascotas`

**Request body:**
```json
{"id": {
	"nombre":"Pepito",
	"amo":"antoniogp68@gmail.com"
	},
"fechaNacimiento":"2020-01-30"}
```

**Response body:**
```

```
