import React from "react";
import PersonalChatRoomList from "../personal-chatroom/PersonalChatRootList";
import GroupChatRoomList from "../group-chatroom/GroupChatRoomList";
import MyChatRoomList from "../my-chatroom/MyChatRoomList";

function FirstMainRow() {
  return (
    <div className="row ms-4 me-4 mb-5 mx-auto">
      <PersonalChatRoomList />
      <GroupChatRoomList />
      <MyChatRoomList />
    </div>
  );
}

export default FirstMainRow;
