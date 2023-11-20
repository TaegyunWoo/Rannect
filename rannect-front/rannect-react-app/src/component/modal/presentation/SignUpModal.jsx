import React from "react";

function SignUpModal({ show }) {
  return (
    <div style={show ? { display: "block" } : { display: "none" }}>
      <div className="modal-dialog">
        <div className="modal-content">
          {/* Modal Header */}
          <div className="modal-header">
            <h4 className="modal-title text-center">회원가입</h4>
          </div>

          {/* Modal body */}
          <div className="modal-body">
            {/* Form */}
            <div>
              <form action="">
                <div className="row mx-3 my-3 rounded text-center py-1 form-item">
                  <div className="col-sm-3 align-self-center">
                    <label for="signUpUserId" className="form-label">
                      <div className="form-label">아이디</div>
                    </label>
                  </div>
                  <div className="col-sm-9">
                    <input
                      type="text"
                      className="form-control"
                      id="signUpUserId"
                      placeholder="4자리 이상, 12자리 이하, 영문"
                      name="userId"
                    />
                  </div>
                </div>
                <div className="row mx-3 my-3 rounded text-center py-1 form-item">
                  <div className="col-sm-3 align-self-center">
                    <label for="signUpPassword" className="form-label">
                      <div className="form-label">비밀번호</div>
                    </label>
                  </div>
                  <div className="col-sm-9">
                    <input
                      type="password"
                      className="form-control"
                      id="signUpPassword"
                      placeholder="6자 이상, 12자 이하, 영어, 숫자"
                      name="password"
                    />
                  </div>
                </div>
                <div className="row mx-3 my-3 rounded text-center py-1 form-item">
                  <div className="col-sm-3 align-self-center">
                    <label for="signUpNickname" className="form-label">
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
                    <label for="signUptopic" className="form-label">
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
                    type="submit"
                    className="btn"
                    style={{ backgroundColor: "#8C52FF" }}
                  >
                    확인
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default SignUpModal;
