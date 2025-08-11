import { create } from "zustand";

// jwt 토큰 완성되기 전 localstorage로 일단 테스트하기
const STORAGE_KEY = "auth";

const useAuthStore = create((set) => ({
  isLoggedIn: false,
  user: null,
  loginUser: (userData) => {
    set({ isLoggedIn: true, user: userData });
    try {
      localStorage.setItem(STORAGE_KEY, JSON.stringify(userData));
      console.log("로그인 완료:", userData);
    } catch (error) {
      console.error("로컬스토리지 저장 실패:", err);
    }
  },
  logout: () => {
    set({ isLoggedIn: false, user: null });
    try {
      localStorage.removeItem(STORAGE_KEY);
      console.log("로그아웃 완료");
    } catch (err) {
      console.error("로컬스토리지 삭제 실패:", err);
    }
  },
  initFromStorage: () => {
    try {
      const raw = localStorage.getItem(STORAGE_KEY); //가공 전 데이터
      if (!raw) {
        console.log("저장된 로그인 정보 없음");
        return;
      }
      const parse = JSON.parse(raw);
      set({ isLoggedIn: true, user: parse });
      console.log("스토리지에서 로그인 정보 복원:", parse);
    } catch (err) {
      console.error("로컬스토리지 불러오기 실패:", err);
    }
  },
}));

export default useAuthStore;
