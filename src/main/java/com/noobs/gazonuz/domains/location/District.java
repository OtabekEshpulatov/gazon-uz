package com.noobs.gazonuz.domains.location;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Table( uniqueConstraints = {@UniqueConstraint( columnNames = {"region_id,id"} )} )
public class District {


    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    private String id;
    private String name;
    @ManyToOne
//    @JoinColumn( name = "region_id" )
    private Region region;

}
