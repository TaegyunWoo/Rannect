import "./App.css";
import React, { useState, useEffect } from "react";
import Header from "./module/Header";
import Main from "./module/Main";
import callMemberInfoAPI from "./api/member/MemberInfoAPI";

function App() {
  const [existAccessToken, setExistAccessToken] = useState(
    document.cookie.match("Exist-Access-Token=true") ? true : false
  );
  const [currentUserInfo, setCurrentUserInfo] = useState({});

  useEffect(() => {
    setExistAccessToken(
      document.cookie.match("Exist-Access-Token=true") ? true : false
    );
  });
  useEffect(() => {
    //로그인 상태이고, currentUserInfo 가 비어있다면
    if (existAccessToken && Object.keys(currentUserInfo).length === 0) {
      callMemberInfoAPI((res) => {
        setCurrentUserInfo(() => res);
      });
    }
  });

  return (
    <div className="App">
      <Header
        signInState={existAccessToken}
        setSignInState={setExistAccessToken}
        currentUserInfo={currentUserInfo}
        setCurrentUserInfo={setCurrentUserInfo}
      />
      <Main />
    </div>
  );
}

export default App;
