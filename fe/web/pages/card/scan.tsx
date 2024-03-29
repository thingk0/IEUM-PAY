import { useEffect, useRef } from 'react';

function ScanCardPage() {
  let videoRef = useRef<HTMLVideoElement>(null);
  function handleClick() {
    const canvas = document.createElement('canvas');
    const context = canvas.getContext('2d');
    if (videoRef.current) {
      console.log(videoRef.current.width);
      canvas.width = videoRef.current.width;
      canvas.height = videoRef.current.height;
      context?.drawImage(
        videoRef.current,
        0,
        0,
        videoRef.current?.width,
        videoRef.current?.height,
      );

      const link = document.createElement('a');
      link.download = 'receipt.png';
      link.href = canvas.toDataURL('image/png');
      link.click();
    }
  }
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
  }, []);
  return (
    <>
      <video className={''} autoPlay playsInline ref={videoRef}></video>
      <button onClick={handleClick}>촬영</button>
    </>
  );
}

export default ScanCardPage;
