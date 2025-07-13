#!/bin/bash
# LDAP_PID=$(lsof -ti :8389)
# if [ -n "$LDAP_PID" ]; then
#   echo "⚠️ LDAP 포트(8389)를 점유 중인 프로세스 종료 중... (PID: $LDAP_PID)"
#   kill -9 "$LDAP_PID"
# fi

echo "🚀 [1] React(Vite) 앱 빌드 시작"
cd frontend || { echo "❌ frontend 폴더 없음"; exit 1; }
npm run build || { echo "❌ React 빌드 실패"; exit 1; }

echo "🧹 [2] Spring Boot static 폴더 비우기"
rm -rf ../backend/src/main/resources/static/*

echo "📦 [3] 빌드 결과 복사 중..."
cp -r dist/* ../backend/src/main/resources/static/

echo "✅ 빌드 & 복사 완료!"

cd ../backend
./gradlew bootRun