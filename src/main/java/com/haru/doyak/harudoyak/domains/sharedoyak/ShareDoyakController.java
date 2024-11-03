package com.haru.doyak.harudoyak.domains.sharedoyak;

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
    * 도약하기 추가
    * */
    @PostMapping("doyak/{memberId}/{shareDoyakId}")
    public void setDoyakAdd(){

    }

    /*
    * 서로도약 작성
    * req : memberId(Long), content(String), shareImage(MultipartFile)
    * res :
    * */
    @PostMapping("{memberId}")
    public void setShareDoyakAdd(@PathVariable("memberId") Long memberId, ReqShareDoyakDTO reqShareDoyakDTO, @RequestParam("shareImage") MultipartFile shareImage){

        shareDoyakService.setShareDoyakAdd(memberId, reqShareDoyakDTO, shareImage);

    }

}
