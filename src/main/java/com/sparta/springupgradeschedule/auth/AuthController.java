package com.sparta.springupgradeschedule.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api")
public class AuthController {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @GetMapping("/create-cookie")
    public String createCookie(HttpServletResponse res) {
        // 작성한 addCookie() 호출, name value와 response 객체 전달.
        addCookie("Robbie Auth", res);

        return "createCookie";
    }

    // 가지고오고자 하는 쿠키의 name을 @CookieValue()안에, 우리는 create에서 new Cookie(AUTHORIZATION_HEADER,~)를 name에 전달했다.
    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue(AUTHORIZATION_HEADER) String value) {
        System.out.println("value = " + value);

        return "getCookie : " + value;
    }

    @GetMapping("/create-session")
    public String createSession(HttpServletRequest req) {
        // 세션이 존재할 경우 세션 반환, 없을 경우 새로운 세션을 생성한 후 반환
        HttpSession session = req.getSession(true);

        // 세션에 저장될 정보 Name - Value 를 추가합니다.
        session.setAttribute(AUTHORIZATION_HEADER, "Robbie Auth");

        return "createSession";
    }

    public static void addCookie(String cookieValue, HttpServletResponse res) {
        try {
            // 공백들을 변환하고 16진수로 인코딩
            cookieValue = URLEncoder.encode(cookieValue, "utf-8").replaceAll("\\+", "%20"); // Cookie Value 에는 공백이 불가능해서 encoding 진행

            // name : value 쌍으로 쿠키객체를 생성, value는 인코딩했던 cookieValue
            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, cookieValue); // Name-Value
            cookie.setPath("/");
            cookie.setMaxAge(30 * 60);

            // Response 객체에 Cookie 추가
            res.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}