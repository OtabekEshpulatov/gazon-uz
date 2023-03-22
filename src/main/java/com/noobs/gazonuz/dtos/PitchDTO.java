package com.noobs.gazonuz.dtos;


import com.noobs.gazonuz.domains.Order;
import com.noobs.validators.ValidPrice;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PitchDTO {

    @Size(min = 8, max = 100, message = "pitch.min.name.size")
    private String name;
    //  private String latitude;
//  private String longitude;
    @Size(max = 500, message = "pitch.max.info.size")
    private String info;

    private String fullAddress;

    private List<MultipartFile> documents;

    @ValidPrice(message = "pitch.price.is.not.valid")
    private double price;
    @Pattern(regexp = "[0-9]{9}", message = "phone.number.is.invalid")
    private String phoneNumber;
    @Positive(message = "pitch.district.select")
    private String districtId;
//  private User user;

    public Collection<Order> getOrders() {
        return null;
    }
}