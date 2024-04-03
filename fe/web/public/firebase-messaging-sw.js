// /public/firebase-messaging-sw.js
importScripts(
  'https://www.gstatic.com/firebasejs/10.10.0/firebase-app-compat.js',
);
importScripts(
  'https://www.gstatic.com/firebasejs/10.10.0/firebase-messaging-compat.js',
);

// 이곳에 아까 위에서 앱 등록할때 받은 'firebaseConfig' 값을 넣어주세요.
const config = {
  apiKey: 'AIzaSyAnHEyqUnFoxudUMWnFvntVMDKZTymOLxw',
  authDomain: 'webpush-test-f3072.firebaseapp.com',
  projectId: 'webpush-test-f3072',
  storageBucket: 'webpush-test-f3072.appspot.com',
  messagingSenderId: '5581773458',
  appId: '1:5581773458:web:a0a8db6b8d8a7311d4644b',
};

// Initialize Firebase
firebase.initializeApp(config);

const messaging = firebase.messaging();
