package com.lucasrech.furiaapi.controller;

import com.lucasrech.furiaapi.dtos.RequestDTO;
import com.lucasrech.furiaapi.dtos.ResponseDTO;
import com.lucasrech.furiaapi.services.BotService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BotController {

    private final BotService botService;

    public BotController(BotService botService) {
        this.botService = botService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> talkBot(@RequestBody RequestDTO requestDTO) throws Exception {
        String response = botService.talkBot(requestDTO.input());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(new ResponseDTO(response));
    }
}
