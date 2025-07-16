---
description: Repository Information Overview
globs: []
alwaysApply: true
---

# Datagen IntelliJ Plugin Information

## Summary
Datagen is a lightweight IntelliJ-based plugin for generating test data, samples, and demos. It provides features for UUID generation and number generation, making it easier to insert test data directly into the editor.

## Structure
- **src/main/kotlin**: Contains the Kotlin source code for the plugin
- **src/main/resources**: Contains resources like icons, messages, and plugin configuration
- **src/test**: Contains test classes and test data
- **.github/workflows**: CI/CD workflows for building, testing, and releasing the plugin
- **gradle**: Contains Gradle configuration files and wrapper

## Language & Runtime
**Language**: Kotlin
**Version**: 1.9.25
**JVM Toolchain**: 17
**Build System**: Gradle 8.9
**Package Manager**: Gradle

## Dependencies
**Main Dependencies**:
- IntelliJ Platform SDK (2023.3.7)
- JUnit 4.13.2 (for testing)

**Plugin Dependencies**:
- org.jetbrains.intellij.platform (2.0.1)
- org.jetbrains.changelog (2.2.1)
- org.jetbrains.qodana (2024.1.9)
- org.jetbrains.kotlinx.kover (0.8.3)

## Build & Installation
```bash
# Build the plugin
./gradlew build

# Run the plugin in a development instance
./gradlew runIde

# Run tests
./gradlew test

# Build plugin distribution
./gradlew buildPlugin
```

## Plugin Configuration
**Plugin ID**: com.solmano.datagen
**Plugin Name**: Datagen
**Version**: 1.2.0-SNAPSHOT
**Since Build**: 233 (IntelliJ 2023.3)
**Platform Type**: IC (IntelliJ Community Edition)

## Features
The plugin provides actions for:
- Generating and inserting UUIDs
- Generating random integers in ranges 1-999 and 1-999999

## Testing
**Framework**: JUnit 4
**Test Location**: src/test/kotlin/com/solmano/datagen
**Test Data**: src/test/testData
**Run Command**:
```bash
./gradlew test
```

## CI/CD
The project uses GitHub Actions for continuous integration and delivery with workflows for:
- Building and testing the plugin
- Running UI tests
- Creating and publishing release candidates
- Publishing public releases
- Nightly builds

## Installation Options
- Via IDE built-in plugin system (Settings > Plugins > Marketplace)
- Via JetBrains Marketplace
- Manual installation from GitHub releases