package com.zhakav.ecommerce.exeption;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;

    private List<String> messages;

    public ErrorResponse(List<String> messages){

        this.timeStamp=LocalDateTime.now();
        this.messages=messages;

    }

}
