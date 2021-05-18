# OAuth 2.0

## 프론트 코드 읽는 순서

- [index.html](./src/main/resources/static/index.html) -> [callback.html](./src/main/resources/static/callback.html) -> [home.html](./src/main/resources/static/home.html) 순으로 읽는다.

## 참고 링크

- [생활코딩 Oauth 2.0](https://opentutorials.org/course/3405)
- [K 가 구현한 OAuth](https://github.com/bong6981/OAuthStudy/tree/k)
- [Honux의 express 예시](https://github.com/honux77/oauth2-node)
- [GitHub Docs](https://docs.github.com/en/developers/apps/authorizing-oauth-apps)
- [rfc6749](https://datatracker.ietf.org/doc/html/rfc6749#section-4.1)
- [rfc8628](https://datatracker.ietf.org/doc/html/rfc8628)

## 기타 링크

- [oauth.net](https://oauth.net/2/)
- [Aaron Parecki](https://aaronparecki.com/oauth-2-simplified/)
- [digitalocean](https://www.digitalocean.com/community/tutorials/an-introduction-to-oauth-2)
- [솔라 한글 설명](https://gist.github.com/blossun/5057cf64f85b809fd76c1c1c750e8cdf)

## 핵심 용어 정리

- Resource Owner: 파이로
- User-Agent: React 혹은 모바일 앱
- Client: Spring 서버
- Authorization Server: GitHub 인증 서버
- Resource Server: GitHub 리소스 서버

## [생활 코딩 정리](https://opentutorials.org/course/3405)

- Federated Identity: 다른 서비스와의 연합을 통해서 사용자를 식별
- Resource Owner: 사용자(파이로)
- Client: 내 서버(생활코딩)
- Authorization Server: 인증 서버(구글, 페이스북, 트위터)
- Resource Server: 외부 서버(구글, 페이스북, 트위터)
- accessToken: Client 가, Owner 권한으로 Resource에 접근할 때 사용함

### 1. Client의 등록(Register)

- Client 가 Resource Server 에 Client 앱 등록
- 다음 3가지 요소가 필요
  - Client ID: 앱 식별자, 노출 가능
  - Client Secret: 비밀번호, 노출하면 안됨
  - Authorized redirect URIs: `https://client/callback`, 여기말고 딴 주소로 요청하면 Resource Server 가 무시함

### 2. Resource Owner의 승인

- Client 가 Owner 에게 링크를 보냄
  - `https://resource.server?client_id=1&scope=B,C&redirect_uri=https://cleint/callback`
  - Owner 가 허용하면, Resource Server는 Owner 의 user id 의 인증 정보를 기억함

### 3. Resource Server 의 승인

- Resource Server 가 Resource Owner 에게 authorization code 전달
  - authorization code: 임시 비밀번호
  - Location 을 전달
    - Location: `https://client/callback?code=3`
- 그러면 Resource Owner 는 위의 Location 링크로 이동
  - 링크 이동을 통해 Resource Owner 가 Client 에게 code 전달
- Client 가 Resource Server 에게 code, redirect_uri,client_id, client_secret 전달
  - 즉 핵심 비밀번호는 code 와 client_secret
- Resource Server 가 본인의 code, redirect_uri, client_id, client_secret 4가지 정보가 모두 일치할 때에만 accessToken 을 발행

### 4. Access token

- Resource Server 는 accessToken 발행 후, code 가 다시 사용될 수 없도록 지워야함
- 발행한 accessToken 을 Client 에게 Response 함
- Client 는 accessToken 을 저장함

### 5. API 호출

- 이제 Client 는 accessToken 으로 Resource Server 의 API 맘껏 호출 가능

### 6. Refresh Token

- accessToken 이 expired 되었을 때, 처음부터 1번부터 4번을 다시 하기 귀찮다.
- Authorization Server 가 accessToken 과 refreshToken 을 발급하는 경우가 있다.
- accessToken 이 expired 되면, refreshToken 으로 Client 는 다시 accessToken 을 재발급 받을 수 있다.
- refreshToken 은 가끔씩만 주고 받기 때문에, accessToken 보다 상대적으로 안전하다.
