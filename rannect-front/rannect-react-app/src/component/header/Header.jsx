import React, { useState } from "react";
import MainHeader from "./presentation/MainHeader";
import LoginedBtns from "./presentation/LoginedBtns";
import NotLoginedBtns from "./presentation/NotLoginedBtns";
import UserSettingModal from "../modal/presentation/UserSettingModal";
import SignUpModal from "../modal/presentation/SignUpModal";
import SignInModal from "../modal/presentation/SignInModal";

function Header() {
  const [showSignUpModal, setShowSignUpModal] = useState(false);
  const [showSignInModal, setShowSignInModal] = useState(false);
  const [showUserSettingModal, setShowUserSettingModal] = useState(false);

  const handleSignUpModal = () => {
    setShowSignUpModal(!showSignUpModal);
  };

  const handleSignInModal = () => {
    setShowSignInModal(!showSignInModal);
  };

  const handleUserSettingModal = () => {
    setShowUserSettingModal(!showUserSettingModal);
  };

  /*
   * 로그인 상태라면 아래 반환
   * return (
   *   <>
   *     <MainHeader btns={<NotLoginedBtns />} />
   *     <SignUpModal show={handleSignUpModal} />
   *   </>
   * );
   */
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
      <SignUpModal show={showSignUpModal} />
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
