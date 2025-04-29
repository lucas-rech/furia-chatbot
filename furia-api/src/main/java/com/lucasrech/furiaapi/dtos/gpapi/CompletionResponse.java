package com.lucasrech.furiaapi.dtos.grok;

import java.util.List;

public record CompletionResponse(
        List<Choice> choices
) {
}
