import React from "react";

function FooterInfo() {
  return (
    <footer className="row ms-4 me-4 pb-4 mx-auto">
      <span className="col-sm-6" style={{ textAlign: "left" }}>
        Developed by : WooTaegyun (dnxprbs@gmail.com)
      </span>
      <span
        className="col-sm-6 float-end text-end"
        style={{ textAlign: "right" }}
      >
        WooTaegyun, all rights reserved.
      </span>
    </footer>
  );
}

export default FooterInfo;
