import React, { useState, useEffect } from "react";
import SignUpModal from "../component/modal/SignUpModal";
import callSignUpAPI from "../api/member/SignUpAPI";

function SignUp({show, handleHide}) {
  const [signUpFormData, setSignUpFormData] = useState({});

  const callSignUpApi = (req) => {
    callSignUpAPI(req);
  };

  const handleSignUpChange = ({ target }) => {
    setSignUpFormData((prev) => {
      const newReq = {...prev};
      newReq[target.name] = target.value;
      return newReq;
    });
  };

  const handleSignUpSubmit = (e) => {
    e.preventDefault();
    callSignUpApi(signUpFormData);
    handleHide();
  };

  return (
    <SignUpModal
      show={show}
      handleChange={handleSignUpChange}
      handleSubmit={handleSignUpSubmit}
    />
  );
}

export default SignUp;
