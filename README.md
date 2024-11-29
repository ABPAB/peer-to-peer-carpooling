<h2>Carpooling - Connecting Riders, Reducing Commutes</h2>

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/ABPAB/peer-to-peer-carpooling/blob/main/LICENSE)
[![Node.js](https://img.shields.io/badge/Node.js-v14%2B-green)](https://nodejs.org/en/about)
[![Express.js](https://img.shields.io/badge/Express.js-Framework-black)](https://expressjs.com/en/starter/installing.html)
[![MySQL](https://img.shields.io/badge/Database-MySQL-orange)](https://dev.mysql.com/doc/)
[![Spring Boot](https://img.shields.io/badge/Backend-Spring%20Boot-lightgrey)](https://docs.spring.io/spring-boot/documentation.html)

"Carpooling" is an innovative platform designed to connect individuals traveling along `similar-routes`. This application aims to make commuting more affordable and eco-friendly by enabling seamless coordination between owners and riders.

## **Key Users**

### `Owners`

Car owners offering available seats for upcoming trips.

**Capabilities:**

- Set trip details: `source`, `destination`, `ride-status` and `available-seats`.
- Accept or reject ride requests from riders.
- Cancel/Delete ride.
- View list of confirmed riders for their trips.

### **`Riders`**

Individuals seeking rides along specific routes.

**Capabilities:**

- Search and filter trips based on preferred routes.
- Request seats in a driver's carpool.
- Cancel a ride.

## **Features**

 **1. Trip Creation:**
 
  Owners can create trips by specifying:
- Start and end locations.
- Number of available seats.

**2. Trip Search:**

  Riders can browse available trips using:
- Origin and destination filters.

**3. Booking System**

- Riders can request a seat on their chosen trip.
- Owners can accept or reject requests, ensuring mutual agreement.
  
**4. Notifications**

Real-time updates for:
- Booking confirmation, rejection, or cancellation.
- Trip modifications or updates from owners.
  
## **Tech Stack**

- **Node.js**: Backend server handling trip data, user authentication, and ride coordination.
- **Express.js**: Framework for building robust REST APIs.
- **MySQL**: To store user and trip data.
- **Kafka**: To emit and consume events.
- **Spring-boot**: For implementing the business-logic.
- **Firebase Cloud Messaging (FCM)**: Sends real-time notifications for trip updates and booking statuses.
  
# **Modules**

**1. User Authentication**

- Sign-up and login functionality.
    
**2. Trip Management**

Owners can:
- Add, edit, and delete trips.
- View and manage ride requests.
  
Riders can:
- Browse, request, and track trip bookings.
  
**3. Notifications**

Push notifications for:
- Owners: Booking requests and rider cancellations.
- Riders: Booking approvals or rejections, and trip updates.

**3. Service Discovery**

- Allows service instances to register themselves and facilitate service-discovery

**4. Payment System (Future Scope)**

- Integration of payment gateways to facilitate fare sharing.

# **Setup Instructions**

## **Prerequisites**

- Node.js (v14 or higher)
- MongoDB
- Firebase account for FCM
  
### **1. Clone the Repository**

```
git clone https://github.com/ABPAB/peer-to-peer-carpooling.git

cd peer-to-peer-carpooling
```

### **2. Install Dependencies**

```
npm install
```
### **3. Configure Environment Variables**

Create a `.env` file in the project root and add the following variables:

```
PORT=3000
MONGO_URI=mongodb://localhost:27017/peer-to-peer-carpooling
FIREBASE_API_KEY=your_firebase_api_key
```

### **4. Run the Application**

```
npm start
```

### **5. Access the Application**

Open your browser and navigate to:

```
http://localhost:3000
```

## **Future Enhancements**

- Integration with payment gateways for in-app fare handling.
- Advanced search with AI-driven route suggestions.
- Ride safety features, including driver verification and trip tracking.
- Integration with Google Maps for better route visualization.
  
## **Contributing**

We welcome contributions to enhance the Peer-to-Peer Carpooling platform! To contribute:

1. Fork the repository.

2. Create a feature branch:

```
git checkout -b feature-name
```

3. Commit your changes:

```
git commit -m "Add feature-name"
```

4. Push to your branch:

```
git push origin feature-name
```
5. Create a pull request.
  
## **License**

This project is licensed under the MIT License. See the `LICENSE` file for details.

## **Contact**

For queries or suggestions, please contact the project maintainers:

**Email**: kpkumar.8757@gmail.com

**GitHub Issues**: [Create an issue](https://github.com/ABPAB/peer-to-peer-carpooling/issues)

<div align="center">
  
## **Team Members**

| Name                         | Roll Number   |
|------------------------------|---------------|
| **Pragati Upadhyay**          | 2023SL93036   |
| **Harsh Vardhan Srivastava**  | 2023SL93015   |
| **Gayatri Deshmukh**          | 2023SL93091   |
| **Pawan Mehta**               | 2023SL93046   |
| **Gopal Anand**               | 2023SL93044   |
</div>


