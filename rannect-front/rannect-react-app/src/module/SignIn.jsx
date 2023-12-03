import React, { useState } from "react";
import SignInModal from "../component/modal/SignInModal";
import callSignInAPI from "../api/member/SignInAPI";

function SignIn({ show, handleHideBasic, setSignInState }) {
  const [signInFormData, setSignInFormData] = useState({});
  const [apiResErrMsg, setApiResErrMsg] = useState("");

  const handleSignInChange = ({ target }) => {
    setSignInFormData((prev) => {
      const newReq = { ...prev };
      newReq[target.name] = target.value;
      return newReq;
    });
  };

  const handleSignInSubmit = (e) => {
    e.preventDefault();
    callSignInAPI(
      signInFormData,
      (res) => {
        //SignIn 성공시
        localStorage.setItem("refreshToken", res.refreshToken);
        handleHideWithClearState();
        setSignInState(true);
      },
      (errRes) => {
        //SignIn 실패시
        if (errRes.code === "A001") {
          setApiResErrMsg(() => "로그인 정보가 틀립니다.");
        }
      }
    );
  };

  const handleHideWithClearState = () => {
    setSignInFormData({});
    setApiResErrMsg("");
    handleHideBasic();
  };

  return (
    <SignInModal
      show={show}
      handleHide={handleHideWithClearState}
      handleChange={handleSignInChange}
      handleSubmit={handleSignInSubmit}
      errMsg={apiResErrMsg}
    />
  );
}

export default SignIn;
