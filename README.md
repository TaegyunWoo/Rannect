# RANNECT - 랜덤 채팅 서비스

> 아직 진행 중인 프로젝트입니다.

<div style="width:20rem;margin:0 auto;">

![logo](/doc/img/logo.png)

</div>

<br/>

## 프로젝트 설명

이 프로젝트는 랜덤 채팅 서비스 RANNECT입니다.

본 서비스에 접속한 사용자들과 무작위 혹은 지정 채팅을 할 수 있습니다.

1대1 채팅, 그룹 채팅을 지원합니다.

### 1대1 랜덤 채팅
- 현재 서비스에 접속한 무작위 사용자와 매칭 후, 채팅이 시작됩니다.
- 1대1 랜덤 채팅을 요청하면, 현재 파트너를 탐색하고 있는 다른 사용자와 매칭됩니다.
- 채팅방에서 한명이라도 나가게 되면, 해당 채팅방은 비활성화됩니다.

### 1대1 요청 채팅
- 현재 접속 중인 사용자의 목록을 통해, 특정 사용자에게 채팅을 요청할 수 있습니다.
  - 사용자 목록에는 사용자가 설정해둔 채팅 주제가 함께 노출됩니다.
- 채팅 요청 후 바로 채팅방에 입장하게 되지만, 상대가 수락할 때까지 채팅 기능은 비활성화됩니다.
- 채팅을 요청받은 사용자는 웹페이지 상단에 알림을 받게 됩니다.
- 채팅방에서 한명이라도 나가게 되면, 해당 채팅방은 비활성화됩니다.

### 그룹 채팅방 개설
- `그룹 채팅방 제목`과 `최대 인원 수`를 설정해서 그룹 채팅방을 개설할 수 있습니다.
- 해당 채팅방에 접속한 유저가 없어도, 해당 채팅방은 유지됩니다.
- 개설된 전체 그룹 채팅방은 메인 페이지에서 목록 형태로 노출됩니다.

### 그룹 랜덤 채팅
- 현재 개설된 그룹 채팅방 중, 무작위 채팅방에 입장합니다.

### 그룹 선택 채팅
- 그룹 채팅방 목록에서 직접 채팅방을 선택해서 접속할 수 있습니다.
- 그룹 채팅방 목록에는 채팅방 제목이 노출됩니다.

<br/>

## 디렉토리 구조
```sh
├── doc : 문서
├── rannect-back : 서버 애플리케이션
└── rannect-front : 클라이언트 리소스
```

<br/>

## 환경
### 기술 스택
**FRONT**

<span><img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"></span>
<span><img src="https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white"></span>
<span><img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"></span>

**BACK**

<span><img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"></span>
<span><img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"></span>
<span><img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=mariadb&logoColor=white"></span>
<span><img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white"></span>
<span><img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"></span>

### 개발 환경
**FRONT**
> 작성 예정

**BACK**
> 작성 예정

### 배포 환경
**FRONT**
> 작성 예정

**BACK**
> 작성 예정

### 사용 라이브러리 및 모듈
**FRONT**
> 작성 예정

**BACK**
> 작성 예정

<br/>

## 프로젝트 결과물
### Web Site
> URL 주소 추가 예정

### HTTP API
|Method|EndPoint|설명|
|------|--------|---|
|      |        |   |

### WebSocket API
> 작성 예정

<br/>

## Learn & Apply
### Spring
- [ ] [Spring MVC - Problem Details for HTTP APIs](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-rest-exceptions)
- [ ] Spring Security
- [ ] Spring Data JPA - Interface Repository
- [ ] Spring Data JPA - QueryDSL

### Message Broker
- [ ] Redis (as message broker)
- [ ] RabbitMQ (as message broker)
- [ ] Kafka (as message broker)

### Event Based
- [ ] [Event-Driven Architecture](https://www.asyncapi.com/docs/tutorials/getting-started/event-driven-architectures)
- [ ] [AsyncApi (for websocket documentation)](https://www.asyncapi.com/docs)

<br/>

## 기여
개인 프로젝트 (BE + FE + PM + Design)

<br/>

Last Modified : 2023-04-11