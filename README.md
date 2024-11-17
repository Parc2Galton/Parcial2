#Simulación de Producción Concurrente con Spring Boot WebFlux y RabbitMQ en la Fábrica de Campanas de Gauss (Trabajo realizado por Juan Manuel Rodriguez, Pablo Alonso y francisco carlos lopez soto)

#Descripción del Proyecto

En este proyecto, se implementa una simulación del proceso de fabricación de la "Máquina de la Distribución Normal", inspirada en el histórico tablero de Galton. Utilizando Spring Boot WebFlux y RabbitMQ, se modelan las estaciones de trabajo de una fábrica para demostrar conceptos de concurrencia, sincronización y programación distribuida.

La simulación tiene como objetivo principal mostrar cómo, a medida que las bolas caen a través del tablero, su distribución final se aproxima a una curva de campana, representando una distribución normal. Esto se visualiza en tiempo real, sincronizando los eventos de producción y ensamblaje mediante colas de mensajes.

#Requisitos
Cada estación de trabajo se implementa como un componente independiente en Spring Boot WebFlux.
RabbitMQ se utiliza para la sincronización entre estaciones de trabajo, asegurando:
Producción y ensamblaje en el orden correcto.
Prevención de condiciones de carrera y deadlocks.
La simulación sigue el modelo Productor-Consumidor, donde:
Las estaciones de trabajo son los productores.
La línea de ensamblaje es el consumidor.
Se implementa un algoritmo de scheduling personalizado para la asignación de tareas entre estaciones.
Se utiliza programación paralela y distribuida para optimizar el rendimiento del sistema.
Se visualiza en tiempo real cómo las bolas caen a través del tablero y se distribuyen en los contenedores inferiores, evidenciando la aproximación a la distribución normal.
Tecnologías Utilizadas
Backend:
Spring Boot WebFlux
RabbitMQ
Java 17+
Frontend:
Node.js con React (o cualquier framework de tu preferencia)
WebSockets para actualizaciones en tiempo real
Infraestructura:
Docker para el despliegue de RabbitMQ
Spring Boot Actuator para monitoreo
Arquitectura del Sistema
Backend (Spring Boot WebFlux):
Estaciones de trabajo como servicios independientes.
Integración con RabbitMQ para colas de mensajes y sincronización.
Algoritmos de scheduling implementados para asignación eficiente.
Frontend:
Visualización en tiempo real utilizando gráficos (por ejemplo, Chart.js o D3.js).
Comunicación con el backend mediante WebSockets y REST APIs.
RabbitMQ:
Gestión de colas para garantizar la comunicación sin conflictos entre productores y consumidores.
Instrucciones de Instalación y Ejecución
1. Prerrequisitos
Java 17+
Node.js y npm (última versión recomendada)
Docker (para RabbitMQ)
IDE como IntelliJ IDEA o Visual Studio Code
2. Clonación del Proyecto
bash
Copiar código
git clone https://github.com/tu-repositorio/campanas-de-gauss.git
cd campanas-de-gauss
3. Configuración del Backend
Levantar RabbitMQ con Docker:

bash
Copiar código
docker run -d --hostname rabbit --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
Accede a la interfaz de administración en http://localhost:15672 con:

Usuario: guest
Contraseña: guest
Configurar el Backend:

Crea un archivo application.yml en el directorio src/main/resources:
yaml
Copiar código
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
  webflux:
    base-url: http://localhost:8080
Compila y ejecuta el Backend:

bash
Copiar código
./mvnw spring-boot:run
4. Configuración del Frontend
Instalar dependencias:

bash
Copiar código
cd frontend
npm install
Configurar el archivo .env:

bash
Copiar código
REACT_APP_BACKEND_URL=http://localhost:8080
REACT_APP_WEBSOCKET_URL=ws://localhost:8080/simulation
Ejecutar el Frontend:

bash
Copiar código
npm start
Ejecución de la Simulación
Inicio:

Desde el frontend, selecciona el número de bolas y los niveles del tablero.
Presiona "Iniciar Simulación".
Producción y Ensamblaje:

Las estaciones de trabajo producen componentes y los envían a través de RabbitMQ.
La línea de ensamblaje consume los componentes y completa el producto final.
Visualización:

Observa en tiempo real cómo las bolas caen y se distribuyen en los contenedores inferiores.
La gráfica se actualiza continuamente hasta que todas las bolas hayan sido procesadas.
Rúbrica de Evaluación
Criterio	Puntos
Diseño e implementación de componentes (WebFlux)	15
Sincronización utilizando RabbitMQ	15
Problema del Productor-Consumidor	15
Modelo de memoria compartida con RabbitMQ	10
Algoritmo de scheduling personalizado	10
Programación paralela y distribuida	15
Visualización en tiempo real	15
Total	100
Consideraciones Técnicas
Concurrencia:
Utilizamos Spring Boot WebFlux para manejar múltiples solicitudes simultáneamente.
RabbitMQ asegura que los mensajes se procesen en orden y sin pérdida.
Visualización:
La aproximación a la distribución normal se visualiza en gráficos dinámicos.
Scheduling:
Algoritmo configurable (round-robin, prioridades, etc.) para optimizar la producción.
Mejoras Futuras
Incorporar un tablero de Galton físico conectado a sensores para visualización en hardware.
Implementar machine learning para predecir eficiencias en la producción.
Escalabilidad hacia microservicios con Kubernetes.
¡Disfruta explorando la simulación de las Campanas de Gauss! 🎉
