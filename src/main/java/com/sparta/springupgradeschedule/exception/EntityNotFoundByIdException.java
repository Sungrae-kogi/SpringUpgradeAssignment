package com.sparta.springupgradeschedule.exception;

//Primary Key : Id 를 기준으로 검색시 값이 없을 경우 사용할 사용자 정의 예외 클래스.
public class EntityNotFoundByIdException extends Exception{
    public EntityNotFoundByIdException(){}

    public EntityNotFoundByIdException(String message){
        super(message);
    }
}
