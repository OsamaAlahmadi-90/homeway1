HomeWay README (HTML Page with English/Arabic Toggle)

────────────────────────────────────────────────────────────
Document Type
────────────────────────────────────────────────────────────
- Full HTML page (<!DOCTYPE html> … </html>)
- Includes inline CSS + a language toggle (English / العربية)
- Contains two content sections:
  1) English (content-en)
  2) Arabic (content-ar) with RTL styling
- Includes a JavaScript function switchLanguage(lang) to toggle visibility

────────────────────────────────────────────────────────────
HTML Structure (Organized Outline)
────────────────────────────────────────────────────────────

1) Document Header
   - <!DOCTYPE html>
   - <html lang="en">

2) <head>
   - <meta charset="UTF-8">
   - <meta name="viewport" content="width=device-width, initial-scale=1.0">
   - <title>HomeWay README</title>
   - <style> … </style>

3) CSS (inside <style>)
   A) .language-toggle
      - Fixed position: top-right
      - z-index: 1000

   B) .language-toggle button
      - padding, margin
      - cursor: pointer
      - border, background
      - border-radius
      - font-weight: bold

   C) .language-toggle button.active
      - active button colors (dark background, white text)

   D) .lang-content
      - display: none

   E) .lang-content.active
      - display: block

   F) .rtl
      - direction: rtl
      - text-align: right

────────────────────────────────────────────────────────────
4) <body>
────────────────────────────────────────────────────────────

4.1) Language Toggle UI
   - <div class="language-toggle">
       - English button
         id="btn-en"
         class="active"
         onclick="switchLanguage('en')"
       - Arabic button
         id="btn-ar"
         onclick="switchLanguage('ar')"
     </div>

────────────────────────────────────────────────────────────
5) English Content Section (content-en)
────────────────────────────────────────────────────────────
Container:
- <div id="content-en" class="lang-content active">

Content:
5.1) Title + Intro
   - <h1 id="top">HomeWay – Property Services Management Platform</h1>
   - Intro paragraph describing the platform
   - <hr />

5.2) Table of Contents
   - <h2>Table of Contents</h2>
   - Links:
     - #introduction
     - #key-features
     - #roles-permissions
     - #architecture
     - #core-workflows
     - #problems-solved
     - #contributing
     - #contributors
   - <hr />

5.3) Section 1: Introduction
   - <h2 id="introduction">1. Introduction</h2>
   - Paragraphs explaining:
     - production-style backend system
     - business rules + RBAC + payments + notifications + reporting + subscription-gated AI
     - controlled request lifecycle:
       Pending → Approved → In Progress → Completed (or Rejected)
   - Back to top link
   - <hr />

5.4) Section 2: Key Features
   - <h2 id="key-features">2. Key Features</h2>

   5.4.1) Property Management
          - Customers manage multiple properties
          - Requests tied to property

   5.4.2) Service Request Lifecycle
          - Controlled flow + strict validation

   5.4.3) Offer & Payment System
          - Offers, accept/reject
          - Moyasar payment + verification + auto updates

   5.4.4) Resource Management
          - Worker assignment
          - Vehicle assignment (moving)
          - Availability tracking

   5.4.5) Reports & Reviews
          - Worker reports
          - Customer views reports
          - One review per completed request

   5.4.6) Notification System
          - Critical event notifications
          - Customer/company/worker flows

   5.4.7) AI-Powered Assistance (Subscription Based)
          - Cost estimation
          - Timeline guidance
          - Issue diagnosis (text/image URL)
          - Inspection planning
          - Safety requirements + repair checklist
          - Redesign suggestions

   5.4.8) Subscription & Billing
          - FREE and AI plans
          - AI gated by active subscription
          - Scheduled renewal/expiry emails

   - Back to top link
   - <hr />

5.5) Section 3: User Roles & Permissions
   - <h2 id="roles-permissions">3. User Roles & Permissions</h2>

   5.5.1) Customer
          - Profile + properties
          - Create requests
          - Manage requests/offers
          - Pay
          - Reports + reviews
          - AI if subscribed

   5.5.2) Company (Role-specialized)
          - Roles: INSPECTION_COMPANY, MOVING_COMPANY, MAINTENANCE_COMPANY, REDESIGN_COMPANY
          - Admin approval required
          - Approve/reject requests + create offers
          - Start only if offer accepted + paid + correct state
          - Assign/release resources
          - View reviews + notifications

   5.5.3) Worker
          - Assigned to requests
          - Only assigned requests accessible
          - Report CRUD after completion
          - AI only if subscription active + worker active

   5.5.4) Admin
          - Approve/reject company registrations
          - Oversees platform users/data/notifications
          - Platform governance

   - Back to top link
   - <hr />

5.6) Section 4: Architecture
   - <h2 id="architecture">4. Architecture</h2>
   - Layered Spring Boot architecture

   5.6.1) Controller Layer
          - REST APIs
          - Spring Security Basic Auth + @AuthenticationPrincipal

   5.6.2) Service Layer
          - Business logic + workflow enforcement
          - Transactions
          - Subscription checks for AI

   5.6.3) Persistence Layer
          - JPA/Hibernate
          - Ownership via @OneToOne / @ManyToOne / @MapsId

   5.6.4) External Integrations
          - Moyasar (offers + subscriptions)
          - OpenAI API
          - Email for renewal/expiry notifications

   - Back to top link
   - <hr />

5.7) Section 5: Core Workflows
   - <h2 id="core-workflows">5. Core Workflows</h2>

   5.7.1) Service Request Flow
          - Customer creates Pending request
          - Company approves (offer) or rejects
          - Customer accepts offer + pays
          - Company starts: validates paid + accepted + correct status, assigns worker/vehicle, sets In Progress
          - Company completes: releases resources, sets Completed
          - Notifications at key steps

   5.7.2) Payment Flow
          - Customer pays via Moyasar
          - System verifies
          - isPaid updated automatically
          - Execution blocked unless paid

   5.7.3) AI Feature Flow
          - Subscribe
          - Validate before each AI call
          - Extra gating rules:
            - worker must be active for worker tools
            - company role must match AI feature type
          - Structured actionable outputs

   - Back to top link
   - <hr />

5.8) Section 6: Problems Solved
   - <h2 id="problems-solved">6. Problems Solved</h2>
   - Unstructured requests → controlled lifecycle
   - Pricing ambiguity → offers + acceptance before payment
   - Resource conflicts → availability tracking
   - Unauthorized access → RBAC + ownership checks
   - Poor communication → notifications
   - Lack of decision support → AI tools
   - Scalability → modular structure

   - Back to top link
   - <hr />

5.9) Section 7: Contributing
   - <h2 id="contributing">7. Contributing</h2>
   - Explains this is a collaborative capstone with complete feature implementation
   - Welcomes feedback/discussions
   - Suggests:
     - open issues
     - review documentation
     - explore Postman docs and tests
   - Forking guidance + keep structure + preserve validation/auth rules

   - Back to top link
   - <hr />

5.10) Section 8: Contributors
   - <h2 id="contributors">8. Contributors</h2>

   Contributor 1: @Turki1927
   - Backend:
     - Property, Worker, Admin entities
     - Notifications system
     - SubscriptionPaymentService, UserSubscriptionService
     - Maintenance & Redesign request flows
   - Testing & Design:
     - JUnit (Service layer)
     - Postman testing
     - Figma initial design
     - Class diagram
   - External APIs:
     - Moyasar subscription payment
     - Email (subscription/notifications)
     - AI endpoints list

   Contributor 2: @leenref
   - Backend:
     - Report, Vehicle, Customer entities
     - RequestPaymentService
     - Moving flow
   - Testing & Documentation:
     - JUnit (Controller)
     - Postman testing
     - Postman documentation
     - Figma initial design
     - Class diagram
   - Deployment & External APIs:
     - Deployment
     - Moyasar service payment
     - AI endpoints list

   Contributor 3: @OsamaAlahmadi-90
   - Backend:
     - User, Offer, UserRegister, Review, Company entities
     - Inspection flow
   - Testing & Design:
     - JUnit (Repository)
     - Postman testing
     - Figma final design
     - Class diagram
     - Use case diagram
     - Presentation
   - External APIs:
     - Email integration (company/customer requests)
     - AI endpoints list

   - Back to top link

Close English section:
- </div>

────────────────────────────────────────────────────────────
6) Arabic Content Section (content-ar) + RTL
────────────────────────────────────────────────────────────
Container:
- <div id="content-ar" class="lang-content rtl">

Content mirrors the English structure (translated to Arabic):
6.1) Arabic title + intro
6.2) Arabic table of contents with Arabic anchors:
   - #introduction-ar
   - #key-features-ar
   - #roles-permissions-ar
   - #architecture-ar
   - #core-workflows-ar
   - #problems-solved-ar
   - #contributing-ar
   - #contributors-ar

6.3) Sections 1–8 in Arabic:
   - Introduction
   - Key Features
   - Roles & Permissions
   - Architecture
   - Core Workflows
   - Problems Solved
   - Contributing
   - Contributors (same contributors, Arabic descriptions)

Close Arabic section:
- </div>

────────────────────────────────────────────────────────────
7) JavaScript Logic
────────────────────────────────────────────────────────────
- <script>
  function switchLanguage(lang) {
    1) Remove 'active' from content-en and content-ar
    2) Remove 'active' from btn-en and btn-ar
    3) If lang == 'en':
         add 'active' to content-en and btn-en
       else:
         add 'active' to content-ar and btn-ar
  }
  </script>

────────────────────────────────────────────────────────────
8) Closing Tags
────────────────────────────────────────────────────────────
- </body>
- </html>
