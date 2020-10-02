package org.igman92.igman92.restservices.lkp;

import org.igman92.igman92.entity.impl.lkp.place.LKPMunicipality;
import org.igman92.igman92.services.lkp.LKPMunicipalityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/municipalities")
public class LKPMunicipalityController extends LKPEntityControllerAbstract<LKPMunicipality> {

    @RequestMapping(value = "/{entityId}/{cantonId}", method = RequestMethod.GET)
    public List<LKPMunicipality> getMunicipalitiesByEntityAndCanton(@PathVariable(name = "entityId") Integer entityId,
                                                                    @PathVariable(name = "cantonId", required = false) Short cantonId)  {

        return ((LKPMunicipalityService) service).getMunicipalities(entityId, cantonId);
    }
}
