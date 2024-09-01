package com.sparta.springupgradeschedule.config;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String url = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();

        // url이 /auth면 회원가입, 로그인처리이므로 인증이 필요없다.     - 해설 2:00:59
        if(url.startsWith("/auth")){
            chain.doFilter(request, response);
            return;
        }
        
        // name - value 설정했던 JWTvalue를 가져옴. String값이다.
        String bearerJwt = httpRequest.getHeader("Authorization");

        // 존재하지않거나, 접두사가 설정했던 Bearer 가 아니라면 응답에 에러메시지 
        if (bearerJwt == null || !bearerJwt.startsWith("Bearer ")) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT 토큰이 필요합니다.");
            return;
        }

        // 존재한다면, 접두사를 뗀 value를 가져옴.
        String jwt = jwtUtil.substringToken(bearerJwt);

        try {
            // 접두사를 뗀 토큰을 검증한다.
            if (jwtUtil.validateToken(jwt)) {
                // 검증성공시 chain.doFilter()로 다음 Filter로 전달
                chain.doFilter(request, response);
            } else {
                // 검증실패 -> 유효하지 않은 토큰
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT 토큰이 유효하지 않습니다.");
            }
        } catch (Exception e) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT 토큰 검증 중 오류가 발생했습니다.");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}