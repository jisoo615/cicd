package com.haru.doyak.harudoyak.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 의존성 주입
@RequiredArgsConstructor
// RESTfulAPI 형태의 컨트롤러 : JSON, XML과 같은 데이터 반환
@RestController
// url 매핑
@RequestMapping("members")

// 회원 컨트롤러
public class MemberController {
    private final MemberService memberService;

}
