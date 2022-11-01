# APLICACIÓN DISTRIBUIDA SEGURA EN TODOS SUS FRENTES

Link del Video: [Video del funcionamiento](https://pruebacorreoescuelaingeduco-my.sharepoint.com/:v:/g/personal/brayan_macias_mail_escuelaing_edu_co/EblofwN9EAxOpeAbMbXwJvMBrM0V6N9HQ3yUWC_bPj9Gqw?e=4mvF44)

## REQUERIMIENTOS
Desarrolle una aplicación Web segura con los siguientes requerimientos:
- Debe permitir un acceso seguro desde el browser a la aplicación. Es decir debe garantizar autenticación, autorización e integridad de usuarios.
- Debe tener al menos dos computadores comunicacndose entre ellos y el acceso de servicios remotos debe garantizar: autenticación, autorización e integridad entre los servicios. Nadie puede invocar los servicios si no está autorizado.

## FUNCIONAMIENTO
La siguiente construcción tiene como finalidad evidenciar como asegurar los 3 aspectos claves de la seguridad: Autenticación, Autorización e Integridad, todo a través del uso seguro de HTTP, o sea, HTTPS con sus respectivos certificados.
La aplicación fue desarrollada con dos códigos fuente, el primero llamado ```UserLogin``` y el segundo llamado ```ServerLogin```. En ```UserLogin``` el usuario puede registrarse e iniciar sesión de forma segura en la plataforma, y este tiene su propio certificado HTTPS. ```ServerLogin``` contiene un mensaje que solo puede ser visto una vez el usuario se haya logueado, de manera que hay un doble proceso de inicio de sesión y autorización, desde el cliente al servidor de usuario, y desde el servidor de usuario hacia el servidor interno.
Finalmente, evidenciamos la integridad cuando la cadena del mensaje viaja cifrada a través de HTTPS entre los 2 servidores hasta el usuario y finalmente la cadena es correctamente visualizada en el browser.
El servidor de suario tiene 3 servicios:
- El usuario se puede Registrar.
  
  ![Register](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/Register.jpg)

- El usuario se puede Loguear.
  
  ![Login](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/Login.jpg)  

- El usuario puede acceder a una página de Home.
  Si el usuario intenta acceder al home sin estar autorizado, se le notificará tal acción.
  
  ![Home No Authorized](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/Home_not.jpg)
  
  Una vez el usuario se loguea, se le indica que está autorizado y se le muestra el mensaje que llega desde el servidor interno.
  
  ![Home Authorized](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/Home_yes.jpg)
  
- Si intentamos acceder a los recursos del servidor interno, veremos que estos están bloqueados, ya que es necesario un proceso de autenticación y autorización para poder acceder a las peticiones del servidor.

  ![Internal Server Locked](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/Locked.jpg)

Finalmente, destacamos que para que se pueda mantener la consistencia del proceso seguro, todas las peticiones de solo HTTP están bloquedas, de manera que solo es posible realizar peticiones a través de HTTPS únicamente.

## PRERREQUISITOS
Para la realización y ejecución tanto del programa como de las pruebas de este, se requieren ser instalados los siguientes programas:
* [Maven](https://maven.apache.org/). Herramienta que se encarga de estandarizar la estructura física de los proyectos de software, maneja dependencias (librerías) automáticamente desde repositorios y administra el flujo de vida de construcción de un software.
* [GIT](https://git-scm.com/). Sistema de control de versiones que almacena cambios sobre un archivo o un conjunto de archivos, permite recuperar versiones previas de esos archivos y permite otras cosas como el manejo de ramas (branches).
* [Docker](https://www.docker.com/). Programa encargado de crear contenedores ligeros y portables para las aplicaciones software que puedan ejecutarse en cualquier máquina con Docker instalado, independientemente del sistema operativo que la máquina tenga por debajo, facilitando así también los despliegues.
Para asegurar que el usuario cumple con todos los prerrequisitos para poder ejecutar el programa, es necesario disponer de un Shell o Símbolo del Sistema para ejecutar los siguientes comandos para comprobar que todos los programas están instalados correctamente, para así compilar y ejecutar tanto las pruebas como el programa correctamente.

Para asegurar que el usuario cumple con todos los prerrequisitos para poder ejecutar el programa, es necesario disponer de un Shell o Símbolo del Sistema para ejecutar los siguientes comandos para comprobar que todos los programas están instalados correctamente, para así compilar y ejecutar tanto las pruebas como el programa correctamente.

```
mvn -version
git --version
java -version
docker version
```

### Instalación
Para descargar el proyecto de GitHub, primero debemos clonar este repositorio, ejecutando la siguiente línea de comando en GIT.

```
git clone https://github.com/Brayandres/AREP-Secure-Distributed-Application.git
```

### Ejecución
Para compilar el proyecto utilizando la herramienta Maven, nos dirigimos al directorio donde se encuentra alojado el proyecto, y dentro de este ejecutamos en un Shell o Símbolo del Sistema el siguiente comando:

```
mvn package
```

## CÓMO CORRER EN AWS
Lo primero es crear 3 instancias generando llaves privadas propias.

![Instances](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/Instances.jpg)

Ingresamos a las máquinas a través de SSH usando las claves generadas.

para instalar Docker, utilizamos el siguiente comando:

```sudo yum install docker```

Una vez que docker se ha instalado, procedemos a iniciar el Daemon de Docker

```sudo systemctl start docker```

Para poder usar nuestros programas previamente creados, descargaremos y usremos las imágenes que los contienen desde un repositorio público de DockerHub.

![Dockerhub](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/DockerHub.jpg)

- Ahora va a ser necesario abrir los puertos correspondientes para poder acceder a los servicios en las instancias a través de sus direcciones públicas.
Así que en el apartado de "Seguridad" de cada instancia,

![SecurityGroup](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/SecurityGroupx.jpg)

- seleccionamos el grupo de seguridad de esta y nos situamos en las reglas de entrada.
Hacemos clic en editar reglas

![EditRules](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/EditRulesx.jpg)

- Agregamos una nueva regla con el puerto 4567 para TCP y libre para todos. Luego damos clic en "Guardar Reglas".

![NewRule](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/NewRulex.jpg)

Para poder mantener la seguridad al máximo posible, y con el ánimo de llevar a cabo también buenas prácticas, definimos las siguiente variables de entorno que serán leídas en tiempo de ejecución por los programas.
- En UserLogin:

  Creammos el siguiente archivo con privilegios de root user
  ```bash
    sudo su
    cat > /etc/profile.d/awsenv.sh
  ```
  Con el siguiente contenido:
  ```bash
    export AWS-SERVER-LOGIN=ec2-54-91-222-206.compute-1.amazonaws.com
    export app.secret=AREP
    export SSLKEY-TYPE=PKCS12
    export SSLKEY-PSWD=123456
    export SSLKEY-ALIAS=ecikeypair
    export TRUST-STORE-PSWD=123456
  ```

- En ServerLogin:

  creammos el siguiente archivo con privilegios de root user
  ```bash
    sudo su
    cat > /etc/profile.d/awsenv.sh
  ```
  Con el siguiente contenido:
  ```bash
    export AWS-USER-LOGIN=ec2-54-166-136-249.compute-1.amazonaws.com
    export SERVER-EMAIL=server@mail.com
    export SERVER-PSWD=SeeYouBaby
    export AWS-SERVER-LOGIN=ec2-54-91-222-206.compute-1.amazonaws.com
    export app.secret=AREP
    export SSLKEY-TYPE=PKCS12
    export SSLKEY-PSWD=123456
    export SSLKEY-ALIAS=ecikeypair
    export TRUST-STORE-PSWD=123456
  ```

Leugo reiniciamos la máquina desde el panel de AWS.
Finalmente tendremos todo nuestro sistema funcionado correctamente.

### Nota
Dentro del repositorio podremos encontrar los 2 programas, cada uno con su respectivo archivo Dockerfile.


## CERTIFICADOS
Para poder establecer una conexión segura (HTTPS) con la aplicación, garantizando autenticación, autorización e integridad de usuarios, se realiza el respectivo procedimiento de establecimiendo de certificados con llaves para cada uno de los siguientes programas. Para esto, se realizaron dos certificados, uno por cada código fuente, que son ```UserLogin``` y ```ServerLogin```.

### Pasos

Para establecer la llave en ```UserLogin```, primero se ejecutó el siguiente comando dentro de la carpeta ```keystores```, carpeta que almacena todos los certificados y llaves.

```
keytool -genkeypair -alias ecikeypair -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore ecikeystore.p12 -validity 3650
```

Luego de ejecutar el comando, se ingresaron los datos correspondientes a cada una de las preguntas para poder crear la llave. Luego de crear la llave, el resultado se obtiene tal y como se observa en la siguiente imagen.

![step1](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/Step1.jpg)

Para establecer el certificado de la llave ya creada, dentro de la misma carpeta de ```keystores``` se ejecuta el siguiente comando.

```
keytool -export -keystore ./ecikeystore.p12 -alias ecikeypair -file ecicert.cer
```

Luego de ejecutar el comando, se observa que el certificado ha sido creado satisfactoriamente para la llave en cuestión.

![step2](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/Step2.jpg)

Para crear el ```myTrustStore``` para la llave ya creada con su respectivo certificado, se ingresa el siguiente comando.

```
keytool -import -file ./ecicert.cer -alias firstCA -keystore myTrustStore
```

Luego de haber ingresado el comando, se verifica que se esté creando el ```myTrustStore``` para la llave que ha sido creada con su respectivo certificado. Luego de verificar que todos los datos retornados estén correctos, se ingresa ```yes``` para poder crear el ```myTrustStore```. Como se ve a continuación, el ```myTrustStore``` ha sido creado satisfactoriamente con su respectivo certificado.

![step3](https://github.com/Brayandres/AREP-Secure-Distributed-Application/blob/main/Evidence/Step3.jpg)

El proceso es similar para el otro certificado.
Una vez tenemos los certificados, los dejamos dentro del directorio ```.../src/main/resource/keys``` de nuestros proyectos.

## Construido en

* [Maven](https://maven.apache.org/) - Dependency Management

## Despliegue en AWS
(Link de referencia, probablemente no funcional a la hora de probarlo)

[![Deploy](https://pbs.twimg.com/profile_images/1377340526890872832/Qvi0U8pF_200x200.jpg)](https://ec2-54-166-136-249.compute-1.amazonaws.com:8443/login.html)

## Control de versiones 

[Github](https://github.com/) para el versionamiento.

## Authors

[Brayan Macías](https://github.com/brayandres) 

_Fecha : 31 de octubre del 2022_ 

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE - see the [LICENSE](LICENSE)