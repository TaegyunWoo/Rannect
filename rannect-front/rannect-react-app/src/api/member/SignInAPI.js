const SERVER_URL = `${process.env.REACT_APP_SERVER_URL}:${process.env.REACT_APP_SERVER_PORT}`;

async function call(req, callback) {
  const request = {
    method: "POST",
    headers: {
      accept: "application/json;charset=UTF-8",
      "Content-Type": "application/json;charset=UTF-8",
    },
    body: JSON.stringify({
      accountId: req.userId,
      rawPassword: req.password,
    }),
  };
  const response = await fetch(`${SERVER_URL}/members/sign-in`, request);
  callback(await response.json());
}

export default call;
