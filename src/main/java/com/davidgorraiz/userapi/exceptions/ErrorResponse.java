package com.davidgorraiz.userapi.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp,
                            String message,
                            int status,
                            String path // Opcional: ayuda mucho a saber qué URL falló
                            ) {
}
