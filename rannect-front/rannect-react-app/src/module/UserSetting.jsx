import React, { useState, useEffect } from "react";
import UserSettingModal from "../component/modal/UserSettingModal";
import callSignOutAPI from "../api/member/SignOutApi";
import callMemberInfoAPI from "../api/member/MemberInfoAPI";

function UserSetting({ show, handleHideBasic, setSignInState }) {
  const [formData, setFormData] = useState({});
  const [apiResErrMsg, setApiResErrMsg] = useState("");

  const handleChange = ({ target }) => {
    setFormData((prev) => {
      const newReq = { ...prev };
      newReq[target.name] = target.value;
      return newReq;
    })
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
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
        setFormData(res)
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
      errMsg={apiResErrMsg}
    />
  );
}

export default UserSetting;
