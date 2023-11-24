import React, { useState, useEffect } from "react";
import SignUpModal from "../component/modal/SignUpModal";
import callSignUpAPI from "../api/member/SignUpAPI";

function SignUp({ show, handleHideBasic }) {
  const [signUpFormData, setSignUpFormData] = useState({});
  const [apiResErrMsg, setApiResErrMsg] = useState("");

  const handleSignUpChange = ({ target }) => {
    setSignUpFormData((prev) => {
      const newReq = { ...prev };
      newReq[target.name] = target.value;
      return newReq;
    });
  };

  const handleSignUpSubmit = (e) => {
    e.preventDefault();
    callSignUpAPI(signUpFormData, (res) => {
      if (Object.keys(res).includes("code")) {
        if (res.code === "M001") {
          setApiResErrMsg(() => "아이디가 이미 존재합니다.");
        } else if (res.code === "C001" && res.fieldErrors.length > 0) {
          if (res.fieldErrors[0].field === "accountId") {
            setApiResErrMsg(
              () => "아이디는 4자 이상, 12자 이하, 영문만 가능합니다."
            );
          } else if (res.fieldErrors[0].field === "rawPassword") {
            setApiResErrMsg(
              () => "비밀번호는 6자 이상, 12자 이하, 영문 및 숫자만 가능합니다."
            );
          } else if (res.fieldErrors[0].field === "nickname") {
            setApiResErrMsg(
              () => "닉네임은 2자 이상, 15자 이하, 영문·한글·숫자만 가능합니다."
            );
          } else if (res.fieldErrors[0].field === "interestedIn") {
            setApiResErrMsg(
              () => "관심 주제는 50자 이하, 영문·한글·숫자만 가능합니다."
            );
          }
        }
      } else {
        handleHideWithClearState();
      }
    });
  };

  const handleHideWithClearState = () => {
    setSignUpFormData({});
    setApiResErrMsg("");
    handleHideBasic();
  };

  return (
    <SignUpModal
      show={show}
      handleHide={handleHideWithClearState}
      handleChange={handleSignUpChange}
      handleSubmit={handleSignUpSubmit}
      errMsg={apiResErrMsg}
    />
  );
}

export default SignUp;
