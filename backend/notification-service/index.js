// notification-service/index.js

const express = require('express');
const bodyParser = require('body-parser');
const { sendNotification } = require('./notificationController');
// for simulating registered device
const registerDevice = require('./registerDevice');

const app = express();
const port = 5001;

// Middleware
app.use(bodyParser.json());

// // Simulate device registration (get device token) and send a test notification
// app.post('/register-device', async (req, res) => {
//     try {
//       const response = await registerDevice();
//       res.status(200).json({
//         message: 'Device registered and notification sent.',
//         response,
//       });
//     } catch (error) {
//       res.status(500).send('Error during device registration.');
//     }
//   });

// // Endpoint to send notification
// app.post('/send-notification', async (req, res) => {
//   const { token, message } = req.body;
//   if (!token || !message) {
//     return res.status(400).send('Token and message are required');
//   }

//   try {
//     await sendNotification(token, message);
//     res.status(200).send('Notification sent');
//   } catch (error) {
//     res.status(500).send('Error sending notification');
//   }
// });

// Simulate device registration (subscribe to a topic) and send a test notification to a topic
app.post('/register-device', async (req, res) => {
    try {
      const response = await registerDevice();
      res.status(200).json({
        message: 'Device registered to topic and notification sent.',
        response,
      });
    } catch (error) {
      res.status(500).send('Error during device registration to topic.');
    }
  });
  
  // Endpoint to send notification to a topic (use "test-topic")
  app.post('/send-notification', async (req, res) => {
    const { topic, message } = req.body;
    if (!topic || !message) {
      return res.status(400).send('Topic and message are required');
    }
  
    try {
      await sendNotification(topic, message);
      res.status(200).send('Notification sent to topic');
    } catch (error) {
      res.status(500).send('Error sending notification to topic');
    }
  });

app.listen(port, () => {
  console.log(`Notification service running at http://localhost:${port}`);
});
