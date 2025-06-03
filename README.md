# 🎵 CRUD de Canciones con JavaFX y MySQL

Una aplicación de escritorio en JavaFX que permite gestionar un listado de canciones usando operaciones CRUD (Crear, Leer, Actualizar, Eliminar) conectadas a una base de datos MySQL.

## 🧠 Tecnologías utilizadas

* **Java 17+**
* **JavaFX 21** (FXML)
* **MySQL** 5.7 o superior
* **JDBC** (Java Database Connectivity)
* **SceneBuilder** (opcional, para edición gráfica de FXML)

## 📦 Estructura del proyecto

```
├── src/
│   └── com.mycompany.crud_canciones/
│       ├── PrimaryController.java     # Controlador JavaFX
│       ├── ModeloBD.java              # Lógica de acceso a datos
│       ├── Cancion.java               # Modelo de entidad canción
│       └── primary.fxml               # Interfaz gráfica en FXML
└── README.md
```

## 💄 Estructura de la Base de Datos

Asegúrate de tener una base de datos MySQL llamada `canciones` y ejecuta la siguiente consulta para crear la tabla:

```sql
CREATE TABLE canciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    artista VARCHAR(100) NOT NULL,
    album VARCHAR(100),
    anio INT,
    genero VARCHAR(50)
);
```

## ⚙️ Configuración

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/tu-usuario/crud-canciones-javafx.git
   cd crud-canciones-javafx
   ```

2. **Configurar tu conexión MySQL**
   En `ModeloBD.java`, asegúrate de que los datos coinciden con tu entorno:

   ```java
   private String url = "jdbc:mysql://localhost:3306/canciones";
   private String usuario = "canciones";
   private String contraseña = "canciones";
   ```

3. **Abrir el proyecto en tu IDE**

   * Puedes usar **NetBeans**, **IntelliJ IDEA**, o cualquier otro IDE con soporte para JavaFX.
   * Si usas Maven/Gradle, agrega las dependencias de JavaFX y MySQL.

4. **Ejecutar el proyecto**
   Ejecuta el `Main` o `App.java` si está disponible, o configura tu clase principal.

## ✅ Funcionalidades

* 📅 Insertar una nueva canción
* 🔍 Ver todas las canciones en una tabla
* ✏️ Modificar canción seleccionada
* ❌ Borrar canción
* 🧽 Limpiar campos del formulario

## 🎨 Interfaz

* Estilo limpio y moderno basado en una paleta azul/blanca.
* Componentes organizados con `GridPane` y `TableView`.

## 💡 Notas

* Asegúrate de que tu servidor MySQL esté corriendo.
* No olvides agregar el **conector JDBC de MySQL** al classpath.

---

Desarrollado por \[Tu Nombre] ❤️
