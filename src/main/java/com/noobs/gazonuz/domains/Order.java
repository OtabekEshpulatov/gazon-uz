package com.noobs.gazonuz.domains;

import com.noobs.gazonuz.domains.auth.AuthUser;
import com.noobs.gazonuz.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    private String id;

    @CreationTimestamp
    @Column( columnDefinition = "timestamp default now()", name = "created_at" )
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column( columnDefinition = "timestamp default now()", name = "accepted_at" )
    private LocalDateTime acceptedAt;


    @Column( name = "order_status" )
    @Builder.Default
    private OrderStatus orderStatus = OrderStatus.REQUESTED;


    @Column( columnDefinition = "boolean default false" )
    private Boolean isDeleted;
    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @ToString.Exclude
    private AuthUser authUser;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @ToString.Exclude
    private Pitch pitch;
}
