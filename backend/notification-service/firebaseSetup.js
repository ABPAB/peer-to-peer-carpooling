// notification-service/firebaseSetup.js

const admin = require('firebase-admin');
const path = require('path');

// Use relative path to serviceAccountKey.json
const serviceAccount = require('./peer-to-peer-carpooling-486a2-firebase-adminsdk-8sg44-58acb6aa3e.json'); 

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
});

module.exports = admin;
