import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173, // 원하는 포트로 설정
    proxy: {
      '/auth': 'http://localhost:8080',
      '/api' : 'http://localhost:8080',
    }
  },
  build : {
    outDir : 'dist',
    emptyOutDir: true,
  }
})
