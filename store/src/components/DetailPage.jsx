import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import holidaydata from "../Data/Holyday.json";

export default function DetailPage() {
  const { state } = useLocation();
  const productId = state.id;

  // 상품 데이터를 상태로 관리합니다.
  const [productDetails, setProductDetails] = useState(null);

  // 페이지 로드 시 해당 상품의 상세 정보를 찾습니다.
  useEffect(() => {
    const product = holidaydata.find((item) => item.id === productId);
    setProductDetails(product);
  }, [productId]);

  const containerStyle = {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    height: "100vh", // 전체 높이
    padding: "150px",
    margin: "30px",
  };
  const leftSectionStyle = {
    flex: 1, // 왼쪽 섹션과 오른쪽 섹션은 flex 값을 같게 하여 동일한 너비를 가지도록 설정
    padding: "20px",
    display: "flex",
    flexDirection: "column",
  };

  const rightSectionStyle = {
    flex: 1,
    padding: "20px",
    borderLeft: "3px solid #ccc", // 구분선 더 두껍게
    display: "flex",
    justifyContent: "center", // 세로 중앙 정렬
  };

  const productInfoStyle = {
    flex: 1,
    maxWidth: "600px",
  };

  const productImageStyle = {
    maxWidth: "60%",
    height: "auto",
    alignSelf: "center",
  };

  const backButtonStyle = {
    display: "inline-block",
    marginTop: "20px",
    padding: "10px 20px",
    backgroundColor: "#f2f2f2",
    border: "none",
    cursor: "pointer",
  };

  const qrCodeStyle = {
    width: "200px",
    height: "200px",
  };

  if (!productDetails) {
    return <div>Loading...</div>;
  }

  return (
    <div style={containerStyle}>
      <div style={leftSectionStyle}>
        <img
          src={productDetails.himage}
          alt={productDetails.hname}
          style={productImageStyle}
        />
        <br />
        <br />
        <h2>{productDetails.hname}</h2>
        <p>가격: {productDetails.hprice} 원</p>
        <button style={backButtonStyle} onClick={() => window.history.back()}>
          뒤로 가기
        </button>
      </div>
      <div style={rightSectionStyle}>
        <img src={productDetails.img} alt="QR Code" style={qrCodeStyle} />
      </div>
    </div>
  );
}
