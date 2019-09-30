# ReservasGrupo2019
## Descripción
Proyecto del grupo 2 de 1ºDAM sobre gestionar reservas de espacios en un centro, desde dos usuarios con funcionalidades diferenciadas: administrador y usuario.

## Funcionamiento
Primero has de acceder al repositorio de github (en el que deberías estar si estás leyendo este readme. URL: https://github.com/EsperanzaMacarena/ReservasGrupo2-2019). Una vez en él has de hacer click en la opción de "Clone or download". Puedes descargártelo en .zip sin necesidad de ningún otro programa o utilizar la url del repositorio para clonarlo con ayuda de algún programa que tenga el entorno de git incluido (GitHub Desktop, cmder...). 

Una vez el repositorio se encuentre en tu disco duro local, has de importarlo en tu IDE de elección, siempre y cuando soporte proyectos de Spring. Cuando lo hayas importado has de ejecutarlo como una aplicación de Spring Boot, e introducir la siguiente URL en la barra de búsqueda de tu navegador: http://localhost:9000/.

## LogIn
De entrada podrás hacer LogIn con dos usuarios: administrador e usuario.

Credenciales administrador:
Email: admin@admin.com
Contraseña: admin

Credenciales usuario:
Email: usuario@usuario.com
Contraseña: 1234

También podrás registrarte como un nuevo usuario, pero ten en cuenta que antes de poder logearte con un usuario recién registrado deberás validar la solicitud de registro desde la pantalla "solicitudes" del panel de control del administrador.

## Funcionamiento

### Admin
Al logearte como administrador se te redirigirá al panel de control de administrador. Desde él podrás acceder a las distintas pantallas de gestión a las que tiene acceso el admin, a saber: espacios, días festivos, reservas, solicitudes y usuarios.

#### Espacios
Dentro de la pantalla de gestión de espacios verás una lista de todos los espacios disponibles para reunirse en el centro, pudiendo registrar uno nuevo o borrar los ya existentes.

#### Días Festivos
Este apartado gestiona los días festivos, es decir, los días que el admin ha especificado como no disponibles para crear reservas ya sea porque el colegio cierra ese día, hay un evento, etcétera. Lo primero que te encuentras es un calendario desde el que se puede seleccionar un día para marcarlo como festivo y deshabilitar las reservas. A continuación verás un texto especificando si las reservas están reservadas los sábados y domingos (por defecto están deshabilitadas), seguido de los botones correspondientes para habilitar y deshabilitar las reservas esos días. Por último verás una lista de todos los días festivos añadidos a mano (desde el calendario) por el administrador, desde la que se pueden desmarcar para habilitarlos de nuevo como días en los que poder hacer reservas.

#### Reservas
En esta página aparece una lista con todas las reservas futuras creadas por los usuarios, con la posiblidad de eliminarlas si fuera necesario. También podemos encontrar un botón que te lleva al histórico de reservas, una lista con todas las reservas de días que ya han pasado.

#### Solicitudes
Pantalla desde la que se gestionan las peticiones de registro de usuario. Cuando un usuario nuevo se registra, no podrá iniciar sesión hasta que su petición de registro haya sido validada por un administrador. Se muestra una lista en la que se ven todas las solicitudes que aún no se han aprobado ni eliminado, y desde la misma lista podrás gestionarlas pulsando los iconos correspondientes.

#### Usuarios
Página que contiene una lista con el correo nombre y apellidos de todos los usuarios registrados cuya solicitud ya ha sido aceptada.

### Usuario
Al logearte como usuario lo primero que verás será una lista con todos los espacios habilitados para reuniones del centro. Al hacer click en una de ellas te lleva a una nueva pantalla en la que se mostrará un calendario para escoger el día y una tabla donde se muestran todas las reservas hechas esa semana y las horas que aún están disponibles. Al seleccionar un día en el calendario se cambia la tabla para mostrar la semana a la que pertenece dicho día, para luego poder seleccionar una hora libre desde el desplegable. Al hacer click en el botón de "submit" teniendo una fecha y una hora escogidas se guardará la reserva con su día, hora y usuario.