import React from "react";
import "./DetailPage.css"; // 상세 페이지 CSS

const DetailPage = ({ product, close }) => {
  return (
    <div className="detail-page-container">
      {/* 제품 정보 표시 */}
      <button onClick={close}>닫기</button> {/* 닫기 버튼 */}
    </div>
  );
};

export default DetailPage;
