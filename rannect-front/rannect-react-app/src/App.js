import "./App.css";
import React, { useState, useEffect } from "react";
import Header from "./module/Header";

function App() {
  const [existAccessToken, setExistAccessToken] = useState(
    document.cookie.match("Exist-Access-Token=true") ? true : false
  );

  useEffect(() => {
    setExistAccessToken(
      document.cookie.match("Exist-Access-Token=true") ? true : false
    );
  });

  return (
    <div className="App">
      <Header
        signInState={existAccessToken}
        setSignInState={setExistAccessToken}
      />
    </div>
  );
}

export default App;
