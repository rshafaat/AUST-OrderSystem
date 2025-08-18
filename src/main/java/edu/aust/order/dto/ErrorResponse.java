package edu.aust.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.aust.enums.ErrorResponseCode;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
                    ErrorResponseCode errorCode,
                    @JsonFormat(
                        shape = JsonFormat.Shape.STRING,
                        pattern = "dd-MM-yyyy hh:mm:ss"
                    )
                    LocalDateTime timestamp,
                    List<String> errorMessage) {

    public ErrorResponse(ErrorResponseCode errorCode, List<String> errorMessage) {
        this(errorCode, LocalDateTime.now(), errorMessage);
    }

    public ErrorResponse(ErrorResponseCode errorCode, String errorMessage) {
        this(errorCode, LocalDateTime.now(), List.of(errorMessage));
    }

}
