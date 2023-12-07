import React from "react";
import PersonalChatRoomList from "../personal-chatroom/PersonalChatRoomListComponent";
import GroupChatRoomList from "../group-chatroom/GroupChatRoomListComponent";
import MyChatRoomList from "../my-chatroom/MyChatRoomListComponent";

function FirstMainRow({
  handleShowPersonalChatMatchingModal,
  handleShowPersonalChatRoomModal,
}) {
  return (
    <div className="row ms-4 me-4 mb-5 mx-auto">
      <PersonalChatRoomList
        handleShowPersonalChatMatchingModal={
          handleShowPersonalChatMatchingModal
        }
        handleShowPersonalChatRoomModal={handleShowPersonalChatRoomModal}
      />
      <GroupChatRoomList />
      <MyChatRoomList />
    </div>
  );
}

export default FirstMainRow;
