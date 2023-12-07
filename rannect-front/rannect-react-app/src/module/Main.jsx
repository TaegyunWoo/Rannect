import React, { useState, useEffect } from "react";
import FirstMainRowComponent from "../component/main/FirstMainRowComponent";
import SecondMainRowComponent from "../component/main/SecondMainRowComponent";
import PersonalChatRoomMatching from "./PersonalChatRoomMatching";
import PersonalChatRoom from "./PersonalChatRoom";

function Main() {
  //1대1 랜덤 채팅 매칭 로딩 모달 관련
  const [showPersonalChatMatchingModal, setShowPersonalChatMathcingModal] =
    useState(false);
  const handleShowPersonalChatMatchingModal = () => {
    setShowPersonalChatMathcingModal(true);
  };
  const handleHidePersonalChatMatchingModal = () => {
    setShowPersonalChatMathcingModal(false);
  };
  //1대1 랜덤 채팅방 모달 관련
  const [showPersonalChatRoomModal, setShowPersonalChatRoomModal] =
    useState(false);
  const handleShowPersonalChatRoomModal = () => {
    setShowPersonalChatRoomModal(true);
  };
  const handleHidePersonalChatRoomModal = () => {
    setShowPersonalChatRoomModal(false);
  };

  return (
    <>
      <FirstMainRowComponent
        handleShowPersonalChatMatchingModal={
          handleShowPersonalChatMatchingModal
        }
        handleShowPersonalChatRoomModal={handleShowPersonalChatRoomModal}
      />
      <PersonalChatRoom
        show={showPersonalChatRoomModal}
        handleHide={handleHidePersonalChatRoomModal}
      />
      <PersonalChatRoomMatching
        show={showPersonalChatMatchingModal}
        handleHide={handleHidePersonalChatMatchingModal}
      />
      <SecondMainRowComponent />
    </>
  );
}

export default Main;
