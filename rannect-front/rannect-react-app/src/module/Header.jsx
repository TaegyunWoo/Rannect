import React, { useState } from "react";
import MainHeaderComponent from "../component/header/MainHeaderComponent";
import LoginedBtnsComponent from "../component/header/LoginedBtnsComponent";
import NotLoginedBtnsComponent from "../component/header/NotLoginedBtnsComponent";
import UserSetting from "./UserSetting";
import SignUp from "./SignUp";
import SignIn from "./SignIn";

function Header({
  signInState,
  setSignInState,
  currentUserInfo,
  setCurrentUserInfo,
}) {
  const [showSignUpModal, setShowSignUpModal] = useState(false);
  const [showSignInModal, setShowSignInModal] = useState(false);
  const [showUserSettingModal, setShowUserSettingModal] = useState(false);

  //회원가입 관련
  const handleShowSignUpModal = () => {
    setShowSignUpModal(true);
  };

  const handleHideSignUpModal = () => {
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

  if (signInState) {
    return (
      <>
        <MainHeaderComponent
          btns={
            <LoginedBtnsComponent
              handleClick={handleUserSettingModal}
              nickname={currentUserInfo.nickname}
            />
          }
        />
        <UserSetting
          show={showUserSettingModal}
          handleHideBasic={handleUserSettingModal}
          setSignInState={setSignInState}
          setCurrentUserInfo={setCurrentUserInfo}
        />
      </>
    );
  } else {
    return (
      <>
        <MainHeaderComponent
          btns={
            <NotLoginedBtnsComponent
              handleSignUpClick={handleShowSignUpModal}
              handleSignInClick={handleSignInModal}
            />
          }
        />
        <SignUp
          show={showSignUpModal}
          handleHideBasic={handleHideSignUpModal}
          setSignInState={setSignInState}
        />
        <SignIn
          show={showSignInModal}
          handleHideBasic={handleSignInModal}
          setSignInState={setSignInState}
          setCurrentUserInfo={setCurrentUserInfo}
        />
      </>
    );
  }
}

export default Header;
