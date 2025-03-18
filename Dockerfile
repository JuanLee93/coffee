# 1️⃣ JDK가 포함된 가벼운 이미지 사용 (Ubuntu 대신 Temurin JDK)
FROM eclipse-temurin:17-jdk
# 2️⃣ 컨테이너 내 작업 디렉터리 설정
WORKDIR /app
# 3️⃣ JAR 파일 복사
COPY ./build/libs/*.jar app.jar
# 4️⃣ 컨테이너 실행 시 애플리케이션 자동 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD []