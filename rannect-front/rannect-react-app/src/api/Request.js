export default class Request {
  constructor(method, body) {
    //customizing
    if (["GET", "POST", "PUT", "PATCH", "DELETE"].includes(method))
      this.method = method;
    else throw new Error("Invalid Request Method");

    if (typeof body === "string") this.body = body;
    else if (typeof body === "object") this.body = JSON.stringify(body);

    //default
    this.credentials = "include"; //브라우저 동일과 교차 출처 요청 모두에 자격증명을 보내고, 응답 자격증명도 모두 사용 (즉, 응답 Set-Cookie 헤더, 요청 Cookie 헤더 등을 사용할 수 있도록 허용)
    this.headers = {
      accept: "application/json;charset=UTF-8",
      "Content-Type": "application/json;charset=UTF-8",
    };
  }
}
