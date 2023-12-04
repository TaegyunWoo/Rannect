import Request from "../Request";

const SERVER_URL = `${process.env.REACT_APP_SERVER_URL}:${process.env.REACT_APP_SERVER_PORT}`;

async function call(
  succeedCallback = () => {},
  failCallback = (resp) => {}
) {
  const request = new Request("DELETE");
  const response = await fetch(`${SERVER_URL}/auth/cookies`, request);
  if (response.ok) succeedCallback();
  else failCallback(await response.json());
}

export default call;
