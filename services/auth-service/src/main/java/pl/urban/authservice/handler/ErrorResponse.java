package pl.urban.authservice.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
