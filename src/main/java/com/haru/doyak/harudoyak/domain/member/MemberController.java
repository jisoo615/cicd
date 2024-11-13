package com.haru.doyak.harudoyak.domain.member;

import com.haru.doyak.harudoyak.domain.sharedoyak.ShareDoyakService;
import com.haru.doyak.harudoyak.dto.member.ChangeMemberInfoReqDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ResCommentDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ResShareDoyakDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/members")
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final ShareDoyakService shareDoyakService;
//  깃허브 액션 테스트 1

    /*
     * 회원의 댓글 모아보기
     * @param : membaerId(Long)
     * */
    @GetMapping("/{memberId}/comments")
    public ResponseEntity<List<ResCommentDTO>> getMemberCommentList(@PathVariable("memberId") Long memberId){
        List<ResCommentDTO> resCommentDTOS= shareDoyakService.getMemberCommentList(memberId);
        return ResponseEntity.ok(resCommentDTOS);
    }

    /*
    * 회원의 서로도약 글 모아보기
    * @param : membaerId(Long)
    * */
    @GetMapping("/{memberId}/posts")
    public ResponseEntity<List<ResShareDoyakDTO>> getMemberShareDoyakList(@PathVariable("memberId") Long memberId){
        List<ResShareDoyakDTO> resShareDoyakDTOS = shareDoyakService.getMemberShareDoyakList(memberId);
        return ResponseEntity.ok(resShareDoyakDTOS);
    }

    @GetMapping("check")
    public ResponseEntity check(@RequestParam("nickname")String nickname) {
        if(memberService.isNicknameAvailable(nickname)){
            return ResponseEntity.ok().body(nickname+"가 사용 가능합니다.");
        }
        return ResponseEntity.badRequest().body(nickname+"가 중복입니다.");
    }

    @PutMapping("{memberId}/nickname")
    public ResponseEntity chageNickname(@PathVariable("memberId") Long memberId,
                                        @RequestBody ChangeMemberInfoReqDTO dto){
        System.out.println(dto);
        if(dto.getNickname()==null){
            return ResponseEntity.badRequest().body("닉네임이 null 입니다.");
        }
        memberService.changeNickname(memberId, dto.getNickname());
        return ResponseEntity.ok().body("닉네임이 변경되었습니다.");
    }

    @PutMapping("{memberId}/aiNickname")
    public ResponseEntity changeAiNickname(@PathVariable("memberId") Long memberId,
                                           @RequestBody ChangeMemberInfoReqDTO dto){
        if(dto.getAiNickname()==null){
            return ResponseEntity.badRequest().body("도약이 닉네임이 null 입니다.");
        }
        memberService.changeAiNickname(memberId, dto.getAiNickname());
        return ResponseEntity.ok().body("도약이 닉네임이 변경되었습니다.");
    }

    @PutMapping("{memberId}/goalName")
    public ResponseEntity changeGoalName(@PathVariable("memberId") Long memberId,
                                         @RequestBody ChangeMemberInfoReqDTO dto){
        if(dto.getGoalName()==null){
            return ResponseEntity.badRequest().body("도약목표가 null 입니다.");
        }
        memberService.changeGoalName(memberId, dto.getGoalName());
        return ResponseEntity.ok().body("도약목표가 변경되었습니다.");
    }

    @PutMapping("{memberId}/pwd")
    public ResponseEntity changePassword(@PathVariable("memberId") Long memberId,
                                         @RequestBody ChangeMemberInfoReqDTO dto){
        if(dto.getPassword()==null){
            return ResponseEntity.badRequest().body("비밀번호가 null 입니다.");
        }
        memberService.changePassword(memberId, dto.getPassword());
        return ResponseEntity.ok().body("비밀번호가 변경되었습니다.");
    }

    @PostMapping("{memberId}/pwd")
    public ResponseEntity confirmPwd(@PathVariable("memberId") Long memberId,
                                         @RequestBody ChangeMemberInfoReqDTO dto){
        if(dto.getPassword()==null){
            return ResponseEntity.badRequest().body("비밀번호가 null 입니다.");
        }
        if(memberService.isCorrectPassword(memberId, dto.getPassword())) {
            return ResponseEntity.ok().body("비밀번호가 일치합니다.");
        }
        return ResponseEntity.badRequest().body("비밀번호가 불일치 합니다");
    }

}
