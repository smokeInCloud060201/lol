package vn.com.lol.yasuo.commons.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static vn.com.lol.constants.GlobalHibernateConstant.IS_DELETED;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalHibernateConstant {
    /**
     * The HibernateConstant.Table class modifier abstract constants of hibernate or SQL query for special table
     *
     * @author Ngoc Khanh
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Table {
        public static final String PRODUCT = "product";
        public static final String IMAGE_PRODUCT = "image_product";
        public static final String CATEGORY = "category";
        public static final String FILE = "file";
        public static final String SOFT_DELETE_PRODUCT = "UPDATE " + PRODUCT + " SET " + IS_DELETED + " = TRUE WHERE id = ?";
        public static final String SOFT_DELETE_CATEGORY = "UPDATE " + CATEGORY + " SET " + IS_DELETED + " = TRUE WHERE id = ?";
        public static final String SOFT_DELETE_FILE = "UPDATE " + FILE + " SET " + IS_DELETED + " = TRUE WHERE id = ?";
        public static final String SOFT_DELETE_IMAGE_PRODUCT = "UPDATE " + IMAGE_PRODUCT + " SET " + IS_DELETED + " = TRUE WHERE id = ?";

    }

    /**
     * The HibernateConstant.Entity class modifier abstract constants of entity
     *
     * @author Ngoc Khanh
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Entity {

        public static final String PRODUCT = "Product";
        public static final String IMAGE_PRODUCT = "ImageProduct";
        public static final String CATEGORY = "Category";
        public static final String FILE = "File";
    }
}
