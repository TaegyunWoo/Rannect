import React from "react";

function MainHeader({ btns }) {
  return (
    <header className="container-fluid shadow-sm pb-3">
      <div className="row mx-auto">
        <div className="col-sm-4"></div>
        <img
          src="/logo.png"
          className="col-sm-4 w-25 mb-5 mx-auto"
          alt="logo"
        />
        {btns}
      </div>
    </header>
  );
}

export default MainHeader;
