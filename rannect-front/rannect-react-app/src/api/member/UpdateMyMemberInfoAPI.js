import Request from "../Request";

const SERVER_URL = `${process.env.REACT_APP_SERVER_URL}:${process.env.REACT_APP_SERVER_PORT}`;

async function call(req, succeedCallback, failCallback = (errResp) => {}) {
  const request = new Request("PATCH", {
    nickname: req.nickname,
    interestedIn: req.interestedIn,
  });
  const response = await fetch(`${SERVER_URL}/members/me`, request);
  if (response.ok) succeedCallback(await response.json());
  else failCallback(await response.json());
}

export default call;
