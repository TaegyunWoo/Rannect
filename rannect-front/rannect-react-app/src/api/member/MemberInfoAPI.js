import Request from "../Request";
import reissueToken from "../../auth/ReissueToken";

const SERVER_URL = `${process.env.REACT_APP_SERVER_URL}:${process.env.REACT_APP_SERVER_PORT}`;

async function call(succeedCallback, failCallback = (errResp) => {}) {
  const request = new Request("GET");
  const response = await fetch(`${SERVER_URL}/members/me`, request);
  if (response.ok) {
    succeedCallback(await response.json());
  } else if (response.status === 401) {
    const respJson = await response.json();
    await reissueToken(respJson);
    call(succeedCallback, failCallback);
  } else {
    failCallback(await response.json());
  }
}

export default call;
