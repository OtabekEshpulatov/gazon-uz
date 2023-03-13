package com.noobs.payload;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Response<DATA> {


    private DATA data;
    private String message;
    private String developerMessage;
    private boolean success;

}
