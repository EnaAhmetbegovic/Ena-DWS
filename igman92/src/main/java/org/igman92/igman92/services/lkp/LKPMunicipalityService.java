package org.igman92.igman92.services.lkp;

import org.igman92.igman92.dao.exceptions.NoEntityFoundException;
import org.igman92.igman92.entity.impl.lkp.place.LKPMunicipality;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LKPMunicipalityService extends LKPService<LKPMunicipality> {

    @Transactional(readOnly = true)
    public List<LKPMunicipality> getMunicipalities(Integer entityId, Short cantonId)  {
        if (entityId == null || cantonId == null) {
            throw new IllegalArgumentException("entityId and cantonId parameters must be specified");
        }

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("entityId", entityId);
            params.put("cantonId", cantonId);

            return lookupDao.query("FROM LKPMunicipality WHERE entity.id = :entityId and canton.id = :cantonId", params);
        } catch (NoEntityFoundException e) {
            return Collections.emptyList();
        }
    }
}
