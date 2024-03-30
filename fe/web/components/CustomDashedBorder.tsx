import { useEffect, useRef, useState } from 'react';
import classes from './CustomDashedBorder.module.scss';

function CustomDashedBorder() {
  const [width, setWidth] = useState<number>(0);
  const myElementRef = useRef<SVGSVGElement>(null);

  useEffect(() => {
    updateWidth(); // 초기 렌더링 시 넓이 업데이트
    window.addEventListener('resize', updateWidth); // resize 이벤트 핸들러 등록

    return () => {
      // 컴포넌트가 언마운트될 때 이벤트 핸들러 제거
      window.removeEventListener('resize', updateWidth);
    };
  }, []); // 초기 렌더링 시에만 실행

  const updateWidth = () => {
    if (myElementRef.current) {
      const elementWidth = myElementRef.current.clientWidth;
      setWidth(elementWidth);
    }
  };

  return (
    <svg
      width={width}
      height="3"
      viewBox={`0 0 ${width} 3`}
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
      className={classes.border}
      ref={myElementRef}
    >
      <line
        y1="0.5"
        x2={width}
        y2="0.5"
        stroke="#191E28"
        strokeDasharray="8 3"
      />
    </svg>
  );
}

export default CustomDashedBorder;
