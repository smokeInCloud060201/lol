package vn.com.lol.yorick.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static vn.com.lol.constants.GlobalHibernateConstant.IS_DELETED;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateConstant {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Table {
        public static final String USER = "users";
        public static final String PERMISSION = "permission";
        public static final String ROLE = "role";
        public static final String SOFT_DELETE_USER = "UPDATE " + USER + " SET " + IS_DELETED + " = TRUE WHERE id = ?";
        public static final String SOFT_DELETE_PERMISSION = "UPDATE " + PERMISSION + " SET " + IS_DELETED + " = TRUE WHERE id = ?";
        public static final String SOFT_DELETE_ROLE = "UPDATE " + ROLE + " SET " + IS_DELETED + " = TRUE WHERE id = ?";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Entity {
        public static final String USER = "User";
        public static final String PERMISSION = "Permission";
        public static final String ROLE = "Role";
    }

}
