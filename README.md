# pet-helper-service

[Точка входа в приложение](https://github.com/vitmvit/pet-helper-api-gateway-service)

Данный микросервис предоставляет функционал для работы мобильной частью приложения PetHelper.

## Swagger

http://localhost:8085/api/doc/swagger-ui/index.html#/

## StateController (8081/api/v1/states)

Контроллер поддерживает следующие операции:

- поиск записи состояния по id
- поиск записей по id дневника
- просмотр всех записей состояния
- создание записи
- обновление записи
- удаление записи по id

### GET-запросы:

#### StateDto findById(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/states/29
```

Response:

```json
{
  "id": 29,
  "dictionaryId": 21,
  "value": 65,
  "description": "desc",
  "dateCreated": "2024-05-06T10:12:59.803"
}
```

Error:

```json
{
  "errorMessage": "Entity not found!",
  "errorCode": 404
}
```

#### List<StateDto> findByDictionaryId(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/states/dict/21
```

Response:

```json
[
  {
    "id": 30,
    "dictionaryId": 21,
    "value": 65,
    "description": "66",
    "dateCreated": "2024-05-06T10:12:59.803"
  },
  {
    "id": 31,
    "dictionaryId": null,
    "value": 76,
    "description": "",
    "dateCreated": "2024-05-06T10:13:07.823"
  },
  {
    "id": 32,
    "dictionaryId": null,
    "value": 45,
    "description": "",
    "dateCreated": "2024-05-06T10:13:16.06"
  }
]
```

#### List<StateDto> findAll()

Request:

```http request
http://localhost:8085/api/v1/states
```

Response:

```json
[
  {
    "id": 30,
    "dictionaryId": 21,
    "value": 65,
    "description": "66",
    "dateCreated": "2024-05-06T10:12:59.803"
  },
  {
    "id": 31,
    "dictionaryId": null,
    "value": 76,
    "description": "",
    "dateCreated": "2024-05-06T10:13:07.823"
  },
  {
    "id": 32,
    "dictionaryId": null,
    "value": 45,
    "description": "",
    "dateCreated": "2024-05-06T10:13:16.06"
  }
]
```

### POST-запросы:

#### StateDto create(@RequestBody StateCreateDto stateCreateDto)

Request:

```http request
http://localhost:8085/api/v1/states
```

Body:

```json
{
  "dictionaryId": 21,
  "value": 34,
  "description": "string",
  "dateCreated": "2024-05-06T10:16:38.441Z"
}
```

Response:

```json
{
  "id": 33,
  "dictionaryId": 21,
  "value": 34,
  "description": "string",
  "dateCreated": "2024-05-06T10:16:38.441"
}
```

### PUT-запросы:

#### StateDtoupdate(@RequestBody StateUpdateDto stateUpdateDto)

Request:

```http request
http://localhost:8085/api/v1/states
```

Body:

```json
{
  "dictionaryId": 21,
  "value": 33,
  "description": "string",
  "dateCreated": "2024-05-06T10:18:09.110Z",
  "id": 31
}
```

Response:

```json
{
  "id": 31,
  "dictionaryId": null,
  "value": 33,
  "description": "string",
  "dateCreated": "2024-05-06T10:18:09.11"
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)

## StateTemplateController (8081/api/v1/stateTemplates)

Контроллер поддерживает следующие операции:

- поиск записи состояния по id
- просмотр всех записей состояния
- создание записи
- обновление записи
- удаление записи по id

### GET-запросы:

#### StateTemplateDto findById(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/stateTemplates/1
```

Response:

```json
{
  "id": 1,
  "name": "Температура",
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget arcu nec mauris blandit convallis.",
  "uuidImage": "26b999fe-a099-4185-a203-840d3dc26eed"
}
```

Error:

```json
{
  "errorMessage": "Entity not found!",
  "errorCode": 404
}
```

#### List<StateTemplateDto> findAll()

Request:

```http request
http://localhost:8085/api/v1/stateTemplates
```

Response:

```json
[
  {
    "id": 1,
    "name": "Температура",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget arcu nec mauris blandit convallis.",
    "uuidImage": "26b999fe-a099-4185-a203-840d3dc26eed"
  },
  {
    "id": 2,
    "name": "Вес",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget arcu nec mauris blandit convallis.",
    "uuidImage": "ae89b38d-2953-4a57-9878-68361043e151"
  },
  {
    "id": 3,
    "name": "Рост",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget arcu nec mauris blandit convallis.",
    "uuidImage": "ec164f6c-2882-4e71-b9bf-95075e267c6a"
  }
]
```

### POST-запросы:

#### StateTemplateDto create(@RequestBody StateTemplateCreateDto stateCreateDto)

Request:

```http request
http://localhost:8085/api/v1/stateTemplates
```

Body:

```json
{
  "name": "Температура2",
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget arcu nec mauris blandit convallis.",
  "uuidImage": "26b999fe-a099-4185-a203-840d3dc26eed"
}
```

Response:

```json
{
  "id": 4,
  "name": "Температура2",
  "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget arcu nec mauris blandit convallis.",
  "uuidImage": "26b999fe-a099-4185-a203-840d3dc26eed"
}
```

### PUT-запросы:

#### StateDtoupdate(@RequestBody StateUpdateDto stateUpdateDto)

Request:

```http request
http://localhost:8085/api/v1/stateTemplates
```

Body:

```json
{
  "name": "string",
  "description": "string",
  "uuidImage": "string",
  "id": 4
}
```

Response:

```json
{
  "id": 4,
  "name": "string",
  "description": "string",
  "uuidImage": "string"
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)

## StateDictionaryController (8081/api/v1/stateTemplates)

Контроллер поддерживает следующие операции:

- поиск дневника по id
- поиск дневников по id записи
- просмотр всех дневников
- создание дневника
- обновление дневника
- удаление дневника по id

### GET-запросы:

#### StateDictionaryDto findById(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/dictionaries/3
```

Response:

```json
{
  "id": 3,
  "recordId": 10,
  "name": "state_dict_3",
  "uuid": "26b999fe-a099-4185-a203-840d3dc26eed",
  "description": null,
  "dateCreated": "2024-04-30T17:59:41.13576",
  "active": true,
  "constant": false
}
```

Error:

```json
{
  "errorMessage": "Entity not found!",
  "errorCode": 404
}
```

#### List<StateDictionaryDto> findAllByRecordId(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/dictionaries/recordId/10
```

Response:

```json
[
  {
    "id": 3,
    "recordId": 10,
    "name": "state_dict_3",
    "uuid": "26b999fe-a099-4185-a203-840d3dc26eed",
    "description": null,
    "dateCreated": "2024-04-30T17:59:41.13576",
    "active": true,
    "constant": false
  },
  {
    "id": 16,
    "recordId": 10,
    "name": "Вес",
    "uuid": "26b999fe-a099-4185-a203-840d3dc26eed",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget arcu nec mauris blandit convallis.",
    "dateCreated": "2024-05-04T13:56:09.543726",
    "active": true,
    "constant": false
  }
]
```

#### List<StateDictionaryDto> findAll()

Request:

```http request
http://localhost:8085/api/v1/dictionaries
```

Response:

```json
[
  {
    "id": 3,
    "recordId": 10,
    "name": "state_dict_3",
    "uuid": "26b999fe-a099-4185-a203-840d3dc26eed",
    "description": null,
    "dateCreated": "2024-04-30T17:59:41.13576",
    "active": true,
    "constant": false
  },
  {
    "id": 16,
    "recordId": 10,
    "name": "Вес",
    "uuid": "26b999fe-a099-4185-a203-840d3dc26eed",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget arcu nec mauris blandit convallis.",
    "dateCreated": "2024-05-04T13:56:09.543726",
    "active": true,
    "constant": false
  }
]
```

### POST-запросы:

#### StateDictionaryDto create(@RequestBody StateDictionaryCreateDto stateDictionaryCreateDto)

Request:

```http request
http://localhost:8085/api/v1/dictionaries
```

Body:

```json
{
  "recordId": 10,
  "name": "string",
  "description": "string",
  "uuid": "00000000-0000-0000-000000000000"
}
```

Response:

```json
{
  "id": 20,
  "recordId": 10,
  "name": "string",
  "uuid": "00000000-0000-0000-000000000000",
  "description": "string",
  "dateCreated": "2024-05-06T13:04:27.92353",
  "active": true,
  "constant": false
}
```

### PUT-запросы:

#### StateDictionaryDto update(@RequestBody StateDictionaryUpdateDto stateDictionaryUpdateDto)

Request:

```http request
http://localhost:8085/api/v1/dictionaries
```

Body:

```json
{
  "recordId": 10,
  "name": "string",
  "description": "string",
  "uuid": "00000000-0000-0000-000000000000",
  "id": 20,
  "active": true
}
```

Response:

```json
{
  "id": 20,
  "recordId": 10,
  "name": "string",
  "uuid": "00000000-0000-0000-000000000000",
  "description": "string",
  "dateCreated": "2024-05-06T13:04:27.92353",
  "active": true,
  "constant": false
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)

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

## NotExistParentController ()

Контроллер поддерживает следующие операции:

- поиск родителя по id
- просмотр всех родителей
- создание родителя
- обновление родителя
- удаление родителя по id

### GET-запросы:

#### NotExistParentDto findById(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/parents/11
```

Response:

```json
{
  "id": 11,
  "name": "name",
  "sex": "FEMALI",
  "description": "desc"
}
```

Error:

```json
{
  "errorMessage": "Entity not found!",
  "errorCode": 404
}
```

#### List<NotExistParentDto> findAll()

Request:

```http request
http://localhost:8085/api/v1/parents
```

Response:

```json
[
  {
    "id": 12,
    "name": "name1",
    "sex": "FEMALE",
    "description": "d"
  },
  {
    "id": 13,
    "name": "name2",
    "sex": "FEMALE",
    "description": "d"
  },
  {
    "id": 14,
    "name": "name3",
    "sex": "FEMALE",
    "description": "d"
  }
]
```

### POST-запросы:

#### NotExistParentDto create(@RequestBody NotExistParentCreateDto notExistParentCreateDto)

Request:

```http request
http://localhost:8085/api/v1/parents
```

Body:

```json
{
  "name": "string",
  "sex": "string",
  "description": "string"
}
```

Response:

```json
{
  "id": 18,
  "name": "string",
  "sex": "string",
  "description": "string"
}
```

### PUT-запросы:

#### StateDtoupdate(@RequestBody StateUpdateDto stateUpdateDto)

Request:

```http request
http://localhost:8085/api/v1/parents
```

Body:

```json
{
  "name": "name",
  "sex": "FEMALE",
  "description": "desc",
  "id": 18
}
```

Response:

```json
{
  "id": 18,
  "name": "name",
  "sex": "FEMALE",
  "description": "desc"
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)

## PedigreeController ()

Контроллер поддерживает следующие операции:

- поиск родословной по id
- поиск родословных по id записи
- просмотр всех родословных
- создание родословной
- обновление родословной
- удаление родословной по id

### GET-запросы:

#### PedigreeDto findById(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/pedigree/19
```

Response:

```json
{
  "id": 19,
  "recordId": 10,
  "parentExistOneId": 9,
  "parentExistTwoId": null,
  "parentNotExistOneId": 17,
  "parentNotExistTwoId": null
}
```

Error:

```json
{
  "errorMessage": "Entity not found!",
  "errorCode": 404
}
```

#### PedigreeDto findByRecordId(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/pedigree/record/10
```

Response:

```json
{
  "id": 19,
  "recordId": 10,
  "parentExistOneId": 9,
  "parentExistTwoId": null,
  "parentNotExistOneId": 17,
  "parentNotExistTwoId": null
}
```

#### List<PedigreeDto> findAll()

Request:

```http request
http://localhost:8085/api/v1/pedigree
```

Response:

```json
[
  {
    "id": 18,
    "recordId": 7,
    "parentExistOneId": 10,
    "parentExistTwoId": null,
    "parentNotExistOneId": null,
    "parentNotExistTwoId": null
  },
  {
    "id": 20,
    "recordId": 9,
    "parentExistOneId": 11,
    "parentExistTwoId": null,
    "parentNotExistOneId": null,
    "parentNotExistTwoId": null
  },
  {
    "id": 19,
    "recordId": 10,
    "parentExistOneId": 9,
    "parentExistTwoId": null,
    "parentNotExistOneId": 17,
    "parentNotExistTwoId": null
  }
]
```

#### List<StateDictionaryDto> findAll()

Request:

```http request
http://localhost:8085/api/v1/dictionaries
```

Response:

```json
[
  {
    "id": 3,
    "recordId": 10,
    "name": "state_dict_3",
    "uuid": "26b999fe-a099-4185-a203-840d3dc26eed",
    "description": null,
    "dateCreated": "2024-04-30T17:59:41.13576",
    "active": true,
    "constant": false
  },
  {
    "id": 16,
    "recordId": 10,
    "name": "Вес",
    "uuid": "26b999fe-a099-4185-a203-840d3dc26eed",
    "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget arcu nec mauris blandit convallis.",
    "dateCreated": "2024-05-04T13:56:09.543726",
    "active": true,
    "constant": false
  }
]
```

### POST-запросы:

#### PedigreeDto create(@RequestBody PedigreeCreateDto pedigreeCreateDto)

Request:

```http request
http://localhost:8085/api/v1/pedigree
```

Body:

```json
{
  "recordId": 10,
  "parentExistOneId": 13,
  "parentExistTwoId": null,
  "parentNotExistOneId": 20,
  "parentNotExistTwoId": null
}
```

Response:

```json
{
  "id": 24,
  "recordId": 10,
  "parentExistOneId": 13,
  "parentExistTwoId": null,
  "parentNotExistOneId": 20,
  "parentNotExistTwoId": null
}
```

### PUT-запросы:

#### PedigreeDto update(@RequestBody PedigreeUpdateDto pedigreeUpdateDto)

Request:

```http request
http://localhost:8085/api/v1/pedigree
```

Body:

```json
{
  "recordId": 10,
  "parentExistOneId": 78,
  "parentExistTwoId": null,
  "parentNotExistOneId": null,
  "parentNotExistTwoId": null,
  "id": 24
}
```

Response:

```json
{
  "id": 24,
  "recordId": 10,
  "parentExistOneId": 78,
  "parentExistTwoId": null,
  "parentNotExistOneId": null,
  "parentNotExistTwoId": null
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)