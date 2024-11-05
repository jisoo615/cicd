package com.haru.doyak.harudoyak.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 의존성 주입
@RequiredArgsConstructor
// RESTfulAPI 형태의 컨트롤러 : JSON, XML과 같은 데이터 반환
@RestController
// url 매핑
@RequestMapping("api/members")

// 회원 컨트롤러
public class MemberController {
    private final MemberService memberService;

    @GetMapping("check")
    public ResponseEntity check(@RequestParam("nickname")String nickname) {
        return ResponseEntity.ok().body("");
    }

    @PostMapping("{memberId}/nickname")
    public ResponseEntity check() {
        return ResponseEntity.ok().body("");
    }

    @PutMapping("{memberId}/aiNickname")
    public ResponseEntity putAiNickname(){
        return ResponseEntity.ok().body("");
    }

    @PutMapping("{memberId}/nickname")
    public ResponseEntity putNickname(){
        return ResponseEntity.ok().body("");
    }

    @PutMapping("{memberId}/goalName")
    public ResponseEntity putGoalName(){
        return ResponseEntity.ok().body("");
    }

    @PutMapping("{memberId}/pwd")
    public ResponseEntity putpwd(){
        return ResponseEntity.ok().body("");
    }
}
