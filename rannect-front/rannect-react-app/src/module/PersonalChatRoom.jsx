import React from "react";
import PersonalChatRoomComponent from "../component/personal-chatroom/PersonalChatRoomComponent";

function PersonalChatRoom({ show, handleHide }) {
  return <PersonalChatRoomComponent show={show} onHide={handleHide} />;
}

export default PersonalChatRoom;
