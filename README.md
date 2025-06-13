# 🛵 Sky Take Out - Full Stack Food Delivery System

## 🔥 Project Overview
Sky Take Out is a comprehensive enterprise-level food delivery platform featuring:

- A robust **Spring Boot backend**
- An intuitive **Vue.js-based Admin Panel**
- A native **Kotlin Jetpack Compose Android mobile app** for users

The system supports food browsing, shopping cart, order placement, and real-time order tracking, suitable for both end-users and administrators.

---

## 🧰 Tech Stack

### 🔙 Backend
- **Spring Boot**, **Spring MVC**, **MyBatis + PageHelper**
- **Redis** for caching
- **JWT** for authentication
- **Aliyun OSS** for image storage
- **Swagger / Knife4j** for API documentation
- **WebSocket** for real-time order notifications
- **Apache POI** for Excel import/export
- **Spring Task** for scheduled jobs

### 🖥 Admin Panel (Web - Vue.js)
- Built with **Vue 2** and **Element UI**
- Uses **Axios** for REST API communication
- **ECharts** for reporting and dashboard
- Key features:
  - Employee Management  
  - Category & Dish Management  
  - Combo Meal Management  
  - Orders & Real-Time Voice Notifications  
  - Statistical Reports

### 📱 User App (Mobile - Kotlin Jetpack Compose)
- Developed using **Jetpack Compose** for modern Android UI
- Follows **MVVM** architecture (`ViewModel`, `State`, `LiveData`)
- Uses:
  - **Retrofit** for API networking
  - **Coil** for image loading
  - **Navigation Compose** for navigation
- Features:
  - Onboarding and Login
  - Category and dish browsing
  - Shopping cart and order placement
  - Order history and tracking

---

🖥 Admin Panel

![Admin Panel Screen](https://i.imgur.com/hfRKC1p.png)

🖥 User Mobile App

![Splash & Login Screen](https://i.imgur.com/ZOWKUCR.png)
![ Home & Dish Screen](https://i.imgur.com/5dpbsrd.png)


## ✅ How to Use

### 🔧 Backend Setup
1. Import `sky.sql` into your MySQL database.
2. Configure `application.yml` with your MySQL, Redis, and OSS credentials.
3. Start backend using your IDE or run:
   ```bash
   mvn spring-boot:run
4. API Docs available at: http://localhost:8080/doc.html


🖥 Admin Panel (Vue.js)
Navigate to the Vue project directory.

Install dependencies and run:

npm install
npm run serve


📱 Mobile App (Jetpack Compose)
Clone the Kotlin project (not included in this repo).

Set the backend base URL in Retrofit config.

Open in Android Studio and run on an emulator or device.




