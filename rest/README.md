# REST API

[그런 REST API로 괜찮은가?](https://youtu.be/RP_f5dMoHFc) 영상보고 정리

## REST 정의

- REpresentational State Transfer
- a way of providing interoperability between computer systems on the internet.
- interoperability(상호운용성): 프론트, 백엔드, 프레임워크는 독립적으로 개발될 수 있어야 한다.

## History

### 팀 버너스리가 발명한 www의 3요소

- HTML: 표현형식
- URI: 식별자
- HTTP: 전송방법

### Roy T. Fielding 이 위의 3요소를 유지하면서 HTTP를 개선해보고자 함

- 개선책: HTTP Object Model
- 위의 내용이 REST 이름으로 박사 논문으로 발표 됨
- "Architectural Styles and the Design of Network-based Software Architectures"

### API 의 태동

- Microsoft 가 SOAP 라는 걸 만듬: XML-RPC
- SOAP 가 끔찍해서 REST 가 대세가 됨
- CMIS 가 REST 바인딩을 지원한다고 하지만, 로이 필딩 왈 " No REST in CMIS"

## REST API 란?

- REST APIs must be hypertext-driven
- REST API를 위한 최고의 버저닝 전략은 버저닝을 안 하는 것

- REST API: REST 아키텍처 스타일을 따르는 API
- REST: 분산 하이퍼미디어 시스템(웹)을 위한 아키텍쳐 스타일(제약조건의 집합)

- 자주 깜빡하는 REST의 제약 조건
  - uniform interface
    - 서버의 기능이 변경되어도 클라이언트를 업데이트할 필요가 없다.
    - 마찬가지로 클라이언트 기능이 변경되어도, 서버를 변경할 필요가 없다.
  - self-descriptive messages
    - 메시지는 스스로를 설명해야한다.
  - HATEOAS(hypermedia as the engine of application state)
    - 애플리케이션의 상태는 Hyperlink를 이용해 전이되어야한다.

## 왜 HTTP API 는 REST 가 잘 안될까?

- HTML 을 주고받는 웹 API 는 사람이 본다. hyperlink 사용 가능하고 가독성에 신경쓴다.
- JSON 으로 주고받는 API 는 기계가 본다. hyperlink 못쓰고 기계가 보기 편해야한다.

- self-descriptive: 서버와 클라이언트 사이에 오고가는 메시지는 해석 가능해야한다.
  - 방법1: Media Type 을 IANA에 등록 한다.
  - 방법2: profile Link 를 헤더에 포함한다.
- HATEOAS: 서버의 링크가 동적으로 바뀌더라도 클라이언트는 정상 동작해야한다.
  - 해결책: late binding
  - 방법1: data에 하이퍼링크를 포함한다.
  - 방법2: 헤더의 Link와 Location 을 활용한다.

## 결론

- REST API 라 부른다음에 로이 필딩에게 쪽팔림 당하지 말고, HTTP API 라고 부르자.
