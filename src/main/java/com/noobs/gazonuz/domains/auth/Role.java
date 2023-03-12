package com.noobs.gazonuz.domains.auth;

import com.noobs.gazonuz.domains.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Role implements BaseEntity {
    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    private String id;
    @Column( nullable = false )
    private String name;
    @Column( nullable = false, unique = true )
    private String code;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id" ),
            inverseJoinColumns = @JoinColumn( name = "permission_id", referencedColumnName = "id" )
    )
    private Collection<Permission> authPermissions;

}
