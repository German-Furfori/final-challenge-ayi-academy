# Proyecto final para la AYI Academy, Backend

## Presentación

Soy Germán Furfori, estudiante de la AYI Academy Fullstack Java - React - PWA.
Este proyecto trata sobre una aplicación para la asignación de proyectos a los empleados
con su correspondiente actualización de sueldo, y también está conectado con los servidores
del banco central para informar de cuanto fue la inflación mensual, y a raíz
de eso aplicar un aumento de sueldo a todos los empleados.

## Reglas de negocio establecidas (por endpoint)

#### Empleado:

/getAllEmployeePages: Me devuelve una lista paginada de todos los empleados de la base de datos. Solo sus atributos principales. <br>
/getEmployeeById: Me devuelve un empleado solo, buscado por ID, en donde puedo ver además de sus atributos principales, su detalle (seniority, rol, sueldo) y el projecto en el cual está trabajando. <br>
/incrementSalaries: Incrementa en porcentaje los salarios de todos los empleados. Éste no puede ser 0 ni negativo, y no puede ser mayor a 100. <br>
/assignProjectToEmployee: Asigna un proyecto existente en la base de datos a un empleado. <br>
/updateEmployeeSalary: Actualiza el sueldo del empleado. Éste no puede ser menor a $100000. <br>

#### Projecto: 

/getAllProjects: Me devuelve un listado solo con los datos de los proyectos. <br>

#### Api externa (API del Banco Central de la República Argentina):

/getInflationInfo: Me devuelve el valor de la inflación mensual hasta el último día del mes anterior.

Esta aplicación también tiene un login y registro de usuarios manejado desde el frontend con un estado de Redux, el cual se guarda en el localStorage del navegador para recordar la última sesión activa.

## Configuración y ejecución

Aclaración 1: La siguiente explicación requiere de tener instalado IntelliJ IDEA en tu computadora.

1) Clonar este proyecto o descargarlo en un .zip
2) Abrir una terminal en el IDE y correr el comando: mvn clean install -DskipTests
3) Dirigirse arriba de todo a la derecha donde dice "Current File", hacer clic e ir a "Edit Configurations"
4) Agregar una nueva configuración de aplicación (Add new... -> Application)
   1) Name: AyiFinalSpringApplication 
   2) Main class: com.finalchallenge.app.AyiFinalSpringApplication
   3) Variables de entorno: cambiar el valor de las variables a necesidad del usuario
   4) DB_PORT=3307;DB_HOST=localhost;DB_USERNAME=root;DB_PASSWORD=;DB_DATABASE=curso_ayi_final

Aclaración 2: estas variables están en el application.yml dentro de la carpeta src/main/resources.
En la configuración de spring se encuentran las variables antes mencionadas, por lo que también se pueden poner sus valores directamente ahí.

Una vez finalizada la configuración, para correr el proyecto apretar el "play" verde. Asegurarse antes de tener levantada la base de datos MySQL y también revisar de no tener una base de datos con el mismo nombre que el definido en DB_DATABASE

Finalizado esto, entrar en http://localhost:8080/swagger-ui.html#!/ para usar la API, o bien, pasar a instalar y ejecutar el frontend.