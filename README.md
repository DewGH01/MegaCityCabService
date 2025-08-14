# MegaCityCab – Java REST API (Jakarta EE on GlassFish)

Simple cab service web app.  
Frontend: HTML/CSS • Backend: Java (Jakarta EE) • App Server: GlassFish

## Prerequisites
- JDK 17+
- Maven 3.9+
- GlassFish 7+ (domain running)
- MySQL/PostgreSQL (pick one)

## Project Structure
- `src/main/java` – JAX‑RS resources, services, entities
- `src/main/resources/META-INF/persistence.xml` – JPA config (JTA)
- `frontend/` – HTML/CSS 
- `pom.xml` – WAR packaging

## Quick Start
1) Build
```bash
mvn clean package
