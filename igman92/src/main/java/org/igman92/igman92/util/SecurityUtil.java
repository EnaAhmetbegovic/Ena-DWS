package org.igman92.igman92.util;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String SECRETARY = "ROLE_SECRETARY";

    public static final String[] ALL_ROLES = new String[] {
            ADMIN,
            SECRETARY
    };

    public static boolean checkPrivileges (Long requiredId, Long foundId) {

        return requiredId.equals(foundId);
    }
}
