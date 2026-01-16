# Simple project to try to solve some algorithms in Java

## Prerequisites

- Java 21
- Maven (or use the Maven Wrapper included in the project)

## Building the Project

This project uses Maven for building. You can use the Maven Wrapper (included in the project) or your system's Maven installation.

### Using Maven Wrapper (Recommended)

**Windows:**
```bash
.\mvnw.cmd clean install
```

**Linux/Mac:**
```bash
./mvnw clean install
```

### Using System Maven

```bash
mvn clean install
```

### Common Build Commands

- **Compile the project:**
  ```bash
  .\mvnw.cmd compile
  ```

- **Run tests:**
  ```bash
  .\mvnw.cmd test
  ```

- **Package the project (creates JAR file):**
  ```bash
  .\mvnw.cmd package
  ```

- **Clean build artifacts:**
  ```bash
  .\mvnw.cmd clean
  ```