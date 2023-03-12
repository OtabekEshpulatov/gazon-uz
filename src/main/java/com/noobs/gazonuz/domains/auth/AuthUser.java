package com.noobs.gazonuz.domains.auth;

import com.noobs.gazonuz.domains.Comment;
import com.noobs.gazonuz.domains.Document;
import com.noobs.gazonuz.domains.Order;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.enums.AuthUserStatus;
import com.noobs.gazonuz.enums.Languages;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@Builder
//@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {
    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    private String id;
    @Column( unique = true, nullable = false )
    private String username;
    @Column( unique = true, nullable = false )
    private String phoneNumber;
    @Column( unique = true )
    private String email;
    @Column( columnDefinition = "bigint default 0" )
    private Long orderNumbers;
    @Column( nullable = false )
    private String password;
    @Column( columnDefinition = "varchar default INACTIVE" )
    @Builder.Default
    private AuthUserStatus status = AuthUserStatus.INACTIVE;
    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authUser" )
//    @ToString.Exclude
    private Document document;
    @CreationTimestamp
    @Column( columnDefinition = "timestamp default now()" )
    private LocalDateTime createdAt;
    @Column( columnDefinition = "boolean default false" )
    private Boolean blocked;
    @CreationTimestamp
    @Column( columnDefinition = "timestamp    default now()" )
    private LocalDateTime blockedTill;
    @Column( nullable = false )
    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable( name = "user_roles", joinColumns = @JoinColumn( name = "user_id", referencedColumnName = "id" ), inverseJoinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id" ) )
    private Collection<AuthRole> authRoles;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authUser" )
//    @ToString.Exclude
    private Collection<Order> checks;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authUser" )
//    @ToString.Exclude
    private Collection<Comment> comments;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "authUser" )
//    @ToString.Exclude
    private Collection<Pitch> pitches;


    @Builder.Default
    private Languages language = Languages.UZBEK;


}
