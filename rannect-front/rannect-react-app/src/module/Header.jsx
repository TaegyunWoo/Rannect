import React, { useState } from "react";
import MainHeader from "../component/header/MainHeader";
import LoginedBtns from "../component/header/LoginedBtns";
import NotLoginedBtns from "../component/header/NotLoginedBtns";
import UserSettingModal from "../component/modal/UserSettingModal";
import SignUp from "./SignUp";
import SignIn from "./SignIn";

function Header({ signInState, setSignInState }) {
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
        <MainHeader
          btns={<LoginedBtns handleClick={handleUserSettingModal} />}
        />
        <UserSettingModal show={showUserSettingModal} />
      </>
    );
  } else {
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
          handleHideBasic={handleHideSignUpModal}
        />
        <SignIn
          show={showSignInModal}
          handleHideBasic={handleSignInModal}
          setSignInState={setSignInState}
        />
      </>
    );
  }
}

export default Header;
