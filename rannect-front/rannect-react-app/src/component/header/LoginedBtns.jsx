import React from "react";

function LoginedBtns({ handleClick, nickname }) {
  return (
    <div
      className="col-sm-4 mt-auto user-setting-btn"
      style={{ float: "right" }}
      onClick={handleClick}
    >
      <span className="col-sm-9 text-end" style={{ float: "right" }}>
        로그인한 사용자 :{" "}
        <span className="badge">{nickname ? nickname : ""}</span>
        <img src="/icon/person-circle.svg" className="col-sm-3 ms-2" />
      </span>
    </div>
  );
}

export default LoginedBtns;
