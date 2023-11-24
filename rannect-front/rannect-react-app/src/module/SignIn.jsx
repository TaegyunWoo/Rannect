import React, { useState } from "react";
import SignInModal from "../component/modal/SignInModal";
import callSignInAPI from "../api/member/SignInAPI";

function SignIn({ show, handleHideBasic }) {
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
    callSignInAPI(signInFormData, (res) => {
      console.log(res);
      if (Object.keys(res).includes("code")) {
        if (res.code === "M002") {
          setApiResErrMsg(() => "로그인 정보가 틀립니다.");
        }
      } else {
        handleHideWithClearState();
      }
    });
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
