package org.igman92.igman92.restservices.lkp;

import org.igman92.igman92.entity.base.LookUpEntity;
import org.igman92.igman92.services.lkp.LKPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@RestController
public abstract class LKPEntityControllerAbstract<T extends LookUpEntity> {

    @Autowired
    protected LKPService<T> service;

    @PostConstruct
    protected void configureServiceClass() {
        Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        service.setClazz(clazz);
    }

    @CrossOrigin(origins = "http://localhost", maxAge = 3000)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<T> getAllEntities()  {
        return service.getAllEntities();
    }

    @RequestMapping(value = "name", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<T> getByName(@RequestParam String name)  {
        return service.getByName(name);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public T addEntity(@RequestBody T newEntity)  {
        return service.save(newEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public T getEntity(@PathVariable("id") Short lookupEntityId)  {
        return service.get(lookupEntityId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public T updateEntity(@RequestBody T updatedLookupEntity)  {
        return service.updateEntity(updatedLookupEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEntity(@PathVariable("id") Short id)  {
        service.delete(id);
    }
}
