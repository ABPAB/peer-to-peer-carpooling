import firebase from "firebase/app";
import "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyD06t8hl72PaeKArGdXF41uJbDgNwDkrvw",
  authDomain: "car-pooling-app-bits.firebaseapp.com",
  projectId: "car-pooling-app-bits",
  storageBucket: "car-pooling-app-bits.firebasestorage.app",
  messagingSenderId: "498160504039",
  appId: "1:498160504039:web:8c3e0f0f861e0c0eed6642",
  measurementId: "G-81Y4964JZ0",
};

if (!firebase.apps.length) {
  firebase.initializeApp(firebaseConfig);
} else {
  firebase.app();
}

export const auth = firebase.auth();
export const googleProvider = new firebase.auth.GoogleAuthProvider();
