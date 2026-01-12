# 🤖 AI System Health Monitor & Auto-Incident Ticket Generator

A **real-world DevOps + AI integrated monitoring system** that continuously collects server health metrics (CPU, RAM, Disk), analyzes them using an AI model, and automatically generates incident tickets when abnormal conditions are detected — just like enterprise IT monitoring tools.

This project demonstrates **backend engineering, AI integration, rule-based automation, dashboard visualization, Docker deployment, and agent-based monitoring** — making it ideal for placement interviews.

---

## 🚀 Project Overview

Modern IT infrastructures require continuous monitoring.
This system simulates an **enterprise monitoring pipeline**:

1. A **Python Agent** runs on a server and collects system metrics.
2. The agent sends metrics to a **Spring Boot backend API**.
3. Backend stores metrics in **MySQL**.
4. A **Rule Engine** determines severity.
5. If abnormal → **AI model generates incident summary**.
6. An **Incident Ticket** is automatically created.
7. A **Web Dashboard** displays live metrics & incidents.

---

## 🏗️ System Architecture

```
[ Python Agent ]
      ↓ REST API
[ Spring Boot Backend ]
      ↓
[ MySQL Database ]
      ↓
[ AI Model (Groq LLaMA3) ]
      ↓
[ Incident Ticket Generator ]
      ↓
[ HTML + Chart.js Dashboard ]
```

---

## 🧠 AI Integration

This project uses **Groq Cloud AI API** with:

**Model Used:**
`llama-3.1-8b-instant`

**Purpose:**
Generate human-readable IT incident reports automatically from raw metrics.

**Example AI Prompt:**

```
Write a short IT incident report for:
CPU=92%, RAM=88%, Disk=96%
```

**Example AI Output:**

```
Incident Report: High server utilization detected.
CPU and Disk usage exceeded critical thresholds.
Immediate investigation required.
```

If AI service is unavailable, the system gracefully falls back to a basic summary — ensuring zero downtime.

---

## ⚙️ Tech Stack

| Layer            | Technology                 |
| ---------------- | -------------------------- |
| Backend API      | Spring Boot + REST         |
| Database         | MySQL                      |
| AI Engine        | Groq LLaMA 3 API           |
| Monitoring Agent | Python (psutil + requests) |
| Frontend         | HTML + CSS + Chart.js      |
| Containerization | Docker + Docker Compose    |

---

## ✨ Features

✔ Real-time CPU / RAM / Disk monitoring
✔ Automatic incident detection
✔ AI-generated incident summaries
✔ Priority rule engine (LOW → CRITICAL)
✔ MySQL persistence
✔ Live dashboard charts
✔ Fully Dockerized deployment
✔ Multi-module real-world structure

---

## 📂 Project Structure

```
systemmonitor/
 ├── systemmonitor/       → Spring Boot backend
 ├── agent/               → Python monitoring agent
 ├── docker-compose.yml  → Multi-container setup
 ├── README.md
 ├── .gitignore
```

---

## 🖥️ How It Works (Process Flow)

1️⃣ Python Agent collects system metrics every few seconds
2️⃣ Sends metrics to `/api/metrics` endpoint
3️⃣ Backend saves metrics
4️⃣ Rule engine checks thresholds
5️⃣ If abnormal → AI generates incident report
6️⃣ Incident ticket saved in database
7️⃣ Dashboard displays everything live

---

## 📊 Priority Rule Engine

| Condition                         | Priority          |
| --------------------------------- | ----------------- |
| CPU ≥ 90 OR RAM ≥ 90 OR Disk ≥ 95 | CRITICAL          |
| CPU ≥ 80 OR RAM ≥ 80 OR Disk ≥ 90 | HIGH              |
| CPU ≥ 65 OR RAM ≥ 65 OR Disk ≥ 80 | MEDIUM            |
| Else                              | LOW (no incident) |

---

## 🐳 Docker Deployment

### Prerequisites

* Docker
* Docker Compose

### Run everything with one command

```
docker compose up --build
```

### Services started

| Service      | Port                                           |
| ------------ | ---------------------------------------------- |
| Backend API  | [http://localhost:8080](http://localhost:8080) |
| MySQL DB     | localhost:3307                                 |
| Python Agent | Auto-runs                                      |
| Dashboard    | Open index.html in browser                     |

---

## 🔧 Backend Environment Variables

Set Groq API key:

```
GROQ_API_KEY=your_api_key_here
```

---

## 📈 Dashboard View

* Live CPU / RAM / Disk charts
* Metrics history table
* Incident tickets table

---

## 🧪 API Endpoints

| Endpoint                        | Method | Description                |
| ------------------------------- | ------ | -------------------------- |
| `/api/metrics`                  | POST   | Receive metrics from agent |
| `/api/dashboard/metrics`        | GET    | View all metrics           |
| `/api/dashboard/incidents`      | GET    | View all incidents         |
| `/api/dashboard/incidents/open` | GET    | View open incidents        |

---

## 🖥️ Python Agent Setup (Standalone)

If running without Docker:

```
cd agent
python agent.py
```

Agent automatically sends metrics to backend every few seconds.

---

## 🎯 Why This Project is Industry-Ready

✔ Simulates enterprise monitoring systems (Nagios, Zabbix, Datadog)
✔ Demonstrates DevOps automation
✔ AI-powered reporting
✔ Microservice-like architecture
✔ Dockerized deployment
✔ Clean REST APIs
✔ Database integration

---

## 🔮 Future Enhancements

* Email alerts on CRITICAL incidents
* User authentication
* Grafana integration
* Kubernetes deployment
* Historical analytics reports
* Multi-server monitoring

---

## 👨‍💻 Author

**Seenivasan H**
Final Year MCA Student
Aspiring Java Backend / DevOps Engineer

---

## ⭐ How to Run Quick Demo

```
git clone <your-repo-url>
cd systemmonitor
docker compose up --build
Open dashboard.html in browser
```
