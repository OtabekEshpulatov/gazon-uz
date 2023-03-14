package com.noobs.gazonuz.dtos;


import com.noobs.gazonuz.domains.Comment;
import com.noobs.gazonuz.domains.Document;
import com.noobs.gazonuz.domains.Order;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.domains.location.District;
import lombok.*;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PitchCreateDTO {
    String name;
    String latitude;
    String longitude;
    String info;
    String fullAddress;
    Collection<Document> documents;
    Collection<Order> orders;
    Collection<Comment> comments;
    Double price;
    String phoneNumber;

    User user;
    District district;


}
