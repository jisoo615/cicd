package com.haru.doyak.harudoyak.domains.log;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/logs/*")
public class LogController {
    private final LogService logService;

}
