package com.sparta.springupgradeschedule.exception;


//orElseThrow()에 사용할 사용자 지정 예외클래스 - 대입하면 오류발생
//Primary Key : ScheduleId 를 기준으로 검색시 값이 없을 경우 사용할 사용자 정의 예외 클래스.
public class ScheduleNotFoundByIdException extends Exception {
    public ScheduleNotFoundByIdException() {
    }

    public ScheduleNotFoundByIdException(String message) {
        super(message);
    }


}
