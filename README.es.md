<div align="center">

[![Português](https://img.shields.io/badge/Idioma-Portugu%C3%Aas-green.svg)](./README.md)
[![English](https://img.shields.io/badge/Language-English-blue.svg)](./README.en.md)
[![Español](https://img.shields.io/badge/Idioma-Espa%C3%B1ol-yellow.svg)](./README.es.md)

[![Release](https://img.shields.io/badge/release-v1.0.0-brightgreen?style=flat-square&logo=github)](../../releases/tag/v1.0.0)
![Status](https://img.shields.io/badge/status-planificado-blue?style=flat-square)
![License](https://img.shields.io/badge/license-MIT-green?style=flat-square)

</div>

# 🚚 AlgaDelivery — Sistema de Logística y Envíos en Microservicios

Este repositorio contiene la arquitectura de microservicios de **AlgaDelivery**, un sistema de logística y seguimiento
de envíos diseñado para aplicar en la práctica los conceptos fundamentales de sistemas distribuidos, arquitectura
reactiva y **Domain-Driven Design (DDD)**.

El proyecto fue desarrollado durante la capacitación **Ignição de Microsserviços** de **AlgaWorks**, alineando la base
teórica esencial con la implementación práctica inmediata en código, enfocándose en la construcción gradual de una
aplicación empresarial con desacoplamiento de servicios, comunicación síncrona y asíncrona, resiliencia y pruebas de
integración automatizadas.

---

## 🎯 Sobre el Sistema y Conceptos Clave

**AlgaDelivery** resuelve los desafíos del dominio de logística dividiendo responsabilidades en servicios
especializados, aislados por contextos delimitados (*Bounded Contexts*) y con persistencia de datos propia (*Database
per Service*).

La aplicación aborda e implementa:

* **Domain-Driven Design (DDD):**
    * **Diseño Estratégico:** Mapeo de subdominios (Core, Soporte, Genérico) y contextos delimitados para garantizar
      alineación directa entre el código y el lenguaje ubicuo del negocio.
    * **Diseño Táctico:** Implementación de agregados ricos (`Delivery`), entidades (`Item`), objetos de
      valor (`ContactPoint`), eventos de dominio y repositorios desacoplados.
* **Comunicación entre Servicios:**
    * **Síncrona (REST):** Llamadas directas vía `Spring RestClient` para consultas e integraciones operativas que
      requieren respuesta inmediata.
    * **Asíncrona (Event-Driven Architecture):** Publicación y consumo de eventos de
      dominio (`DeliveryPlacedEvent`, `DeliveryPickedUpEvent`, `DeliveryFulfilledEvent`) utilizando **Apache Kafka** y *
      *Spring for Apache Kafka**.
* **Infraestructura y Resiliencia:**
    * Enrutamiento centralizado y seguridad de borde con **Spring Cloud Gateway**.
    * Descubrimiento y registro dinámico de instancias con **Netflix Eureka** (*Service Registry*).
    * Tolerancia a fallos con **Resilience4j** y **Spring Cloud Circuit Breaker** (patrones *Circuit Breaker* y
      *Retry*).
    * Aprovisionamiento del entorno local de base de datos y mensajería mediante **Docker Compose**.
* **Calidad y Pruebas:**
    * Pruebas de integración automatizadas cubriendo los escenarios de las REST APIs y flujos de dominio con **JUnit 5**
      y **REST Assured**.

---

## 📁 Estructura del Proyecto

```text
aw-delivery-ms/
├── backend/                  # Código fuente de los microservicios (Spring Boot)
├── .gitignore                # Reglas de ignorados de Git (Java, IDEs, Node)
├── LICENSE                   # Licencia del repositorio (MIT)
├── README.md                 # Documentación principal (Portugués)
├── README.en.md              # Documentation (Inglés)
└── README.es.md              # Documentación (Español)
```

---

## 🏛️ Arquitectura de la Solución

```mermaid
graph TD
    Client[Cliente / Frontend] --> Gateway[Spring Cloud Gateway]
    Gateway --> Eureka[Netflix Eureka - Service Registry]
    Gateway --> DT[Delivery Tracking MS]
    Gateway --> CM[Courier Management MS]

    DT --> DB1[(PostgreSQL - Delivery DB)]
    CM --> DB2[(PostgreSQL - Courier DB)]

    DT -. Eventos (DeliveryPlaced / Fulfilled) .-> Kafka((Apache Kafka))
    CM -. Consumo y Publicación de Eventos .-> Kafka
```

### 📐 Diagrama de Clases del Dominio (DDD)

```mermaid
classDiagram
    class Delivery {
        +UUID id
        +ContactPoint sender
        +ContactPoint recipient
        +DeliveryStatus status
        +BigDecimal totalAmount
        +List~Item~ items
        +OffsetDateTime createdAt
        +place()
        +pickUp()
        +fulfill()
    }

    class Item {
        +UUID id
        +String name
        +Integer quantity
        +BigDecimal unitPrice
    }

    class ContactPoint {
        +String name
        +String phone
        +String address
        +String zipCode
    }

    class DeliveryStatus {
        <<enumeration>>
        DRAFT
        PLACED
        PICKED_UP
        FULFILLED
        CANCELLED
    }

    Delivery "1" *-- "1..*" Item : contiene
    Delivery "1" *-- "2" ContactPoint : origen y destino
    Delivery --> DeliveryStatus : posee estado
```

### 🧩 Componentes del Ecosistema

* **`Spring Cloud Gateway`:** Punto único de entrada de la aplicación, encargado de redirigir y proteger las peticiones
  hacia los servicios internos.
* **`Netflix Eureka`:** Servidor de registro y descubrimiento dinámico de instancias de microservicios.
* **`Delivery Tracking Service`:** Microservicio responsable de la creación, transiciones de estado y seguimiento
  detallado de envíos.
* **`Courier Management Service`:** Microservicio responsable de la gestión de repartidores, asignación de fletes y
  disponibilidad.
* **`Apache Kafka`:** Bus distribuido para la propagación de eventos asíncronos y desacoplados.

---

## 🛠️ Tecnologías y Herramientas

* **Lenguaje y Frameworks:** Java 17+, Spring Boot 3+, Spring Cloud (Gateway, Eureka)
* **Arquitectura y Diseño:** Domain-Driven Design (DDD), Event-Driven Architecture (EDA)
* **Mensajería y Comunicación:** Apache Kafka, Spring for Apache Kafka, Spring RestClient
* **Resiliencia:** Resilience4j, Spring Cloud Circuit Breaker
* **Persistencia de Datos:** Spring Data JPA, PostgreSQL (Database per Service)
* **Contenedores y Entorno:** Docker, Docker Compose
* **Pruebas Automatizadas:** JUnit 5, REST Assured

<br />

<div align="center">

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=flat-square&logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=flat-square&logo=spring&logoColor=white) ![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-Gateway%20%26%20Eureka-6DB33F?style=flat-square&logo=spring&logoColor=white)  
![Apache Kafka](https://img.shields.io/badge/Apache_Kafka-Event_Driven-231F20?style=flat-square&logo=apachekafka&logoColor=white) ![Resilience4j](https://img.shields.io/badge/Resilience4j-Circuit_Breaker-red?style=flat-square)  
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-Persistence-6DB33F?style=flat-square&logo=spring&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database_per_Service-4169E1?style=flat-square&logo=postgresql&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=flat-square&logo=docker&logoColor=white)  
![JUnit 5](https://img.shields.io/badge/JUnit_5-Testing-25A162?style=flat-square&logo=junit5&logoColor=white) ![REST Assured](https://img.shields.io/badge/REST_Assured-Integration_Tests-00599C?style=flat-square)

</div>

---

## 📌 Próximos Pasos (Hoja de Ruta)

| Versión                                                                                                                    | Descripción                                                                                                                                            |
|:---------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![v1.0.0](https://img.shields.io/badge/v1.0.0-Planificado-blue?style=flat-square&logo=github)](../../releases/tag/v1.0.0) | Línea base completa de la capacitación AlgaDelivery: Microservicios de Logística, DDD, Kafka, Gateway, Eureka, Resiliencia y Pruebas con REST Assured. |
| [![v2.0.0](https://img.shields.io/badge/v2.0.0-Futuro-lightgrey?style=flat-square)](../../releases/tag/v2.0.0)             | Desarrollo de una interfaz web (Dashboard Front-end) para el seguimiento del mapa y estado de envíos en tiempo real.                                   |
| [![v3.0.0](https://img.shields.io/badge/v3.0.0-Futuro-lightgrey?style=flat-square)](../../releases/tag/v3.0.0)             | Despliegue de la infraestructura en contenedores orquestados mediante Kubernetes (K8s) y flujo de CI/CD vía GitHub Actions.                            |

---

## 🍃 Sobre

Teoría y proyecto práctico desarrollado durante el curso **Ignição Microsserviços** de la plataforma 🍃 **AlgaWorks**.

---

## 👤 Autor

Desarrollado por **Ivan Ferreira**.

Copyright © 2026 Ivan Ferreira. Todos los derechos reservados.

---

<div align="center">

[![Volver al README Principal](https://img.shields.io/badge/⬅️_Volver_al_README_Principal-PT--BR-brightgreen?style=flat-square)](./README.md)

</div>