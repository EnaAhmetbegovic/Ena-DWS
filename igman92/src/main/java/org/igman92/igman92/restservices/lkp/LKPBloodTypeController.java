package org.igman92.igman92.restservices.lkp;

import org.igman92.igman92.entity.impl.lkp.person.LKPBloodType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/blood-types")
public class LKPBloodTypeController extends LKPEntityControllerAbstract<LKPBloodType> {
}
