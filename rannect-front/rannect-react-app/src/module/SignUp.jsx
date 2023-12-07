import React, { useState, useEffect } from "react";
import SignUpModalComponent from "../component/etc-modal/SignUpModalComponent";
import callSignUpAPI from "../api/member/SignUpAPI";
import callSignInAPI from "../api/member/SignInAPI";

function SignUp({ show, handleHideBasic, setSignInState }) {
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
    callSignUpAPI(
      signUpFormData,
      () => {
        //SignUp 성공시
        callSignInAPI(signUpFormData, (res) => {
          localStorage.setItem("refreshToken", res.refreshToken);
          handleHideWithClearState();
          setSignInState(true);
        });
        handleHideWithClearState();
      },
      (errRes) => {
        //SignUp 실패시
        if (errRes.code === "M001") {
          setApiResErrMsg(() => "아이디가 이미 존재합니다.");
        } else if (errRes.code === "C001" && errRes.fieldErrors.length > 0) {
          if (errRes.fieldErrors[0].field === "accountId") {
            setApiResErrMsg(
              () => "아이디는 4자 이상, 12자 이하, 영문자 및 숫자만 가능합니다."
            );
          } else if (errRes.fieldErrors[0].field === "rawPassword") {
            setApiResErrMsg(
              () => "비밀번호는 6자 이상, 15자 이하, 영문 및 숫자만 가능합니다."
            );
          } else if (errRes.fieldErrors[0].field === "nickname") {
            setApiResErrMsg(
              () => "닉네임은 2자 이상, 15자 이하, 영문·한글·숫자만 가능합니다."
            );
          } else if (errRes.fieldErrors[0].field === "interestedIn") {
            setApiResErrMsg(
              () => "관심 주제는 50자 이하, 영문·한글·숫자만 가능합니다."
            );
          }
        }
      }
    );
  };

  const handleHideWithClearState = () => {
    setSignUpFormData({});
    setApiResErrMsg("");
    handleHideBasic();
  };

  return (
    <SignUpModalComponent
      show={show}
      handleHide={handleHideWithClearState}
      handleChange={handleSignUpChange}
      handleSubmit={handleSignUpSubmit}
      errMsg={apiResErrMsg}
    />
  );
}

export default SignUp;
