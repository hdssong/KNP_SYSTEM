import { create } from "zustand";
import { login as loginApi, logoutServer, wireAuthHelpers } from "../api/auth";

const useAuthStore = create((set, get) => ({
  isLoggedIn: false,
  user: null,
  accessToken: null,
  refreshToken: null,

  // axios 인터셉터가 쓸 getter/setter
  getAccessToken: () => get().accessToken,
  getRefreshToken: () => get().refreshToken,
  setTokens: (access, refresh) => set({ accessToken: access, refreshToken: refresh }),

  loginUser: async (loginData) => {
    try {
      const { accessToken, refreshToken, user } = await loginApi(loginData);
      set({ isLoggedIn: true, user, accessToken, refreshToken });
      return true;
    } catch (error) {
      console.error("로그인 실패:", error?.response?.status, error?.response?.data ?? error);
      throw error;
    }
  },
  logout: async () => {
    try {
      const rt = get().refreshToken;
      if (rt) {
        await logoutServer(rt);
      }
    } catch (err) {
      console.log("서버 로그아웃 실패", err);
    } finally {
      set({ isLoggedIn: false, user: null, accessToken: null, refreshToken: null });
      console.log("로그아웃 완료");
    }
  },
  initSession: async () => false, // 새로고침 시 로그인 유지 기능은 나중에 구현 예정
}));
// axios에 스토어 헬퍼 주입

wireAuthHelpers(
  () => useAuthStore.getState().getAccessToken(),
  () => useAuthStore.getState().getRefreshToken(),
  (a, r) => useAuthStore.getState().setTokens(a, r),
  () => useAuthStore.getState().logout()
);

export default useAuthStore;
