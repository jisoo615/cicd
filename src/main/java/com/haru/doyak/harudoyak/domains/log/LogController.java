package com.haru.doyak.harudoyak.domains.log;

import com.haru.doyak.harudoyak.dto.log.ReqLogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/logs/*")
public class LogController {
    private final LogService logService;

    @PostMapping("{memberId}")
    public void setLogAdd(@PathVariable("memberId") Long memberId, @RequestBody ReqLogDTO reqLogDTO) {
        System.out.println("서버접속!!!");
        logService.setLogAdd(reqLogDTO, memberId);
    }

}
