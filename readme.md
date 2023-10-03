 
# Proyecto SOLITARIO - GRUPO L5-3



- Repositorio: https://github.com/gii-is-DP1/dp1-2022-2023-l5-3.git
- Vídeo explicando el juego: https://youtu.be/65uOcDNLOZI
- Vídeo navegando por el juego ya desarrollado: https://www.youtube.com/watch?v=9wx4hu8YVYM

## Miembros:
- Barba Trejo, Francisco Javier.
- Caro Albarrán, Francisco Andrés.
- Gallego Huerta, Alberto.
- Navarro Rivera, Álvaro.
- Sánchez Naranjo,Mario.
- Sillero Manchón, Jorge.


Tutor: José Antonio Parejo


## Descripción general del proyecto

El solitario es un juego de naipes que, como su propio nombre indica, es jugado por una sola persona. La partida comienza con 7 columnas de cartas que , de izquierda a derecha ,cada columna debe tener una carta más que la anterior , estando todas las cartas boca abajo excepto la primera de cada columna. Las cartas sobrantes se recogen en un mazo aparte del que se van sacando cartas de 1 en 1(tal y como acordamos todos los miembros del grupo,ya que es posible sacar de 2 en 2 o de 3 en 3), si se ha sacado una carta de este mazo y no se ha usado, al sacar otra carta, la anterior se colocará en la última posición del mazo.

El jugador deberá colocar las cartas de las columnas o del mazo en otras columnas de manera que formen escaleras de cartas de mayor a menor y siempre con colores intercalados, es decir, no puedes colocar dos cartas seguidas con el mismo color.

Posteriormente,el jugador deberá almacenar cartas en las pilas,clasificadas por tipos:diamantes, corazones, tréboles y picas, de menor a mayor.

El tiempo de partida puede variar según cada persona. La partida termina cuando se consigue construir 4 pilas de cartas del mismo palo o bien cuando no haya más movimientos posibles, es decir, que no se pueda colocar más cartas ni en la pilas ni en las columnas.

Con esta implementación pretendemos crear un juego de solitario totalmente funcional y que soporte más funcionalidades extra, como puede ser gestión de usuarios y otros opcionales, como por ejemplo un módulo que permita saber las estadísticas globales y por usuarios.

##Tipos de Usuario / Roles

Jugador: Cualquier usuario que pueda acceder a la aplicación y jugar una partida de solitario.

Administrador: Usuario que gestiona las partidas, puede visualizar que partidas están en marcha y acceder al listado de usuarios y gestionarlos.


## Historias de Usuario

### H1 - Crear Partida
Como jugador, quiero que el sistema me permita crear una partida para poder jugar. 

Escenarios Positivos:

H1+E1- Creación exitosa de partida
Dado que estoy autenticado en el sistema como jugador y que no tengo ninguna partida pendiente, cuando le doy al botón de crear partida y se procede a generar una partida de cero, devolviendo una vista del tablero inicializado.


Escenarios Negativos:

H1-E1-Creación fallida de partida
Dado que el jugador no  está autenticado en el sistema, cuando le dé al botón de crear partida, entonces la aplicación nos pedirá primero que iniciemos sesión y posteriormente permitirá crear la partida

### H2 - Registro
Como usuario no registrado deseo que el sistema me permita darme de alta introduciendo mi nombre de usuario y contraseña, para poder acceder al sistema.

Escenarios Positivos:

H2+E1 - Registro en el sistema
Dado que no estamos aún registrados en el sistema, cuando pulsamos en el enlace para acceder el formulario de registro e introducimos un nombre que no sea una cadena vacía y tenga una longitud de entre 3 y 20 caracteres, un apellido que no sea una cadena vacía y tenga una longitud de entre 3 y 20 caracteres, un nombre de usuario que sea único y tenga una longitud de entre 3 y 20 caracteres, una  contraseña que tenga una longitud de entre 3 y 20 caracteres, al pulsar el botón de registrarse estaremos registrados en el sistema y se nos redirigirá a la pantalla de inicio, estando ya autenticados.

Escenarios Negativos:

H2-E1 - Registro en el sistema con campos vacíos
Dado que no estamos aún registrados en el sistema, cuando pulsamos en el enlace para acceder el formulario de registro y le damos al botón de registrarse dejando los campos vacíos, se nos mostrará un mensaje en la misma pantalla, indicando que los campos no deben estar vacíos.

H2-E2 - Registro en el sistema con nombre de usuario repetido
Dado que no estamos aún registrados en el sistema, cuando pulsamos en el enlace para acceder el formulario de registro y le damos al botón de registrarse introduciendo un nombre válido, un apellido válido,  nombre de usuario repetido, una contraseña válida, se nos mostrará un mensaje en la misma pantalla, indicando que el nombre de usuario debe ser único.

H2-E3 - Registro en el sistema con contraseña inválida
Dado que no estamos aún registrados en el sistema, cuando pulsamos en el enlace para acceder el formulario de registro y le damos al botón de registrarse introduciendo un nombre válido, un apellido válido, un nombre de usuario válido, una contraseña de dos caracteres, se nos mostrará un mensaje en la misma pantalla, indicando que la contraseña debe tener una longitud mínima de tres caracteres.

### H3 - Edición de usuario
Como usuario registrado deseo que el sistema me permita modificar mis datos, para poder corregir algún dato que haya cambiado o que sea incorrecto.

Escenarios positivos

H3+E1 - Modificar nombre y apellido
Dado que estamos autenticados en el sistema como usuario, cuando pulsamos en el enlace que nos dirige hacia nuestro perfil, nos aparecerá una pantalla en la que aparecen nuestros datos  y podremos modificarlos (nombre, apellido, nombre de usuario). Modificamos nuestro nombre y nuestro apellido, y como no hemos dejado el campo en blanco, al pulsar en ‘Actualizar datos’, nuestro perfil se habrá modificado y se nos redirigirá a la pantalla anterior en la que aparecen nuestros datos ya modificados.

Escenarios negativos

H3-E1 - Modificar nombre y apellidos de forma errónea
Dado que estamos autenticados en el sistema como usuario, cuando pulsamos en el enlace que nos dirige hacia nuestro perfil, nos aparecerá una pantalla en la que aparecen nuestros datos y un botón que al pulsarlo nos lleva a una pantalla en la que podemos modificar nuestros datos (nombre, apellido y nombre de usuario). Modificamos nuestro nombre y nuestro apellido, dejando vacíos ambos campos o uno de ellos, al pulsar en guardar, nos aparecerá un mensaje en la misma pantalla en el que se nos indica que no podemos dejar esos campos vacíos.


### H4 - Mover una carta
Como jugador registrado en el sistema y que está jugando una partida, deseo que el sistema me permita mover las cartas de un mazo a otro para así poder conseguir ganar la partida.

Escenarios positivos

H4+E1 - Mover una carta de mazo principal a mazo intermedio
Dado que estamos registrados en el sistema y jugando una partida, al seleccionar la carta del mazo principal de la partida deseo poder moverla a algún mazo secundario si la lógica del 
juego lo permite.

H4+E2 - Mover una carta de mazo principal a mazo final
Dado que estamos registrados en el sistema y jugando una partida, al seleccionar la carta del mazo principal de la partida deseo poder moverla a algún mazo final si la lógica del juego lo permite.

H4+E3 - Mover una carta de mazo intermedio a mazo intermedio
Dado que estamos registrados en el sistema y jugando una partida, al seleccionar la carta de algún mazo secundario de la partida deseo poder moverla a algún otro mazo secundario si la lógica del juego lo permite.

H4+E4 - Mover una carta de mazo intermedio a mazo final
Dado que estamos registrados en el sistema y jugando una partida, al seleccionar la carta de algún mazo secundario de la partida deseo poder moverla a algún mazo final si la lógica del juego lo permite.

H4+E5 - Mostrar siguiente carta del mazo inicial
Dado que estamos registrados en el sistema y jugando una partida, al clicar sobre el mazo inicial, deseo que se me muestre la siguiente carta y la que estaba antes se coloque en la última posición del mazo.

Escenarios negativos:

H4-E1 - Mover una carta de mazo intermedio a mazo inicial
Dado que estamos registrados en el sistema y jugando una partida, al seleccionar la carta de un mazo secundario de la partida e intentar moverla al mazo inicial, no deberá dejar completar dicho movimiento ya que es un movimiento inválido.

H4-E2 - Mover una carta de mazo final a mazo inicial
Dado que estamos registrados en el sistema y jugando una partida, al seleccionar la carta de un mazo final de la partida e intentar moverla al mazo inicial, no deberá dejar completar dicho movimiento ya que es un movimiento inválido.


### H5 - Visualizar estadísticas jugador
Como jugador registrado en el sistema deseo ver mis estadísticas para saber el número de partidas que he jugado, número de partidas que he ganado y número de partidas que he perdido,para poder saber cuanto tiempo he invertido en este juego.


Escenarios positivos:

H5+E1 - Ver estadísticas estando registrado
Dado que estamos registrados en el sistema, al pulsar en el enlace del perfil, se nos redirigirá a una pantalla en la que aparecerán nuestros datos y un botón llamado estadísticas en el que al clicar en él, nos aparecerá una nueva pantalla en la que aparecen el número de partidas jugadas, número de partidas que ganadas y número de partidas perdidas.


Escenarios negativos:

H5-E1 - Ver estadísticas sin estar registrado
Dado que no estamos registrados en el sistema, no nos aparece el enlace del perfil. Si intentamos acceder a él mediante la URL, nos pedirá autenticarnos.


### H6 - Ver lista usuarios registrados
Como administrador deseo que el sistema me permita ver un listado con los usuarios registrados para saber el número de usuarios registrados y su información para poder ver quien está registrado y quien no.


Escenarios positivos:

H6+E1 - Ver lista de usuarios registrados como administrador
Dado que estamos autenticados en el sistema  como administrador, cuando pulsemos el botón lista de usuarios registrados, el sistema nos devolverá un listado con todos los usuarios registrados.


Escenarios negativos:

H6-E1 - Ver lista de usuarios registrados sin ser administrador
Dado que no estamos autenticados en el sistema como administrador, al intentar acceder al listado de usuarios registrados nos pedirá que iniciemos sesión.


### H7 - Ver lista de partidas en curso
Como administrador,deseo que el sistema me permita consultar las partidas en curso, incluyendo su creador y el usuario participante, para poder ver cuántas partidas se están jugando en ese momento

Escenarios positivos

H7+E1 - Ver lista de partidas en curso siendo administrador
Dado que estamos autenticados en el sistema como administrador,cuando pulsemos en el botón de listado de partidas en curso, el sistema nos permitirá ver la lista de partidas en curso.


Escenarios negativos

H7-E1 - Ver lista de partidas en curso sin ser administrador
Dado que no estamos autenticados en el sistema como administrador, al intentar acceder al listado de partidas en curso, el sistema nos pedirá que nos autenticamos como administrador, para luego permitirnos ver la lista de partidas en curso.


### H8 - Ver estadísticas de partidas jugadas
Como administrador deseo que el sistema me permita consultar un listado con las partidas jugadas y los usuarios relacionados a ellas, para poder ver cuántas partidas se han jugado y que usuarios las han jugado.

Escenario positivo

H8+E1 Ver lista de partidas jugadas siendo administrador
Dado que estamos autenticados en el sistema como administradores, cuando pulsemos el botón de lista de partidas jugadas el sistema nos redirigirá a un listado con todas las partidas jugadas y los jugadores relacionados a ellas.


Escenario negativo

H8-E1 Ver lista de partidas jugadas sin ser administrador
Dado que no estamos autenticados en el sistema como administradores, cuando intentemos acceder al listado de partidas jugadas el sistema nos mostrará un error en pantalla, en la que se nos informará que no tenemos suficientes permisos para acceder a ella.


### H9-Auditoría de los datos de los perfiles de usuario
Como administrador deseo saber en todo momento la edición en los datos de cualquier usuario registrado anteriormente. Se obtendrá la fecha de creación, el usuario creador, la fecha de última edición y usuario último editor.

H9+E1 - Datos de perfil de usuario accedidos correctamente
Dado que el usuario existe dentro del sistema y estoy autenticado como administrador, podría entrar en la base de datos del sistema para apreciar los usuarios que han sido 
editados y toda la información acerca de ellos.

### H10-Mostrar datos perfil de usuario
Como jugador deseo acceder a los datos de mi perfil de usuario, para poder verlos, editarlos o acceder a mis estadísticas.

Escenarios positivos

H10+E1 - Datos de perfil de usuario accedidos correctamente
Dado que el usuario está registrado en el sistema sistema y estoy autenticado con mi nombre de usuario, al hacer clic en el icono de usuario se mostrará un botón de ver perfil, el cual mostrará una página con los datos de usuario(nombre, apellidos, nombre de usuario), un botón para editarlo y otro para ver las estadísticas.

Escenarios negativos

H10-E1 - Mostrar datos de perfil de usuario sin estar registrado
Dado que el usuario no existe dentro del sistema al intentar acceder al perfil de este con una id, se mostrará una pantalla de error.



H10-E2 - Mostrar datos de perfil de usuario sin estar autenticado con el mismo id
Dado que el usuario no está registrado con los mismos datos que el perfil que quiere acceder, ya sea por url o un botón, el sistema mostrará un error.




### H11 - Finalizar partida
Como jugador registrado que está jugando una partida quiero poder finalizar una partida para que pueda terminar la partida correctamente.

Escenarios positivos

H11+E1- Finalización exitosa de una partida
Dado que estoy autenticado en el sistema como jugador y que estoy jugando una partida, cuando le dé al botón de finalizar partida quiero que se acabe la partida y me lleve a la pantalla de inicio.


Escenarios negativos

H11-E1- Finalización errónea de una partida
Dado que estoy autenticado en el sistema como un usuario, al acceder a una partida a través de la URL y darle al botón de finalizar partida, esta no finalizará dado que yo no soy el jugador asignado a esa partida.

### H12 - Actualizar usuario
Como administrador deseo que el sistema me permita actualizar los datos de los usuarios si es necesario, para tener la información más correcta posible.

Escenario positivo

H12+E1 Actualizar nombre y apellido del usuario siendo administrador
Dado que estamos autenticados en el sistema como administradores, cuando pulsamos en el enlace que nos dirige hacia el perfil del usuario, nos aparecerá una pantalla en la que aparecen los datos de los usuarios y un botón que al pulsarlo nos lleva a una pantalla en la que podemos modificar los datos (nombre, apellido, nombre de usuario). Modificamos el nombre y apellido, y como no hemos dejado el campo en blanco, al pulsar en guardar, el perfil se habrá modificado y se nos redirigirá a la pantalla anterior en la que aparecen los datos ya modificados.


Escenario negativo

H12-E1 Actualizar nombre y apellido de forma errónea siendo administrador
Dado que estamos autenticados en el sistema como administrador, cuando pulsamos en el enlace que nos dirige hacia el perfil del usuario, nos aparecerá una pantalla en la que aparecen sus datos y un botón que al pulsarlo nos lleva a una pantalla en la que podemos modificar los datos (nombre, apellido, nombre de usuario). Modificamos el nombre y nuestro apellido, dejando vacíos ambos campos o uno de ellos, al pulsar en guardar, nos aparecerá un mensaje en la misma pantalla en el que se nos indica que no podemos dejar esos campos vacíos.



### H13-Eliminar usuario
Como administrador, quiero eliminar usuarios que ya no necesiten estar en el sistema con el fin de controlar o limitar el acceso a derechos superiores

Escenarios positivos

H13+E1 - Usuario correctamente eliminado
Dado que el usuario existe todavía en el sistema y estoy autenticado como administrador, cuando le dé al botón de eliminar usuario,este será borrado del sistema, mostrando una notificación que muestra que se ha eliminado correctamente y que muestre la lista sin la fila eliminada


Escenarios negativos

H13-E1 - Usuario erróneamente eliminado
Dado que el usuario ya no existe en el sistema y estoy autenticado como administrador,no me debería salir siquiera el botón de eliminar usuario, ya que éste debería salir en la fila de cada usuario en la lista.


### H14 - Visualizar logros del  jugador
Como jugador registrado en el sistema deseo ver mis logros para saber cuáles he conseguido y cuáles no.

Escenarios positivos

H14+E1 -Visualización correcta de los logros de un jugador

Dado que estamos autenticados en el sistema como jugador, al pulsar sobre el botón de mis logros, se nos mostrará una pantalla con todos los logros disponibles, tanto los que se han conseguido como los que no.


Escenarios negativos:

H14-E1 -Visualización errónea de los logros sin estar registrado

Dado que no estamos autenticados en el sistema, al intentar acceder a la pantalla que muestra los logros de un jugador, aunque sea a través de una url, el sistema nos pedirá que nos demos de alta en el mismo para poder visualizar dicho contenido.


### H15 -Visualizar ranking de jugadores

Como jugador o administrador registrado en el sistema, deseó ver el ranking actual de jugadores para poder saber quién ha ganado más partidas, quién tiene más puntos o quién ha alcanzado más movimientos.

Escenarios positivos:

H15+E1 -Visualización correcta del ranking

Dado que estamos autenticados en el sistema, al pulsar sobre ranking, este nos mostrará varias listas ordenadas de los usuarios que más partidas han ganado, que más puntos han conseguido y que más movimientos han alcanzado.

Escenarios negativos:

H15-E1 -Visualización incorrecta del ranking

Dado que no estamos autenticados en el sistema, al intentar visualizar el ranking, este nos pedirá que nos demos de alta en el mismo para poder verlo.


### H16 - Editar un logro

Como administrador registrado en el sistema, deseo poder editar el logro para cambiar el valor que valida la obtención del logro.

Escenarios positivos:

H16+E1 - Logro editado correctamente

Dado que estamos autenticados en el sistema, al pulsar sobre editar un logro, este nos mostrará el contenido del logro y podrá ser editado correctamente.

Escenarios negativos:

H16+E1 - Logro no editado

Dado que no estamos autenticados en el sistema, no podremos editar los logros.


## Reglas de Negocio

### R1 - Carta al mazo inicial
Una carta no puede nunca ir al mazo inicial, de este mazo únicamente se permiten las acciones de pasar a la siguiente carta o mover una carta del mazo inicial a un mazo intermedio o final. 

### R2 - Colocación incorrecta mazo intermedio
Una carta no puede ir de un mazo intermedio o final  a otro mazo intermedio si la carta que está boca arriba en el mazo destino no es el número inmediatamente superior y de distinto color. Por ejemplo, no puede colocar el siete de picas sobre el seis de corazones porque el seis no es el número inmediatamente superior al siete. Otro ejemplo, no puede colocar el ocho de corazones sobre el nueve de rombos porque son del mismo color.

### R3 - Colocación incorrecta mazo final
Una carta no puede ir de un mazo intermedio a un mazo final si la carta que está boca arriba en el mazo final es de distinto palo o no es el número inmediatamente superior. Por ejemplo, no puedo colocar un tres de picas sobre un dos de corazones. Otro ejemplo, no puedo colocar un cuatro de picas sobre un dos de picas.

### R4 - Carta perdida
Cada una de las cartas del mazo general debe estar en alguno de los tres tipos de mazo que hay en la partida, es decir, no puede haber una carta que no pertenezca a algún mazo final, ni al mazo inicial, ni a algún mazo intermedio.

### R5 - Colores y palos imposibles
Los tréboles y las picas deben ser siempre negros y los corazones y diamantes deben ser siempre rojos. Por ejemplo, no puede haber un trébol rojo.

### R6- Mover grupo de cartas
Cuando mueves una carta de un mazo intermedio a otro mazo intermedio y tiene cartas encima, las cartas que están encima también se mueven con ella. Por ejemplo, si quieres mover un siete de picas sobre un ocho de diamantes y el siete de picas tiene encima un seis de corazones y un cinco de tréboles, sobre el ocho de diamantes se colocarían el siete de picas, el seis de corazones y el cinco de tréboles, en este orden.

### R7 - Ganar partida
Cuando los cuatro mazos finales están completos, esto es, tienen todas las cartas del mismo palo ordenadas de menor a mayor, la partida se dará por terminada.

### R8 - No ganar una partida
Un jugador podrá rendirse en cualquier momento de la partida si ve que no tiene más movimientos posibles.

### R9 - Partida simultánea por jugador
Un jugador no podrá jugar más de una partida de forma simultánea.En caso de que haya ya una partida abierta,se le notificará al jugador que no puede crear otra partida.

### R10 - Mazo completado no puede albergar más cartas
Un jugador no podrá poner más cartas en un mazo final que se ha completado. Al intentar albergar una carta en un mazo completado, la carta permanecerá en el mismo sitio en el que estaba.

### R11 - Posicionar carta o mazo en una columna vacía
Un jugador solo podrá colocar una carta en una columna vacía si dicha carta tiene un valor igual a 13(K)



Módulo extra desarrollado:
Módulo de estadísticas.
Módulo de logros. 
