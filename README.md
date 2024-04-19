# pet-helper-service

[Точка входа в приложение](https://github.com/vitmvit/pet-helper-api-gateway-service)

Данный микросервис предоставляет функционал для работы мобильной частью приложения PetHelper.

## RecordController (8085/api/v1/records)

Контроллер поддерживает следующие операции:

- поиск записи по id
- поиск записей по логину пользователя
- просмотр всех записей
- создание записи
- обновление записи
- обновление аватара записи по id
- удаление записи

### GET-запросы:

#### RecordDto findById(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/records/5
```

Response:

```json
{
  "id": 5,
  "createDate": "2024-04-15T12:11:42.859605",
  "updateDate": "2024-04-19T14:24:48.566219",
  "userLogin": "user1@mail.com",
  "name": "Кошка",
  "uuidAvatar": "4c472274-1c31-4e83-bfe4-0827d9dd0adb",
  "dataBirthday": "2011-04-19T14:23:00",
  "fullName": "Кошка",
  "sex": "FEMALE",
  "description": "Что-то с персом",
  "hasPedigree": false
}
```

Error:

```json
{
  "errorMessage": "Entity not found!",
  "errorCode": 404
}
```

#### List<RecordDto> findByUserId(@PathVariable("id") String id)

Request:

```http request
http://localhost:8085/api/v1/records/user/user1@mail.com
```

Response:

```json
[
  {
    "id": 27,
    "createDate": "2024-04-19T11:38:11.116022",
    "updateDate": "2024-04-19T14:38:31.144034",
    "userLogin": "user1@mail.com",
    "name": "No name",
    "uuidAvatar": "00000000-0000-0000-0000-000000000000",
    "dataBirthday": null,
    "fullName": null,
    "sex": null,
    "description": null,
    "hasPedigree": false
  },
  {
    "id": 7,
    "createDate": "2024-04-15T12:12:22.630946",
    "updateDate": "2024-04-19T14:06:17.18275",
    "userLogin": "user1@mail.com",
    "name": "Женя",
    "uuidAvatar": "f1996e19-fb0d-4bd7-b86f-90a914be24fe",
    "dataBirthday": "2018-05-02T13:36:00",
    "fullName": "Женя Марьевна",
    "sex": "FEMALE",
    "description": "Черепаха красноухая",
    "hasPedigree": false
  },
  {
    "id": 28,
    "createDate": "2024-04-19T11:41:31.552262",
    "updateDate": "2024-04-19T14:11:20.288582",
    "userLogin": "user1@mail.com",
    "name": "Аквариум",
    "uuidAvatar": "a0a8c41e-fb4e-4245-8b1a-40e9d10563de",
    "dataBirthday": null,
    "fullName": null,
    "sex": null,
    "description": null,
    "hasPedigree": false
  },
  {
    "id": 5,
    "createDate": "2024-04-15T12:11:42.859605",
    "updateDate": "2024-04-19T14:24:48.566219",
    "userLogin": "user1@mail.com",
    "name": "Кошка",
    "uuidAvatar": "4c472274-1c31-4e83-bfe4-0827d9dd0adb",
    "dataBirthday": "2011-04-19T14:23:00",
    "fullName": "Кошка",
    "sex": "FEMALE",
    "description": "Что-то с персом",
    "hasPedigree": false
  }
]
```

#### List<RecordDto> findAll()

Request:

```http request
http://localhost:8085/api/v1/records
```

Response:

```json
[
  {
    "id": 27,
    "createDate": "2024-04-19T11:38:11.116022",
    "updateDate": "2024-04-19T14:38:31.144034",
    "userLogin": "user1@mail.com",
    "name": "No name",
    "uuidAvatar": "00000000-0000-0000-0000-000000000000",
    "dataBirthday": null,
    "fullName": null,
    "sex": null,
    "description": null,
    "hasPedigree": false
  },
  {
    "id": 7,
    "createDate": "2024-04-15T12:12:22.630946",
    "updateDate": "2024-04-19T14:06:17.18275",
    "userLogin": "user1@mail.com",
    "name": "Женя",
    "uuidAvatar": "f1996e19-fb0d-4bd7-b86f-90a914be24fe",
    "dataBirthday": "2018-05-02T13:36:00",
    "fullName": "Женя Марьевна",
    "sex": "FEMALE",
    "description": "Черепаха красноухая",
    "hasPedigree": false
  },
  {
    "id": 28,
    "createDate": "2024-04-19T11:41:31.552262",
    "updateDate": "2024-04-19T14:11:20.288582",
    "userLogin": "user1@mail.com",
    "name": "Аквариум",
    "uuidAvatar": "a0a8c41e-fb4e-4245-8b1a-40e9d10563de",
    "dataBirthday": null,
    "fullName": null,
    "sex": null,
    "description": null,
    "hasPedigree": false
  },
  {
    "id": 5,
    "createDate": "2024-04-15T12:11:42.859605",
    "updateDate": "2024-04-19T14:24:48.566219",
    "userLogin": "user1@mail.com",
    "name": "Кошка",
    "uuidAvatar": "4c472274-1c31-4e83-bfe4-0827d9dd0adb",
    "dataBirthday": "2011-04-19T14:23:00",
    "fullName": "Кошка",
    "sex": "FEMALE",
    "description": "Что-то с персом",
    "hasPedigree": false
  }
]
```

### POST-запросы:

#### RecordDto create(@RequestBody RecordCreateDto recordCreateDto)

Request:

```http request
http://localhost:8085/api/v1/records
```

Body:

```json
{
  "userLogin": "user1@mail.com",
  "name": "pet",
  "uuidAvatar": "00000000-0000-0000-0000-000000000000",
  "dataBirthday": "2024-04-02T22:33:18.722023",
  "fullName": "pet",
  "sex": "MALE",
  "description": "description"
}
```

Response:

```json
{
  "id": 31,
  "createDate": "2024-04-19T17:41:08.972896",
  "updateDate": "2024-04-19T17:41:08.976085",
  "userLogin": "user1@mail.com",
  "name": "pet",
  "uuidAvatar": "00000000-0000-0000-0000-000000000000",
  "dataBirthday": "2024-04-02T22:33:18.722023",
  "fullName": "pet",
  "sex": "MALE",
  "description": "description",
  "hasPedigree": false
}
```

### PUT-запросы:

#### RecordDto updateAvatarUuid(@PathVariable("id") Long id, @PathVariable("uuid") String uuid)

Request:

```http request
http://localhost:8085/api/v1/records/avatar/31/00000000-0000-0000-0000-000000000000
```

Response:

```json
{
  "id": 31,
  "createDate": "2024-04-19T17:41:08.972896",
  "updateDate": "2024-04-19T17:41:08.976085",
  "userLogin": "user1@mail.com",
  "name": "pet",
  "uuidAvatar": "00000000-0000-0000-0000-000000000000",
  "dataBirthday": "2024-04-02T22:33:18.722023",
  "fullName": "pet",
  "sex": "MALE",
  "description": "description",
  "hasPedigree": false
}
```

#### RecordDto update(@RequestBody RecordUpdateDto recordUpdateDto)

Request:

```http request
http://localhost:8081/api/v1/users/support1@mail.com
```

Body:

```json
{
  "id": 31,
  "userLogin": "user1@mail.com",
  "name": "pet",
  "uuidAvatar": "00000000-0000-0000-0000-000000000000",
  "dataBirthday": "2024-04-02T22:33:18.722023",
  "fullName": "pet",
  "sex": "MALE",
  "description": "description",
  "hasPedigree": false
}
```

Response:

```json
{
  "id": 31,
  "createDate": "2024-04-19T17:41:08.972896",
  "updateDate": "2024-04-19T17:41:08.976085",
  "userLogin": "user1@mail.com",
  "name": "pet",
  "uuidAvatar": "00000000-0000-0000-0000-000000000000",
  "dataBirthday": "2024-04-02T22:33:18.722023",
  "fullName": "pet",
  "sex": "MALE",
  "description": "description",
  "hasPedigree": false
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)

## PedigreeController ()

Контроллер поддерживает следующие операции:

