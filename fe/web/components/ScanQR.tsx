import { useEffect, useRef } from 'react';
import classes from './ScanQR.module.scss';

function ScanQR() {
  let videoRef = useRef<HTMLVideoElement>(null);
  async function getUserMedia() {
    try {
      let myStream = await navigator.mediaDevices.getUserMedia({
        video: true,
      });
      console.log(myStream);
      if (videoRef.current) {
        videoRef.current.srcObject = myStream;
      }
    } catch (e) {
      console.log(e);
    }
  }
  useEffect(() => {
    getUserMedia();
  },[]);
  return (
    <>
      <video
        className={classes.camera}
        autoPlay
        playsInline
        ref={videoRef}
      ></video>
    </>
  );
}

export default ScanQR;
