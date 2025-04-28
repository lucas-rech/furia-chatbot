package com.lucasrech.furiaapi.controller;


import com.lucasrech.furiaapi.services.BotService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/")
public class BotController {

    private final BotService botService;

    public BotController() {
        this.botService = new BotService();
    }

    @PostMapping
    public ResponseEntity<Object> talkBot(@RequestBody HashMap<String, String> json) {
        String input = json.get("input");
        String response = botService.returnMessage(input);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(new HashMap<String, String>() {{
                    put("response", response);
                }});

    }
}
