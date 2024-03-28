// import dynamic from 'next/dynamic';
// import React, { useState } from 'react';
// // import { QrReader } from 'react-qr-reader';

// const Test: React.FC = () => {
//   const [data, setData] = useState<string>('No result');

//   const DynamicQrReader = dynamic(() => import('react-qr-reader'), {
//     ssr: false,
//   });

//   const handleScan = (result: string | null) => {
//     if (result) {
//       setData(result);
//     }
//   };

//   const handleError = (error: Error) => {
//     console.error(error);
//   };

//   return (
//     <>
//       <DynamicQrReader
//         delay={300}
//         onScan={handleScan}
//         onError={handleError}
//         style={{ width: '100%' }}
//       />
//       <p>{data}</p>
//     </>
//   );
// };

// export default Test;
