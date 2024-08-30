# 주특기 숙련 주차 개인과제

🏁 **Goal:  "JPA를 활용한 upgrade 일정 관리 앱 서버 만들기"**

## 🆘 API 명세서 - JPA를 활용한 upgrade 일정 관리 앱 서버

### Schedule

| 기능          | Method | URL                                 | Request  | Response |
|-------------|--------|-------------------------------------|----------|----------|
| 일정 등록       | POST   | /api/schedule/{userId}              | 요청 body  | 등록 정보    |
| 특정 일정 조회    | GET    | /api/schedule/{scheduleId}          | 요청 Param | 응답 정보    |
| 일정 전체 조회    | GET    | /api/schedule                       | 요청 Param | 응답 정보    |
| 특정 일정 변경    | PUT    | /api/schedule/{scheduleId}          | 요청 body  | 변경 정보    |
| 특정 일정 유저 추가 | PUT    | /api/schedule/{scheduleId}/{userId} | 요청 Param | 응답 정보    |
| 특정 일정 삭제    | DELETE | /api/schedule/{scheduleId}          | 요청 param |          |

### Comment

| 기능       | Method | URL                                             | Request  | Response |
|----------|--------|-------------------------------------------------|----------|----------|
| 댓글 등록    | POST   | /api/schedules/{scheduleId}/comments            | 요청 body  | 등록 정보    |
| 전체 댓글 조회 | GET    | /api/schedule/{scheduleId}/comments             | 요청 body  | 등록 정보    |
| 특정 댓글 조회 | GET    | /api/schedule/{scheduleId}/comments/{commentId} | 요청 Param | 응답 정보    |
| 특정 댓글 변경 | PUT    | /api/schedule/{scheduleId}/comments/{commentId} | 요청 body  | 변경 정보    |
| 특정 일정 삭제 | DELETE | /api/schedule/{scheduleId}/comments/{commentId} | 요청 param |          |

### User

| 기능       | Method | URL                 | Request  | Response |
|----------|--------|---------------------|----------|----------|
| 유저 등록    | POST   | /api/users          | 요청 body  | 등록 정보    |
| 특정 유저 조회 | GET    | /api/users/{userId} | 요청 Param | 응답 정보    |
| 유저 전체 조회 | GET    | /api/users          | 요청 Param | 응답 정보    |
| 특정 유저 삭제 | DELETE | /api/users/{userId} | 요청 param |          |

## ERD

![3](https://github.com/user-attachments/assets/fe8b4abe-ae95-4327-a466-765209a35ab5)
