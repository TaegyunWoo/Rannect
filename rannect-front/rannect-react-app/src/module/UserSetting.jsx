import React, { useState, useEffect } from "react";
import UserSettingModal from "../component/modal/UserSettingModal";
import callSignOutAPI from "../api/member/SignOutApi";
import callMemberInfoAPI from "../api/member/MemberInfoAPI";
import callUpdateMyMemberInfoAPI from "../api/member/UpdateMyMemberInfoAPI";

function UserSetting({ show, handleHideBasic, setSignInState }) {
  const [formData, setFormData] = useState({});
  const [apiResErrMsg, setApiResErrMsg] = useState("");
  const [apiResSuccMsg, setApiResSuccMsg] = useState("");

  const handleChange = ({ target }) => {
    setFormData((prev) => {
      const newReq = { ...prev };
      newReq[target.name] = target.value;
      return newReq;
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    callUpdateMyMemberInfoAPI(
      formData,
      (res) => {
        setFormData(res);
        setApiResSuccMsg("회원정보가 성공적으로 변경되었습니다.");
      },
      (errRes) => {
        if (errRes.code === "C001" && errRes.fieldErrors.length > 0) {
          if (errRes.fieldErrors[0].field === "nickname") {
            setApiResErrMsg(
              () => "닉네임은 2자 이상, 15자 이하, 영문·한글·숫자만 가능합니다."
            );
          } else if (errRes.fieldErrors[0].field === "interestedIn") {
            setApiResErrMsg(
              () => "관심 주제는 50자 이하, 영문·한글·숫자만 가능합니다."
            );
          }
          setApiResSuccMsg("");
        }
      }
    );
  };

  const handleLogout = (e) => {
    e.preventDefault();
    callSignOutAPI(() => {
      localStorage.removeItem("refreshToken");
      setSignInState(false);
      handleHideWithClearState();
    });
  };

  const handleHideWithClearState = () => {
    setFormData({});
    setApiResErrMsg("");
    handleHideBasic();
  };

  useEffect(() => {
    if (show) {
      callMemberInfoAPI((res) => {
        setFormData(res);
      });
    }
  }, [show]);

  return (
    <UserSettingModal
      show={show}
      handleHide={handleHideWithClearState}
      handleChange={handleChange}
      handleSubmit={handleSubmit}
      handleLogout={handleLogout}
      formValue={formData}
      succeedMsg={apiResSuccMsg}
      errMsg={apiResErrMsg}
    />
  );
}

export default UserSetting;
