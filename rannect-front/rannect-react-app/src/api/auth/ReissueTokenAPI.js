import Request from "../Request";

const SERVER_URL = `${process.env.REACT_APP_SERVER_URL}:${process.env.REACT_APP_SERVER_PORT}`;

async function call(req, succeedCallback, failCallback) {
  const request = new Request("POST", {
    validRefreshToken: req.refreshToken,
  });
  const response = await fetch(`${SERVER_URL}/auth/reissue-tokens`, request);
  if (response.ok) succeedCallback(await response.json());
  else failCallback(await response.json());
}

export default call;
