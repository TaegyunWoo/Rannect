import callReissueTokenAPI from "../api/auth/ReissueTokenAPI";
import callDeleteAuthCookiesAPI from "../api/auth/DeleteAuthCookiesAPI";

function ReissueToken({ code }) {
  const refreshToken = localStorage.getItem("refreshToken");
  if (refreshToken && code === "A004") {
    callReissueTokenAPI(
      { refreshToken },
      (res) => {
        localStorage.setItem("refreshToken", res.refreshToken);
        console.log("reissue succeed!!");
      },
      () => {
        callDeleteAuthCookiesAPI();
        window.alert("다시 로그인해주세요.");
        window.location.href = "/"; //리다이렉션
      }
    );
  } else {
    callDeleteAuthCookiesAPI();
    window.alert("다시 로그인해주세요.");
    window.location.href = "/"; //리다이렉션
  }
}

export default ReissueToken;
