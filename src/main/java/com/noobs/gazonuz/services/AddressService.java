package com.noobs.gazonuz.services;


import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.domains.location.Region;
import com.noobs.gazonuz.repositories.adress.DistrictRepository;
import com.noobs.gazonuz.repositories.adress.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;

    public Collection<Region> getRegion() {
        Collection<Region> regions = regionRepository.findAll();
        return regions;
    }

    public District getDistrictById(String id) {
        return districtRepository.findById(id).orElse(null);
    }
}
