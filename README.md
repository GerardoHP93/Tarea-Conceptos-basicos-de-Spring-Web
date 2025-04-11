# Ejercicio: Conceptos básicos de Spring Web
- **Nombre:** Gerardo Isidro Herrera Pacheco
- **Matrícula:** ISC 68612
- **Semestre:** 8vo
- **Materia**: Temas selectos de Programación
- **Maestro:** Jose C Aguilar Canepa
- **Institución:** Universidad Autónoma de Campeche, Facultad de Ingeniería

## Descripción
Este ejercicio implementa un sistema de inicio de sesión básico utilizando Spring Boot. La aplicación presenta un formulario de login, aplicamos el FrontController visto en el parcial anterior, procesa las credenciales (simulado) y muestra una página de bienvenida o un mensaje de error según corresponda.
Usando Thymeleaf como motor de plantillas para tener contenido dinámico.
## Estructura del Proyecto

```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   ├── example
│   │   │   │   │   ├── demo
│   │   │   │   │   │   ├── DemoApplication.java
│   │   │   ├── controladores
│   │   │   │   ├── FrontController.java
│   │   ├── resources
│   │   │   ├── static
│   │   │   │   ├── css
│   │   │   │   │   ├── styles.css
│   │   │   ├── templates
│   │   │   │   ├── login.html
│   │   │   │   ├── welcome.html
├── pom.xml
```

## Tecnologías Utilizadas

- **Spring Boot 3.4.4**: Framework para el desarrollo de aplicaciones Java
- **Thymeleaf**: Motor de plantillas para generar vistas HTML
- **Maven**: Herramienta de gestión de dependencias
- **Java 17**: Versión del lenguaje de programación

## Mejoramiento del Sistema de Login

Este proyecto presenta una evolución respecto al sistema original:

- **Sistema original**: El método `login()` solo devolvía un texto plano ("LOGEADO" o "INCORRECTO").
- **Sistema mejorado**: El método `processLogin()` implementa una experiencia de usuario completa con:
  - Redirección a páginas HTML según el resultado
  - Paso de información entre controlador y vistas mediante el objeto Model
  - Manejo de mensajes de error en la interfaz
  - Interfaz visual mejorada con CSS
 
## Características principales 
- Formulario que envía datos a /inicio mediante POST
- Validación de campos requeridos
- Mensaje de error condicional usando Thymeleaf (th:if, th:text)
- Estilos CSS aplicados mediante recursos estáticos

##  Diagrama de Clases UML

![Diagrama de clases para Spring Web](https://github.com/user-attachments/assets/11f6d5d3-0342-4ff2-a472-c3eff4fed567)


## Componentes Principales

### 1. DemoApplication.java

Este es el punto de entrada principal de la aplicación Spring Boot. Contiene el método `main()` que inicia todo el contexto de Spring y ejecuta la aplicación.

```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

### 2. FrontController.java

Este controlador maneja las solicitudes web relacionadas con el inicio de sesión:

- **showLoginForm()**: Método que responde a solicitudes GET en "/inicio" y muestra el formulario de login.
- **processLogin()**: Método que procesa las solicitudes POST en "/inicio", verifica las credenciales y redirecciona según el resultado.

```java
@Controller
public class FrontController {
    // Método para mostrar el formulario de inicio de sesión (GET)
    @RequestMapping(path = "/inicio", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }
    
    // Método para procesar el formulario de inicio de sesión (POST)
    @RequestMapping(path = "/inicio", method = RequestMethod.POST)
    public String processLogin(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {
        // Verificación de credenciales
        if (username.equals("admin") && password.equals("admin")) {
            // Login exitoso - Redirigir a la página de bienvenida
            model.addAttribute("username", username);
            return "welcome";
        } else {
            // Login fallido - Volver al formulario con mensaje de error
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }
    
}
```

### 3. Templates (Vistas)

#### login.html

Formulario de inicio de sesión con soporte para mostrar mensajes de error. Utiliza Thymeleaf para la integración con Spring.

#### welcome.html

Página de bienvenida que muestra el nombre del usuario que ha iniciado sesión correctamente.

## Características principales
- Muestra el nombre de usuario usando la expresión ${username} de Thymeleaf
- Botón para cerrar sesión que redirecciona a /inicio
- Estilos CSS aplicados mediante recursos estáticos

### 4. Recursos Estáticos

#### styles.css

Archivo CSS que proporciona estilos para las páginas HTML.

## Funcionamiento del Sistema

1. **Inicio**: El usuario accede a la URL "/inicio" mediante GET.
2. **Formulario**: Se muestra el formulario de login (login.html).
3. **Envío de credenciales**: El usuario introduce sus datos y envía el formulario mediante POST a "/inicio".
4. **Procesamiento**: El controlador verifica las credenciales:
   - Si son correctas (admin/admin): Redirecciona a welcome.html mostrando el nombre del usuario.
   - Si son incorrectas: Redirecciona a login.html mostrando un mensaje de error.
5. **Cierre de sesión**: El usuario puede hacer clic en "Cerrar Sesión" para volver a la pantalla de login.


## Aplicación de Conceptos Spring Web

El proyecto aplica varios conceptos fundamentales de Spring Web:

1. **Controladores MVC**: Uso de la anotación `@Controller` para manejar solicitudes HTTP.
2. **RequestMapping**: Definición de rutas y métodos HTTP con `@RequestMapping`.
3. **Inyección de dependencias**: Uso del objeto `Model` para pasar datos a las vistas.
4. **Thymeleaf**: Integración del motor de plantillas para renderizar HTML dinámico.
5. **Manejo de parámetros**: Uso de `@RequestParam` para capturar datos del formulario.
6. **Redirección y enrutamiento**: Retorno de nombres de vistas para navegación.
7. **Recursos estáticos**: Organización y uso de CSS para estilizar la aplicación.

# Proceso de Uso de la Aplicación

## Flujo de Autenticación

### 1. Acceso al Formulario de Login
- **URL**: `/inicio` (Método GET)
- **Interfaz**:  
  ![Formulario de Login](contraseña incorrecta.png)  
  - Campo para ingresar usuario.
  - Campo para ingresar contraseña.
  - Botón "INICIAR SESIÓN".

### 2. Envío de Credenciales
- **Acción**: El usuario ingresa sus credenciales y hace clic en "INICIAR SESIÓN".
- **Método**: POST a `/inicio`.
- **Validación**:
  - **Credenciales correctas** (`usuario: admin`, `contraseña: admin`):
    - Redirección a la página de bienvenida.
    - Visualización del mensaje:
    - ![contraseña correctas](https://github.com/user-attachments/assets/a24dbbe5-1cac-436a-b078-c4488af4f059)

      - Saludo personalizado con el nombre de usuario.
      - Botón "CERRAR SESIÓN" para volver al formulario de login.
  - **Credenciales incorrectas**:
    - Muestra el mismo formulario con un mensaje de error:
    - ![contraseña incorrecta](https://github.com/user-attachments/assets/472a1db2-2690-4753-8610-15bc19b7cc21)

      - Texto: "Usuario o contraseña incorrectos".
      - Campos vacíos para reintento.

### 3. Cierre de Sesión
- **Acción**: El usuario hace clic en "CERRAR SESIÓN".
- **Resultado**: Redirección a `/inicio` (GET), mostrando nuevamente el formulario de login.
