import React, { useState } from "react";
import MainHeader from "../component/header/MainHeader";
import LoginedBtns from "../component/header/LoginedBtns";
import NotLoginedBtns from "../component/header/NotLoginedBtns";
import UserSettingModal from "../component/modal/UserSettingModal";
import SignUpModal from "../component/modal/SignUpModal";
import SignInModal from "../component/modal/SignInModal";
import signUpAPI from "../api/member/SignUpAPI";

function Header() {
  const [showSignUpModal, setShowSignUpModal] = useState(false);
  const [newAccountId, setNewAccountId] = useState("");
  const [showSignInModal, setShowSignInModal] = useState(false);
  const [showUserSettingModal, setShowUserSettingModal] = useState(false);

  //회원가입 관련
  const handleSignUpModal = (e) => {
    setShowSignUpModal(!showSignUpModal);
  };

  const handleSignUpChange = (e) => {
    console.log(e.target);
  };

  const handleSignUpSubmit = (e) => {};

  //로그인 관련
  const handleSignInModal = (e) => {
    setShowSignInModal(!showSignInModal);
  };

  //사용자 세팅 관련
  const handleUserSettingModal = (e) => {
    setShowUserSettingModal(!showUserSettingModal);
  };

  // 로그인 상태라면 아래 반환
  // return (
  //   <>
  //     <MainHeader btns={<LoginedBtns handleClick={handleUserSettingModal} />} />
  //     <UserSettingModal show={showUserSettingModal} />
  //   </>
  // );
  // signUpAPI({
  //   newAccountId: "hello",
  //   newPassword: "myPassword",
  //   newNickname: "myNickname",
  //   interestedIn: "hihihihihi",
  // });
  return (
    <>
      <MainHeader
        btns={
          <NotLoginedBtns
            handleSignUpClick={handleSignUpModal}
            handleSignInClick={handleSignInModal}
          />
        }
      />
      <SignUpModal
        show={showSignUpModal}
        handleChange={handleSignUpChange}
        handleSubmit={handleSignUpSubmit}
      />
      <SignInModal show={showSignInModal} />
    </>
  );
  // return (
  //   <>
  //     <MainHeader btns={<LoginedBtns handleClick={handleUserSettingModal} />} />
  //     <UserSettingModal show={showUserSettingModal} />
  //   </>
  // );
}

export default Header;
