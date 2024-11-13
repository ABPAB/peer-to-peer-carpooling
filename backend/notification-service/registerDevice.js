// notification-service/registerDevice.js

const admin = require('./firebaseSetup'); // Import firebase admin setup

// This is a simulated device registration process
const registerDevice = async () => {
  try {
    // Simulate a device registration by sending a message to a test device
    const message = {
      notification: {
        title: 'Test Notification',
        body: 'This is a test message from Firebase Admin SDK.',
      },
      //token: 'dGhpcy1pcy1hLXRlc3QtdG9rZW4=', // This will be manually replaced by an actual token
      topic: 'test-topic', // Sending to a test topic instead of a device token
    };

    console.log('Sending test notification to topic...'); // for topic

//     // This is a simulated registration. You'd actually get the token through Firebase's frontend SDK
//     const response = await admin.messaging().send(message);
//     console.log('Test notification sent:', response);
//     return response;
//   } catch (error) {
//     console.error('Error sending test notification:', error);
//     throw error;
//   }

// Send a message to devices subscribed to the topic 'test-topic'
const response = await admin.messaging().send(message);
console.log('Test notification sent successfully to topic:', response);
return response;

} catch (error) {
console.error('Error during device registration (topic messaging):', error.message);
throw error;
}

};

module.exports = registerDevice;
