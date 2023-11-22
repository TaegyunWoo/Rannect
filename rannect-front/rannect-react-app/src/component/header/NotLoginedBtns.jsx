import React from "react";

function NotLoginedBtns({ handleSignUpClick, handleSignInClick }) {
  return (
    <div className={`col-sm-4 float-end mt-auto user-setting-btn`}>
      <button
        type="button"
        className="btn float-end mx-2"
        onClick={handleSignUpClick}
        style={{ color: "whitesmoke", backgroundColor: "#8C52FF" }}
      >
        회원가입
      </button>
      <button
        type="button"
        className="btn float-end mx-2"
        onClick={handleSignInClick}
        style={{ color: "whitesmoke", backgroundColor: "#8C52FF" }}
      >
        로그인
      </button>
    </div>
  );
}

export default NotLoginedBtns;
