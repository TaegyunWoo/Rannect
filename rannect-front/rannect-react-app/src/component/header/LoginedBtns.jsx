import React from "react";

function LoginedBtns({ handleClick, nickname }) {
  return (
    <div
      className="col-sm-4 float-end mt-auto user-setting-btn"
      onClick={handleClick}
    >
      <span className="col-sm-9 text-end float-end">
        로그인한 사용자 :{" "}
        <span className="badge">{nickname ? nickname : ""}</span>
      </span>
      <img src="/icon/person-circle.svg" className="col-sm-2 ms-2 float-end" />
    </div>
  );
}

export default LoginedBtns;
