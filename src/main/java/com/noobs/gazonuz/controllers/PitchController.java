package com.noobs.gazonuz.controllers;
import com.noobs.gazonuz.domains.Location;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.repositories.PitchPaginationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/pitch")
@RequiredArgsConstructor
public class PitchController {
    private final PitchPaginationRepository pitchPaginationRepository;

    //"/find={elem_number}","/find=?page={page_num}&{elem_number}&search={search}"
@GetMapping( "/searched" )
public ModelAndView searchUsers(@RequestParam( name = "page", defaultValue = "0" ) int page ,
                                @RequestParam( name = "perPage", defaultValue = "10" ) int perPage ,
                                @RequestParam( name = "search", defaultValue = "" ) String search,
                                @ModelAttribute Location location) {
    System.out.println(location);
    var mav = new ModelAndView();
    List<Pitch> pitches;
    final Pageable pageable = PageRequest.of(page , perPage);
    final String searchBy = "%" + search + "%";
    pitches = pitchPaginationRepository.pitches(location.getLatitude()-0.0015, location.getLatitude()+0.0015,location.getLongitude()-0.0015, location.getLongitude()+0.0015, pageable);
    final Long pitchesSize = pitchPaginationRepository.countPitchesThatMatch(searchBy);
    preparePageableMAV(page , mav , pitches , pitchesSize , search);
    System.out.println(pitches);
    return mav;
}
    private void preparePageableMAV(Integer page , ModelAndView mav , List<Pitch> pitches , Long pitchesSize , String baseUrl) {
        mav.addObject("pitches" , pitches);
        mav.addObject("currentPage" , page);
        mav.addObject("totalPage" , pitchesSize/ PitchPaginationRepository.PER_PAGE);
        mav.addObject("perPage" , PitchPaginationRepository.PER_PAGE);
        mav.addObject("search" , baseUrl);
        mav.setViewName("pitch/list");
    }


}
