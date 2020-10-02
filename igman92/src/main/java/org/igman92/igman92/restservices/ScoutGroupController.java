package org.igman92.igman92.restservices;

import org.igman92.igman92.dto.scoutGroup.YearlyDataDTO;
import org.igman92.igman92.services.scoutGroup.ScoutGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/scout-group")
public class ScoutGroupController {

    @Autowired
    private ScoutGroupService scoutGroupService;

    @RequestMapping(value = "/data/{scout-group}/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public YearlyDataDTO getBasicYearlyData(@PathVariable("scout-group") Long scoutGroupId) {

        return scoutGroupService.getBasicYearlyData(scoutGroupId);
    }
}
