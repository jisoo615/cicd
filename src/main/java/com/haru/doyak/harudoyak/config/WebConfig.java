package com.haru.doyak.harudoyak.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 리액트 CORS설정
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);

    }

    // CORS 필터 등록
/*    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {

        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:3000");
        corsConfig.addAllowedMethod("GET");
        corsConfig.addAllowedMethod("POST");
        corsConfig.addAllowedMethod("OPTIONS");
        corsConfig.addAllowedHeader("*");  // 모든 헤더 허용
        corsConfig.setAllowCredentials(true);  // 자격 증명 허용
        corsConfig.setMaxAge(3600L);  // Preflight 요청에 대한 캐시 시간 설정 (1시간)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);  // 모든 엔드포인트에 CORS 설정

        FilterRegistrationBean<CorsFilter> corsFilterBean = new FilterRegistrationBean<>(new CorsFilter(source));
        corsFilterBean.addUrlPatterns("/api/*"); // CORS 필터를 적용할 URL 패턴
        corsFilterBean.setOrder(0); // 가장 먼저 실행되도록 설정

        return corsFilterBean;
    }*/

}