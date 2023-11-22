const SERVER_URL = `${process.env.REACT_APP_SERVER_URL}:${process.env.REACT_APP_SERVER_PORT}`;

function call(req) {
  const request = {
    method: "POST",
    headers: {
      accept: "application/json;charset=UTF-8",
      "Content-Type": "application/json;charset=UTF-8",
    },
    body: JSON.stringify({
      accountId: req.newAccountId,
      rawPassword: req.newPassword,
      nickname: req.newNickname,
      interestedIn: req.interestedIn,
    }),
  };

  fetch(`${SERVER_URL}/members`, request)
    .then((res) => {
      if (!res.ok) {
        return res.text().then((text) => {
          throw new Error(text);
        });
      } else {
        console.log(res);
      }    
    })
    .catch((err) => console.log(err));
}

export default call;
