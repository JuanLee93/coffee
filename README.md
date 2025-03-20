Coffee 프로젝트

📌 프로젝트 소개

Coffee는 팀 내에서 커피를 사는 순서를 관리하는 API 기반 서비스입니다. 현재 등록된 멤버를 확인하고, 누구 차례인지 쉽게 알 수 있도록 도와줍니다.

🚀 주요 기능

1️⃣ 멤버 조회 API

현재 등록된 모든 멤버 리스트를 반환합니다.

API Endpoint: /members

Response 예시:

2️⃣ 커피 구매 순서 조회 API

다음 커피를 살 멤버를 확인할 수 있습니다.

모든 멤버가 한 번씩 샀다면 순서를 초기화합니다.

API Endpoint: /next-buyer

Response 예시:

(모든 멤버가 한 번씩 샀다면 "reset": true 반환)

🛠 기술 스택

Backend: Java17, Spring Boot 3.X

Database: Maria DB

Containerization: Docker



📌 추가 기능 예정

멤버 추가 / 삭제 API

커피 구매 기록 저장

관리자 기능 추가
