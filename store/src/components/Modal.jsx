import React from "react";
import "./Modal.css"; // 모달에 대한 CSS 스타일링

const Modal = ({ children }) => {
  return (
    <div className="modal-backdrop">
      <div className="modal-content">{children}</div>
    </div>
  );
};

export default Modal;
