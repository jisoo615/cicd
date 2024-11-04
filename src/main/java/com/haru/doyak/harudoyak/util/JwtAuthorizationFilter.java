package com.haru.doyak.harudoyak.util;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthorizationFilter implements Filter {

    private final JwtProvider jwtProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(!PatternMatchUtils.simpleMatch(jwtRequiredUris(), request.getRequestURI())){
            // jwt 가 필요하지 않는 uri는 넘어감
            doFilter(servletRequest, servletResponse, filterChain);
            return;
        }

        String token = parseBearerToken(request);
        if(token != null && jwtProvider.validateToken(token)) {
            Map<String, Object> claims = jwtProvider.extractClaimsFromJwt(token);
            // 인증 객체 넣기
            RequestContextHolder.getRequestAttributes()
                    .setAttribute("authenticatedUser", claims, RequestAttributes.SCOPE_REQUEST);

        }else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String[] jwtRequiredUris() {
        // jwt가 필요한 uris
        return new String [] {

        };
    }

    private String parseBearerToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(token -> token.substring(0, 7).equalsIgnoreCase("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

}
