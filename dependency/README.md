# Dependency Inversion vs Inversion Of Control

의존성 역전과 제어권 역전의 차이가 대체 뭔지 설명하기 위한 예시

코드 읽는 순서: plain -> dip -> circular -> ioc

## Dependency Flow vs Control Flow

- 의존성 흐름: import 하는 방향, 실선 ->
    - 뭔가 잘못되었을 때 내리 갈굼하는 방향
- 제어권 흐름: 코드가 실행되는 방향, 점선 --> (어떤 클래스의 코드가 실행되고 있나)
    - 누가 실행을 하고 있나 == 누가 실행 흐름의 제어권을 가지고 있나

## 의존성 역전(Dependency Inversion)

- 의존성 역전은 Dependency Flow 를 역전시킨 것이다.
- Abstraction 을 통해 Dependency Injection 을 하면 의존성이 역전된다.

#### 필요한 이유

의존성 분리 == 디커플링 == 추상화하세욧!

Dependency Segregation == Decoupling == Abstraction

포비님의 TDD 과제를 하면, 의존성 역전이 없으면 개고생을 하게 되는지 몸소 느낄 수 있습니다.

## 제어권 역전(Inversion Of Control)

IoC는 Control Flow 를 역전시킨 것이다.

Bean Factory 를 통해 Dependency Injection 을 하면 제어권이 역전된다.

Abstraction 은 이루어질 수도, 이루어지지 않을 수도 있다.

Bean Factory == IoC Container == Application Context

#### 필요한 이유

cyclic dependency 문제의 해결

의존성 역전만으로는 해결불가능한 문제이기에, 제어권 역전이 필요
