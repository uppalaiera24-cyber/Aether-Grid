# ⚡ AetherGrid
### Intelligent Energy-Aware Workload Scheduling Platform

AetherGrid is a Java-based platform designed to intelligently schedule computing workloads in data centres by analysing workload priorities, deadlines, resource availability, electricity costs, and carbon intensity.

Its rule-based Intelligent Decision Agent determines whether workloads should execute immediately, wait for a better energy window, or be rescheduled, helping optimise resource utilisation, reduce electricity costs, and minimise estimated carbon emissions.

---

## 📌 Problem Statement

Modern data centres handle multiple computing workloads that compete for resources such as CPU, RAM, and GPU.

However, electricity prices and carbon intensity fluctuate over time, while different workloads have different priorities and deadlines.

Traditional scheduling approaches may not adequately consider these changing energy conditions.

AetherGrid aims to address this challenge through intelligent, energy-aware workload scheduling.

## 🎯 Project Objectives

- Optimise workload scheduling based on priority and deadlines.
- Monitor compute node resource availability.
- Consider electricity tariffs and carbon intensity while scheduling.
- Allocate CPU, RAM, and GPU efficiently.
- Reduce estimated electricity costs and carbon emissions.
- Maintain workload execution history and analytics.

## 🧠 Key Features

- **Workload Management:** Submit and manage computing workloads.
- **Energy-Aware Scheduling:** Consider electricity costs and carbon intensity.
- **Intelligent Decision Agent:** Make rule-based scheduling decisions.
- **Resource Allocation:** Manage CPU, RAM, and GPU allocation.
- **Job Monitoring:** Track workload execution and status.
- **Energy Analytics:** Estimate energy consumption, electricity costs, and carbon emissions.
- **Reporting & Audit:** Maintain scheduling records and system reports.

## 🏗️ System Modules

1. User & Authentication Management
2. Data Centre & Infrastructure Management
3. Workload Management
4. Energy & Grid Monitoring
5. Scheduling Engine
6. Intelligent Decision Agent
7. Resource Allocation
8. Job Execution & Monitoring
9. Energy & Carbon Analytics
10. Reporting & Audit

## ⚙️ Technology Stack

| Technology | Purpose |
|---|---|
| Java | Core application development |
| JDBC | Database connectivity |
| MySQL | Data storage |
| OOP | Modular application design |
| Java Collections | Workload and resource management |
| Multithreading | Concurrent workload execution |

## 🏛️ Architecture

AetherGrid follows a **Modular Monolithic Architecture**.

The application is developed as a single Java application, internally organised into independent functional modules with clearly defined responsibilities.

Each module communicates with other modules through appropriate classes, interfaces, and services.

## 🔄 How It Works

1. A user logs into the platform and submits a workload.
2. The system validates the workload and stores its details.
3. The Scheduling Engine analyses workload priority, deadlines, available resources, and energy conditions.
4. The Intelligent Decision Agent evaluates the available information and makes a scheduling decision.
5. The Resource Allocation module reserves the required computing resources.
6. The Job Execution module simulates workload execution and monitors its status.
7. Energy Analytics estimates electricity consumption, costs, and carbon emissions.
8. Reports and audit records are generated.

## 🗄️ Database

AetherGrid uses MySQL to maintain information related to:

- Users and roles
- Data centres, clusters, and compute nodes
- Workloads and scheduling policies
- Grid conditions and scheduling decisions
- Resource allocations and job executions
- Energy records and audit logs

The proposed database design consists of 13 tables connected through primary key and foreign key relationships.

## 🚀 Current Implementation Scope

The initial academic implementation focuses on:

- Java-based workload scheduling.
- Simulated data centre infrastructure and compute nodes.
- Predefined or manually entered electricity and carbon-intensity data.
- Rule-based scheduling decisions.
- Simulated workload execution.
- Estimated energy and carbon analytics.

Real-time electricity APIs and actual cloud infrastructure integration are potential future enhancements.

## 🔮 Future Scope

- Integration with real-time electricity tariff and grid-intensity APIs.
- Integration with cloud infrastructure.
- Kubernetes integration for actual container orchestration.
- Advanced energy-aware scheduling algorithms.
- Enhanced workload monitoring and analytics.

## 👥 Project Team

**Developed by:** Divya Bindra, Aiera uppal, Avni Singh

**Academic Project:** B.E. Computer Science & Engineering (AI & Future Technologies)

## 📄 License

This project is developed for academic and educational purposes.
