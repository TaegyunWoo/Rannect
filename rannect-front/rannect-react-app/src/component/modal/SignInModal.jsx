import React from "react";
import Modal from "react-bootstrap/Modal";

function SignInModal({ show, handleHide }) {
  return (
    <Modal show={show} onHide={handleHide}>
        <div className="modal-content">
          {/* Modal Header */}
          <div className="modal-header">
            <h4 className="modal-title text-center">로그인</h4>
          </div>

          {/* Modal body */}
          <div className="modal-body">
            {/* Form */}
            <div>
              <form action="">
                <div className="row mx-3 my-3 rounded text-center py-1 form-item">
                  <div className="col-sm-3 align-self-center">
                    <label for="signInUserId" className="form-label">
                      <div className="form-label">아이디</div>
                    </label>
                  </div>
                  <div className="col-sm-9">
                    <input
                      type="text"
                      className="form-control"
                      id="signInUserId"
                      placeholder="입력해주세요"
                      name="userId"
                    />
                  </div>
                </div>
                <div className="row mx-3 my-3 rounded text-center py-1 form-item">
                  <div className="col-sm-3 align-self-center">
                    <label for="signInPassword" className="form-label">
                      <div className="form-label">비밀번호</div>
                    </label>
                  </div>
                  <div className="col-sm-9">
                    <input
                      type="password"
                      className="form-control"
                      id="signInPassword"
                      placeholder="입력해주세요"
                      name="password"
                    />
                  </div>
                </div>
                <div className="mx-3 my-3 rounded text-center py-1">
                  <button
                    type="submit"
                    className="btn"
                    style={{ backgroundColor: "#8C52FF", color: "white" }}
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

export default SignInModal;
