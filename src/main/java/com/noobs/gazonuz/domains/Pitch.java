package com.noobs.gazonuz.domains;

import com.noobs.gazonuz.domains.auth.AuthUser;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    private String latitude;
    //    @Column( nullable = false )
    private String longitude;
    private String info;
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


    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private AuthUser authUser;

}