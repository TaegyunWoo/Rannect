import React, { useState, useEffect } from "react";
import PersonalChatRoomMatchingModalComponent from "../component/personal-chatroom/PersonalChatRoomMatchingModalComponent";

function PersonalChatRoomMatching({ show, handleHide }) {
  return (
    <PersonalChatRoomMatchingModalComponent show={show} onHide={handleHide} />
  );
}

export default PersonalChatRoomMatching;
