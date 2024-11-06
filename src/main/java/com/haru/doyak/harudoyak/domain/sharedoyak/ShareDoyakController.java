package com.haru.doyak.harudoyak.domain.sharedoyak;

import com.haru.doyak.harudoyak.dto.sharedoyak.ReqCommentDTO;
import com.haru.doyak.harudoyak.dto.sharedoyak.ReqShareDoyakDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    * @param : memberId(Long), shareDoyakId(Long), commentContent(String)
    * @return :
    * */
    @PostMapping("comments/{memberId}/{shareDoyakId}")
    public void setCommentAdd(@PathVariable("memberId") Long memberId, @PathVariable("shareDoyakId") Long shareDoyakId, ReqCommentDTO reqCommentDTO) {

        log.info("콘텐츠가 파라미터로 잘 넘어왔니? {}", reqCommentDTO.getCommentContent());

        shareDoyakService.setCommentAdd(memberId, shareDoyakId, reqCommentDTO);

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
    * @param : memberId(Long), shareContent(String), shareImegeUrl(String), shareOriginalName(String)
    * @return :
    * */
    @PostMapping("{memberId}")
    public void setShareDoyakAdd(@PathVariable("memberId") Long memberId, @RequestBody ReqShareDoyakDTO reqShareDoyakDTO) {

        shareDoyakService.setShareDoyakAdd(memberId, reqShareDoyakDTO);

    }

}
