package com.noobs.gazonuz.dtos;

import com.noobs.gazonuz.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {


    private String id;


    private String created_at;


//    private String accepted_at;



    private String start_time;
    private int minutes;


    private OrderStatus order_status;


    private Boolean isDeleted;

    private String username;
    private String user_id;

    private String pitch_id;
}
