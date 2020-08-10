# Email API


AGREGAR LA DEFINICIÓN DE LA API.......... 


## Contenido 
---
- [Requisitos](#requisitos)
- [Estructura de proyecto](#estructura-de-proyecto)
- [Archivo de propiedades](#archivo-de-propiedades)
- [Dependencias con servicios y proyectos](#dependencias-con-servicios-y-proyectos)
	- [1.- Configuración de proyectos adicionales.](#configuracion-proyectos-adicionales)
	- [2.- Instalación de JAR's Externos.](#instalacion-jars-externos)
- [Inicio de aplicación](#inicio-de-aplicacion)
- [Validaciones de ambiente](#validaciones-ambiente)
- [Configuración](#configuracion)
	- [1.- Generar archivo de despliegue (JAR).](#generar-archivo-jar)
	- [2.- Seguridad (Inicio de sesión)](#seguridad-aplicacion)






## Requisitos 
---

  * [Visual Studio Code (1.45.1+)](https://code.visualstudio.com/)
  * [Gradle 6.3+](https://gradle.org/install/)
  * [OpenJDK 11 (OpenJ9)](https://adoptopenjdk.net/ "OpenJDK 11 (OpenJ9)")
  
  > Es importante tener en cuenta que se deben tener las variables de entorno de **Gradle** y **Java** configuradas en el equipo.
 



## Estructura de proyecto
---

El proyecto cuenta con la siguiente estructura:


 * **hb-mail-api**
	* mail
        * api
        * commons
	        * mapper
	        * vo
        * config
        * domain
	        * bean
        * facade 
        * model
	        * entity
	        * ql
	        * repository
		        * impl
        * service


 
 * **habil** *(ver apartado '[Dependencias con servicios y proyectos](#dependencias-con-servicios-y-proyectos)')*
    


## Archivo de propiedades
---
El archivo de propiedades se encuentra en la siguiente ubicación:
>  /src/main/resources/application.yml 

Este archivo contiene datos generales de la API: 

		server:
		  port: 8080
		
		spring:
		    application:
		        name: mail-api
		
		    messages:
		        encoding: UTF-8
		    jackson:
		        property-naming-strategy: SNAKE_CASE





> **Nota:** si se requiere acceso a Base de Datos, se debe de considerar agregar la conexión en este archivo.




## Dependencias con servicios y proyectos.

### 1.- Configuración de proyectos adicionales. 

* En **/hb-mail-api/mail/libs/** se encuentra el JAR *habil-0.0.1-SNAPSHOT.jar* que corresponde al proyecto de **habil** compilado con *OpenJDK 11* para el correcto funcionamiento de la API de correo. 
	

**Nota:** Para conocer cuales son las dependencias que tiene el proyecto, se puede ejecutar el siguiente comando, el cual mostrará en la consola, cuales son las dependencias con las cuenta el proyecto y la dependencia que existe entre ellas. 	

	gradle dependencies


## Inicio de aplicación.

Para iniciar la aplicación, abrir una terminal en donde se encuentren los archivos *gradlew* y *build.gradle*. 
Ejecutar los siguientes comandos: 

	gradle clean 
	
	gradle build 
	
	gradle bootRun

En la consola, se mostrará el log indicando que la aplicación ha iniciado correctamente. 




## Validaciones de ambiente. 
---
Al iniciar la aplicación web en el servidor de aplicaciones se puede validar de la siguiente manera:

* **API**  
    1. Visualización en navegador
    
        ```
        http://localhost:8080/api/mail/
        ```

    
    2. Visualización en consola
    
        ```
        Aparecerá el logo de Spring
        ```



        


## Configuración. 
---

**A continuación se detallan las consideraciones que deben tomarse en cuenta para la configuración de la aplicación:**


### 1.- Configuración inicial del proyecto.

**1.- Clonar el repositorio**
> git clone https://git.habil.mx/root/hb-mail-api.git

**2.- Ir al siguiente directorio**
> cd hb-mail-api/mail

**3.- Ejecutar los siguientes comandos:** 
> gradle clean
> 
> gradle build
> 
> gradle bootRun



### 2.- Documentación (Swagger)

La API ya cuenta con la configuración necesaria para exponer la documentación mediante Swagger, para poder consultarla y validar que se encuentra disponible, en el navegador consultar la siguiente URL: 

	http://localhost:8080/swagger-ui.html#/


### 3.- Sonarqube

Es importante tener en cuenta que la API debe ser escaneada con Sonarqube, para ello, se debe solicitar la alta del proyecto. 

Se debe solicitar acceso al Sonarqube que se encuentra en https://sonarqube-com.habil.mx/ ya que se trata de un proyecto de Java. 

Una vez que se ha configurado, entrar a la consola de Sonarqube y asegurarse que la puerta de calidad sea la de Java.   

Como resultado de la configuración del proyecto en Sonarqube se obtendrán los siguientes datos, los cuales deberán ser sustituidos en el archivo *build.gradle* ya que serán necesarios para ejecutar el análisis: 

	1. sonar.projectKey
	2. sonar.projectName


Para ejecutar el análisis, se deben ejecutar los siguientes comandos en **/hb-mail-api/mail/**:

	gradle build

	gradle jacocoTestReport
	
	gradle sonarqube


> **La siguiente URL, ayuda a colocar el estatus del análisis de Sonar en el repositorio Git:**  
> 



[![Quality Gate Status](https://sonarqube-com.habil.mx/api/project_badges/measure?project=hb-mail-api&metric=alert_status)](https://sonarqube-com.habil.mx/dashboard?id=hb-mail-api)


> **Nota:** 
> Es necesario que la plataforma Sonarqube esté disponible para que se muestre correctamente el tag del estatus.  



### 4.- Seguridad (Inicio de sesión).

Dependerá de la que se utilice.  
 
 
### 5.- Generar archivo de despliegue (JAR).

 1.- Ejecutar el siguiente comando en la ruta en **/hb-mail-api/mail/**
	> gradle build 
	 

 2.- El JAR estará disponible en **/hb-mail-api/mail/build/libs**
	 
	 




