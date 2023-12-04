import Request from "../Request";
import reissueToken from "../../auth/ReissueToken";

const SERVER_URL = `${process.env.REACT_APP_SERVER_URL}:${process.env.REACT_APP_SERVER_PORT}`;

async function call(req, succeedCallback, failCallback = (errResp) => {}) {
  console.log("update api calling started");
  const request = new Request("PATCH", {
    nickname: req.nickname,
    interestedIn: req.interestedIn,
  });
  const response = await fetch(`${SERVER_URL}/members/me`, request);
  if (response.ok) {
    succeedCallback(await response.json());
  } else if (response.status === 401) {
    const respJson = await response.json();
    await reissueToken(respJson);
    call(req, succeedCallback, failCallback);
  } else failCallback(await response.json());
}

export default call;
