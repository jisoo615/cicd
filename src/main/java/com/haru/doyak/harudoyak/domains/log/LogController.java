package com.haru.doyak.harudoyak.domains.log;

import com.haru.doyak.harudoyak.dto.log.ReqLogDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/logs/*")
@Slf4j
public class LogController {
    private final LogService logService;

    /*
     * 도약 기록
     * */

    /*
     * 도약 기록 목록
     * req : memberId(Long)
     * res :
     * */
    @GetMapping("list/{memberId}")
    public void getLogList(){

    }

    /*
     * 도약 기록 작성
     * req : memberId(Long), logImage(MultipartFile),
     *       content(String), tagName(String []), emotion(String)
     * res : 200 ok 400 등
     * */
    @PostMapping("{memberId}")
    public void setLogAdd(@PathVariable("memberId") Long memberId, ReqLogDTO reqLogDTO, @RequestParam("logImage")MultipartFile logImage) {
        logService.setLogAdd(reqLogDTO, memberId, logImage);
    }

}
