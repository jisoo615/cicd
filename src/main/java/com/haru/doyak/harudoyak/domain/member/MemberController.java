package com.haru.doyak.harudoyak.domain.member;

import com.haru.doyak.harudoyak.dto.member.ChangeMemberInfoReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("check")
    public ResponseEntity check(@RequestParam("nickname")String nickname) {
        memberService.isNicknameAvailable(nickname);
        return ResponseEntity.ok().body(nickname+"가 사용 가능합니다.");
    }

    @PutMapping("{memberId}/nickname")
    public ResponseEntity chageNickname(@PathVariable("memberId") Long memberId, ChangeMemberInfoReqDTO dto){
        if(dto.getNickname()==null){
            return ResponseEntity.badRequest().body("닉네임이 null 입니다.");
        }
        memberService.changeNickname(memberId, dto.getNickname());
        return ResponseEntity.ok().body("닉네임이 변경되었습니다.");
    }

    @PutMapping("{memberId}/aiNickname")
    public ResponseEntity changeAiNickname(@PathVariable("memberId") Long memberId, ChangeMemberInfoReqDTO dto){
        if(dto.getAiNickname()==null){
            return ResponseEntity.badRequest().body("도약이 닉네임이 null 입니다.");
        }
        memberService.changeAiNickname(memberId, dto.getAiNickname());
        return ResponseEntity.ok().body("도약이 닉네임이 변경되었습니다.");
    }

    @PutMapping("{memberId}/goalName")
    public ResponseEntity changeGoalName(@PathVariable("memberId") Long memberId, ChangeMemberInfoReqDTO dto){
        if(dto.getGoalName()==null){
            return ResponseEntity.badRequest().body("도약목표가 null 입니다.");
        }
        memberService.changeGoalName(memberId, dto.getGoalName());
        return ResponseEntity.ok().body("도약목표가 변경되었습니다.");
    }

    @PutMapping("{memberId}/pwd")
    public ResponseEntity changePassword(@PathVariable("memberId") Long memberId, ChangeMemberInfoReqDTO dto){
        if(dto.getPassword()==null){
            return ResponseEntity.badRequest().body("비밀번호가 null 입니다.");
        }
        memberService.changePassword(memberId, dto.getPassword());
        return ResponseEntity.ok().body("비밀번호가 변경되었습니다.");
    }

    @GetMapping("{memberId}/account")
    public ResponseEntity<MemberAccountResDTO> getAccount(@PathVariable("memberId") Long memberId){
        MemberAccountResDTO dto = memberService.getAccount(memberId);
        return ResponseEntity.ok().body(dto);
    }
}
