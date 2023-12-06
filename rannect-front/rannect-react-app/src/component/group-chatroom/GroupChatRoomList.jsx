import React from "react";

function GroupChatRoomList() {
  return (
    <div className="col-sm-4 mt-5">
      <h1 style={{ textAlign: "left" }}>개설된 그룹 채팅방</h1>
      <div className="ms-2 mt-2" style={{ textAlign: "left" }}>
        현재 그룹 채팅방 : <span className="badge">10개</span>
      </div>
      <div className="mt-4">
        <div className="row mb-2">
          <div className="col-sm-6" style={{ marginTop: "0.5rem" }}>
            <div className="card">
              <div className="card-body" style={{ padding: "0.2rem" }}>
                <div className="col-sm-10" style={{ textAlign: "left" }}>
                  그룹 채팅방 제목
                </div>
                <span
                  className="badge"
                  style={{ float: "right", margin: "0rem 0.3rem" }}
                >
                  3 / 10
                </span>
                <button
                  type="button"
                  className="btn mt-3 w-100 group-chat-btn"
                  data-bs-toggle="modal"
                  data-bs-target="#groupChatRoomModal"
                >
                  입장
                </button>
              </div>
            </div>
          </div>
          <div className="col-sm-6" style={{ marginTop: "0.5rem" }}>
            <div className="card">
              <div className="card-body" style={{ padding: "0.2rem" }}>
                <div className="col-sm-10" style={{ textAlign: "left" }}>
                  그룹 채팅방 제목
                </div>
                <span
                  className="badge"
                  style={{ float: "right", margin: "0rem 0.3rem" }}
                >
                  3 / 10
                </span>
                <button
                  type="button"
                  className="btn mt-3 w-100 group-chat-btn"
                  data-bs-toggle="modal"
                  data-bs-target="#groupChatRoomModal"
                >
                  입장
                </button>
              </div>
            </div>
          </div>
        </div>
        <div className="row mb-2">
          <div className="col-sm-6" style={{ marginTop: "0.5rem" }}>
            <div className="card">
              <div className="card-body" style={{ padding: "0.2rem" }}>
                <div className="col-sm-10" style={{ textAlign: "left" }}>
                  그룹 채팅방 제목
                </div>
                <span
                  className="badge"
                  style={{ float: "right", margin: "0rem 0.3rem" }}
                >
                  3 / 10
                </span>
                <button
                  type="button"
                  className="btn mt-3 w-100 group-chat-btn"
                  data-bs-toggle="modal"
                  data-bs-target="#groupChatRoomModal"
                >
                  입장
                </button>
              </div>
            </div>
          </div>
          <div className="col-sm-6" style={{ marginTop: "0.5rem" }}>
            <div className="card">
              <div className="card-body" style={{ padding: "0.2rem" }}>
                <div className="col-sm-10" style={{ textAlign: "left" }}>
                  그룹 채팅방 제목
                </div>
                <span
                  className="badge"
                  style={{ float: "right", margin: "0rem 0.3rem" }}
                >
                  3 / 10
                </span>
                <button
                  type="button"
                  className="btn mt-3 w-100 group-chat-btn"
                  data-bs-toggle="modal"
                  data-bs-target="#groupChatRoomModal"
                >
                  입장
                </button>
              </div>
            </div>
          </div>
        </div>
        <div>
          <button
            type="button"
            className="btn btn-light w-25"
            style={{ float: "left" }}
          >
            <img src="/icon/caret-left-fill.svg" />
          </button>
          <button
            type="button"
            className="btn btn-light w-25"
            style={{ float: "right" }}
          >
            <img src="/icon/caret-right-fill.svg" />
          </button>
        </div>
      </div>
      <div className="col-sm-8 pt-5 mx-auto mt-4">
        <button
          type="button"
          className="btn chat-start-btn w-100 mx-auto"
          data-bs-toggle="modal"
          data-bs-target="#groupChatMatchingModal"
        >
          그룹 랜덤채팅 시작
        </button>
      </div>
    </div>
  );
}

export default GroupChatRoomList;
