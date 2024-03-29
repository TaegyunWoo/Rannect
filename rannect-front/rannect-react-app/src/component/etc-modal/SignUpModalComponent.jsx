import React from "react";
import Modal from "react-bootstrap/Modal";

function SignUpModal({
  show,
  handleHide,
  handleChange,
  handleSubmit,
  errMsg,
}) {
  return (
    <Modal show={show} onHide={handleHide}>
      <div className="modal-content">
        {/* Modal Header */}
        <div className="modal-header">
          <h4 className="modal-title text-center">회원가입</h4>
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
              <div className="row mx-3 my-3 rounded text-center py-1 form-item">
                <div className="col-sm-3 align-self-center">
                  <label htmlFor="signUpUserId" className="form-label">
                    <div className="form-label">아이디</div>
                  </label>
                </div>
                <div className="col-sm-9">
                  <input
                    type="text"
                    className="form-control"
                    id="signUpUserId"
                    placeholder="4자리 이상, 12자리 이하, 영문 및 숫자"
                    name="userId"
                  />
                </div>
              </div>
              <div className="row mx-3 my-3 rounded text-center py-1 form-item">
                <div className="col-sm-3 align-self-center">
                  <label htmlFor="signUpPassword" className="form-label">
                    <div className="form-label">비밀번호</div>
                  </label>
                </div>
                <div className="col-sm-9">
                  <input
                    type="password"
                    className="form-control"
                    id="signUpPassword"
                    placeholder="6자 이상, 15자 이하, 영어, 숫자"
                    name="password"
                  />
                </div>
              </div>
              <div className="row mx-3 my-3 rounded text-center py-1 form-item">
                <div className="col-sm-3 align-self-center">
                  <label htmlFor="signUpNickname" className="form-label">
                    <div className="form-label">닉네임</div>
                  </label>
                </div>
                <div className="col-sm-9">
                  <input
                    type="text"
                    className="form-control"
                    id="signUpNickname"
                    placeholder="2자 이상, 15자 이하, 영어, 숫자, 한글"
                    name="nickname"
                  />
                </div>
              </div>
              <div className="row mx-3 my-3 rounded text-center py-1 form-item">
                <div className="col-sm-3 align-self-center">
                  <label htmlFor="signUptopic" className="form-label">
                    <div className="form-label">관심 주제</div>
                  </label>
                </div>
                <div className="col-sm-9">
                  <input
                    type="text"
                    className="form-control"
                    id="signUptopic"
                    placeholder="50자 이하, 영어, 숫자, 한글"
                    name="topic"
                  />
                </div>
              </div>
              <div className="mx-3 my-3 rounded text-center py-1">
                <button
                  type="button"
                  className="btn"
                  onClick={handleHide}
                  style={{ backgroundColor: "#F8DCBF" }}
                >
                  취소
                </button>
                <span className="mx-3 my-3 rounded text-center py-1"></span>
                <button
                  type="submit"
                  className="btn"
                  style={{ backgroundColor: "#8C52FF", color: "whitesmoke" }}
                >
                  확인
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </Modal>
  );
}

export default SignUpModal;
