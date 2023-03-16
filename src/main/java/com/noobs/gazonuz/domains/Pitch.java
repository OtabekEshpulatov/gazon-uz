package com.noobs.gazonuz.domains;

import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.enums.PitchStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Setter
@Getter
//@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pitch {
    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    private String id;
    @Column( nullable = false )
    private String name;
    //    @Column( nullable = false )
    private Double latitude;
    //    @Column( nullable = false )
    private Double longitude;
    private String info;

    @Column( name = "full_address" )
    private String fullAddress;
    @Column( columnDefinition = "int default 0" )
    private int likes;
    @Column( columnDefinition = "int default 0" )
    private int dislikes;

    @Column( columnDefinition = "smallint default 0" )
    private Byte rating;
    @OneToMany( cascade = CascadeType.ALL )
    private Collection<Document> documents;
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pitch" )
//    @ToString.Exclude
    private Collection<Order> orders;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pitch" )
//    @ToString.Exclude
    private Collection<Comment> comments;

    private double price;
    private String phoneNumber;

    @ManyToOne( cascade = CascadeType.MERGE )
    private User user;
    private PitchStatus status = PitchStatus.ACTIVE;
    @ManyToOne
    private District district;

    @CreationTimestamp
    @Column( name = "created_at" )
    private LocalDateTime createdAt;

}