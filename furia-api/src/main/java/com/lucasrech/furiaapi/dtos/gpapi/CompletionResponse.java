package com.lucasrech.furiaapi.dtos.gpapi;

import java.util.List;

public record CompletionResponse(
        List<Choice> choices
) {
}
