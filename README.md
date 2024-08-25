# 주특기 숙련 주차 개인과제

🏁 **Goal:  "JPA를 활용한 upgrade 일정 관리 앱 서버 만들기"**

## 🆘 API 명세서 - JPA를 활용한 upgrade 일정 관리 앱 서버
### Schedule 
| 기능 | Method | URL | Request | Response |
| --- | --- | --- | --- | --- |
| 일정 등록 | POST | /api/schedule | 요청 body | 등록 정보 |
| 특정 일정 조회 | GET | /api/schedule/{scheduleid} | 요청 Param | 응답 정보 |
| 특정 일정 변경 | PUT | /api/schedule/{scheduleid} | 요청 body | 변경 정보 |
| 특정 일정 삭제 | DELETE | /api/schedule/{scheduleid} | 요청 param |  |

### Comment
| 기능       | Method | URL                                    | Request | Response |
|----------|--------|----------------------------------------| --- | --- |
| 댓글 등록    | POST   | /api/schedule/{scheduleid}             | 요청 body | 등록 정보 |
| 전체 댓글 조회 | GET    | /api/schedule/{scheduleid}             | 요청 body | 등록 정보 |
| 특정 댓글 조회 | GET    | /api/schedule/{scheduleid}/{commentid} | 요청 Param | 응답 정보 |
| 특정 댓글 변경 | PUT    | /api/schedule/{scheduleid}/{commentid} | 요청 body | 변경 정보 |
| 특정 일정 삭제 | DELETE | /api/schedule/{scheduleid}/{commentid} | 요청 param |  |


## ERD
![2](https://github.com/user-attachments/assets/46794f3d-36cf-482f-95e6-1fa38a12ff95)
