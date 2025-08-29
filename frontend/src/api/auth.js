import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";

// 순환 import 문제 방지를 위해 스토어 헬퍼 주입
let getAccessToken = () => null;
let getRefreshToken = () => null;
let setTokens = () => {};
let forceLogout = () => {};

export const wireAuthHelpers = (getAT, getRT, setTok, logoutFn) => {
  getAccessToken = getAT;
  getRefreshToken = getRT;
  setTokens = setTok;
  forceLogout = logoutFn;
};

// axios 인스턴스
export const raw = axios.create({
  baseURL: BASE_URL,
  headers: { "Content-Type": "application/json" },
}); // 로그인, 리프레시 같은 순수 호출에 사용. 인터셉터 X

export const api = axios.create({
  baseURL: BASE_URL,
  headers: { "Content-Type": "application/json" },
}); // authorization 필요한 것들에 사용. 인터셉터 O

// 요청 인터셉터 - accessToken 있으면 자동으로 헤더에 붙임.
api.interceptors.request.use((config) => {
  const at = getAccessToken();
  if (at) config.headers.Authorization = `Bearer ${at}`;
  return config;
});

// 응답 인터셉터: 401 → /jwt/refresh(JSON 바디) → 재시도
let isRefreshing = false;
let queue = [];

api.interceptors.response.use(
  (res) => res,
  async (error) => {
    // 변경됨!!
    const original = error.config;
    if (error?.response?.status === 401 && !original._retry) {
      original._retry = true;

      if (isRefreshing) {
        // 이미 다른 요청이 리프레시중이면 끝날 때까지 대기
        return new Promise((resolve, reject) => queue.push({ resolve, reject })).then(
          (newAccess) => {
            original.headers.Authorization = `Bearer ${newAccess}`;
            return api(original);
          }
        );
      }

      isRefreshing = true;
      try {
        const rt = getRefreshToken();
        if (!rt) throw new Error("No refresh token in memory");

        const { data } = await raw.post("/jwt/refresh", { refreshToken: rt }); // 변경됨!!
        const newAccess = data?.accessToken;
        const newRefresh = data?.refreshToken;
        if (!newAccess) throw new Error("No access token from refresh");

        setTokens(newAccess, newRefresh || rt);
        queue.forEach((p) => p.resolve(newAccess));
        queue = [];

        original.headers.Authorization = `Bearer ${newAccess}`;
        return api(original);
      } catch (e) {
        // 리프레시 실패시 전부 실패처리 + 강제 로그아웃
        queue.forEach((p) => p.reject(e));
        queue = [];
        forceLogout(); // 세션 초기화
        return Promise.reject(e);
      } finally {
        isRefreshing = false;
      }
    }
    return Promise.reject(error);
  }
);
export async function login(loginData) {
  try {
    const { data } = await raw.post(`${BASE_URL}/auth/login`, loginData);
    return data;
  } catch (error) {
    console.error("[LOGIN ERROR] status:", error?.response?.status, "data:", error?.response?.data);
    throw error;
  }
}
export async function logoutServer() {
  try {
    await raw.post(`/auth/logout`);
  } catch (err) {
    console.log("서버 로그아웃 실패", err);
  }
}
