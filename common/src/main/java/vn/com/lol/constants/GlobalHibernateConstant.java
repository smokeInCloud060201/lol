package vn.com.lol.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The HibernateConstant class modifier abstract constants of hibernate or SQL query
 *
 * @author Ngoc Khanh
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalHibernateConstant {
    public static final String IS_NOT_DELETED = "is_deleted = FALSE";
    public static final String IS_DELETED = "is_deleted";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Table {
        public static final String FILE = "file";

        public static final String SOFT_DELETE_FILE = "UPDATE " + FILE + " SET " + IS_DELETED + " = TRUE WHERE id = ?";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Entity {
        public static final String FILE = "File";
    }
}
