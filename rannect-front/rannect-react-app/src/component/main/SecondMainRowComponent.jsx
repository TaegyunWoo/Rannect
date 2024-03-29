import React from "react";
import IntroService from "../intro-service/IntroServiceComponent";
import MainLogo from "../intro-service/MainLogoComponent";

function SecondMainRow() {
  return (
    <div className="row ms-4 me-4 mx-auto">
      <IntroService />
      <MainLogo />
    </div>
  );
}

export default SecondMainRow;
