## Individual Sprint 1

**React UI → Express REST API → Prisma ORM → PostgreSQL DB**

**Sprint Goal:** Build a tiny but complete vertical slice proving that every layer of the technology stack works well.

## 1. What We Are Building Across Labs 1 to 4

TokTickIT is an IT service desk application for Account and Access, Hardware, Software, and Network requests. Across seven individual sprints, each student will incrementally build the same product from a full-stack foundation into a polished local web application.

The instructor acts as the stakeholder and product owner, releasing a new engineering contract for each sprint. Every contract defines the required behavior, UI, business rules, acceptance criteria, and tests. Students use AI coding agents to assist with implementation, but remain responsible for the specifications, code, tests, reviews, and final product quality.

- **Specification:** Defines the product behavior and design.
- **Engineering contract:** Defines the specification plus the evidence required to prove it is complete.

The final application will support three roles: Requester, IT Staff, and Administrator. A Ticket stores the current state of the request and contains related Public Comments, Internal Notes, Actions Taken, and Attachments. Requesters and IT Staff share some functions, such as public comments and attachments, while role-based rules control sensitive actions such as assignment, IT priority, status changes, internal notes, and user management.

By the end of Lab 4, every student should have a professional application running locally with responsive screens, consistent UI styling, clear validation and warnings, safe error handling, automated tests, GitHub Issues, feature branches, peer-reviewed Pull Requests, and complete documentation. CI/CD and cloud deployment will be introduced later during the team-project phase.

### 1.1. User Roles and Role-Specific Actions

Each user has one user role. There are three user roles: Requester, IT Staff, and Administrator. The Administrator role includes all IT Staff permissions in addition to user and reference-data management permissions.

#### Requester, IT Staff, and Administrator can all

- View permitted ticket information
- Add public comments
- Add attachments

#### Requester can

- Create a ticket
- View their own tickets
- Set Requested Priority
- Respond to IT Staff
- Confirm or reject a resolution
- Request reopening

#### IT Staff and Administrator can both

- Become or change the Ticket Owner
- Set IT Priority
- Change ticket status
- Add internal notes
- Add and update actions taken
- Resolve and close tickets

#### Administrator can

- View and search users
- Create user accounts
- Edit user details
- Assign a user role
- Activate or deactivate accounts
- Set a new initial password
- Manage Categories and Related Systems

### 1.2. Final Ticket Model

**Parent entity:** Ticket  
**Number of child entities:** 5

```text
Ticket
├── Public Comments
├── Internal Notes
├── Actions Taken
└── Attachments
```

The Ticket header stores the current state, including:

- Ticket Number
- Requester
- Category
- Related System
- Summary and Description
- Requested Priority
- IT Priority
- Current Status
- Ticket Owner
- Important Dates
- Resolution Summary

### 1.3. Final Main Screens

- Login
- First Password Change
- Requester Dashboard
- Create Ticket
- Requester Ticket Detail
- IT Staff Dashboard
- IT Staff Ticket Detail
- User Management
- Reference Data Management
- Access Denied
- Not Found

### 1.4. Final Quality Expectations

The completed local application should include:

- Responsive desktop, tablet, and mobile layouts
- Consistent color theme
- Defined styles for labels, textboxes, buttons, grids, badges, and validation
- Editable and non-editable field styles
- Loading, empty, success, warning, and error states
- Clear field-level validation
- Unit, API, UI, and E2E tests
- GitHub Issues, feature branches, Pull Requests, and peer review
- Complete setup and usage documentation

### 1.5. Example UI Screen

This screen is provided as illustration only. For Lab 1, do not implement any UI yet.

## 2. Lab 1 Learning Outcomes

- Build a full-stack vertical slice: React/Vite/Bootstrap UI → Express API → Prisma/PostgreSQL.
- Implement REST endpoints, automated testing (Vitest/Supertest), and Git Flow.
- Utilize GitHub Projects for task tracking and peer-reviewed Pull Requests.

## 3. Lab 1 Request from Stakeholder

Here, the stakeholder refers to the product owner, customer, or end user requesting this application.

> “Before developing features for this ticketing app, the IT department wants evidence that the proposed technology stack works as one integrated system. Create a basic TokTickIT application that displays the service status and the supported request categories stored in the database.”

### 3.1. Required Working Result

At the end of this lab, opening the frontend in a browser must show the app name and a **[Check System]** button that, when clicked, shows the system status and the four request categories.

- Backend status: **Online**, based on a real API call for health check.
- The four supported categories loaded from PostgreSQL through the backend via API call.
- A loading state (hour glass … “loading”) while data is being requested.
- A useful error message when the backend or database is unavailable.

```text
TokTickIT   IT Service Desk

[Check System]

System Status: Online

Supported Request Categories:
• Account and Access
• Hardware
• Software
• Network
```

## 4. Technology and Scope Constraints

| Area         | Required choice                                                                              |
| ------------ | -------------------------------------------------------------------------------------------- |
| Frontend     | React + TypeScript + Vite + Bootstrap                                                        |
| Backend      | Node.js + Express + TypeScript                                                               |
| Database     | PostgreSQL + Prisma                                                                          |
| Architecture | REST-style APIs                                                                              |
| Testing      | Vitest and Supertest in Lab 1                                                                |
| Workflow     | Git, GitHub Projects, four Issues, `main`/`dev`/feature branches, Pull Requests, peer review |

Do not substitute another framework, database, ORM, or UI library. Playwright, authentication, ticket creation, and image upload will be introduced in later labs.

## 5. IDE Use

For this course, students will use a VS Code-based IDE that includes an integrated AI coding assistant, most likely Antigravity, subject to the availability of Google Cloud Platform support for the course.

Many development tasks may be completed with assistance from the IDE agent, including project setup, Git operations, coding, debugging, documentation, and preparation of detailed SDD and TDD drafts. Students are expected to use an AI agent / LLM to help make specifications, acceptance criteria, and test scenarios more complete.

However, the AI agent is an assistant, not the owner of the work. Students must review, correct, and approve all generated specifications, tests, code, commands, and Git operations. They must understand each step of the workflow, know what they are asking the agent to do, and be able to explain the resulting work. You may delegate execution to the IDE agent, but you may not delegate understanding, judgment, or responsibility.

## 6. Lab 1 Execution Steps

1. **Setup:** Find a peer reviewer, create the GitHub repository, and initialize `main` and `lab1-staging` branches.
2. **Planning:** Create the GitHub Project board and the four Issues (Section 7) in the Backlog.
3. **Git Flow via the git branch structure:** Use `main` as the stable release (production) branch and `lab1-staging` as the Lab 1 integration branch. Create `lab1-staging` from `main` and push it to GitHub. Do not develop directly on `main` or `lab1-staging`.
4. **Create the GitHub Project:** Create a GitHub Project named **TokTickIT Individual Sprints**. Use a Board view with these statuses in this exact order:

   `Backlog`, `Specified`, `Started`, `PR Review`, `Fixing`, `Done`

   A status moves from `PR Review` to `Fixing` if it did not pass review; otherwise, it moves to `Done`. Issues move from `Started` to `PR Review` and also from `Fixing` to `PR Review`.

## 7. Required GitHub Issues and Kanban Workflow

Create all four Issues (Tasks) before implementation begins. Add every Issue to the Project with initial status **Backlog**. Move an Issue to **Specified** only after you have read and understood its requirements. Move only the Issue you are actively implementing to **Started**.

### 7.1. Issue 1: Set up the TokTickIT project foundation

- **Type:** Technical setup
- **Required branch:** `feature/1-project-foundation`

**Acceptance criteria:**

- React + TypeScript + Vite frontend starts successfully.
- Bootstrap is installed and visible in the frontend.
- Node.js + Express + TypeScript backend starts successfully.
- PostgreSQL is reachable and Prisma is initialized.
- Vitest and Supertest commands are configured.
- `.gitignore` and `.env.example` exist; secrets and `node_modules` are not committed.
- Initial README setup instructions are present.

### 7.2. Issue 2: Implement the API health check

- **Type:** Feature
- **Required branch:** `feature/2-health-check`

**Acceptance criteria:**

- `GET /api/health` returns HTTP 200.
- The JSON response contains `status = ok` and `service = TokTickIT API`.
- A Supertest test verifies the endpoint.
- The React page displays the backend status based on a real API call.
- A useful error message appears when the backend is unavailable.

### 7.3. Issue 3: Create and seed IT request categories

- **Type:** Database preparation
- **Required branch:** `feature/3-category-seed`

**Acceptance criteria:**

- A Prisma `Category` model exists with `id`, unique `name`, and `createdAt`.
- A migration creates the `Category` table.
- The seed inserts Account and Access, Hardware, Software, and Network.
- The seed is safe to run more than once without duplicates.
- Database credentials are not committed.

### 7.4. Issue 4: Display the IT request category list

- **Type:** Feature
- **Required branch:** `feature/4-category-list`

**Acceptance criteria:**

- `GET /api/categories` retrieves categories from PostgreSQL through Prisma.
- The API returns each category ID and name in a predictable order.
- A Supertest test verifies the response.
- React displays the categories returned by the API, not hard-coded values.
- Loading and error states are shown.
- A Vitest test verifies the category-list UI behavior.

**Dependency order:** Issue 1 must be completed first. Issues 2 and 3 may then proceed. Issue 4 starts only after Issue 3 is available in `dev`.

## 8. Required Repository Structure

```text
toktickit/
├── client/
├── server/
│   ├── prisma/
│   ├── src/
│   └── tests/
│       └── lab-01/
├── docs/
│   └── lab-01/
│       ├── ai_use.md
│       └── reviewer.md
├── .gitignore
└── README.md
```

## 9. Minimum Database Model

```prisma
model Category {
  id        Int      @id @default(autoincrement())
  name      String   @unique
  createdAt DateTime @default(now())
}
```

## 10. Required REST Endpoints

### 10.1. Health Check

```http
GET /api/health
```

**200 OK**

```json
{
  "status": "ok",
  "service": "TokTickIT API"
}
```

### 10.2. Category List

```http
GET /api/categories
```

**200 OK**

```json
[
  { "id": 1, "name": "Account and Access" },
  { "id": 2, "name": "Hardware" },
  { "id": 3, "name": "Software" },
  { "id": 4, "name": "Network" }
]
```

## 11. Required Automated Tests

| Tool      | Minimum test                                                    | Expected evidence       |
| --------- | --------------------------------------------------------------- | ----------------------- |
| Supertest | `GET /api/health` returns 200 and `status = ok`                 | Passing terminal output |
| Supertest | `GET /api/categories` returns the seeded categories             | Passing terminal output |
| Vitest    | TokTickIT heading renders                                       | Passing terminal output |
| Vitest    | At least one loading, success, or error state behaves correctly | Passing terminal output |

## 12. Workflow Summary

All work must occur on feature branches and enter `main` via `lab1-staging`. Peer review is mandatory for all PRs. Record reviewer details in `docs/lab-01/reviewer.md`.

| Status    | When to use it                                                                                               |
| --------- | ------------------------------------------------------------------------------------------------------------ |
| Backlog   | The Issue has been provided but has not yet been reviewed and understood.                                    |
| Specified | The Issue is understood and ready to implement.                                                              |
| Started   | The feature branch has been created and implementation has begun.                                            |
| PR Review | A Pull Request to `lab1-staging` is open and the peer reviewer is checking it.                               |
| Fixing    | Review changes are required or tests failed; corrections are being made on the same branch.                  |
| Done      | The PR is approved, tests pass, it is merged into `lab1-staging`, and all acceptance criteria are satisfied. |

| Issue                         | Feature branch                 | Pull Request target |
| ----------------------------- | ------------------------------ | ------------------- |
| 1. Project Foundation         | `feature/1-project-foundation` | `lab1-staging`      |
| 2. API Health Check           | `feature/2-health-check`       | `lab1-staging`      |
| 3. Create and Seed Categories | `feature/3-category-seed`      | `lab1-staging`      |
| 4. Display Category List      | `feature/4-category-list`      | `lab1-staging`      |

## 13. AI Coding Agent Rules

You may use an AI coding agent, but you remain responsible for every file, command, dependency, and test. Give the agent small tasks with clear constraints. Do not submit code you cannot explain.

## 14. Submit One PDF File

Grading a large number of lab submissions is challenging, so please submit one PDF file only and follow the instructions below carefully. You may use LLMs to assist you, but do keep the PDF concise and focused. Unnecessarily long responses may receive a penalty.

To make grading faster and more consistent, you must submit a single PDF using the following format:

```text
Answer Part 1:
[Place your content here]

Answer Part 2:
[Place your content here]

Answer Part 3:
[Place your content here]

Answer Part 4:
[Place your content here]
```

### Part 1: Git Use with Engineering Workflow – 15 points

**Required submission evidence:**

- URL list for your GitHub repository, GitHub Project, all GitHub Issues in your Project, and all Pull Requests (feature branches, staging branch, main branch).
- Evidence you used GitHub Project, showing the list of Issues as a Kanban board, and the final board with all Issues in the `Done` Kanban state.
- Screenshot evidence that you used the git workflow by showing your commit history in the final `main` branch showing you created various feature branches that were eventually merged into the staging branch and then the `main` branch.
- Screenshot showing the entire directory structure of your repository in your IDE. It should align with the **Required Repository Structure** section. These files should appear:
  - `docs/lab-01/tests.md`
  - `docs/lab-01/reviewer.md`
  - `docs/lab-01/ai_use.md`
  - `README.md`
  - Test files under `tests/lab-01/`
- Rendered `README.md` with content as required above and `.gitignore` content.

**PR Review Evidence (5 points):**

- Rendered version of `docs/lab-01/reviewer.md` containing reviewer name, student ID, GitHub username, and reviewed PR links.
- Evidence that your peer partner approved your submitted Pull Requests. What review comment did your partner give you for your Pull Request, and how did you respond?
- Evidence that you reviewed and approved your partner's Pull Requests. What review comment did you give your partner for their Pull Request and how did your partner respond?
- Screenshots of the board showing all four Issues in the `Done` board in your Kanban.

### Part 2: Tests – 10 points

Lab 1 tests are intended to prove that the initial TokTickIT vertical slice works correctly.

- Provide evidence via screenshot or copied output showing all tests passed in the `main` branch.
- Print `docs/lab-01/tests.md` (rendered version) so it is easier to grade. All tests listed should be found in the `tests/lab-01` folder.

Example data for `tests.md`:

| Test File             | Tool      | Test Description                                       |
| --------------------- | --------- | ------------------------------------------------------ |
| `tests/lab-01/API-01` | Supertest | Health endpoint returns 200 and expected JSON          |
| `tests/lab-01/API-02` | Supertest | Categories endpoint returns the four seeded categories |
| `tests/lab-01/UI-01`  | Vitest    | TokTickIT heading renders                              |
| `tests/lab-01/UI-02`  | Vitest    | Loading state changes to category list                 |
| `tests/lab-01/UI-03`  | Vitest    | API failure displays a useful error message            |

### Part 3: AI Use and Reflection – 5 points

1. Use AI with a very brief reflection.
2. Print `docs/lab-01/ai_use.md` (rendered version) that mentions the LLM you used and provides a table of selected key prompts. Six to ten prompts are enough.
3. Provide some reflection on your experience improving the prompts.

Example content:

> I used the Antigravity coding agent through my Google Cloud Platform account. I mainly used Gemini 3.5 Flash as the LLM with a thinking level of Medium.

**Selected Key Prompts:**

| Prompt name                    | Actual prompt text                                                                                                                                                                                                                                                                                        |
| ------------------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Plan Lab 1 Implementation      | Read the enclosed TokTickIT Lab 1 requirements. Summarize the four GitHub Issues, their dependencies, required outputs, and required automated tests. Propose an implementation order, but do not write code yet.                                                                                         |
| Reflection                     | This worked in one shot.                                                                                                                                                                                                                                                                                  |
| Set Up Full-Stack Project      | Set up the TokTickIT project technology stack as given in Lab 1 using React, TypeScript, Vite, and Bootstrap for the frontend, and Node.js, Express, and TypeScript for the backend. Configure PostgreSQL and Prisma. Use the required folder structure. Do not add functionality beyond the Lab 1 scope. |
| Reflection                     | I had to follow up this prompt with a few more to detail …                                                                                                                                                                                                                                                |
| Implement Health Check         | Add `GET /api/health` to the existing Express backend. It must …                                                                                                                                                                                                                                          |
| Reflection                     | …                                                                                                                                                                                                                                                                                                         |
| Implement Category Feature     | Create the Prisma `Category` model, …                                                                                                                                                                                                                                                                     |
| Build and Test Check System UI | Create a Bootstrap-based page with a **[Check System]** button. When clicked, show a loading state, call …                                                                                                                                                                                                |
| Reflection                     | …                                                                                                                                                                                                                                                                                                         |
| Review Final Lab 1 Work        | Review the completed TokTickIT Lab 1 implementation against all acceptance criteria …                                                                                                                                                                                                                     |
| Reflection                     | …                                                                                                                                                                                                                                                                                                         |

### Part 4: App Demo – 10 points

Provide a screenshot of the running browser page with the app demo to show that the two HTTP requests are working. When the app comes on, include a **[Check System]** button with the behavior shown below.

#### Initial screen

```text
┌──────────────────────────────────────────────┐
│ TokTickIT IT Service Desk                    │
│                                              │
│ [ Check System ]                             │
└──────────────────────────────────────────────┘
```

#### Success case after clicking [Check System]

```text
┌──────────────────────────────────────────────┐
│ TokTickIT IT Service Desk                    │
│                                              │
│ [ Check System ]                             │
│                                              │
│ System Status:  Online                       │
│                                              │
│ Supported Request Categories                 │
│ 1. Account and Access                        │
│ 2. Hardware                                  │
│ 3. Software                                  │
│ 4. Network                                   │
└──────────────────────────────────────────────┘
```

#### Failure case after clicking [Check System]

For example, when the database server is not started:

```text
┌──────────────────────────────────────────────┐
│ TokTickIT IT Service Desk                    │
│                                              │
│ [ Check System ]                             │
│                                              │
│ System Status:  Offline                      │
│ Unable to connect to TokTickIT API           │
└──────────────────────────────────────────────┘
```
