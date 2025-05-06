package com.lucasrech.furiaapi.dtos;

import jakarta.annotation.Nullable;

public record QuoteDTO(
        String question,
        String answer,
        @Nullable String shortcut
) {
}
