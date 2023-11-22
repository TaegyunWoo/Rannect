import React, { useState } from "react";
import MainHeader from "../component/header/MainHeader";
import LoginedBtns from "../component/header/LoginedBtns";
import NotLoginedBtns from "../component/header/NotLoginedBtns";
import UserSettingModal from "../component/modal/UserSettingModal";
import SignUp from "./SignUp";
import SignInModal from "../component/modal/SignInModal";
import signUpAPI from "../api/member/SignUpAPI";

function Header() {
  const [showSignUpModal, setShowSignUpModal] = useState(false);
  const [showSignInModal, setShowSignInModal] = useState(false);
  const [showUserSettingModal, setShowUserSettingModal] = useState(false);

  //회원가입 관련
  const handleShowSignUpModal = (e) => {
    setShowSignUpModal(true);
  };

  const handleHideSignUpModal = (e) => {
    setShowSignUpModal(false);
  };

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
            handleSignUpClick={handleShowSignUpModal}
            handleSignInClick={handleSignInModal}
          />
        }
      />
      <SignUp
        show={showSignUpModal}
        handleHide={handleHideSignUpModal}
      />
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
