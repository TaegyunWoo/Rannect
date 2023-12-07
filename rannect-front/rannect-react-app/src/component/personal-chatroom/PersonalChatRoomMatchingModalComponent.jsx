import React from "react";
import Modal from "react-bootstrap/Modal";

function PersonalChatRoomMatchingModal({ show, onHide }) {
  return (
    <Modal show={show} onHide={onHide} id="personalChatMatchingModal">
      <div className="modal-content">
        {/* Modal Header */}
        <div className="modal-header">
          <h4 className="modal-title text-center">1대1 랜덤채팅</h4>
        </div>

        {/* Modal body */}
        <div className="modal-body">
          <div className="text-center h4 text-dark">
            채팅 파트너를 찾는 중...
          </div>
          <div className="spinner-border text-muted mx-auto d-block my-5"></div>
          <button
            type="button"
            className="btn"
            onClick={onHide}
            style={{ backgroundColor: "#F8DCBF", float: "right" }}
          >
            취소
          </button>
        </div>
      </div>
    </Modal>
  );
}

export default PersonalChatRoomMatchingModal;
