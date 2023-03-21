package com.noobs.gazonuz.services;


import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.domains.location.Region;
import com.noobs.gazonuz.repositories.adress.DistrictRepository;
import com.noobs.gazonuz.repositories.adress.RegionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;

    public Collection<Region> getRegion() {
        return regionRepository.findAll();
    }

    public District getDistrictById(String id) {
        return districtRepository.findById(id).orElse(null);
    }


    public List<District> getDistricts(String regionId) {
        return districtRepository.getDistricts(regionId);
    }
}
