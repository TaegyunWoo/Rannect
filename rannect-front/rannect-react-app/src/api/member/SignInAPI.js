import Request from "../Request";

const SERVER_URL = `${process.env.REACT_APP_SERVER_URL}:${process.env.REACT_APP_SERVER_PORT}`;

async function call(req, callback) {
  const request = new Request("POST", {
    accountId: req.userId,
    rawPassword: req.password,
  });
  const response = await fetch(`${SERVER_URL}/members/sign-in`, request);
  callback(await response.json());
}

export default call;
