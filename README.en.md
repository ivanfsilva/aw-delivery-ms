<div align="center">

[![Português](https://img.shields.io/badge/Idioma-Portugu%C3%Aas-green.svg)](./README.md)
[![English](https://img.shields.io/badge/Language-English-blue.svg)](./README.en.md)
[![Español](https://img.shields.io/badge/Idioma-Espa%C3%B1ol-yellow.svg)](./README.es.md)

[![Release](https://img.shields.io/badge/release-v1.0.0-brightgreen?style=flat-square&logo=github)](../../releases/tag/v1.0.0)
![Status](https://img.shields.io/badge/status-planned-blue?style=flat-square)
![License](https://img.shields.io/badge/license-MIT-green?style=flat-square)

</div>

# 🚚 AlgaDelivery — Logistics and Delivery Microservices System

This repository contains the microservices architecture for **AlgaDelivery**, a logistics and delivery tracking system
designed to apply the fundamental concepts of distributed systems, reactive architecture, and **Domain-Driven Design (
DDD)** in practice.

The project was developed during the **Ignição de Microsserviços** training by **AlgaWorks**, combining essential
theoretical foundations with immediate hands-on code implementation, focusing on building an enterprise application step
by step with service decoupling, synchronous and asynchronous communication, resilience, and automated integration
tests.

---

## 🎯 About the System and Key Concepts

**AlgaDelivery** solves logistics domain challenges by dividing responsibilities into specialized services, isolated by
**Bounded Contexts** and using individual persistence (**Database per Service**).

The application addresses and implements:

* **Domain-Driven Design (DDD):**
    * **Strategic Design:** Subdomain mapping (Core, Supporting, Generic) and bounded contexts to ensure direct
      alignment between code and the business ubiquitous language.
    * **Tactical Design:** Implementation of rich aggregates (`Delivery`), entities (`Item`), value
      objects (`ContactPoint`), domain events, and decoupled repositories.
* **Service Communication:**
    * **Synchronous (REST):** Direct calls via `Spring RestClient` for queries and operational integrations requiring
      immediate responses.
    * **Asynchronous (Event-Driven Architecture):** Publishing and consuming domain
      events (`DeliveryPlacedEvent`, `DeliveryPickedUpEvent`, `DeliveryFulfilledEvent`) using **Apache Kafka** and *
      *Spring for Apache Kafka**.
* **Infrastructure & Resilience:**
    * Centralized routing and edge security with **Spring Cloud Gateway**.
    * Dynamic instance registration and discovery with **Netflix Eureka** (*Service Registry*).
    * Fault tolerance with **Resilience4j** and **Spring Cloud Circuit Breaker** (*Circuit Breaker* and *Retry*
      patterns).
    * Local database and messaging environment provisioning via **Docker Compose**.
* **Quality & Testing:**
    * Automated integration tests covering REST API scenarios and domain workflows with **JUnit 5** and **REST Assured
      **.

---

## 📁 Project Structure

```text
aw-delivery-ms/
├── backend/                  # Microservices source code (Spring Boot)
├── .gitignore                # Git ignore rules (Java, IDEs, Node)
├── LICENSE                   # Repository License (MIT)
├── README.md                 # Main Documentation (Portuguese)
├── README.en.md              # Documentation (English)
└── README.es.md              # Documentation (Spanish)
```

---

## 🏛️ Solution Architecture

```mermaid
graph TD
    Client[Client / Frontend] --> Gateway[Spring Cloud Gateway]
    Gateway --> Eureka[Netflix Eureka - Service Registry]
    Gateway --> DT[Delivery Tracking MS]
    Gateway --> CM[Courier Management MS]

    DT --> DB1[(PostgreSQL - Delivery DB)]
    CM --> DB2[(PostgreSQL - Courier DB)]

    DT -. Events (DeliveryPlaced / Fulfilled) .-> Kafka((Apache Kafka))
    CM -. Event Consumption & Publication .-> Kafka
```

### 📐 Domain Class Diagram (DDD)

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

    Delivery "1" *-- "1..*" Item : contains
    Delivery "1" *-- "2" ContactPoint : origin and destination
    Delivery --> DeliveryStatus : has status
```

### 🧩 Ecosystem Components

* **`Spring Cloud Gateway`:** Single point of entry for the application, responsible for routing and securing requests
  to internal services.
* **`Netflix Eureka`:** Service registry and dynamic discovery server for microservices.
* **`Delivery Tracking Service`:** Microservice responsible for delivery creation, status transitions, and detailed
  tracking.
* **`Courier Management Service`:** Microservice responsible for courier management, freight allocation, and
  availability.
* **`Apache Kafka`:** Distributed event streaming platform for asynchronous, decoupled event propagation.

---

## 🛠️ Technologies and Tools

* **Language & Frameworks:** Java 17+, Spring Boot 3+, Spring Cloud (Gateway, Eureka)
* **Architecture & Design:** Domain-Driven Design (DDD), Event-Driven Architecture (EDA)
* **Messaging & Communication:** Apache Kafka, Spring for Apache Kafka, Spring RestClient
* **Resilience:** Resilience4j, Spring Cloud Circuit Breaker
* **Data Persistence:** Spring Data JPA, PostgreSQL (Database per Service)
* **Containers & Environment:** Docker, Docker Compose
* **Automated Testing:** JUnit 5, REST Assured

<br />

<div align="center">

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=flat-square&logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=flat-square&logo=spring&logoColor=white) ![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-Gateway%20%26%20Eureka-6DB33F?style=flat-square&logo=spring&logoColor=white)  
![Apache Kafka](https://img.shields.io/badge/Apache_Kafka-Event_Driven-231F20?style=flat-square&logo=apachekafka&logoColor=white) ![Resilience4j](https://img.shields.io/badge/Resilience4j-Circuit_Breaker-red?style=flat-square)  
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-Persistence-6DB33F?style=flat-square&logo=spring&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database_per_Service-4169E1?style=flat-square&logo=postgresql&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=flat-square&logo=docker&logoColor=white)  
![JUnit 5](https://img.shields.io/badge/JUnit_5-Testing-25A162?style=flat-square&logo=junit5&logoColor=white) ![REST Assured](https://img.shields.io/badge/REST_Assured-Integration_Tests-00599C?style=flat-square)

</div>

---

## 📌 Next Steps (Roadmap)

| Version                                                                                                                | Description                                                                                                                                 |
|:-----------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------|
| [![v1.0.0](https://img.shields.io/badge/v1.0.0-Planned-blue?style=flat-square&logo=github)](../../releases/tag/v1.0.0) | Complete baseline of the AlgaDelivery training: Logistics Microservices, DDD, Kafka, Gateway, Eureka, Resilience, and REST Assured testing. |
| [![v2.0.0](https://img.shields.io/badge/v2.0.0-Future-lightgrey?style=flat-square)](../../releases/tag/v2.0.0)         | Development of a web interface (Front-end Dashboard) for real-time delivery map and status tracking.                                        |
| [![v3.0.0](https://img.shields.io/badge/v3.0.0-Future-lightgrey?style=flat-square)](../../releases/tag/v3.0.0)         | Infrastructure deployment on Kubernetes (K8s) orchestrated containers and CI/CD pipeline via GitHub Actions.                                |

---

## 🍃 About

Theory and practical project developed during the **Ignição Microsserviços** course on the 🍃 **AlgaWorks** platform.

---

## 👤 Author

Developed by **Ivan Ferreira**.

Copyright © 2026 Ivan Ferreira. All rights reserved.

---

<div align="center">

[![Back to Main README](https://img.shields.io/badge/⬅️_Back_to_Main_README-PT--BR-brightgreen?style=flat-square)](./README.md)

</div>