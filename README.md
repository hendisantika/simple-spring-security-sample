# simple-spring-security-sample

[![Java CI with Maven](https://github.com/hendisantika/simple-spring-security-sample/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/simple-spring-security-sample/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.x-6DB33F?logo=springboot)
![Dependabot](https://img.shields.io/badge/Dependabot-enabled-025E8C?logo=dependabot)

A minimal **Spring Boot 4 + Spring Security 7** sample secured with **stateless JWT** authentication
(using [JJWT](https://github.com/jwtk/jjwt)) and an in-memory **H2** database.

## Tech Stack

| Component        | Version / Notes                                   |
|------------------|---------------------------------------------------|
| Java             | 25 (LTS)                                          |
| Spring Boot      | 4.1.x (Spring Security 7, Spring Data JPA, WebMVC) |
| JWT              | `io.jsonwebtoken:jjwt` 0.13.x (HS256)             |
| Database         | H2 (in-memory)                                    |
| Build            | Maven Wrapper (`./mvnw`)                          |
| Misc             | Lombok, Jackson 3                                 |

## Prerequisites

- JDK **25** (e.g. `sdk install java 25-tem`)
- No Maven installation needed — use the bundled `./mvnw`

## Getting Started

```bash
git clone https://github.com/hendisantika/simple-spring-security-sample.git
cd simple-spring-security-sample

# build & run all tests
./mvnw verify

# run the application (http://localhost:8080)
./mvnw spring-boot:run
```

## API Endpoints

| Method | Path          | Auth required | Description                                   |
|--------|---------------|---------------|-----------------------------------------------|
| POST   | `/accounts`   | No            | Register a new account (`CUSTOMER` role)      |
| POST   | `/auth/login` | No            | Authenticate and obtain a JWT access token    |
| GET    | `/user/me`    | Yes (Bearer)  | Return the currently authenticated user       |

### Example

```bash
# 1. Register
curl -X POST http://localhost:8080/accounts \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"password"}'

# 2. Login -> returns {"accessToken":"...","tokenType":"Bearer","expiresIn":7200000}
TOKEN=$(curl -s -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"user","password":"password"}' | jq -r .accessToken)

# 3. Call a protected endpoint
curl http://localhost:8080/user/me -H "Authorization: Bearer $TOKEN"
# {"username":"user","roles":["ROLE_CUSTOMER"]}
```

## Configuration

Key properties in `src/main/resources/application.properties`:

| Property                          | Description                                   |
|-----------------------------------|-----------------------------------------------|
| `app.jwt-secret`                  | HMAC secret used to sign tokens (≥ 256 bits)  |
| `app-jwt-expiration-milliseconds` | Token lifetime in milliseconds (default 2h)   |
| `spring.datasource.*`             | H2 in-memory datasource settings              |

> ⚠️ The JWT secret in this repository is for demo purposes only. Override it (e.g. via an environment
> variable `APP_JWTSECRET`) in any real deployment.

## Project Structure

```
src/main/java/id/my/hendisantika/simplespringsecuritysample
├── config/       # Security filter chain, authentication provider, password encoder
├── controller/   # AccountController, AuthController, UserController
├── entity/       # User, Customer, DTOs, Role enum
├── jwt/          # JwtTokenProvider & JwtAuthenticationFilter
├── repository/   # Spring Data JPA repositories
└── service/      # AuthService, CustomerService, UserService
```

## CI/CD & Dependency Management

- **GitHub Actions** (`.github/workflows/maven.yml`): builds and tests on JDK 25 (Temurin) for every push and
  pull request to `main`, and submits the Maven dependency graph to GitHub on pushes to `main`.
- **Dependabot** (`.github/dependabot.yml`): weekly (Monday 05:00 Asia/Jakarta) updates for Maven and GitHub
  Actions, with grouped PRs (Spring Boot, JJWT, Maven plugins, minor/patch), labels and conventional commit
  prefixes (`chore(deps)`, `ci(deps)`).

## Author

**Hendi Santika** — [s.id/hendisantika](https://s.id/hendisantika) · hendisantika@gmail.com · Telegram [@hendisantika34](https://t.me/hendisantika34)
