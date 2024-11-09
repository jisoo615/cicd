package com.haru.doyak.harudoyak.domain.log;

import com.haru.doyak.harudoyak.dto.letter.ReqLetterDTO;
import com.haru.doyak.harudoyak.dto.log.ReqLogDTO;
import com.haru.doyak.harudoyak.dto.log.ResLogDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    * 일간 도약기록 조회
    * */
    @GetMapping()

    /*
    * 도약이 편지 작성
    * @param : memberId(Long), logId(Long), letterContent(String)
    * @return :
    * */
    @PostMapping("letters/{memberId}/{logId}")
    public ResponseEntity<String> setLetterAdd(@PathVariable("memberId") Long memberId, @PathVariable("logId") Long LogId,@RequestBody ReqLetterDTO reqLetterDTO) {
        logService.setLetterAdd(memberId, LogId, reqLetterDTO);
        return ResponseEntity.ok("작성이 완료되었습니다.");
    }

    /*
     * 도약 기록 목록
     * req : memberId(Long)
     * res : logId(Long), creationDate(Date)
     * */
    @GetMapping("list/{memberId}")
    public ResponseEntity<List<ResLogDTO>> getLogList(@PathVariable("memberId") Long memberId){
        List<ResLogDTO> resLogDTOS = logService.getLogList(memberId);
        return ResponseEntity.ok().body(resLogDTOS);
    }

    /*
     * 도약 기록 작성
     * req : memberId(Long), logImage(MultipartFile),
     *       logContent(String), tagName(String []), emotion(String)
     * res : 200 ok 400 등
     * */
    @PostMapping("{memberId}")
    public ResponseEntity<String> setLogAdd(@PathVariable("memberId") Long memberId, @RequestBody ReqLogDTO reqLogDTO) {
        logService.setLogAdd(reqLogDTO, memberId);
        return ResponseEntity.ok().body("기록도약 게시글 작성을 완료했습니다.");
    }

}
