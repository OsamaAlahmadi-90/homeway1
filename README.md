HomeWay – Property Services Management Platform

HomeWay is a Spring Boot backend that connects customers with specialized service companies to manage residential properties efficiently. It supports end-to-end service lifecycles for Inspection, Maintenance, Moving, and Redesign, enforcing structured workflows, secure access control, transparent pricing, real-time notifications, payment processing, and AI-powered assistance.

Table of Contents

Introduction
Key Features
User Roles & Permissions
Architecture
Core Workflows
Problems Solved
Contributing
Contributors


Introduction
HomeWay is designed as a realistic, production-style backend system. It integrates business rules, role-based authorization, payment verification, notifications, reporting, and subscription-gated AI features to enhance decision-making for customers and service providers.
The platform centers around a controlled request lifecycle: Pending → Approved → In Progress → Completed (or Rejected), with strict validation of role permissions, ownership, and state transitions.

Key Features
Property Management

Customers can create, update, and manage multiple properties.
All service requests are tied to a specific property for traceability.

Service Request Lifecycle

Controlled flow: Pending → Approved → In Progress → Completed (or Rejected).
Requests are explicitly linked to a company and a property.
Strict validation of request state transitions and ownership checks.

Offer & Payment System

Companies approve requests by creating a pricing offer.
Customers can accept or reject offers before payment.
Secure payment processing via Moyasar with verification and automatic request payment updates.

Resource Management

Automatic assignment of available workers for requests.
Vehicle assignment for moving services.
Availability tracking to prevent double-booking and conflicts.

Reports & Reviews

Workers generate structured reports after request completion.
Customers can view reports for their own requests.
Customers can submit reviews (one per completed request) to support service transparency.

Notification System

Notifications created for critical events (approval, rejection, start, completion, review creation, etc.).
Supports customer, company, and worker notification flows.

AI-Powered Assistance (Subscription Based)

Cost estimation and breakdowns (customer/company perspective).
Timeline estimation and planning guidance.
Issue diagnosis (text & image URL input).
Inspection planning checklists and prioritization.
Worker safety requirements and repair checklists.
Redesign scope & style suggestions.

Subscription & Billing

FREE and AI plans.
AI features available only for active subscriptions.
Scheduled email reminders for renewal and expiry.


User Roles & Permissions
Customer

Manage profile and properties.
Create service requests (inspection, moving, maintenance, redesign).
View/manage own requests and offers.
Accept/reject offers and pay for services.
View reports and submit reviews after completion.
Use AI features if subscribed.

Company (Role-specialized)
Company accounts are specialized by role: INSPECTION_COMPANY, MOVING_COMPANY, MAINTENANCE_COMPANY, REDESIGN_COMPANY.

Requires admin approval to operate.
View assigned requests.
Approve/reject pending requests and create pricing offers.
Start requests only after validating: offer accepted + request paid + correct state.
Assign/release resources (workers; vehicles for moving).
View reviews and notifications related to company operations.

Worker

Works under a company and is assigned to requests.
Can access only requests assigned to them.
Create/update/delete reports (after request completion).
AI tools available only if: subscription is active + worker account is active.

Admin

Approve/reject company registrations.
Oversee platform data, users, and notifications.
Manage platform-level operations and governance flows.


Architecture
HomeWay follows a layered Spring Boot architecture to keep responsibilities clean and scalable:
Controller Layer

RESTful APIs for customers, companies, workers, and admin.
Authentication via Spring Security (Basic Auth) and @AuthenticationPrincipal.

Service Layer

Business logic + workflow enforcement (state transitions, ownership checks, role gating).
Transactional operations for request lifecycle, resource assignment, and payment confirmation.
Subscription checks that gate AI features.

Persistence Layer

JPA/Hibernate relational mapping.
Strong entity ownership via @OneToOne, @ManyToOne, and @MapsId.

External Integrations

Moyasar for payments (offers + subscription billing).
OpenAI API for AI assistance endpoints.
Email service for renewal/expiry notifications.


Core Workflows
Service Request Flow

Customer selects a property and a company, then creates a request (Pending).
Company reviews the request:

Approve: creates an offer with a price → request becomes Approved.
Reject: request becomes Rejected.


Customer accepts the offer and completes payment (gates service execution).
Company starts the request:

Validates offer is accepted + request is paid + correct status.
Assigns an available worker (and a vehicle for moving requests).
Updates availability and marks request In Progress.


Company completes the request:

Releases worker/vehicle resources and restores availability.
Marks request Completed and stores completion dates.


Notifications are created at key steps for customer/company/worker visibility.

Payment Flow

Customer pays an accepted offer via Moyasar.
System verifies payment status using Moyasar APIs.
Request isPaid is updated automatically upon successful verification.
Request execution (start/complete) is blocked unless paid.

AI Feature Flow

User subscribes to AI plan (subscription stored and payment tracked).
Every AI endpoint validates subscription status before responding.
Additional gating rules apply depending on endpoint:

Worker must be active for worker tools.
Company role must match feature type (e.g., moving tools for moving company).


AI responses are returned in structured, actionable formats.


Problems Solved

Unstructured service requests: Enforces a controlled lifecycle and valid state transitions.
Pricing ambiguity: Uses explicit offers + acceptance before payment.
Resource conflicts: Prevents double-assignment of workers/vehicles with availability tracking.
Unauthorized access: Strong role-based and ownership validation for sensitive operations.
Poor communication: Built-in notifications for all critical service events.
Lack of decision support: AI tools assist planning, estimation, diagnosis, and documentation.
Scalability: Modular structure supports adding service types and AI tools without redesign.


Contributing
This project was developed as a collaborative capstone project. The current version represents a complete implementation of the HomeWay platform with all planned features and workflows.
While this repository showcases our final work, we welcome feedback, suggestions, and discussions about the implementation. If you're interested in learning more about specific features or have questions about our approach:

Open an issue to discuss potential improvements or ask questions about the architecture.
Review our comprehensive documentation to understand the design decisions and workflows.
Check out the Postman documentation and test cases to explore the API endpoints.

If you'd like to build upon this work or adapt it for your own use case, feel free to fork the repository. Please ensure you maintain the existing structure (Controller → Service → Repository) and preserve validation and authorization rules when making modifications.

Contributors
@Turki1927
Backend Development:

Property, Worker, Admin entities
Notifications system
SubscriptionPaymentService, UserSubscriptionService
Request flows: Maintenance & Redesign

Testing & Design:

JUnit Test (Service layer)
Postman Testing (Local/deployment)
Figma Initial Design
Class Diagram

External APIs:

Subscription payment integration (Moyasar)
Email Integration (Subscription/Notifications)
AI endpoints: customerServicesTimeEstimation, customerReviewWritingAssist, workerRepairChecklist, workerSafetyRequirements, companyServiceEstimationCost, maintenanceCompanySparePartsCosts

@leenref
Backend Development:

Report, Vehicle, Customer entities
RequestPaymentService
Request flow: Moving

Testing & Documentation:

JUnit Test (Controller layer)
Postman Testing (Local/deployment)
Postman Documentation
Figma Initial Design
Class Diagram

Deployment & External APIs:

Platform Deployment
Service payment integration (Moyasar)
AI endpoints: customerAskAIWhatServiceDoesTheIssueFits, customerIsFixOrDesignCheaper, workerReportCreationAssistant, companyInspectionPlanningAssistant, movingCompanyTimeAdvice, maintenanceFixOrReplace

@OsamaAlahmadi-90
Backend Development:

User, Offer, UserRegister, Review, Company entities
Request flow: Inspection

Testing & Design:

JUnit Test (Repository layer)
Postman Testing (Local/deployment)
Figma Final Design
Class Diagram
Use Case Diagram
Project Presentation

External APIs:

Email integration (Company/Customer Requests)
AI endpoints: customerRequestCostEstimation, customerReportSummary, workerIssueDiagnosis, movingCompanyResourceMovingEstimation, workerJobTimeEstimation, maintenanceCompanyMaintenancePlan, redesignCompanyRedesignScope, customerRedesignFromImage, companyIssueImageDiagnosis
___________________________________________________________

HomeWay– منصة إدارة خدمات الممتلكات

HomeWay هي منصة خلفية مبنية بـ Spring Boot تربط العملاء بشركات الخدمات المتخصصة لإدارة العقارات السكنية بكفاءة. تدعم دورة حياة الخدمة الكاملة لـ الفحص، الصيانة، النقل، وإعادة التصميم، مع تطبيق سير عمل منظم، والتحكم الآمن في الوصول، والتسعير الشفاف، والإشعارات الفورية، ومعالجة المدفوعات، والمساعدة المدعومة بالذكاء الاصطناعي.

جدول المحتويات

المقدمة
الميزات الرئيسية
أدوار المستخدمين والصلاحيات
البنية المعمارية
سير العمل الأساسي
المشاكل التي تم حلها
المساهمة
المساهمون


المقدمة
تم تصميم HomeWay كنظام خلفي واقعي على مستوى الإنتاج. يدمج قواعد العمل، والترخيص القائم على الأدوار، والتحقق من الدفع، والإشعارات، والتقارير، وميزات الذكاء الاصطناعي المقيدة بالاشتراك لتحسين اتخاذ القرار للعملاء ومقدمي الخدمات.
تتمحور المنصة حول دورة حياة طلب خدمة محكومة: قيد الانتظار ← موافق عليه ← قيد التنفيذ ← مكتمل (أو مرفوض)، مع التحقق الصارم من صلاحيات الأدوار، والملكية، وانتقالات الحالة.

الميزات الرئيسية
إدارة الممتلكات

يمكن للعملاء إنشاء وتحديث وإدارة عدة عقارات.
جميع طلبات الخدمة مرتبطة بعقار محدد لتتبع أفضل.

دورة حياة طلب الخدمة

سير عمل محكوم: قيد الانتظار ← موافق عليه ← قيد التنفيذ ← مكتمل (أو مرفوض).
الطلبات مرتبطة صراحةً بشركة وعقار.
التحقق الصارم من انتقالات حالة الطلب وفحوصات الملكية.

نظام العروض والدفع

الشركات توافق على الطلبات من خلال إنشاء عرض سعر.
العملاء يمكنهم قبول أو رفض العروض قبل الدفع.
معالجة دفع آمنة عبر ميسر (Moyasar) مع التحقق والتحديث التلقائي لحالة الدفع.

إدارة الموارد

تعيين تلقائي للعمال المتاحين للطلبات.
تعيين المركبات لخدمات النقل.
تتبع التوافر لمنع الحجوزات المزدوجة والتعارضات.

التقارير والمراجعات

العمال ينشئون تقارير منظمة بعد إكمال الطلب.
العملاء يمكنهم عرض التقارير لطلباتهم الخاصة.
العملاء يمكنهم تقديم مراجعات (مراجعة واحدة لكل طلب مكتمل) لدعم الشفافية.

نظام الإشعارات

إنشاء إشعارات للأحداث الحرجة (الموافقة، الرفض، البدء، الإكمال، إنشاء المراجعة، إلخ).
يدعم تدفقات الإشعارات للعملاء والشركات والعمال.

المساعدة المدعومة بالذكاء الاصطناعي (على أساس الاشتراك)

تقدير التكلفة والتفاصيل (من منظور العميل/الشركة).
تقدير الجدول الزمني وإرشادات التخطيط.
تشخيص المشاكل (نص ورابط صورة).
قوائم مراجعة تخطيط الفحص وتحديد الأولويات.
متطلبات السلامة للعمال وقوائم مراجعة الإصلاح.
نطاق إعادة التصميم واقتراحات الأسلوب.

الاشتراك والفوترة

خطط مجانية والذكاء الاصطناعي.
ميزات الذكاء الاصطناعي متاحة فقط للاشتراكات النشطة.
تذكيرات مجدولة عبر البريد الإلكتروني للتجديد والانتهاء.


أدوار المستخدمين والصلاحيات
العميل

إدارة الملف الشخصي والعقارات.
إنشاء طلبات الخدمة (فحص، نقل، صيانة، إعادة تصميم).
عرض/إدارة الطلبات والعروض الخاصة.
قبول/رفض العروض والدفع مقابل الخدمات.
عرض التقارير وتقديم المراجعات بعد الإكمال.
استخدام ميزات الذكاء الاصطناعي إذا كان مشتركًا.

الشركة (متخصصة حسب الدور)
حسابات الشركات متخصصة حسب الدور: شركة فحص، شركة نقل، شركة صيانة، شركة إعادة تصميم.

تتطلب موافقة المسؤول للعمل.
عرض الطلبات المخصصة.
الموافقة/رفض الطلبات المعلقة وإنشاء عروض الأسعار.
بدء الطلبات فقط بعد التحقق من: قبول العرض + دفع الطلب + الحالة الصحيحة.
تعيين/إطلاق الموارد (العمال؛ المركبات للنقل).
عرض المراجعات والإشعارات المتعلقة بعمليات الشركة.

العامل

يعمل تحت شركة ويُعين للطلبات.
يمكنه الوصول فقط للطلبات المعينة له.
إنشاء/تحديث/حذف التقارير (بعد إكمال الطلب).
أدوات الذكاء الاصطناعي متاحة فقط إذا: الاشتراك نشط + حساب العامل نشط.

المسؤول

الموافقة/رفض تسجيلات الشركات.
الإشراف على بيانات المنصة والمستخدمين والإشعارات.
إدارة العمليات والحوكمة على مستوى المنصة.


البنية المعمارية
تتبع HomeWay بنية Spring Boot متعددة الطبقات للحفاظ على المسؤوليات واضحة وقابلة للتوسع:
طبقة المتحكم (Controller)

واجهات برمجية RESTful للعملاء والشركات والعمال والمسؤولين.
المصادقة عبر Spring Security (Basic Auth) و@AuthenticationPrincipal.

طبقة الخدمة (Service)

منطق الأعمال + تطبيق سير العمل (انتقالات الحالة، فحوصات الملكية، تقييد الأدوار).
العمليات المعاملية لدورة حياة الطلب، وتعيين الموارد، وتأكيد الدفع.
فحوصات الاشتراك التي تقيد ميزات الذكاء الاصطناعي.

طبقة الاستمرارية (Persistence)

التعيين العلائقي JPA/Hibernate.
ملكية قوية للكيانات عبر @OneToOne، @ManyToOne، و@MapsId.

التكاملات الخارجية

ميسر (Moyasar) للمدفوعات (العروض + فوترة الاشتراك).
OpenAI API لنقاط نهاية مساعدة الذكاء الاصطناعي.
خدمة البريد الإلكتروني لإشعارات التجديد والانتهاء.


سير العمل الأساسي
سير طلب الخدمة

العميل يختار عقارًا وشركة، ثم ينشئ طلبًا (قيد الانتظار).
الشركة تراجع الطلب:

الموافقة: تنشئ عرضًا بسعر ← الطلب يصبح موافق عليه.
الرفض: الطلب يصبح مرفوضًا.


العميل يقبل العرض ويكمل الدفع (بوابة تنفيذ الخدمة).
الشركة تبدأ الطلب:

التحقق من: قبول العرض + دفع الطلب + الحالة الصحيحة.
تعيين عامل متاح (ومركبة لطلبات النقل).
تحديث التوافر ووضع علامة الطلب قيد التنفيذ.


الشركة تكمل الطلب:

إطلاق موارد العامل/المركبة واستعادة التوافر.
وضع علامة الطلب مكتمل وتخزين تواريخ الإكمال.


يتم إنشاء الإشعارات في الخطوات الرئيسية لرؤية العميل/الشركة/العامل.

سير الدفع

العميل يدفع عرضًا مقبولاً عبر ميسر.
النظام يتحقق من حالة الدفع باستخدام واجهات برمجة ميسر.
يتم تحديث isPaid للطلب تلقائيًا عند نجاح التحقق.
تنفيذ الطلب (البدء/الإكمال) محظور ما لم يتم الدفع.

سير ميزة الذكاء الاصطناعي

المستخدم يشترك في خطة الذكاء الاصطناعي (يتم تخزين الاشتراك وتتبع الدفع).
كل نقطة نهاية للذكاء الاصطناعي تتحقق من حالة الاشتراك قبل الاستجابة.
قواعد تقييد إضافية تنطبق حسب نقطة النهاية:

يجب أن يكون العامل نشطًا لأدوات العامل.
يجب أن يتطابق دور الشركة مع نوع الميزة (مثلاً، أدوات النقل لشركة النقل).


يتم إرجاع استجابات الذكاء الاصطناعي بتنسيقات منظمة وقابلة للتنفيذ.


المشاكل التي تم حلها

طلبات خدمة غير منظمة: تطبيق دورة حياة محكومة وانتقالات حالة صالحة.
غموض التسعير: استخدام عروض صريحة + قبول قبل الدفع.
تعارضات الموارد: منع التعيين المزدوج للعمال/المركبات مع تتبع التوافر.
الوصول غير المصرح به: التحقق القوي القائم على الدور والملكية للعمليات الحساسة.
ضعف التواصل: إشعارات مدمجة لجميع أحداث الخدمة الحرجة.
نقص دعم القرار: أدوات الذكاء الاصطناعي تساعد في التخطيط والتقدير والتشخيص والتوثيق.
قابلية التوسع: بنية معيارية تدعم إضافة أنواع خدمات وأدوات ذكاء اصطناعي دون إعادة تصميم.


المساهمة
تم تطوير هذا المشروع كمشروع تخرج تعاوني. النسخة الحالية تمثل تنفيذًا كاملاً لمنصة HomeWay مع جميع الميزات وسير العمل المخططة.
بينما يعرض هذا المستودع عملنا النهائي، نرحب بالملاحظات والاقتراحات والمناقشات حول التنفيذ. إذا كنت مهتمًا بمعرفة المزيد عن ميزات محددة أو لديك أسئلة حول منهجنا:

افتح مشكلة (issue) لمناقشة التحسينات المحتملة أو طرح أسئلة حول البنية المعمارية.
راجع وثائقنا الشاملة لفهم قرارات التصميم وسير العمل.
تحقق من وثائق Postman وحالات الاختبار لاستكشاف نقاط نهاية الواجهة البرمجية.

إذا كنت ترغب في البناء على هذا العمل أو تكييفه لحالة الاستخدام الخاصة بك، فلا تتردد في عمل fork للمستودع. يرجى التأكد من الحفاظ على البنية الحالية (Controller → Service → Repository) والحفاظ على قواعد التحقق والترخيص عند إجراء التعديلات.

المساهمون
@Turki1927
تطوير الواجهة الخلفية:

كيانات العقار، العامل، المسؤول
نظام الإشعارات
SubscriptionPaymentService، UserSubscriptionService
سير طلبات: الصيانة وإعادة التصميم

الاختبار والتصميم:

اختبار JUnit (طبقة الخدمة)
اختبار Postman (محلي/نشر)
التصميم الأولي على Figma
مخطط الفئات (Class Diagram)

الواجهات البرمجية الخارجية:

تكامل دفع الاشتراك (ميسر)
تكامل البريد الإلكتروني (الاشتراك/الإشعارات)
نقاط نهاية الذكاء الاصطناعي: customerServicesTimeEstimation، customerReviewWritingAssist، workerRepairChecklist، workerSafetyRequirements، companyServiceEstimationCost، maintenanceCompanySparePartsCosts

@leenref
تطوير الواجهة الخلفية:

كيانات التقرير، المركبة، العميل
RequestPaymentService
سير طلب: النقل

الاختبار والوثائق:

اختبار JUnit (طبقة المتحكم)
اختبار Postman (محلي/نشر)
وثائق Postman
التصميم الأولي على Figma
مخطط الفئات (Class Diagram)

النشر والواجهات البرمجية الخارجية:

نشر المنصة
تكامل دفع الخدمة (ميسر)
نقاط نهاية الذكاء الاصطناعي: customerAskAIWhatServiceDoesTheIssueFits، customerIsFixOrDesignCheaper، workerReportCreationAssistant، companyInspectionPlanningAssistant، movingCompanyTimeAdvice، maintenanceFixOrReplace

@OsamaAlahmadi-90
تطوير الواجهة الخلفية:

كيانات المستخدم، العرض، UserRegister، المراجعة، الشركة
سير طلب: الفحص

الاختبار والتصميم:

اختبار JUnit (طبقة المستودع)
اختبار Postman (محلي/نشر)
التصميم النهائي على Figma
مخطط الفئات (Class Diagram)
مخطط حالة الاستخدام (Use Case Diagram)
العرض التقديمي للمشروع

الواجهات البرمجية الخارجية:

تكامل البريد الإلكتروني (طلبات الشركة/العميل)
نقاط نهاية الذكاء الاصطناعي: 

