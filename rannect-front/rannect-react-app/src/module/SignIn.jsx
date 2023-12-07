import React, { useState } from "react";
import SignInModalComponent from "../component/etc-modal/SignInModalComponent";
import callSignInAPI from "../api/member/SignInAPI";

function SignIn({ show, handleHideBasic, setSignInState, setCurrentUserInfo }) {
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
      ({ memberInfo, tokenInfo }) => {
        //SignIn 성공시
        localStorage.setItem("refreshToken", tokenInfo.refreshToken);
        setCurrentUserInfo(memberInfo);
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
    <SignInModalComponent
      show={show}
      handleHide={handleHideWithClearState}
      handleChange={handleSignInChange}
      handleSubmit={handleSignInSubmit}
      errMsg={apiResErrMsg}
    />
  );
}

export default SignIn;
