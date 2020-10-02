package org.igman92.igman92.services.security;

import org.igman92.igman92.entity.impl.user.SecurityUser;
import org.igman92.igman92.entity.impl.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class SecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);

//    @Transactional(readOnly = true)
//    public List<UserRole> getAllUserRoles() {
//        IsssUserDetails userDetails = (IsssUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        //TODO amel, Feb 15, 2018: Exception handling not implemented
//        List<UserRole> userRoles = userDetails.getUser().getUserRoles().stream().sorted(Comparator.comparingLong(ur -> ur.getId()))
//                .collect(Collectors.toList());
//        return userRoles;
//    }
//
//    @Transactional(readOnly = true)
//    public UserRole getUserRole(int ordinal) {
//        IsssUserDetails userDetails = (IsssUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        SecurityUser user = userDetails.getUser();
//
//        //TODO amel, Feb 15, 2018: Exception handling not implemented
//        List<UserRole> userRoles = user.getUserRoles().stream().sorted(Comparator.comparingLong(ur -> ur.getId()))
//                .collect(Collectors.toList());
//        return userRoles.get(ordinal);
//    }
}
