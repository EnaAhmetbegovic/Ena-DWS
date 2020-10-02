package org.igman92.igman92.restservices.lkp;

import org.igman92.igman92.entity.impl.lkp.person.LKPGender;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/genders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LKPGenderController extends LKPEntityControllerAbstract<LKPGender>{
}
