// /src/pages/index.js
import React, { useEffect } from 'react';
import { initializeApp } from 'firebase/app';
import { getMessaging, onMessage, getToken } from 'firebase/messaging';
import Button from '@/stories/Button';

const Index = () => {
  function handleClick() {
    // 브라우저에 알림 권한을 요청합니다.
    async function getPermission() {
      const permission = await Notification.requestPermission();
      if (permission !== 'granted') return;
    }
    getPermission();
  }
  const onMessageFCM = async () => {
    // 이곳에도 아까 위에서 앱 등록할때 받은 'firebaseConfig' 값을 넣어주세요.
    const firebaseApp = initializeApp({
      apiKey: 'AIzaSyAnHEyqUnFoxudUMWnFvntVMDKZTymOLxw',
      authDomain: 'webpush-test-f3072.firebaseapp.com',
      projectId: 'webpush-test-f3072',
      storageBucket: 'webpush-test-f3072.appspot.com',
      messagingSenderId: '5581773458',
      appId: '1:5581773458:web:a0a8db6b8d8a7311d4644b',
    });

    const messaging = getMessaging(firebaseApp);

    // 이곳 vapidKey 값으로 아까 토큰에서 사용한다고 했던 인증서 키 값을 넣어주세요.
    getToken(messaging, {
      vapidKey:
        'BM8z7PYWYKIPxbpVckZ-rUqgRNoWV36rgdSLhqefvAHnNjeW_ZRBC87SLDdwz9DcX1w6xtGPR233v3BzgxYkNnQ',
    })
      .then((currentToken) => {
        if (currentToken) {
          // 정상적으로 토큰이 발급되면 콘솔에 출력합니다.
          console.log(currentToken);
        } else {
          console.log(
            'No registration token available. Request permission to generate one.',
          );
        }
      })
      .catch((err) => {
        console.log('An error occurred while retrieving token. ', err);
      });

    // 메세지가 수신되면 역시 콘솔에 출력합니다.
    onMessage(messaging, (payload) => {
      console.log('Message received. ', payload);
    });
  };

  useEffect(() => {
    onMessageFCM();
  }, []);

  return (
    <div>
      <h1>서비스를 원활하게 이용하기 위해서 알림 권한을 허용해주세요</h1>
      <Button primary onClick={handleClick}>
        허용하기
      </Button>
    </div>
  );
};

export default Index;
