
# Reactive Gaming Platform Microservices 🎮⚡

Reactive architecture for gaming platform with JWT authentication and real-time processing using Kafka.

## 🌟 Key Features
- **API Gateway** with Spring Cloud Gateway
- **JWT Authentication** with Spring Security
- **Reactive Game Catalog** (WebFlux + R2DBC)
- **Eligibility Processing** with Kafka Streams
- **Asynchronous Communication** between services
- **Automation Scripts** for Windows (.bat)
## 🛠 Core Technologies
| Component               | Technologies                          |
|-------------------------|---------------------------------------|
| API Gateway             | Spring Cloud Gateway                 |
| Authentication          | JWT, Spring Security OAuth2          |
| Database                | PostgreSQL 15, R2DBC                 |
| Stream Processing       | Apache Kafka 3.6, Kafka Streams      |
| Reactive Programming    | Project Reactor, WebFlux             |


## 🚀 Quick Start
### ⚠️ Technical Requirements
- Java 17+
- Kafka 3.0+
- PostgreSQL 15+

# 1. Clone the repository
git clone https://github.com/DanielAntolin/reactive-gaming-platform-microservices.git
cd reactive-gaming-platform-microservices

# 2. Configure RunkfkaScript.bat
Specify the paths for zookeeper-server-start.bat and kafka-server-start.bat in the RunkfkaScript.bat file.

# 3. Run RunkfkaScript

# 4. Start each of the services




## 📁   Project Structure

reactive-gaming-platform-microservices/
├── api-gateway/          # Spring Cloud Gateway
├── auth-service/         # JWT Authentication
├── games-service/        # Reactive Catalog
├── eligibility-service/  # Kafka Processing
├── scripts/
│   └── RunKafkaScript.bat  # Kafka Management
├── docker-compose.yml    # Containerized Services
└── README.md             # Main Documentation

## 🛠 Recommended Tools
For managing and monitoring Kafka topics, we recommend using Offset Explorer. This tool provides an intuitive UI for viewing topics, partitions, offsets, and consumer groups, making it easier to debug and analyze Kafka streams efficiently.