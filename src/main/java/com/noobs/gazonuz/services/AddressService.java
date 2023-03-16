package com.noobs.gazonuz.services;


import com.noobs.gazonuz.domains.location.Region;
import com.noobs.gazonuz.repositories.adress.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final RegionRepository regionRepository;

    public Collection<Region> getRegion() {
        Collection<Region> regions = regionRepository.findAll();
        return regions;
    }

    ;
}
