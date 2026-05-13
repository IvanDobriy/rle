# RLE

Многомодульный Java-проект на Gradle (Kotlin DSL).

## Структура

- **lib** — базовая библиотека с общими утилитами
- **domain** — доменная логика, зависит от `lib`
- **data** — слой данных, зависит от `domain` и `lib`
- **cli** — консольное приложение, зависит от `domain` и `data`

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
