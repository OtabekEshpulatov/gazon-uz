package com.noobs.gazonuz.domains.location;

import com.noobs.gazonuz.domains.BaseEntity;
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
public class District implements BaseEntity {


    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    private String id;
    private String name;
    @ManyToOne
    private Region region;

}
