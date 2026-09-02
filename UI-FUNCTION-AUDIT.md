# CRM UI and dashboard audit

## Completed in this pass

- Responsive tables, cards, and modals on narrow screens
- Keyboard focus states and safer horizontal overflow
- Role-specific landing dashboards and navigation
- Live operational-record API for HR, Accounts, Purchase, Site, Management, and Admin modules
- Demo operational records for every role
- Role quick-access navigation and CSV report downloads
- Login/session recovery and automatic demo-account password synchronization

## Still required for production

- Connect each operational page to dedicated domain tables and forms instead of the shared operational-record adapter
- Add real employee, vendor, payment, payroll, purchase-order, site-visit, possession, and document CRUD screens
- Add server-side pagination, filtering, and audit history
- Replace `ddl-auto: update` with versioned database migrations
- Add email/SMS/WhatsApp notification providers
- Add production error monitoring, backups, and secret rotation

The shared operational-record API is an intentional interim layer: it makes the role pages usable and testable while the domain-specific workflows are implemented.
