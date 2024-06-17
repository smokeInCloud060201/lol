package vn.com.lol.yorick.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static vn.com.lol.common.constants.GlobalHibernateConstant.Table.SOFT_DELETE_BY_ID_QUERY;
import static vn.com.lol.common.constants.GlobalHibernateConstant.Table.UPDATE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateConstant {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Table {
        public static final String USER = "users";
        public static final String PERMISSION = "permission";
        public static final String ROLE = "role";
        public static final String SOFT_DELETE_USER = UPDATE + USER + SOFT_DELETE_BY_ID_QUERY;
        public static final String SOFT_DELETE_PERMISSION = UPDATE + PERMISSION + SOFT_DELETE_BY_ID_QUERY;
        public static final String SOFT_DELETE_ROLE = UPDATE + ROLE + SOFT_DELETE_BY_ID_QUERY;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Entity {
        public static final String USER = "User";
        public static final String PERMISSION = "Permission";
        public static final String ROLE = "Role";
    }

}
