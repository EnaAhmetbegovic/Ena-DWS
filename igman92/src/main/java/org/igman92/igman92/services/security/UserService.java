package org.igman92.igman92.services.security;

import org.igman92.igman92.dao.IBaseDao;
import org.igman92.igman92.entity.impl.user.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.igman92.igman92.dao.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private IBaseDao<SecurityUser> userDao;

    @Transactional
    public SecurityUser findUserByUsername(String username) {
        String query = "FROM User WHERE username = :username";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);

        return userDao.querySingleResult(query, params);
    }

    /**
     * Used to find User entities that have a username that starts with the username param.
     *
     * @param username
     * @return
     */
    @Transactional
    public List<SecurityUser> findByBaseUsername(String username) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", username + "%");
        try {
            return userDao.query("SELECT u FROM User u WHERE u.username LIKE :username", params);
        } catch (NoEntityFoundException e) {
            return new ArrayList<>();
        }
    }

    @Autowired
    private void setUserDao(IBaseDao<SecurityUser> daoToSet) {
        userDao = daoToSet;
        userDao.setClazz(SecurityUser.class);
    }
}
