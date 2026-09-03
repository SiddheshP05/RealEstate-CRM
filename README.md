# Sai Vandan CRM

Real-estate CRM foundation for lead management, property inventory, follow-ups, site visits, and bookings.

## Stack

- Backend: Java 17, Spring Boot, Spring Data JPA, PostgreSQL
- Frontend: React, TypeScript, Vite
- Database: PostgreSQL 16 with a persistent native data directory

## Quick start without Docker

```powershell
Set-ExecutionPolicy -Scope Process Bypass
Copy-Item .env.example .env
.\run-local.ps1
```

## Run locally with PostgreSQL

Install Java 17, Maven, Node.js, and pnpm. The project includes a native PostgreSQL setup helper; H2 is not included. PostgreSQL data is kept in the ignored `postgres-data` directory.

From PowerShell:

```powershell
Set-ExecutionPolicy -Scope Process Bypass
.\run-local.ps1
```

On the first run, `run-local.ps1` initializes the native PostgreSQL cluster and creates the database. Keep `postgres-data` and do not delete it; that directory is the permanent local database storage.

Or use two VS Code terminals:

```powershell
cd backend
mvn spring-boot:run "-Dspring-boot.run.profiles=local"
```

```powershell
cd frontend
pnpm install
pnpm run dev
```

Do not run the backend with an H2 profile. The `setup-native-postgres.ps1` script initializes and starts PostgreSQL natively on port 5432, then creates the `sai_vandan_crm` database. `run-local.ps1` calls it automatically.

Copy `.env.example` to `.env` and change the password and JWT secret before sharing or deploying. `.env` is ignored by Git.

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

The seed runner inserts hashed demo passwords and role relationships into PostgreSQL at startup. JWT authentication is stateless, so no server-side session table is required.

Other local role accounts:

- `priya` / `priya123` - Sales Manager
- `sales` / `sales123` - Sales Executive
- `accounts` / `accounts123` - Accounts
- `hr` / `hr123` - HR Manager
- `purchase` / `purchase123` - Purchase Manager
- `site` / `site123` - Site Manager
- `management` / `management123` - Management

The Docker Compose file is retained only for optional container deployment. The normal presentation startup does not use Docker.

After changing backend code, restart the backend window. To stop native PostgreSQL:

```powershell
& 'C:\Program Files\PostgreSQL\16\bin\pg_ctl.exe' -D '.\postgres-data' stop
```
