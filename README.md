# pet-helper-service

[Точка входа в приложение](https://github.com/vitmvit/pet-helper-api-gateway-service)

Данный микросервис предоставляет функционал для работы мобильной частью приложения PetHelper.

Сюда входят:

- эвенты;
- дневники эвентов;
- состояния;
- дневники состояний;
- сохраненные статьи;
- записи родословной;
- заглушки записей родословной;
- звонки;
- расписпние звонка;
- записи питомцев;
- список статей к просмотру.

При каждом запросе необходимо передавать токен в заголовках.

## Доступ

Роли:

- USER

## Swagger

http://localhost:8085/api/doc/swagger-ui/index.html#/

## Порт

```text
8085
```

## StateController

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
http://localhost:8085/api/v1/states/55
```

Response:

```json
{
  "id": 55,
  "dictionaryId": 35,
  "value": 5.0,
  "description": "здоров",
  "dateCreated": "2024-05-20T08:49:10.74"
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
http://localhost:8085/api/v1/states/dict/44
```

Response:

```json
[
  {
    "id": 68,
    "dictionaryId": 44,
    "value": 0.9,
    "description": "много ест",
    "dateCreated": "2024-05-29T06:30:38.576"
  },
  {
    "id": 69,
    "dictionaryId": 44,
    "value": 0.1,
    "description": null,
    "dateCreated": "2024-03-12T06:31:00"
  },
  {
    "id": 70,
    "dictionaryId": 44,
    "value": 0.4,
    "description": null,
    "dateCreated": "2024-04-10T06:31:00"
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
    "id": 55,
    "dictionaryId": 35,
    "value": 5.0,
    "description": "здоров",
    "dateCreated": "2024-05-20T08:49:10.74"
  },
  {
    "id": 59,
    "dictionaryId": 41,
    "value": 3.0,
    "description": "здоров",
    "dateCreated": "2024-05-25T16:38:14.144"
  },
  ...
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

#### StateDtoUpdate(@RequestBody StateUpdateDto stateUpdateDto)

Request:

```http request
http://localhost:8085/api/v1/states
```

Body:

```json
{
  "id": 79,
  "dictionaryId": 44,
  "value": 35.0,
  "description": "string",
  "dateCreated": "2024-05-06T10:16:38.441"
}
```

Response:

```json
{
  "id": 79,
  "dictionaryId": 44,
  "value": 35.0,
  "description": "string",
  "dateCreated": "2024-05-06T10:16:38.441"
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)

## StateDictionaryController

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
http://localhost:8085/api/v1/dictionaries/36
```

Response:

```json
{
  "id": 36,
  "recordId": 23,
  "name": "Температура",
  "uuid": "4407c266-1eaa-4377-bebb-8e92be818525",
  "description": "Температура",
  "dateCreated": "2024-05-21T14:23:51.689583",
  "active": true
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
http://localhost:8085/api/v1/dictionaries/recordId/23
```

Response:

```json
[
  {
    "id": 36,
    "recordId": 23,
    "name": "Температура",
    "uuid": "4407c266-1eaa-4377-bebb-8e92be818525",
    "description": "Температура",
    "dateCreated": "2024-05-21T14:23:51.689583",
    "active": true
  },
  ...
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
    "id": 36,
    "recordId": 23,
    "name": "Температура",
    "uuid": "4407c266-1eaa-4377-bebb-8e92be818525",
    "description": "Температура",
    "dateCreated": "2024-05-21T14:23:51.689583",
    "active": true
  },
  {
    "id": 35,
    "recordId": 34,
    "name": "вес",
    "uuid": "ebaca5c3-b383-43a2-a006-240e722b9855",
    "description": null,
    "dateCreated": "2024-05-20T11:49:04.282653",
    "active": true
  },
  {
    "id": 41,
    "recordId": 34,
    "name": "Назаз",
    "uuid": "ebaca5c3-b383-43a2-a006-240e722b9855",
    "description": null,
    "dateCreated": "2024-05-25T19:34:54.973676",
    "active": true
  },
  ...
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
  "id": 49,
  "recordId": 10,
  "name": "string",
  "uuid": "00000000-0000-0000-000000000000",
  "description": "string",
  "dateCreated": "2024-07-16T13:50:27.368975",
  "active": true
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
  "id": 49,
  "recordId": 10,
  "name": "string1",
  "description": "string1",
  "uuid": "00000000-0000-0000-000000000000",
  "active": true
}
```

Response:

```json
{
  "id": 49,
  "recordId": 10,
  "name": "string1",
  "uuid": "00000000-0000-0000-000000000000",
  "description": "string1",
  "dateCreated": "2024-07-16T13:50:27.368975",
  "active": true
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)

## RecordController

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
http://localhost:8085/api/v1/records/61
```

Response:

```json
{
  "id": 61,
  "createDate": "2024-05-29T08:58:57.895254",
  "updateDate": "2024-05-29T08:59:13.321722",
  "userLogin": "maria@mail.com",
  "name": "Жорик",
  "breed": "Мопс",
  "animalType": "Собака",
  "uuidAvatar": "5a1f765d-10b6-464a-ac8e-08eeb96e51da",
  "dataBirthday": "2021-05-02T08:58:00",
  "fullName": null,
  "sex": "MALE",
  "description": "Ест все что найдет, не перекармливать",
  "hasPedigree": false,
  "hasExhibition": false
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
    "id": 24,
    "createDate": "2024-05-12T23:58:05.313376",
    "updateDate": "2024-05-12T23:59:40.515251",
    "userLogin": "user1@mail.com",
    "name": "Кошка",
    "breed": null,
    "animalType": "Млекопитающее",
    "uuidAvatar": "5bdc7dba-9dd5-4b22-a0db-d9be164c65f9",
    "dataBirthday": null,
    "fullName": null,
    "sex": "FEMALE",
    "description": "Что-то с персом",
    "hasPedigree": false,
    "hasExhibition": false
  },
  {
    "id": 25,
    "createDate": "2024-05-13T00:00:51.690816",
    "updateDate": "2024-05-13T00:01:02.952046",
    "userLogin": "user1@mail.com",
    "name": "ЧереПашка",
    "breed": null,
    "animalType": "Рептилия",
    "uuidAvatar": "6c878bad-e724-4f7a-b55c-986e58384e64",
    "dataBirthday": "2020-05-12T00:00:00",
    "fullName": null,
    "sex": "FEMALE",
    "description": "Нашли в лесу",
    "hasPedigree": true,
    "hasExhibition": false
  },
  {
    "id": 31,
    "createDate": "2024-05-16T11:15:17.79257",
    "updateDate": "2024-05-17T17:13:52.652949",
    "userLogin": "user1@mail.com",
    "name": "j[;o",
    "breed": "Леопардовый геккон",
    "animalType": "Рептилия",
    "uuidAvatar": "86999f04-814e-4ed6-ab65-33f0e5a4dc7c",
    "dataBirthday": null,
    "fullName": null,
    "sex": null,
    "description": null,
    "hasPedigree": false,
    "hasExhibition": false
  },
  {
    "id": 23,
    "createDate": "2024-05-12T23:35:22.312014",
    "updateDate": "2024-06-16T13:40:54.429926",
    "userLogin": "user1@mail.com",
    "name": "Женя",
    "breed": null,
    "animalType": "Рептилия",
    "uuidAvatar": "b40606e3-7786-4a60-b4d1-965b3618c75a",
    "dataBirthday": null,
    "fullName": null,
    "sex": "MALE",
    "description": "Красноухая черепаха",
    "hasPedigree": false,
    "hasExhibition": true
  },
  ...
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
    "id": 61,
    "createDate": "2024-05-29T08:58:57.895254",
    "updateDate": "2024-05-29T08:59:13.321722",
    "userLogin": "maria@mail.com",
    "name": "Жорик",
    "breed": "Мопс",
    "animalType": "Собака",
    "uuidAvatar": "5a1f765d-10b6-464a-ac8e-08eeb96e51da",
    "dataBirthday": "2021-05-02T08:58:00",
    "fullName": null,
    "sex": "MALE",
    "description": "Ест все что найдет, не перекармливать",
    "hasPedigree": false,
    "hasExhibition": false
  },
  {
    "id": 24,
    "createDate": "2024-05-12T23:58:05.313376",
    "updateDate": "2024-05-12T23:59:40.515251",
    "userLogin": "user1@mail.com",
    "name": "Кошка",
    "breed": null,
    "animalType": "Млекопитающее",
    "uuidAvatar": "5bdc7dba-9dd5-4b22-a0db-d9be164c65f9",
    "dataBirthday": null,
    "fullName": null,
    "sex": "FEMALE",
    "description": "Что-то с персом",
    "hasPedigree": false,
    "hasExhibition": false
  },
  ...
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
  "id": 68,
  "createDate": "2024-07-16T13:55:03.177695",
  "updateDate": "2024-07-16T13:55:03.177774",
  "userLogin": "user1@mail.com",
  "name": "pet",
  "breed": null,
  "animalType": null,
  "uuidAvatar": "00000000-0000-0000-0000-000000000000",
  "dataBirthday": "2024-04-02T22:33:18.722023",
  "fullName": "pet",
  "sex": "MALE",
  "description": "description",
  "hasPedigree": false,
  "hasExhibition": false
}
```

### PUT-запросы:

#### RecordDto updateAvatarUuid(@PathVariable("id") Long id, @PathVariable("uuid") String uuid)

Request:

```http request
http://localhost:8085/api/v1/records/avatar/68/00000000-0000-0000-0000-000000000000
```

Response:

```json
{
  "id": 68,
  "createDate": "2024-07-16T13:55:03.177695",
  "updateDate": "2024-07-16T13:55:03.177774",
  "userLogin": "user1@mail.com",
  "name": "pet",
  "breed": null,
  "animalType": null,
  "uuidAvatar": "00000000-0000-0000-0000-000000000000",
  "dataBirthday": "2024-04-02T22:33:18.722023",
  "fullName": "pet",
  "sex": "MALE",
  "description": "description",
  "hasPedigree": false,
  "hasExhibition": false
}
```

#### RecordDto update(@RequestBody RecordUpdateDto recordUpdateDto)

Request:

```http request
http://localhost:8085/api/v1/records
```

Body:

```json
{
  "id": 31,
  "userLogin": "user1@mail.com",
  "name": "pet",
  "uuidAvatar": "00000000-0000-0000-0000-000000000001",
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
  "createDate": "2024-05-16T11:15:17.79257",
  "updateDate": "2024-07-16T13:55:40.751889",
  "userLogin": "user1@mail.com",
  "name": "pet",
  "breed": null,
  "animalType": null,
  "uuidAvatar": "00000000-0000-0000-0000-000000000001",
  "dataBirthday": "2024-04-02T22:33:18.722023",
  "fullName": "pet",
  "sex": "MALE",
  "description": "description",
  "hasPedigree": false,
  "hasExhibition": false
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)

## NotExistParentController

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
http://localhost:8085/api/v1/parents/28
```

Response:

```json
{
  "id": 28,
  "name": "Оладка",
  "sex": "самка",
  "description": "Из московского питомника"
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
    "id": 20,
    "name": "ЧереПрадед",
    "sex": "самец",
    "description": "из Африки"
  },
  {
    "id": 21,
    "name": "ЧереПробабка",
    "sex": "самка",
    "description": "из Нидерландов"
  },
  ...
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
  "id": 31,
  "name": "string1",
  "sex": "string1",
  "description": "string1"
}
```

Response:

```json
{
  "id": 31,
  "name": "string1",
  "sex": "string1",
  "description": "string1"
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)

## PedigreeController

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
http://localhost:8085/api/v1/pedigree/36
```

Response:

```json
{
  "id": 36,
  "recordId": 60,
  "parentExistOneId": 61,
  "parentExistTwoId": null,
  "parentNotExistOneId": null,
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
http://localhost:8085/api/v1/pedigree/record/23
```

Response:

```json
{
  "id": 26,
  "recordId": 23,
  "parentExistOneId": null,
  "parentExistTwoId": null,
  "parentNotExistOneId": 20,
  "parentNotExistTwoId": 21
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
    "id": 26,
    "recordId": 23,
    "parentExistOneId": null,
    "parentExistTwoId": null,
    "parentNotExistOneId": 20,
    "parentNotExistTwoId": 21
  },
  {
    "id": 27,
    "recordId": 25,
    "parentExistOneId": 23,
    "parentExistTwoId": null,
    "parentNotExistOneId": null,
    "parentNotExistTwoId": null
  },
  ...
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

## RecommenderController

Контроллер поддерживает следующие операции:

- получение списка статей на основе анализа записей пользователя

### GET-запросы:

#### List<ArticleDto> findById(@PathVariable("login") String login)

Request:

```http request
http://localhost:8085/api/v1/recommender/user1@mail.com
```

Response:

```json
[
  {
    "id": 10,
    "name": "Красноухие черепахи",
    "redactorName": "editor1@mail.com",
    "photoUuid": "dd7a0fbd-6f4d-465a-9830-ff7d4fe2a5a4",
    "content": "Данный вид черепахи относят к пресноводным рептилиям. Ее главная особенность – длинноватые красно-оранжевые пятна, которые находятся сразу за глазами. Невзирая на то, что ушей у них нет, слышат они прекрасно. Поскольку щиток на их брюхе окрашен в ярко-желтый цвет, их еще частенько именуют желтобрюхими. Самки этих животных, как правило, намного крупнее самцов. Половое созревание этих животных наступает в 4 года.",
    "status": "FINISHED",
    "createDate": "2024-06-08T20:58:47.480456",
    "updateDate": "2024-06-08T20:58:51.885453"
  },
  ...
]
```

Error:

```json
{
  "errorMessage": "Entity not found!",
  "errorCode": 404
}
```

## EventController

Контроллер поддерживает следующие операции:

- поиск эвента по id
- поиск эвентов по id дневника
- просмотр всех эвентов
- создание эвента
- обновление эвента
- удаление эвента по id
- удаление невктивных эвентов

### GET-запросы:

#### EventDto findById(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/events/80
```

Response:

```json
{
  "id": 80,
  "dictionaryId": 3,
  "textColor": "#00ff2f",
  "description": "Трава",
  "dateCreated": "2024-07-17T20:46:34.715"
}
```

Error:

```json
{
  "errorMessage": "Entity not found!",
  "errorCode": 404
}
```

#### List<EventDto> findByDictionaryId(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/events/dict/3
```

Response:

```json
[
  {
    "id": 80,
    "dictionaryId": 3,
    "textColor": "#00ff2f",
    "description": "Трава",
    "dateCreated": "2024-07-17T20:46:34.715"
  },
  {
    "id": 81,
    "dictionaryId": 3,
    "textColor": "#00ff2f",
    "description": "Трава",
    "dateCreated": "2024-07-08T20:46:34.715"
  },
  ...
]
```

#### List<EventDto> findAll()

Request:

```http request
http://localhost:8085/api/v1/events
```

Response:

```json
[
  {
    "id": 80,
    "dictionaryId": 3,
    "textColor": "#00ff2f",
    "description": "Трава",
    "dateCreated": "2024-07-17T20:46:34.715"
  },
  {
    "id": 81,
    "dictionaryId": 3,
    "textColor": "#00ff2f",
    "description": "Трава",
    "dateCreated": "2024-07-08T20:46:34.715"
  },
  ...
]
```

### POST-запросы:

#### EventDto create(@RequestBody EventCreateDto eventCreateDto)

Request:

```http request
http://localhost:8085/api/v1/events
```

Body:

```json
{
  "dictionaryId": 3,
  "textColor": "#00ff2f",
  "description": "Трава",
  "dateCreated": "2024-07-17T20:46:34.715"
}
```

Response:

```json
{
  "id": 586,
  "dictionaryId": 3,
  "textColor": "#00ff2f",
  "description": "Трава",
  "dateCreated": "2024-07-17T20:46:34.715"
}
```

### PUT-запросы:

#### EventDto update(@RequestBody EventUpdateDto eventUpdateDto)

Request:

```http request
http://localhost:8085/api/v1/events
```

Body:

```json
{
  "id": 586,
  "dictionaryId": 3,
  "textColor": "#00ff2f",
  "description": "Fish",
  "dateCreated": "2024-07-17T20:46:34.715"
}
```

Response:

```json
{
  "id": 586,
  "dictionaryId": 3,
  "textColor": "#00ff2f",
  "description": "Fish",
  "dateCreated": "2024-07-17T20:46:34.715"
}
```

### DELETE-запросы:

Не возвращают ничего:

- List<EventDto> deleteBeforeDateByDictionaryId(@PathVariable("id") Long id)
- delete(@PathVariable("id") Long id)

## EventDictionaryController

Контроллер поддерживает следующие операции:

- поиск дневника по id
- поиск дневников по id записи
- просмотр всех дневников
- создание дневника
- обновление дневника
- удаление дневника по id

### GET-запросы:

#### EventDictionaryDto findById(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/eventDictionaries/3
```

Response:

```json
{
  "id": 3,
  "recordId": 23,
  "name": "Кормление",
  "uuid": "26b999fe-a099-4185-a203-840d3dc26eed",
  "description": null,
  "typeVaccination": null,
  "dateCreated": "2024-05-12T23:46:25.138416",
  "active": true
}
```

Error:

```json
{
  "errorMessage": "Entity not found!",
  "errorCode": 404
}
```

#### List<EventDictionaryDto> findAllByRecordId(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/eventDictionaries/recordId/23
```

Response:

```json
[
  {
    "id": 3,
    "recordId": 23,
    "name": "Кормление",
    "uuid": "26b999fe-a099-4185-a203-840d3dc26eed",
    "description": null,
    "typeVaccination": null,
    "dateCreated": "2024-05-12T23:46:25.138416",
    "active": true
  },
  {
    "id": 4,
    "recordId": 23,
    "name": "Прогулка",
    "uuid": "ebaca5c3-b383-43a2-a006-240e722b9855",
    "description": "kkk",
    "typeVaccination": null,
    "dateCreated": "2024-05-12T23:53:49.074805",
    "active": true
  },
  ...
]
```

#### List<EventDictionaryDto>> findAll()

Request:

```http request
http://localhost:8085/api/v1/eventDictionaries
```

Response:

```json
[
  {
    "id": 3,
    "recordId": 23,
    "name": "Кормление",
    "uuid": "26b999fe-a099-4185-a203-840d3dc26eed",
    "description": null,
    "typeVaccination": null,
    "dateCreated": "2024-05-12T23:46:25.138416",
    "active": true
  },
  {
    "id": 4,
    "recordId": 23,
    "name": "Прогулка",
    "uuid": "ebaca5c3-b383-43a2-a006-240e722b9855",
    "description": "kkk",
    "typeVaccination": null,
    "dateCreated": "2024-05-12T23:53:49.074805",
    "active": true
  },
  ...
]
```

### POST-запросы:

#### EventDictionaryDto create(@RequestBody EventDictionaryCreateDto eventDictionaryCreateDto)

Request:

```http request
http://localhost:8085/api/v1/eventDictionaries
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
  "id": 33,
  "recordId": 10,
  "name": "string",
  "uuid": "00000000-0000-0000-000000000000",
  "description": "string",
  "typeVaccination": null,
  "dateCreated": "2024-07-16T14:28:40.534329",
  "active": true
}
```

### PUT-запросы:

#### EventDictionaryDto update(@RequestBody EventDictionaryUpdateDto eventDictionaryUpdateDto)

Request:

```http request
http://localhost:8085/api/v1/eventDictionaries
```

Body:

```json
{
  "id": 33,
  "recordId": 10,
  "name": "String",
  "uuid": "00000000-0000-0000-000000000000",
  "description": "string",
  "typeVaccination": null,
  "dateCreated": "2024-07-16T14:28:40.534329",
  "active": true
}
```

Response:

```json
{
  "id": 33,
  "recordId": 10,
  "name": "String",
  "uuid": "00000000-0000-0000-000000000000",
  "description": "string",
  "typeVaccination": null,
  "dateCreated": "2024-07-16T14:28:40.534329",
  "active": true
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)

## FavoriteArticleController

Контроллер поддерживает следующие операции:

- поиск сохраненной статьи по id
- поиск сохраненной статьи по id статьи
- просмотр сохраненных статей по логину пользователя
- просмотр всех статей
- создание сохраненной статьи
- обновление сохраненной статьи
- удаление сохраненной статьи по id

### GET-запросы:

#### FavoriteArticleDto findById(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/favoriteArticles/16
```

Response:

```json
{
  "id": 16,
  "userLogin": "maria@mail.com",
  "articleId": 3
}
```

Error:

```json
{
  "errorMessage": "Entity not found!",
  "errorCode": 404
}
```

#### FavoriteArticleDto findByArticleId(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/favoriteArticles/articleId/3
```

Response:

```json
{
  "id": 16,
  "userLogin": "maria@mail.com",
  "articleId": 3
}
```

#### List<FavoriteArticleDto> findByUserLogin(@PathVariable("login") String login)

Request:

```http request
http://localhost:8085/api/v1/favoriteArticles/article/maria@mail.com
```

Response:

```json
[
  {
    "id": 16,
    "userLogin": "maria@mail.com",
    "articleId": 3
  },
  {
    "id": 17,
    "userLogin": "maria@mail.com",
    "articleId": 4
  },
  {
    "id": 19,
    "userLogin": "maria@mail.com",
    "articleId": 8
  },
  ...
]
```

#### List<FavoriteArticleDto>> findAll()

Request:

```http request
http://localhost:8085/api/v1/favoriteArticles
```

Response:

```json
[
  {
    "id": 13,
    "userLogin": "user1@mail.com",
    "articleId": 7
  },
  {
    "id": 16,
    "userLogin": "maria@mail.com",
    "articleId": 3
  },
  ...
]
```

### POST-запросы:

#### FavoriteArticleDto create(@RequestBody FavoriteArticleCreateDto dto)

Request:

```http request
http://localhost:8085/api/v1/favoriteArticles
```

Body:

```json
{
  "userLogin": "maria@mail.com",
  "articleId": 3
}
```

Response:

```json
{
  "id": 20,
  "userLogin": "maria@mail.com",
  "articleId": 3
}
```

### PUT-запросы:

#### FavoriteArticleDto update(@RequestBody FavoriteArticleUpdateDto dto) {

Request:

```http request
http://localhost:8085/api/v1/favoriteArticles
```

Body:

```json
{
  "id": 20,
  "userLogin": "maria@mail.com",
  "articleId": 3
}
```

Response:

```json
{
  "id": 20,
  "userLogin": "maria@mail.com",
  "articleId": 3
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)

## NotificationController

Контроллер поддерживает следующие операции:

- поиск уведомления по id
- поиск уведомлений по логину пользователя
- просмотр всех уведомлений
- создание уведомления
- обновление уведомления
- обновление статуса уведомления
- удаление уведомления по логину пользователя
- удаление уведомления по id

### GET-запросы:

#### NotificationDto findById(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/notifications/313
```

Response:

```json
{
  "id": 313,
  "userLogin": "user1@mail.com",
  "name": "Прогулка",
  "description": null,
  "createDate": "2024-05-29T01:08:21.039388",
  "updateDate": "2024-05-29T01:08:21.039409",
  "active": true
}
```

Error:

```json
{
  "errorMessage": "Entity not found!",
  "errorCode": 404
}
```

#### List<NotificationDto> findByUserLogin(@PathVariable("login") String login)

Request:

```http request
http://localhost:8085/api/v1/notifications/userLogin/user1@mail.com
```

Response:

```json
[
  {
    "id": 313,
    "userLogin": "user1@mail.com",
    "name": "Прогулка",
    "description": null,
    "createDate": "2024-05-29T01:08:21.039388",
    "updateDate": "2024-05-29T01:08:21.039409",
    "active": true
  },
  {
    "id": 316,
    "userLogin": "user1@mail.com",
    "name": "Прогулка",
    "description": null,
    "createDate": "2024-05-29T01:21:23.973536",
    "updateDate": "2024-05-29T01:21:23.973562",
    "active": true
  },
  ...
]
```

#### List<NotificationDto> findAll()

Request:

```http request
http://localhost:8085/api/v1/notifications
```

Response:

```json
[
  {
    "id": 313,
    "userLogin": "user1@mail.com",
    "name": "Прогулка",
    "description": null,
    "createDate": "2024-05-29T01:08:21.039388",
    "updateDate": "2024-05-29T01:08:21.039409",
    "active": true
  },
  ...
]
```

### POST-запросы:

#### NotificationDto create(@RequestBody NotificationCreateDto notificationCreateDto)

Request:

```http request
http://localhost:8085/api/v1/notifications
```

Body:

```json
{
  "userLogin": "user1@mail.com",
  "name": "Прогулка",
  "description": null,
  "createDate": "2024-05-29T01:08:21.039388",
  "updateDate": "2024-05-29T01:08:21.039409",
  "active": true
}
```

Response:

```json
{
  "id": 319,
  "userLogin": "user1@mail.com",
  "name": "Прогулка",
  "description": null,
  "createDate": "2024-07-16T15:10:10.319455",
  "updateDate": "2024-07-16T15:10:10.3199",
  "active": true
}
```

### PUT-запросы:

#### NotificationDto update(@RequestBody NotificationUpdateDto notificationUpdateDto)

Request:

```http request
http://localhost:8085/api/v1/notifications
```

Body:

```json
{
  "id": 319,
  "userLogin": "user1@mail.com",
  "name": "Прогулка",
  "description": null,
  "createDate": "2024-07-16T15:10:10.319455",
  "updateDate": "2024-07-16T15:10:10.3199",
  "active": true
}
```

Response:

```json
{
  "id": 319,
  "userLogin": "user1@mail.com",
  "name": "Прогулка",
  "description": null,
  "createDate": "2024-07-16T15:10:10.319455",
  "updateDate": "2024-07-16T15:10:10.3199",
  "active": true
}
```

#### NotificationDto updateStatus(@PathVariable("id") Long id, @PathVariable("status") boolean status)

Request:

```http request
http://localhost:8085/api/v1/notifications/319/false
```

Body:

```json
{
  "id": 319,
  "userLogin": "user1@mail.com",
  "name": "Прогулка",
  "description": null,
  "createDate": "2024-07-16T15:10:10.319455",
  "updateDate": "2024-07-16T15:10:10.3199",
  "active": true
}
```

Response:

```json
{
  "id": 319,
  "userLogin": "user1@mail.com",
  "name": "Прогулка",
  "description": null,
  "createDate": "2024-07-16T15:10:10.319455",
  "updateDate": "2024-07-16T15:10:10.3199",
  "active": false
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)
- deleteNotificationsByUserLogin(@PathVariable("login") String login)

## NotificationTimeController

Контроллер поддерживает следующие операции:

- поиск звонка уведомления по id
- поиск звонков уведомлений по id уведомления
- просмотр всех звонков уведомлений
- создание звонка уведомления
- обновление звонка уведомления
- удаление звонка уведомления по id

### GET-запросы:

#### NotificationTimeDto findById(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/notificationTimes/1254
```

Response:

```json
{
  "id": 1254,
  "notificationId": 317,
  "time": "2024-05-29T06:05:28.13",
  "date": "2024-05-31T06:05:00",
  "recordId": null,
  "eventId": null,
  "stateId": null
}
```

Error:

```json
{
  "errorMessage": "Entity not found!",
  "errorCode": 404
}
```

#### List<NotificationTimeDto> findAll()

Request:

```http request
http://localhost:8085/api/v1/notificationTimes
```

Response:

```json
[
  {
    "id": 1254,
    "notificationId": 317,
    "time": "2024-05-29T06:05:28.13",
    "date": "2024-05-31T06:05:00",
    "recordId": null,
    "eventId": null,
    "stateId": null
  },
  {
    "id": 1255,
    "notificationId": 318,
    "time": "2024-06-08T19:16:32.704",
    "date": "2024-06-09T19:16:00",
    "recordId": null,
    "eventId": null,
    "stateId": null
  },
  ...
]
```

#### List<NotificationTimeDto> findByNotificationId(@PathVariable("id") Long id)

Request:

```http request
http://localhost:8085/api/v1/notificationTimes/dict/317
```

Response:

```json
[
  {
    "id": 1254,
    "notificationId": 317,
    "time": "2024-05-29T06:05:28.13",
    "date": "2024-05-31T06:05:00",
    "recordId": null,
    "eventId": null,
    "stateId": null
  },
  ...
]
```

### POST-запросы:

#### NotificationTimeDto create(@RequestBody NotificationTimeCreateDto notificationTimeCreateDto)

Request:

```http request
http://localhost:8085/api/v1/notificationTimes
```

Body:

```json
{
  "notificationId": 317,
  "time": "2024-05-29T06:05:28.13",
  "date": "2024-05-31T06:05:00",
  "recordId": null,
  "eventId": null,
  "stateId": null
}
```

Response:

```json
{
  "id": 1256,
  "notificationId": 317,
  "time": "2024-05-29T06:05:28.13",
  "date": "2024-05-31T06:05:00",
  "recordId": null,
  "eventId": null,
  "stateId": null
}
```

### PUT-запросы:

#### NotificationTimeDto update(@RequestBody NotificationTimeUpdateDto notificationTimeUpdateDto)

Request:

```http request
http://localhost:8085/api/v1/notificationTimes
```

Body:

```json
{
  "id": 1256,
  "notificationId": 316,
  "time": "2024-05-29T06:05:28.13",
  "date": "2024-05-31T06:05:00",
  "recordId": null,
  "eventId": null,
  "stateId": null
}
```

Response:

```json
{
  "id": 1256,
  "notificationId": 317,
  "time": "2024-05-29T06:05:28.13",
  "date": "2024-05-31T06:05:00",
  "recordId": null,
  "eventId": null,
  "stateId": null
}
```

### DELETE-запросы:

Не возвращают ничего:

- delete(@PathVariable("id") Long id)