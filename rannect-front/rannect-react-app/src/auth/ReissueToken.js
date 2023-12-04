import callReissueTokenAPI from "../api/auth/ReissueTokenAPI";
import callDeleteAuthCookiesAPI from "../api/auth/DeleteAuthCookiesAPI";

async function ReissueToken({ code }) {
  console.log("reissue started");
  const refreshToken = localStorage.getItem("refreshToken");
  if (refreshToken && code === "A004") {
    await callReissueTokenAPI(
      { refreshToken },
      (res) => {
        localStorage.setItem("refreshToken", res.refreshToken);
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
