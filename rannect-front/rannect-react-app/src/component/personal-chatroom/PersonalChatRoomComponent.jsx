import React from "react";
import Modal from "react-bootstrap/Modal";

function PersonalChatRoom({ show, onHide, handleExit, onKeyUpInput }) {
  return (
    <Modal show={show} onHide={onHide} id="personalChatRoomModal">
      <div className="modal-content">
        {/* Modal Header */}
        <div className="modal-header">
          <h4 className="modal-title text-center">[개인] 상대 닉네임</h4>
          <span className="ms-2">
            <button
              type="button"
              className="btn"
              onClick={onHide}
              style={{ backgroundColor: "#ff9252", float: "right" }}
            >
              창 닫기
            </button>
            <button
              type="button"
              className="btn mr-2"
              onClick={handleExit}
              style={{ backgroundColor: "#F8DCBF", float: "right" }}
            >
              방 나가기
            </button>
          </span>
        </div>

        {/* Modal body */}
        <div className="modal-body">
          {/* 메시지 영역 */}
          <div
            className="overflow-auto px-3 shadow-sm"
            style={{ height: "35rem" }}
          >
            {/* 상대 메시지 */}
            <div style={{ float: "left" }}>
              <span className="px-2 py-2 me-5 my-2 rounded chat-message-body">
                <div>
                  [상대방 메시지 내용]
                  <br />
                  메시지 내용 메시지 내용 메시지 내용 메시지 내용 메시지 내용
                  메시지 내용 메시지 내용 메시지 내용 메시지 내용 메시지 내용
                  메시지 내용 메시지 내용
                </div>
                <small
                  className="d-block ms-5 mt-1"
                  style={{ color: "#F8DCBF", float: "right" }}
                >
                  2023.03.14 16:47
                </small>
              </span>
            </div>
            {/* 내 메시지 */}
            <div style={{ float: "right" }}>
              <span className="px-2 py-2 ms-5 my-2 rounded chat-message-body">
                <div>
                  [내 메시지 내용]
                  <br />
                  메시지 내용 메시지 내용 메시지 내용 메시지 내용 메시지 내용
                  메시지 내용 메시지 내용 메시지 내용 메시지 내용 메시지 내용
                  메시지 내용 메시지 내용
                </div>
                <small
                  className="d-block ms-5 mt-1"
                  style={{ color: "#F8DCBF", float: "right" }}
                >
                  2023.03.14 16:47
                </small>
              </span>
            </div>
            {/* 상대 메시지 */}
            <div style={{ float: "left" }}>
              <span className="px-2 py-2 me-5 my-2 rounded chat-message-body">
                <div>
                  [상대방 메시지 내용]
                  <br />
                  메시지 내용
                </div>
                <small
                  className="d-block ms-5 mt-1"
                  style={{ color: "#F8DCBF", float: "right" }}
                >
                  2023.03.14 16:47
                </small>
              </span>
            </div>
            {/* 내 메시지 */}
            <div style={{ float: "right" }}>
              <span className="px-2 py-2 ms-5 my-2 rounded chat-message-body">
                <div>
                  [내 메시지 내용]
                  <br />
                  메시지 내용 메시지 내용 메시지 내용 메시지 내용
                </div>
                <small
                  className="d-block ms-5 mt-1"
                  style={{ color: "#F8DCBF", float: "right" }}
                >
                  2023.03.14 16:47
                </small>
              </span>
            </div>
            {/* 내 메시지 */}
            <div style={{ float: "right" }}>
              <span className="px-2 py-2 ms-5 my-2 rounded chat-message-body">
                <div>
                  [내 메시지 내용]
                  <br />
                  메시지 내용 메시지 내용 메시지 내용 메시지 내용
                </div>
                <small
                  className="d-block ms-5 mt-1"
                  style={{ color: "#F8DCBF", float: "right" }}
                >
                  2023.03.14 16:47
                </small>
              </span>
            </div>
            {/* 내 메시지 */}
            <div style={{ float: "right" }}>
              <span className="px-2 py-2 ms-5 my-2 rounded chat-message-body">
                <div>
                  [내 메시지 내용]
                  <br />
                  메시지 내용 메시지 내용 메시지 내용 메시지 내용
                </div>
                <small
                  className="d-block ms-5 mt-1"
                  style={{ color: "#F8DCBF", float: "right" }}
                >
                  2023.03.14 16:47
                </small>
              </span>
            </div>
            {/* 내 메시지 */}
            <div style={{ float: "right" }}>
              <span className="px-2 py-2 ms-5 my-2 rounded chat-message-body">
                <div>
                  [내 메시지 내용]
                  <br />
                  메시지 내용 메시지 내용 메시지 내용 메시지 내용
                </div>
                <small
                  className="d-block ms-5 mt-1"
                  style={{ color: "#F8DCBF", float: "right" }}
                >
                  2023.03.14 16:47
                </small>
              </span>
            </div>
          </div>
          {/* 메시지 영역 끝 */}
          {/* 메시지 입력 영역 */}
          <div className="row mt-3" action="">
            <div className="col-sm-10">
              <input
                type="text"
                // onKeyUp={(e) => {if(e.target.onKeyUp==13)}}
                className="form-control"
                placeholder="메시지를 입력해주세요."
                name="message"
              />
            </div>
            <div className="col-sm-2">
              <button type="submit" className="btn message-send-btn">
                <img className="mx-auto" src="/icon/send-fill.svg" />
              </button>
            </div>
          </div>
        </div>
      </div>
    </Modal>
  );
}

export default PersonalChatRoom;
