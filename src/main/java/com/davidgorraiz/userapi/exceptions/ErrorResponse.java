package com.davidgorraiz.userapi.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp,
                            String message,
                            int status,
                            String path // Optional: It's very helpful to know which URL failed
                            ) {
}
