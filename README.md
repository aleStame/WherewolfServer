# wherewolfserver

Spring Boot 3 + Gradle + PostgreSQL

## Variabili ambiente su Render
- DATABASE_URL=postgres://user:pass@host:port/dbname

## Esecuzione locale
```bash
./gradlew bootRun
```

## REST API di test
- GET /utenti -> lista utenti
- POST /utenti -> crea utente (JSON {"username":"x","password":"y"})
- DELETE /utenti/{username} -> elimina utente
```