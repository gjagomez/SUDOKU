# 📋 Proyecto Quarkus - Validador de Sudoku y Secuencia de Fibonacci

## 🎯 Descripción General

Este proyecto implementa dos funcionalidades principales en **Quarkus** siguiendo los **principios SOLID**:

1. **🧩 Validador de Sudoku**: Valida tableros de Sudoku 9x9 según las reglas estándar
2. **🔢 Generador de Fibonacci**: Genera secuencias de Fibonacci con números iniciales personalizables

## 🏗️ Arquitectura y Principios SOLID Aplicados


## 📂 Estructura del Proyecto

```
quarkus-api/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/beesion/ms/test/
│   │           └── resource/                    # 🆕 NUEVAS CLASES
│   │               ├── SudokuValidatorResource.java  # ✅ Validador de Sudoku
│   │               └── FibonacciResource.java        # ✅ Generador de Fibonacci
│   └── test/
│       └── java/
│           └── com/beesion/ms/test/
│               └── resource/                    # 🆕 TESTS
│                   └── SolutionTest.java        # ✅ Tests unitarios
```




### 📊 Ejemplos de Uso
```java
// 🔄 Fibonacci clásico
fibonacci([0, 1], 9) → [0, 1, 1, 2, 3, 5, 8, 13, 21]

// 🎲 Fibonacci con números iniciales diferentes
fibonacci([2, 3], 5) → [2, 3, 5, 8, 13]

// 🔸 Casos especiales
fibonacci([0, 1], 0) → []           // Lista vacía
fibonacci([5, 7], 1) → [5]          // Solo primer número
fibonacci([10, 20], 2) → [10, 20]   // Solo números iniciales
```

## 🧪 Tests Unitarios

### 📈 Cobertura de Tests

El proyecto incluye **13 tests exhaustivos** que cubren:

#### 🧩 Validador de Sudoku
- ✅ **Tablero válido** (Ejemplo 1 del documento)
- ✅ **Tablero inválido** (Ejemplo 2 del documento)
- ✅ **Tablero nulo**
- ✅ **Dimensiones incorrectas**
- ✅ **Números repetidos** en columnas

#### 🔢 Generador de Fibonacci
- ✅ **Secuencia clásica** [0,1] con n=9
- ✅ **Secuencia personalizada** [2,3] con n=5
- ✅ **Casos especiales** (n=0, n=1, n=2)
- ✅ **Validación de parámetros** inválidos
- ✅ **Manejo de excepciones**

```
🔢 PROBANDO FIBONACCI EJEMPLO 1:
📥 Entrada: números iniciales = [0, 1], n = 9
📤 Resultado: [0, 1, 1, 2, 3, 5, 8, 13, 21]
✅ Esperado:  [0, 1, 1, 2, 3, 5, 8, 13, 21]
🎯 Match: SÍ
```

## 🛠️ Instalación y Ejecución

### 📋 Prerrequisitos
- ☕ **Java 17** o superior
- 📦 **Maven 3.8+**
- ⚡ **Quarkus CLI** (opcional)

### 🚀 Comandos de Ejecución

#### Ejecutar en modo desarrollo
```bash
./mvnw quarkus:dev
```

#### Compilar proyecto
```bash
./mvnw clean compile
```

#### Ejecutar tests
```bash
./mvnw test
```

#### Compilar y empaquetar
```bash
./mvnw clean package
java -jar target/quarkus-app/quarkus-run.jar
```

## 📸 Evidencia de Funcionamiento

### ✅ Compilación y Tests Exitosos

![Evidencia de Tests Funcionando](https://github.com/gjagomez/SUDOKU/blob/main/Img/1.png)


## 🔧 Configuración del Proyecto

### 📄 `pom.xml` - Dependencias Clave
```xml
<dependencies>
    <!-- Quarkus Core -->
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-resteasy-reactive-jackson</artifactId>
    </dependency>
    
    <!-- Testing -->
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-junit5</artifactId>
        <scope>test</scope>
    </dependency>
    
    <!-- JAX-RS -->
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-resteasy-reactive</artifactId>
    </dependency>
</dependencies>
```

### ⚙️ `application.properties`
```properties
# Puerto de la aplicación
quarkus.http.port=8080

# Configuración de logs
quarkus.log.level=INFO
quarkus.log.category."com.beesion.ms.test".level=DEBUG

# Configuración de desarrollo
%dev.quarkus.http.port=8080
%dev.quarkus.log.console.enable=true
```



### 🔢 Probar Generador de Fibonacci
```bash
# Fibonacci clásico
curl -X POST http://localhost:8080/fibonacci/generate \
  -H "Content-Type: application/json" \
  -d '{"initialNumbers":[0,1],"n":9}'

# Fibonacci simple con parámetros URL
curl "http://localhost:8080/fibonacci/simple?first=2&second=3&count=5"
```

