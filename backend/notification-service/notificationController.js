const admin = require('./firebaseSetup');

//const sendNotification = async (token, message) => {

const sendNotification = async (topic, message) => {
  const messagePayload = {
    notification: {
      title: message.title,
      body: message.body,
    },
   // token: token, // Device token
   topic: topic,  // Use topic instead of token for testing
  };

  try {
    const response = await admin.messaging().send(messagePayload);
    console.log('Notification sent successfully:', response);
  } catch (error) {
    console.error('Error sending notification:', error);
  }
};

module.exports = { sendNotification };