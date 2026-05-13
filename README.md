# RLE

Многомодульный Java-проект на Gradle (Kotlin DSL).

## Структура

- **cli** — CLI-приложение
  - Зависит от: `domain`, `data`, `lib:v1`
- **data** — слой данных
  - Зависит от: `domain`, `lib:api`
- **domain** — доменная логика
  - Зависит от: `lib:api`
- **lib** — агрегирующий модуль библиотеки
  - **lib:api** — базовые утилиты (публичный API)
  - **lib:v1** — реализация v1, зависит от `lib:api`

## Сборка

```bash
./gradlew build
```

## Запуск CLI

```bash
./gradlew :cli:run
```

## Тесты

```bash
./gradlew test
```
