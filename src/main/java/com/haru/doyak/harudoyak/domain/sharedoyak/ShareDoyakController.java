package com.haru.doyak.harudoyak.domain.sharedoyak;

import com.haru.doyak.harudoyak.dto.sharedoyak.ReqCommentDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ReqShareDoyakDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/posts/*")
@Slf4j
public class ShareDoyakController {
    private final ShareDoyakService shareDoyakService;
    /*
    * 서로도약 커뮤니티
    * */

    /*
    * 댓글 작성
    * req : memberId(Long), shareDoyakId(Long), commentContent(String)
    * res :
    * */
    @PostMapping("comments/{memberId}/{shareDoyakId}")
    public void setCommentAdd(@PathVariable("memberId") Long memberId, @PathVariable("shareDoyakId") Long shareDoyakId, ReqCommentDTO reqCommentDTO) {

    }

    /*
    * 도약하기 추가
    * req : memberId(Long), shareDoyakId(Long)
    * res : doyakCount(Long)
     * */
    @PostMapping("doyak/{memberId}/{shareDoyakId}")
    public Long setDoyakAdd(@PathVariable("memberId") Long memberId, @PathVariable("shareDoyakId") Long shareDoyakId) {

        Long doyakCount = shareDoyakService.setDoyakAdd(memberId, shareDoyakId);

        return doyakCount;
    }

    /*
    * 서로도약 작성
    * req : memberId(Long), shareContent(String), shareImage(MultipartFile)
    * res :
    * */
    @PostMapping("{memberId}")
    public void setShareDoyakAdd(@PathVariable("memberId") Long memberId, ReqShareDoyakDTO reqShareDoyakDTO, @RequestParam("shareImage") MultipartFile shareImage){

        shareDoyakService.setShareDoyakAdd(memberId, reqShareDoyakDTO, shareImage);

    }

}
