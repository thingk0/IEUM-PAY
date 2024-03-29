import React from "react";
import "./Holyitem.css";
import DetailModal from "../DetailModal";
import { useNavigate } from "react-router-dom";

export default function Holyitem(props) {
  // const [isModalOpen, setModalOpen] = useState(false);

  // const openModal = () => {
  //   setModalOpen(true);
  // };

  // const closeModal = () => {
  //   setModalOpen(false);
  // };

  const navigate = useNavigate();
  const goDetail = () => {
    navigate("/detail", { state: { id: props.hdata.id } });
  };
  return (
    <div className="holy-container">
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          width: "300px",
          height: "300px",
        }}
      >
        <img
          src={props.hdata.himage}
          className="holy-img"
          style={{ width: "230px", height: "230px" }}
        />
      </div>
      <br></br>
      <p className="holy-one">{props.hdata.hname}</p>
      <p className="holy-two">
        {props.hdata.hprice} 원{" "}
        {props.hdata.hcutprice && <span>{props.hdata.hcutprice} 원</span>}
      </p>
      <hr />
      <p className="holy-three">{props.hdata.hvoucher}원 할인 진행중*</p>
      <br />
      <button className="holy-buy" onClick={goDetail}>
        구매하기
      </button>
      <br />
      <span className="off">{props.hdata.off}% 할인중</span>
    </div>
  );
}
