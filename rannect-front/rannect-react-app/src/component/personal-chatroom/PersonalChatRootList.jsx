import React from "react";

function PersonalChatRoomList() {
  return (
    <div className="col-sm-4 mt-5">
      <h1 style={{ textAlign: "left" }}>1대1 랜덤 채팅</h1>
      <div className="ms-2 mt-2" style={{ textAlign: "left" }}>
        현재 접속자 : <span className="badge ">10명</span>
      </div>
      <div
        className="mt-4 rounded overflow-auto border shadow"
        style={{ height: "265px" }}
      >
        <div className="mt-1 mb-1 ms-1 me-1">
          {/* List Header */}
          <div className="row mx-auto mb-2 list-header text-center">
            <span className="col-sm-4 text-truncate">유저 닉네임</span>
            <span className="col-sm-6 text-truncate">관심주제</span>
            <span className="col-sm-2 text-truncate">채팅</span>
          </div>
          {/* List Header (End) */}
          {/* List Body */}
          <div
            className="row mx-auto my-1 list-body list-item d-flex flex-wrap align-items-center rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-4 text-truncate">
              유저 닉네임 123456789
            </span>
            <span className="col-sm-6 text-truncate">
              관심주제 123456789ABCDEFG
            </span>
            <span className="col-sm-2 text-truncate">
              <img src="/icon/chat-dots-fill.svg" className="mx-auto d-block" />
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item d-flex flex-wrap align-items-center rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-4 text-truncate">
              유저 닉네임 123456789
            </span>
            <span className="col-sm-6 text-truncate">
              관심주제 123456789ABCDEFG
            </span>
            <span className="col-sm-2 text-truncate">
              <img src="/icon/chat-dots-fill.svg" className="mx-auto d-block" />
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item d-flex flex-wrap align-items-center rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-4 text-truncate">
              유저 닉네임 123456789
            </span>
            <span className="col-sm-6 text-truncate">
              관심주제 123456789ABCDEFG
            </span>
            <span className="col-sm-2 text-truncate">
              <img src="/icon/chat-dots-fill.svg" className="mx-auto d-block" />
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item d-flex flex-wrap align-items-center rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-4 text-truncate">
              유저 닉네임 123456789
            </span>
            <span className="col-sm-6 text-truncate">
              관심주제 123456789ABCDEFG
            </span>
            <span className="col-sm-2 text-truncate">
              <img src="/icon/chat-dots-fill.svg" className="mx-auto d-block" />
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item d-flex flex-wrap align-items-center rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-4 text-truncate">
              유저 닉네임 123456789
            </span>
            <span className="col-sm-6 text-truncate">
              관심주제 123456789ABCDEFG
            </span>
            <span className="col-sm-2 text-truncate">
              <img src="/icon/chat-dots-fill.svg" className="mx-auto d-block" />
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item d-flex flex-wrap align-items-center rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-4 text-truncate">
              유저 닉네임 123456789
            </span>
            <span className="col-sm-6 text-truncate">
              관심주제 123456789ABCDEFG
            </span>
            <span className="col-sm-2 text-truncate">
              <img src="/icon/chat-dots-fill.svg" className="mx-auto d-block" />
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item d-flex flex-wrap align-items-center rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-4 text-truncate">
              유저 닉네임 123456789
            </span>
            <span className="col-sm-6 text-truncate">
              관심주제 123456789ABCDEFG
            </span>
            <span className="col-sm-2 text-truncate">
              <img src="/icon/chat-dots-fill.svg" className="mx-auto d-block" />
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item d-flex flex-wrap align-items-center rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-4 text-truncate">
              유저 닉네임 123456789
            </span>
            <span className="col-sm-6 text-truncate">
              관심주제 123456789ABCDEFG
            </span>
            <span className="col-sm-2 text-truncate">
              <img src="/icon/chat-dots-fill.svg" className="mx-auto d-block" />
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item d-flex flex-wrap align-items-center rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-4 text-truncate">
              유저 닉네임 123456789
            </span>
            <span className="col-sm-6 text-truncate">
              관심주제 123456789ABCDEFG
            </span>
            <span className="col-sm-2 text-truncate">
              <img src="/icon/chat-dots-fill.svg" className="mx-auto d-block" />
            </span>
          </div>
          <div
            className="row mx-auto my-1 list-body list-item d-flex flex-wrap align-items-center rounded"
            data-bs-toggle="modal"
            data-bs-target="#personalChatRoomModal"
          >
            <span className="col-sm-4 text-truncate">
              유저 닉네임 123456789
            </span>
            <span className="col-sm-6 text-truncate">
              관심주제 123456789ABCDEFG
            </span>
            <span className="col-sm-2 text-truncate">
              <img src="/icon/chat-dots-fill.svg" className="mx-auto d-block" />
            </span>
          </div>
          {/* List Body (End) */}
        </div>
      </div>
      <span className="float-end text-muted" style={{ float: "right" }}>
        현재 접속 중인 사용자 목록
      </span>
      {/* Button to Open the Modal */}
      <div className="col-sm-8 mt-5 mx-auto">
        <button
          type="button"
          className="btn chat-start-btn w-100 mx-auto"
          data-bs-toggle="modal"
          data-bs-target="#personalChatMatchingModal"
        >
          1대1 랜덤채팅 시작
        </button>
      </div>
    </div>
  );
}

export default PersonalChatRoomList;
