# Simulación de Producción Concurrente con Spring Boot WebFlux y RabbitMQ en la Fábrica de Campanas de Gauss (Trabajo realizado por Juan Manuel Rodriguez, Pablo Alonso y francisco carlos lopez soto)

## Descripción del Proyecto
Este proyecto simula el proceso de fabricación de la "Máquina de la Distribución Normal", inspirada en el histórico tablero de Galton. Utilizando **Spring Boot WebFlux** y **RabbitMQ**, se modelan las estaciones de trabajo de una fábrica para demostrar conceptos de concurrencia, sincronización y programación distribuida.

El objetivo principal es visualizar cómo, a medida que las bolas caen a través del tablero, su distribución final se aproxima a una curva de campana, representando una **distribución normal**. Esta visualización se realiza en tiempo real, sincronizando los eventos de producción y ensamblaje mediante colas de mensajes.

---

## Requisitos
1. Cada estación de trabajo se implementa como un **componente independiente** en Spring Boot WebFlux.
2. **RabbitMQ** se utiliza para la sincronización entre estaciones de trabajo:
   - Asegura la producción y ensamblaje en el orden correcto.
   - Previene condiciones de carrera y deadlocks.
3. Se implementa el modelo **Productor-Consumidor**, donde:
   - Las estaciones de trabajo son los **productores**.
   - La línea de ensamblaje es el **consumidor**.
4. Un algoritmo de **scheduling personalizado** distribuye las tareas entre estaciones.
5. La programación paralela y distribuida optimiza el rendimiento del sistema.
6. La distribución de bolas se visualiza en tiempo real, aproximándose a una distribución normal.

---

## Tecnologías Utilizadas
- **Backend:**
  - Spring Boot WebFlux
  - RabbitMQ
  - Java 17+
- **Frontend:**
  - Node.js con React
  - WebSockets para actualizaciones en tiempo real
- **Infraestructura:**
  - Docker para el despliegue de RabbitMQ
  - Spring Boot Actuator para monitoreo

---

## Arquitectura del Sistema
1. **Backend (Spring Boot WebFlux):**
   - Estaciones de trabajo como servicios independientes.
   - Integración con RabbitMQ para colas de mensajes y sincronización.
   - Algoritmos de scheduling implementados para asignación eficiente.
2. **Frontend:**
   - Visualización en tiempo real utilizando gráficos dinámicos.
   - Comunicación con el backend mediante **WebSockets** y **REST APIs**.
3. **RabbitMQ:**
   - Gestión de colas para garantizar la comunicación sin conflictos entre productores y consumidores.

---

## Instrucciones de Instalación y Ejecución

### 1. Prerrequisitos
- **Java 17+**
- **Node.js** y **npm** (última versión recomendada)
- **Docker** (para RabbitMQ)
- **IDE** como IntelliJ IDEA o Visual Studio Code

### 2. Clonación del Proyecto
```bash
git clone https://github.com/tu-repositorio/campanas-de-gauss.git
cd campanas-de-gauss

