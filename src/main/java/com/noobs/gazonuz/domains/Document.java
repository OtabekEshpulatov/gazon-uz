package com.noobs.gazonuz.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    @Id
    @GenericGenerator( name = "uuid2", strategy = "uuid2" )
    @GeneratedValue( generator = "uuid2" )
    private String id;

    private String originalFileName;
    private String generatedFileName;
    private String filePath;
    private String mimeType;
    private Long fileSize;
    private String extension;
    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @ToString.Exclude
    private User user;


//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @ToString.Exclude
//    private Pitch pitch;
}
