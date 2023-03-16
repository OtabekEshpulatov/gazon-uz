package com.noobs.gazonuz.dtos;


import com.noobs.gazonuz.domains.Order;
import com.noobs.gazonuz.domains.auth.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PitchCreateDTO {
  private String name;
  private String latitude;
  private String longitude;
  private String info;
  private String fullAddress;
  private Collection<MultipartFile> documents;

  private double price;
  private String phoneNumber;
  private String district;
  private User user;


    public Collection<Order> getOrders() {
        return null;
    }
}
