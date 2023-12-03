import Request from "../Request";

const SERVER_URL = `${process.env.REACT_APP_SERVER_URL}:${process.env.REACT_APP_SERVER_PORT}`;

async function call(succeedCallback, failCallback=(errResp) => {}) {
  const request = new Request("GET");
  const response = await fetch(`${SERVER_URL}/members/sign-out`, request);
  if (response.ok) succeedCallback();
  else failCallback(await response.json());
}

export default call;
