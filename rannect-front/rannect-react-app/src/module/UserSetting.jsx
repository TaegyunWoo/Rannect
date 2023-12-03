import React, { useState, useEffect } from "react";
import UserSettingModal from "../component/modal/UserSettingModal";
import callSignOutAPI from "../api/member/SignOutApi";

function UserSetting({ show, handleHideBasic, setSignInState }) {
  const [formData, setFormData] = useState({});
  const [apiResErrMsg, setApiResErrMsg] = useState("");

  const handleChange = ({ target }) => {};

  const handleSubmit = (e) => {};

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

  return (
    <UserSettingModal
      show={show}
      handleHide={handleHideWithClearState}
      handleChange={handleChange}
      handleSubmit={handleSubmit}
      handleLogout={handleLogout}
      errMsg={apiResErrMsg}
    />
  );
}

export default UserSetting;
