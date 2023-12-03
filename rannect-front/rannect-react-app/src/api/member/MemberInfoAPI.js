import Request from "../Request";

const SERVER_URL = `${process.env.REACT_APP_SERVER_URL}:${process.env.REACT_APP_SERVER_PORT}`;

async function call(succeedCallback, failCallback) {
  const request = new Request("GET");
  const response = await fetch(`${SERVER_URL}/members/me`, request);
  if (response.ok) succeedCallback(await response.json());
  else failCallback(await response.json());
}

export default call;
