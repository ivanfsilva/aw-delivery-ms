<div align="center">

[![Português](https://img.shields.io/badge/Idioma-Portugu%C3%Aas-green.svg)](./README.md)
[![English](https://img.shields.io/badge/Language-English-blue.svg)](./README.en.md)
[![Español](https://img.shields.io/badge/Idioma-Espa%C3%B1ol-yellow.svg)](./README.es.md)

[![Release](https://img.shields.io/badge/release-v1.0.0-brightgreen?style=flat-square&logo=github)](../../releases/tag/v1.0.0)
![Status](https://img.shields.io/badge/status-planejado-blue?style=flat-square)
![License](https://img.shields.io/badge/license-MIT-green?style=flat-square)

</div>

# 🚚 AlgaDelivery — Sistema de Logística e Entregas em Microsserviços

Este repositório contém a arquitetura de microsserviços do **AlgaDelivery**, um sistema de logística e rastreamento de
entregas projetado para aplicar na prática os conceitos fundamentais de sistemas distribuídos, arquitetura reativa e *
*Domain-Driven Design (DDD)**.

O projeto foi desenvolvido ao longo do treinamento **Ignição de Microsserviços** da **AlgaWorks**, que alinha o
embasamento teórico essencial à implementação prática imediata no código, focando na construção gradual de uma aplicação
corporativa com desacoplamento de serviços, comunicação síncrona e assíncrona, resiliência e testes de integração
automatizados.

---

## 🎯 Sobre o Sistema e Conceitos-Chave

O **AlgaDelivery** resolve os desafios do domínio de logística dividindo responsabilidades em serviços especializados,
isolados por contextos delimitados (*Bounded Contexts*) e com persistência de dados própria (*Database per Service*).

A aplicação aborda e implementa:

* **Domain-Driven Design (DDD):**
    * **Design Estratégico:** Mapeamento de subdomínios (Core, Suporte, Genérico) e contextos delimitados para garantir
      alinhamento direto entre o código e a linguagem ubíqua do negócio.
    * **Design Tático:** Implementação de agregados ricos (`Delivery`), entidades (`Item`), objetos de
      valor (`ContactPoint`), eventos de domínio e repositórios desacoplados.
* **Comunicação entre Serviços:**
    * **Síncrona (REST):** Chamadas diretas via `Spring RestClient` para consultas e integrações operacionais que exigem
      resposta imediata.
    * **Assíncrona (Event-Driven Architecture):** Publicação e consumo de eventos de
      domínio (`DeliveryPlacedEvent`, `DeliveryPickedUpEvent`, `DeliveryFulfilledEvent`) utilizando **Apache Kafka** e *
      *Spring for Apache Kafka**.
* **Infraestrutura e Resiliência:**
    * Roteamento centralizado e segurança de borda com **Spring Cloud Gateway**.
    * Descoberta e registro dinâmico de instâncias com **Netflix Eureka** (*Service Registry*).
    * Tolerância a falhas com **Resilience4j** e **Spring Cloud Circuit Breaker** (padrões *Circuit Breaker* e *Retry*).
    * Provisionamento do ambiente local de banco de dados e mensageria via **Docker Compose**.
* **Qualidade e Testes:**
    * Testes de integração automatizados cobrindo os cenários das REST APIs e fluxos de domínio com **JUnit 5** e **REST
      Assured**.

---

## 🏛️ Arquitetura da Solução

```mermaid
graph TD
    Client[Cliente / Frontend] --> Gateway[Spring Cloud Gateway]
    Gateway --> Eureka[Netflix Eureka - Service Registry]
    Gateway --> DT[Delivery Tracking MS]
    Gateway --> CM[Courier Management MS]

    DT --> DB1[(PostgreSQL - Delivery DB)]
    CM --> DB2[(PostgreSQL - Courier DB)]

    DT -. Eventos (DeliveryPlaced / Fulfilled) .-> Kafka((Apache Kafka))
    CM -. Consumo & Publicação de Eventos .-> Kafka
```

### 🧩 Componentes do Ecossistema

* **`Spring Cloud Gateway`:** Ponto único de entrada da aplicação, encarregado de redirecionar e proteger as requisições
  para os serviços internos.
* **`Netflix Eureka`:** Servidor de registro e descoberta dinâmica de instâncias dos microsserviços.
* **`Delivery Tracking Service`:** Microsserviço responsável pela criação, transições de status e acompanhamento
  detalhado das entregas.
* **`Courier Management Service`:** Microsserviço responsável pela gestão de entregadores, alocação de fretes e
  disponibilidade.
* **`Apache Kafka`:** Barramento distribuído para propagação de eventos assíncronos e desacoplados.

---

## 🛠️ Tecnologias e Ferramentas

* **Linguagem & Frameworks:** Java 17+, Spring Boot 3+, Spring Cloud (Gateway, Eureka)
* **Arquitetura & Design:** Domain-Driven Design (DDD), Event-Driven Architecture (EDA)
* **Mensageria & Comunicação:** Apache Kafka, Spring for Apache Kafka, Spring RestClient
* **Resiliência:** Resilience4j, Spring Cloud Circuit Breaker
* **Persistência de Dados:** Spring Data JPA, PostgreSQL (Database per Service)
* **Containers & Ambiente:** Docker, Docker Compose
* **Testes Automatizados:** JUnit 5, REST Assured

<br />

<div align="center">

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=flat-square&logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=flat-square&logo=spring&logoColor=white) ![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-Gateway%20%26%20Eureka-6DB33F?style=flat-square&logo=spring&logoColor=white)  
![Apache Kafka](https://img.shields.io/badge/Apache_Kafka-Event_Driven-231F20?style=flat-square&logo=apachekafka&logoColor=white) ![Resilience4j](https://img.shields.io/badge/Resilience4j-Circuit_Breaker-red?style=flat-square)  
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-Persistence-6DB33F?style=flat-square&logo=spring&logoColor=white) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database_per_Service-4169E1?style=flat-square&logo=postgresql&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=flat-square&logo=docker&logoColor=white)  
![JUnit 5](https://img.shields.io/badge/JUnit_5-Testing-25A162?style=flat-square&logo=junit5&logoColor=white) ![REST Assured](https://img.shields.io/badge/REST_Assured-Integration_Tests-00599C?style=flat-square)

</div>

---

## 📌 Próximos Passos (Roadmap)

| Versão                                                                                                                   | Descrição                                                                                                                                       |
|:-------------------------------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------------------------|
| [![v1.0.0](https://img.shields.io/badge/v1.0.0-Planejado-blue?style=flat-square&logo=github)](../../releases/tag/v1.0.0) | Baseline completo do treinamento AlgaDelivery: Microsserviços de Logística, DDD, Kafka, Gateway, Eureka, Resiliência e Testes com REST Assured. |
| [![v2.0.0](https://img.shields.io/badge/v2.0.0-Futuro-lightgrey?style=flat-square)](../../releases/tag/v2.0.0)           | Desenvolvimento de uma interface web (Dashboard Front-end) para acompanhamento do mapa e status de entregas em tempo real.                      |
| [![v3.0.0](https://img.shields.io/badge/v3.0.0-Futuro-lightgrey?style=flat-square)](../../releases/tag/v3.0.0)           | Implantação da infraestrutura em contêineres orquestrados via Kubernetes (K8s) e esteira de CI/CD via GitHub Actions.                           |

---

## 🍃 Sobre

Teoria e projeto prático desenvolvido durante o curso **Ignição Microsserviços** da plataforma 🍃 **AlgaWorks**.

---

## 👤 Autor

Desenvolvido por **Ivan Ferreira**.

Copyright © 2026 Ivan Ferreira. Todos os direitos reservados.