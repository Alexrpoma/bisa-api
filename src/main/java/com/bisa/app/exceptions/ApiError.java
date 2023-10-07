package com.bisa.app.exceptions;

import lombok.Builder;

@Builder
public record ApiError(
    String path,
    String message,
    int statusCode,
    String localDateTime
) {
}
