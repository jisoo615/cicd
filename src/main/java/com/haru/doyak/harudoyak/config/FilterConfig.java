package com.haru.doyak.harudoyak.config;

//@Configuration
//public class FilterConfig {
//    private final JwtProvider jwtProvider;
//
//    public FilterConfig(JwtProvider jwtProvider) {
//        this.jwtProvider = jwtProvider;
//    }
//
//    @Bean
//    public FilterRegistrationBean<JwtAuthorizationFilter> jwtFilter() {
//        FilterRegistrationBean<JwtAuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new JwtAuthorizationFilter(jwtProvider));
//        registrationBean.addUrlPatterns("/api/auth/validate");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
//}
