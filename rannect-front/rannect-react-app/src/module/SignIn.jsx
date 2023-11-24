import React, { useState } from "react";
import SignInModal from "../component/modal/SignInModal";

function SignIn({ show, handleHideBasic }) {
  const [signInFormData, setSignInFormData] = useState({});
  const [apiResErrMsg, setApiResErrMsg] = useState("");

  const handleSignInChange = ({ target }) => {
    console.log(target.name + ":" + target.value);
    setSignInFormData((prev) => {
      const newReq = { ...prev };
      newReq[target.name] = target.value;
      return newReq;
    });
  };

  const handleSignInSubmit = (e) => {
    e.preventDefault();
    //API 호출에 따른 동작 처리
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
