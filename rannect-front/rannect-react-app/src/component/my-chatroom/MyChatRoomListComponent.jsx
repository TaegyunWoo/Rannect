import React from "react";

function MyChatRoomList() {
  return (
    <div className="col-sm-4 mt-5">
      <h1 style={{ textAlign: "left" }}>내 채팅방</h1>
      <div className="ms-2 mt-2" style={{ textAlign: "left" }}>
        현재 참여 중인 채팅방 : <span className="badge ">10개</span>
      </div>
      <div
        className="mt-4 rounded overflow-auto border shadow"
        style={{ height: "265px" }}
      >
        <div className="mt-1 mb-1 ms-1 me-1">
          {/* List Header */}
          <div className="row mx-auto mb-2 list-header text-center">
            <span className="col-sm-3 text-truncate">분류</span>
            <span className="col-sm-5 text-truncate">제목</span>
            <span className="col-sm-4 text-truncate">새 메시지</span>
          </div>
          {/* List Header (End) */}
          {/* List Body */}
          <div
            className="row mx-auto my-1 list-body list-item rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-3 text-truncate">[개인]</span>
            <span className="col-sm-5 text-truncate">
              상대방 닉네임 1234567899999
            </span>
            <span className="col-sm-4">
              <span className="col-sm-4 badge mx-auto d-block">3</span>
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item rounded"
            data-bs-toggle="modal"
            data-bs-target="#groupChatRoomModal"
          >
            <span className="col-sm-3 text-truncate">[그룹]</span>
            <span className="col-sm-5 text-truncate">
              그룹 채팅방 제목 1234567899999
            </span>
            <span className="col-sm-4">
              <span className="col-sm-4 badge mx-auto d-block">100</span>
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-3 text-truncate">[개인]</span>
            <span className="col-sm-5 text-truncate">
              상대방 닉네임 1234567899999
            </span>
            <span className="col-sm-4">
              <span className="col-sm-4 badge mx-auto d-block">3</span>
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item rounded"
            data-bs-toggle="modal"
            data-bs-target="#groupChatRoomModal"
          >
            <span className="col-sm-3 text-truncate">[그룹]</span>
            <span className="col-sm-5 text-truncate">
              그룹 채팅방 제목 1234567899999
            </span>
            <span className="col-sm-4">
              <span className="col-sm-4 badge mx-auto d-block">100</span>
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item rounded"
            data-bs-toggle="modal"
            data-bs-target="#groupChatRoomModal"
          >
            <span className="col-sm-3 text-truncate">[그룹]</span>
            <span className="col-sm-5 text-truncate">
              그룹 채팅방 제목 1234567899999
            </span>
            <span className="col-sm-4">
              <span className="col-sm-4 badge mx-auto d-block">100</span>
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-3 text-truncate">[개인]</span>
            <span className="col-sm-5 text-truncate">
              상대방 닉네임 123456789
            </span>
            <span className="col-sm-4">
              <span className="col-sm-4 badge mx-auto d-block">3</span>
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item rounded"
            data-bs-toggle="modal"
            data-bs-target="#groupChatRoomModal"
          >
            <span className="col-sm-3 text-truncate">[그룹]</span>
            <span className="col-sm-5 text-truncate">
              그룹 채팅방 제목 1234567899999
            </span>
            <span className="col-sm-4">
              <span className="col-sm-4 badge mx-auto d-block">100</span>
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-3 text-truncate">[개인]</span>
            <span className="col-sm-5 text-truncate">
              상대방 닉네임 123456789
            </span>
            <span className="col-sm-4">
              <span className="col-sm-4 badge mx-auto d-block">3</span>
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item rounded"
            data-bs-toggle="modal"
            data-bs-target="#groupChatRoomModal"
          >
            <span className="col-sm-3 text-truncate">[그룹]</span>
            <span className="col-sm-5 text-truncate">
              그룹 채팅방 제목 1234567899999
            </span>
            <span className="col-sm-4">
              <span className="col-sm-4 badge mx-auto d-block">100</span>
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-3 text-truncate">[개인]</span>
            <span className="col-sm-5 text-truncate">
              상대방 닉네임 123456789
            </span>
            <span className="col-sm-4">
              <span className="col-sm-4 badge mx-auto d-block">3</span>
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item rounded"
            data-bs-toggle="modal"
            data-bs-target="#groupChatRoomModal"
          >
            <span className="col-sm-3 text-truncate">[그룹]</span>
            <span className="col-sm-5 text-truncate">
              그룹 채팅방 제목 1234567899999
            </span>
            <span className="col-sm-4">
              <span className="col-sm-4 badge mx-auto d-block">100</span>
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-3 text-truncate">[개인]</span>
            <span className="col-sm-5 text-truncate">
              상대방 닉네임 123456789
            </span>
            <span className="col-sm-4">
              <span className="col-sm-4 badge mx-auto d-block">3</span>
            </span>
          </div>
          {/* List Body (End) */}
        </div>
      </div>
      <div className="col-sm-8 pt-5 mx-auto">
        <button type="button" className="btn chat-start-btn w-100 mx-auto">
          모든 채팅방에서 나가기
        </button>
      </div>
    </div>
  );
}

export default MyChatRoomList;
