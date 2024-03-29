import React from "react";
import Modal from "react-bootstrap/Modal";

//사용자 설정 모달창
function UserSettingModal({
  show,
  handleHide,
  handleChange,
  handleSubmit,
  handleLogout,
  formValue,
  succeedMsg,
  errMsg,
}) {
  return (
    <Modal show={show} onHide={handleHide}>
      <div className="modal-content">
        {/* Modal Header */}
        <div className="modal-header">
          <h4 className="modal-title text-center">사용자 설정</h4>
        </div>

        {/* Modal body */}
        <div className="modal-body">
          {/* Form */}
          <div>
            <form action="" onChange={handleChange} onSubmit={handleSubmit}>
              {errMsg === undefined || errMsg !== "" ? (
                <div
                  className="col-sm-12 my-1 text-center"
                  style={{ color: "red" }}
                >
                  {errMsg}
                </div>
              ) : (
                <></>
              )}
              {succeedMsg === undefined || succeedMsg !== "" ? (
                <div
                  className="col-sm-12 my-1 text-center"
                  style={{ color: "blue" }}
                >
                  {succeedMsg}
                </div>
              ) : (
                <></>
              )}
              <div className="row mx-3 my-3 rounded text-center py-1 form-item">
                <div className="col-sm-3 align-self-center">
                  <label htmlFor="nicknameUpdate" className="form-label">
                    닉네임
                  </label>
                </div>
                <div className="col-sm-9">
                  <input
                    type="text"
                    className="form-control"
                    id="nicknameUpdate"
                    placeholder="기존 사용자 닉네임"
                    name="nickname"
                    value={formValue.nickname}
                  />
                </div>
              </div>
              <div className="row mx-3 my-3 rounded text-center py-1 form-item">
                <div className="col-sm-3 align-self-center">
                  <label htmlFor="topicUpdate" className="form-label">
                    관심 주제
                  </label>
                </div>
                <div className="col-sm-9">
                  <input
                    type="text"
                    className="form-control"
                    id="topicUpdate"
                    placeholder="기존 사용자 관심주제"
                    name="interestedIn"
                    value={formValue.interestedIn}
                  />
                </div>
              </div>
              <div className="row mx-3 my-3 rounded text-center py-1 form-item">
                <button
                  className="btn w-100"
                  style={{ color: "whitesmoke" }}
                  onClick={handleLogout}
                >
                  로그아웃
                </button>
              </div>
              <div className="mx-3 my-3 rounded text-center py-1">
                <span className="float-start ms-5">
                  <button
                    type="button"
                    className="btn"
                    style={{ backgroundColor: "#F8DCBF" }}
                    onClick={handleHide}
                  >
                    취소
                  </button>
                </span>
                <span className="mx-3 my-3 rounded text-center py-1"></span>
                <span className="float-end me-5">
                  <button
                    type="submit"
                    className="btn float-end"
                    style={{ backgroundColor: "#8C52FF" }}
                  >
                    확인
                  </button>
                </span>
              </div>
            </form>
          </div>
        </div>
      </div>
    </Modal>
  );
}

export default UserSettingModal;
