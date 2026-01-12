# 🤖 AI System Health Monitor & Auto-Incident Ticket Generator

A **real-world DevOps + AI integrated monitoring system** that continuously collects server health metrics (CPU, RAM, Disk), analyzes them using an AI model, automatically generates incident tickets when abnormal conditions are detected, and **sends email alerts for critical issues** — similar to enterprise IT monitoring tools.

This project demonstrates **backend engineering, AI integration, rule-based automation, email alerting, dashboard visualization, Docker deployment, and agent-based monitoring** — making it ideal for placement interviews.

---
# Project Banner
<img width="1536" height="1024" alt="ChatGPT Image Jan 12, 2026, 04_07_31 PM" src="https://github.com/user-attachments/assets/510ef778-5ffe-4f40-bb35-cea23c652fd3" />
## 🚀 Project Overview

Modern IT infrastructures require continuous monitoring and instant alerting.
This system simulates an **enterprise monitoring pipeline**:

1. A **Python Agent** runs on a server and collects system metrics.
2. The agent sends metrics to a **Spring Boot Backend API**.
3. Backend stores metrics in **MySQL**.
4. A **Rule Engine** determines severity.
5. If abnormal → **AI model generates incident summary**.
6. An **Incident Ticket** is automatically created.
7. **Email Alert Service** sends notifications for HIGH / CRITICAL incidents.
8. A **Web Dashboard** displays live metrics & incidents.

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
[ Email Alert Service ]
      ↓
[ HTML + Chart.js Dashboard ]
```

---

## 🧠 AI Integration

**AI Provider:** Groq Cloud
**Model Used:** `llama-3.1-8b-instant`

**Purpose:**
Generate human-readable IT incident reports automatically from raw metrics.

**Example AI Prompt**

```
Write a short IT incident report:
CPU=92%, RAM=88%, Disk=96%
```

**Example AI Output**

```
Incident Report:
Critical server resource utilization detected.
CPU and Disk usage exceeded safe thresholds.
Immediate investigation required.
```

**Fail-Safe Mode:**
If AI service is unavailable, a fallback summary is generated — ensuring system stability.

---

## 📧 Email Alert Integration

### Why Email Alerts?

In enterprise IT operations, engineers must be notified immediately when incidents occur.
This system automatically sends **email alerts** for HIGH and CRITICAL incidents.

### How It Works

1. Rule engine detects HIGH / CRITICAL condition
2. AI generates incident summary
3. Incident saved to database
4. EmailAlertService sends alert email
5. Admin receives notification instantly

### Email Trigger Rules

| Priority | Email Sent |
| -------- | ---------- |
| CRITICAL | ✅ Yes      |
| HIGH     | ✅ Yes      |
| MEDIUM   | ❌ No       |
| LOW      | ❌ No       |

### Mail Technology Used

* Spring Boot Mail Starter
* SMTP (Gmail / Outlook / Custom Mail Server)

### Email Configuration

Users can configure their own email credentials in `application.yml`:

```
spring.mail.username = sender_email@gmail.com
spring.mail.password = app_password
alert.recipient.email = receiver_email@gmail.com
```

No code changes required.

---

## ⚙️ Tech Stack

| Layer            | Technology                 |
| ---------------- | -------------------------- |
| Backend API      | Spring Boot + REST         |
| Database         | MySQL                      |
| AI Engine        | Groq LLaMA 3 API           |
| Monitoring Agent | Python (psutil + requests) |
| Frontend         | HTML + CSS + Chart.js      |
| Email Alerts     | Spring Boot Mail (SMTP)    |
| Containerization | Docker + Docker Compose    |

---

## ✨ Features

✔ Real-time CPU / RAM / Disk monitoring
✔ Automatic incident detection
✔ AI-generated incident summaries
✔ Rule-based priority engine
✔ Email alerts for critical issues
✔ MySQL persistence
✔ Live dashboard charts
✔ Fully Dockerized deployment

---

## 📂 Project Structure

```
systemmonitor/
 ├── systemmonitor/        → Spring Boot backend
 ├── agent/                → Python monitoring agent
 ├── docker-compose.yml   → Multi-container setup
 ├── README.md
 ├── .gitignore
```

---

## 🖥️ Process Flow

1️⃣ Agent collects metrics
2️⃣ Sends data to backend API
3️⃣ Backend saves metrics
4️⃣ Rule engine checks severity
5️⃣ AI generates incident summary
6️⃣ Incident ticket stored
7️⃣ Email alert triggered
8️⃣ Dashboard updates live

---

## 📊 Priority Rule Engine

| Condition                         | Priority          |
| --------------------------------- | ----------------- |
| CPU ≥ 90 OR RAM ≥ 90 OR Disk ≥ 95 | CRITICAL          |
| CPU ≥ 80 OR RAM ≥ 80 OR Disk ≥ 90 | HIGH              |
| CPU ≥ 65 OR RAM ≥ 65 OR Disk ≥ 80 | MEDIUM            |
| Else                              | LOW (No Incident) |

---

## 🐳 Docker Deployment

### Prerequisites

* Docker
* Docker Compose

### Run Everything

```
docker compose up --build
```

### Services Started

| Service      | Port                                           |
| ------------ | ---------------------------------------------- |
| Backend API  | [http://localhost:8080](http://localhost:8080) |
| MySQL DB     | localhost:3307                                 |
| Python Agent | Auto Runs                                      |
| Dashboard    | Open dashboard.html                            |

---

## 🔧 Environment Variables

```
GROQ_API_KEY=your_groq_api_key
SPRING_MAIL_USERNAME=your_email@gmail.com
SPRING_MAIL_PASSWORD=your_app_password
ALERT_RECIPIENT_EMAIL=receiver_email@gmail.com
```

---

## 🧪 API Endpoints

| Endpoint                        | Method | Description                |
| ------------------------------- | ------ | -------------------------- |
| `/api/metrics`                  | POST   | Receive metrics from agent |
| `/api/dashboard/metrics`        | GET    | View all metrics           |
| `/api/dashboard/incidents`      | GET    | View all incidents         |
| `/api/dashboard/incidents/open` | GET    | View open incidents        |

---

## 🖥️ Python Agent Standalone Run

```
cd agent
python agent.py
```

---

## 🎯 Why This Project is Placement-Ready

✔ Enterprise-style monitoring simulation
✔ AI automation integration
✔ Email alert system
✔ REST API architecture
✔ Database persistence
✔ Dockerized deployment
✔ DevOps + Backend showcase

---

## 🔮 Future Enhancements

* Slack / WhatsApp alerts
* Authentication & roles
* Grafana integration
* Kubernetes deployment
* Historical analytics
* Multi-server monitoring

---

## 👨‍💻 Author

**Seenivasan H**
MCA Student
Aspiring Java Backend & DevOps Engineer

---

## ⭐ Quick Demo

```
git clone <your-repo-url>
cd systemmonitor
docker compose up --build
Open dashboard.html in browser
```
