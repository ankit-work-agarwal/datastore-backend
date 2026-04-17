# Datastore — Personal Data Management Backend

A **Spring Boot** REST API backend that acts as a personal data vault — like your own *MySpace* — where you can securely manage information about your family, vehicles, documents, investments, insurance, medical records, properties, bank accounts, contacts, subscriptions, and loans.

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 17 |
| Framework | Spring Boot 4.0.5 |
| Persistence | Spring Data JPA (Hibernate) |
| Database | MySQL |
| Build Tool | Maven |
| Utilities | Lombok |

---

## 📁 Project Structure

```
src/main/java/com/personal/datastore/
├── DatastoreApplication.java       # Application entry point
├── controller/                     # REST Controllers (API layer)
├── dto/                            # Data Transfer Objects (response shapes)
├── model/                          # JPA Entity classes (database tables)
├── repository/                     # Spring Data JPA Repositories
├── service/                        # Business logic
└── exception/                      # Global exception handling
```

---

## ⚙️ Configuration

In `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://<host>:3306/personal_db
spring.datasource.username=<username>
spring.datasource.password=<password>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

> **Note:** Uploaded files (e.g., document scans, medical reports) are stored locally under `src/main/resources/uploads/`.

---

## 🚀 Running the Application

```bash
# Using Maven Wrapper
./mvnw spring-boot:run

# Or on Windows
mvnw.cmd spring-boot:run
```

The application starts on **http://localhost:8080** by default.

---

## 📦 Modules & API Reference

All endpoints follow REST conventions:
- `POST /<resource>` — Create a new record
- `GET /<resource>` — Fetch all records
- `GET /<resource>?familyMemberId={id}` — Fetch records filtered by family member
- `GET /<resource>/{id}` — Fetch a single record by ID (returns 404 if not found)
- `PUT /<resource>/{id}` — Update a record by ID
- `DELETE /<resource>/{id}` — Delete a record by ID (returns 404 if not found)

All sub-entity responses (Vehicle, Document, Investment, etc.) include the linked family member's name (`ownerName`, `holderName`, `memberName`, `borrowerName`, or `addedByName`) for easy identification in standalone list views.

---

### 👨‍👩‍👧‍👦 Family Members — `/family`

Tracks all family members. Each family member is the root entity and can have associated vehicles, documents, investments, insurance policies, medical records, properties, bank accounts, contacts, subscriptions, and loans.

| Method | Endpoint | Description |
|---|---|---|
| POST | `/family` | Add a new family member |
| GET | `/family` | Get all family members (with all linked data) |
| GET | `/family/{id}` | Get a specific family member by ID |
| PUT | `/family/{id}` | Update a family member |
| DELETE | `/family/{id}` | Delete a family member |

**Family Member Fields:**
- `name`, `relation`, `phone`, `email`
- Nested: `vehicles`, `documents`, `investments`, `insurances`, `medicalRecords`, `properties`, `bankAccounts`, `contacts`, `subscriptions`, `loans`

---

### 🚗 Vehicles — `/vehicle`

Tracks vehicles owned by family members (cars, bikes, etc.).

| Method | Endpoint | Description |
|---|---|---|
| POST | `/vehicle` | Add a new vehicle |
| GET | `/vehicle` | Get all vehicles |
| GET | `/vehicle?familyMemberId={id}` | Get vehicles for a specific family member |
| GET | `/vehicle/{id}` | Get a specific vehicle by ID |
| PUT | `/vehicle/{id}` | Update a vehicle |
| DELETE | `/vehicle/{id}` | Delete a vehicle |

**Vehicle Fields:**
- `id`, `type` *(Car, Bike, etc.)*, `model`, `registrationNumber`, `ownerName` *(family member name)*

---

### 📄 Documents — `/document`

Stores important documents (Aadhaar, PAN, Passport, etc.) along with file uploads.

| Method | Endpoint | Description |
|---|---|---|
| POST | `/document/upload` | Upload a new document with file (`multipart/form-data`) |
| GET | `/document` | Get all documents |
| GET | `/document?familyMemberId={id}` | Get documents for a specific family member |
| GET | `/document/{id}` | Get a specific document by ID |
| GET | `/document/{id}/download` | Download the actual file for a document |
| PUT | `/document/{id}` | Update document metadata |
| DELETE | `/document/{id}` | Delete a document |

**Document Fields:**
- `id`, `title`, `type` *(Aadhaar, PAN, Passport, etc.)*, `filePath`, `expiryDate`, `ownerName` *(family member name)*

**Upload Request:** `multipart/form-data` with parts:
- `document` — JSON metadata
- `file` — The actual file (PDF, image, etc.)

---

### 💰 Investments — `/investment`

Manages investment records such as mutual funds, FDs, stocks, PPF, NPS, etc.

| Method | Endpoint | Description |
|---|---|---|
| POST | `/investment` | Add a new investment |
| GET | `/investment` | Get all investments |
| PUT | `/investment/{id}` | Update an investment |
| DELETE | `/investment/{id}` | Delete an investment |

**Investment Fields:**
- `id`, `type` *(MutualFund, FD, Stock, PPF, NPS, etc.)*, `name`, `institution`
- `investedAmount`, `currentValue`, `startDate`, `maturityDate`
- `holderName` *(family member name)*

---

### 🛡️ Insurance — `/insurance`

Tracks insurance policies (life, health, vehicle, home, etc.).

| Method | Endpoint | Description |
|---|---|---|
| POST | `/insurance` | Add a new insurance policy |
| GET | `/insurance` | Get all insurance policies |
| PUT | `/insurance/{id}` | Update an insurance policy |
| DELETE | `/insurance/{id}` | Delete an insurance policy |

**Insurance Fields:**
- `id`, `type` *(Life, Health, Vehicle, Home, etc.)*, `policyNumber`, `provider`
- `premiumAmount`, `sumAssured`, `startDate`, `expiryDate`
- `holderName` *(family member name)*

---

### 🏥 Medical Records — `/medical-record`

Stores health-related records like diagnoses, prescriptions, lab reports, and vaccinations.

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/medical-record` | Add a new medical record |
| GET | `/medical-record` | Get all medical records |
| PUT | `/medical-record/{id}` | Update a medical record |
| DELETE | `/medical-record/{id}` | Delete a medical record |

**Medical Record Fields:**
- `id`, `type` *(Diagnosis, Prescription, Lab Report, Vaccination, etc.)*
- `title`, `doctorName`, `hospitalName`, `recordDate`, `notes`, `filePath`
- `memberName` *(family member name)*

---

### 🏠 Properties — `/property`

Manages real estate properties owned by family members.

| Method | Endpoint | Description |
|---|---|---|
| POST | `/property` | Add a new property |
| GET | `/property` | Get all properties |
| PUT | `/property/{id}` | Update a property |
| DELETE | `/property/{id}` | Delete a property |

**Property Fields:**
- `id`, `type` *(House, Apartment, Plot, Commercial, etc.)*
- `address`, `city`, `state`, `purchaseDate`, `purchaseValue`, `currentValue`
- `isRented`, `rentalIncome`
- `ownerName` *(family member name)*

---

### 🏦 Bank Accounts — `/bank-account`

Tracks bank accounts (savings, current, FD, etc.) for family members.

| Method | Endpoint | Description |
|---|---|---|
| POST | `/bank-account` | Add a new bank account |
| GET | `/bank-account` | Get all bank accounts |
| PUT | `/bank-account/{id}` | Update a bank account |
| DELETE | `/bank-account/{id}` | Delete a bank account |

**Bank Account Fields:**
- `id`, `accountType` *(Savings, Current, FD, etc.)*, `bankName`, `accountNumber`
- `ifscCode`, `branchName`, `balance`, `openedDate`
- `ownerName` *(family member name)*

---

### 📞 Contacts — `/contact`

Stores important contacts such as doctors, lawyers, financial advisors, and emergency contacts.

| Method | Endpoint | Description |
|---|---|---|
| POST | `/contact` | Add a new contact |
| GET | `/contact` | Get all contacts |
| PUT | `/contact/{id}` | Update a contact |
| DELETE | `/contact/{id}` | Delete a contact |

**Contact Fields:**
- `id`, `name`, `category` *(Doctor, Lawyer, Financial Advisor, Emergency, etc.)*
- `phone`, `email`, `organization`, `notes`
- `addedByName` *(family member name)*

---

### 📺 Subscriptions — `/subscription`

Tracks active and inactive subscriptions (OTT, music, fitness, software, etc.).

| Method | Endpoint | Description |
|---|---|---|
| POST | `/subscription` | Add a new subscription |
| GET | `/subscription` | Get all subscriptions |
| PUT | `/subscription/{id}` | Update a subscription |
| DELETE | `/subscription/{id}` | Delete a subscription |

**Subscription Fields:**
- `id`, `name` *(Netflix, Spotify, Gym, etc.)*, `category` *(OTT, Music, Fitness, etc.)*
- `amount`, `billingCycle` *(Monthly, Quarterly, Yearly)*
- `startDate`, `renewalDate`, `isActive`, `notes`
- `ownerName` *(family member name)*

---

### 📊 Dashboard — `/dashboard`

A single endpoint that gives an at-a-glance summary of all your data.

| Method | Endpoint | Description |
|---|---|---|
| GET | `/dashboard` | Get a full summary across all modules |

**Response Fields:**

| Field | Description |
|---|---|
| `familyMembers` | Total number of family members |
| `vehicles` | Total number of vehicles |
| `documents` | Total number of documents |
| `investments` | Total number of investments |
| `insurances` | Total number of insurance policies |
| `medicalRecords` | Total number of medical records |
| `properties` | Total number of properties |
| `bankAccounts` | Total number of bank accounts |
| `contacts` | Total number of contacts |
| `subscriptions` | Total number of subscriptions |
| `loans` | Total number of loans |
| `totalInvestedAmount` | Sum of all invested amounts |
| `totalCurrentInvestmentValue` | Sum of current values of all investments |
| `totalPropertyValue` | Sum of current (or purchase) value of all properties |
| `totalOutstandingLoanAmount` | Sum of outstanding balances across all loans |
| `totalMonthlySubscriptionCost` | Monthly-equivalent cost of all active subscriptions |
| `activeSubscriptions` | Count of active subscriptions |

---



Manages loan records (home, personal, vehicle, education, business, etc.).

| Method | Endpoint | Description |
|---|---|---|
| POST | `/loan` | Add a new loan |
| GET | `/loan` | Get all loans |
| PUT | `/loan/{id}` | Update a loan |
| DELETE | `/loan/{id}` | Delete a loan |

**Loan Fields:**
- `id`, `loanType` *(Home, Personal, Vehicle, Education, Business, etc.)*
- `bankName`, `accountNumber`, `principalAmount`, `outstandingAmount`
- `interestRate`, `emiAmount`, `startDate`, `endDate`
- `status` *(Active, Closed, Overdue)*
- `borrowerName` *(family member name)*

---

## 🔒 Error Handling

All APIs return a consistent error response via `GlobalExceptionHandler`:

- **404 Not Found** — When a record with the given ID does not exist
- **500 Internal Server Error** — For unexpected failures

**Error Response Format:**
```json
{
  "timestamp": "2026-04-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Resource not found with id: 99"
}
```

---

## 🗄️ Data Model Overview

```
FamilyMember (root)
 ├── Vehicle          (type, model, registrationNumber)
 ├── Document         (title, type, filePath, expiryDate)
 ├── Investment       (type, name, institution, investedAmount, currentValue, maturityDate)
 ├── Insurance        (type, policyNumber, provider, premiumAmount, sumAssured, expiryDate)
 ├── MedicalRecord    (type, title, doctorName, hospitalName, recordDate, notes)
 ├── Property         (type, address, purchaseValue, currentValue, isRented, rentalIncome)
 ├── BankAccount      (accountType, bankName, accountNumber, ifscCode, balance)
 ├── Contact          (name, category, phone, email, organization)
 ├── Subscription     (name, category, amount, billingCycle, renewalDate, isActive)
 └── Loan             (loanType, bankName, principalAmount, outstandingAmount, emiAmount, status)
```

All sub-entities have a `ManyToOne` relationship to `FamilyMember`, and the family member fetch returns all nested data in one response.

---

## ✅ Key Design Decisions

- **DTO pattern** — All API responses use DTOs to control the shape and field order of JSON output, avoiding circular reference issues. All endpoints including `POST` consistently return DTOs.
- **Jackson `@JsonPropertyOrder`** — Applied on DTOs to guarantee consistent field ordering in responses.
- **Bean Validation** — All models carry `@NotBlank`, `@NotNull`, `@Positive`, and `@Email` constraints. Controllers use `@Valid` on request bodies. Validation failures return a structured `400 Bad Request` with per-field error messages.
- **Validation error response format:**
  ```json
  {
    "timestamp": "2026-04-17T10:00:00",
    "status": 400,
    "error": "Validation Failed",
    "fields": {
      "name": "Name is required",
      "email": "Email must be valid"
    }
  }
  ```
- **Owner name in all sub-entity DTOs** — Every sub-entity DTO exposes the linked family member's name (`ownerName`, `holderName`, `memberName`, `borrowerName`, `addedByName`), making standalone list views immediately meaningful without a separate family lookup.
- **`GET /{id}` on all modules** — Every module supports fetching a single record by ID, returning 404 if it doesn't exist.
- **Filter by family member** — All sub-entity GET endpoints support `?familyMemberId={id}` query param via Spring Data JPA derived queries (`findByOwner_Id`, `findByHolder_Id`, etc.).
- **File download endpoint** — `GET /document/{id}/download` streams the file back as an attachment using `FileSystemResource`.
- **Global Exception Handling** — Centralised via `@RestControllerAdvice` so all controllers get consistent error responses.
- **Cascade ALL on FamilyMember** — Deleting a family member also deletes all linked records.
- **File uploads** — Documents and medical records support file attachment via `multipart/form-data`; files are saved to the local `uploads/` directory.
- **Multipart config** — Max file size and request size are both set to 10MB.

