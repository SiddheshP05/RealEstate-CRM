# Sai Vandan CRM

Real-estate CRM foundation for lead management, property inventory, follow-ups, site visits, and bookings.

## Stack

- Backend: Java 17, Spring Boot, Spring Data JPA, PostgreSQL
- Frontend: React, TypeScript, Vite
- Local infrastructure: Docker Compose

## Quick start

```powershell
docker compose up --build
```

Then open:

- Frontend: http://localhost:5173
- Backend API: http://localhost:8080/api/health

The backend seeds a small Sai Vandan Complex inventory and sample CRM data on startup.

## Current MVP

- Dashboard summary
- Lead list and lead creation
- Follow-up scheduling
- Follow-up calendar view
- Property inventory with availability filters
- Booking creation against available units
- JWT login with protected CRM APIs

## Local login

- Username: `admin`
- Password: `admin123`

Other local role accounts:

- `priya` / `priya123` - Sales Manager
- `sales` / `sales123` - Sales Executive
- `accounts` / `accounts123` - Accounts
- `hr` / `hr123` - HR Manager
- `purchase` / `purchase123` - Purchase Manager
- `site` / `site123` - Site Manager
- `management` / `management123` - Management

After changing backend code, rebuild the containers:

```powershell
docker compose up -d --build
```
