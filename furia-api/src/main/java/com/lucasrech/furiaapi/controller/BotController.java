package com.lucasrech.furiaapi.controller;

import com.lucasrech.furiaapi.dtos.ErrorResponseDTO;
import com.lucasrech.furiaapi.dtos.RequestDTO;
import com.lucasrech.furiaapi.dtos.ResponseDTO;
import com.lucasrech.furiaapi.services.BotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Tag(name = "Furia chatbot API", description = "API com lógica e serviços de conversação com chatbot.")
public class BotController {

    private final BotService botService;
    
    public BotController(BotService botService) {
        this.botService = botService;
    }

    @PostMapping("/talk")
    @Operation(summary = "Recebe um valor em application/json como entrada, que é processado pelo serviço de conversa, retornando uma resposta.")
    @ApiResponses(value  = {
            @ApiResponse(responseCode = "200", description = "Message received successfully",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Empty input value",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "408", description = "Time out processing data",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),
            @ApiResponse(responseCode = "503", description = "Data files cannot be read",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDTO.class))}),

    })
    public ResponseEntity<ResponseDTO> talkBot(@RequestBody RequestDTO requestDTO) {
        String response = botService.talkBot(requestDTO.input());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(new ResponseDTO(response));
    }
}
