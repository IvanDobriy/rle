# Agent Instructions

## Project Overview

Multi-module Java project built with Gradle using Kotlin DSL.

- **Project name:** `rle`
- **Working directory:** `/home/irma/Документы/otus/rle`
- **Current state:** Active development with 6 subprojects (including nested ones)

## Repository Structure

```
rle/
├── .git/
├── gradle/
│   └── wrapper/
├── cli/              # CLI application (depends on domain, data, lib:v1)
│   ├── build.gradle.kts
│   └── src/
├── data/             # Data layer (depends on domain, lib:api)
│   ├── build.gradle.kts
│   └── src/
├── domain/           # Domain logic (depends on lib:api)
│   ├── build.gradle.kts
│   └── src/
├── lib/              # Aggregating library module
│   ├── api/          # Base utilities / public API
│   │   ├── build.gradle.kts
│   │   └── src/
│   ├── v1/           # v1 implementation (depends on lib:api)
│   │   ├── build.gradle.kts
│   │   └── src/
│   └── build.gradle.kts
├── build.gradle.kts  # Root build script
├── settings.gradle.kts
└── README.md
```

## Technology Stack

- **Language:** Java 17
- **Build tool:** Gradle 8.5 (Kotlin DSL)
- **Test framework:** JUnit 5 (Jupiter)
- **Base package:** `ru.otus.danilchenko`

## Build and Test Commands

```bash
# Build all modules
./gradlew build

# Run tests
./gradlew test

# Run CLI application
./gradlew :cli:run
```

If `JAVA_HOME` is not set to Java 17+, use the SDKMAN candidate:
```bash
export JAVA_HOME=/home/irma/.sdkman/candidates/java/17.0.12-oracle
export PATH=$JAVA_HOME/bin:$PATH
```

## Code Style Guidelines

- Standard Java package naming: `ru.otus.danilchenko.<module>`
- Keep modules loosely coupled; dependencies flow inward:
  - `cli` → `domain`, `data`, `lib:v1`
  - `data` → `domain`, `lib:api`
  - `domain` → `lib:api`
  - `lib:v1` → `lib:api`
  - `lib:api` → none

## Testing Instructions

- Unit tests located in `src/test/java`
- Run with `./gradlew test`

## Deployment Process

Not configured.

## Security Considerations

None applicable at this stage.
