# Build guide

## build settings
- local, dev 환경으로 분리
- yml 파일 필요.

<br />

### EC2 접속
```bash
ssh -i aws-unan-demo-key.pem ec2-user@ec2-52-79-56-207.ap-northeast-2.compute.amazonaws.com
// pem key 발급받아서 사용
```

<br />

### Deploy

#### build 방법
- jwt_secret_key는 실제 JWT value 넣기
```bash
JWT_SECRET={jwt_secret_key} ./gradlew build -Dspring-boot.run.profiles=dev
```

<br />

- 백그라운드 서버 실행
```bash
nohup java -jar -Dspring.profiles.active=dev git-talk-0.0.1-SNAPSHOT.jar & > /dev/null
```