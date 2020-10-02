package org.igman92.igman92.restservices.lkp;

import org.igman92.igman92.dto.person.PersonDTO;
import org.igman92.igman92.entity.impl.lkp.LKPContactType;
import org.igman92.igman92.services.lkp.LKPContactTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/contact-types")
public class LKPContactTypeController extends LKPEntityControllerAbstract<LKPContactType> {

    @Autowired
    private LKPContactTypeService contactTypeService;

    @RequestMapping(value = "/personal/")
    @ResponseStatus(HttpStatus.OK)
    public List<LKPContactType> getPersonalContactTypes() {

        return contactTypeService.getPersonalContactTypes();
    }
}
