# Modular Web Application Firewall (WAF) & Reverse Proxy

A modular, Java-based Web Application Firewall (WAF) designed to inspect, validate, and filter incoming HTTP traffic to defend web applications against common security vulnerabilities.

## Project Overview
This application operates as a reverse proxy sitting on port `8080`. It intercepts incoming client requests, passes them through a chain of security evaluation modules, blocks malicious attempts with a `403 Forbidden` response, and forwards clean requests to the backend server.

## Features & Architecture
- **Request Inspection & Validation:** Validates incoming HTTP requests to ensure structural integrity and parameter compliance.
- **Modular Security Filters:** Extensible security chain supporting rules for SQL Injection (SQLi), Cross-Site Scripting (XSS), Rate Limiting, and Custom Rules.
- **HTTP Reverse Proxying:** Transparently forwards verified traffic to target backend services.
- **Real-Time Logging:** Logs request paths, statuses, and module-specific interception events to stdout.

## Tech Stack
- **Language:** Java (JDK 11+)
- **Networking:** `com.sun.net.httpserver`
- **Architecture:** Object-Oriented Security Module Pipeline & Interception Middleware

## Technical Documentation
*Full project report and architectural documentation are available upon request.*

## Team & Credits
Developed collaboratively as a group project:
Nikos Gkolfinos: SQL Injection
Eleni Zoga: Rate Limiting
Pangiotis Vidalis: XSS Module
