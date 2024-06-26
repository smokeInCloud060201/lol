package vn.com.lol.yasuo.commons.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static vn.com.lol.common.constants.GlobalHibernateConstant.Table.SOFT_DELETE_BY_ID_QUERY;
import static vn.com.lol.common.constants.GlobalHibernateConstant.Table.UPDATE;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateConstant {
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
        public static final String SOFT_DELETE_PRODUCT = UPDATE + PRODUCT + SOFT_DELETE_BY_ID_QUERY;
        public static final String SOFT_DELETE_CATEGORY = UPDATE + CATEGORY + SOFT_DELETE_BY_ID_QUERY;
        public static final String SOFT_DELETE_FILE = UPDATE + FILE + SOFT_DELETE_BY_ID_QUERY;
        public static final String SOFT_DELETE_IMAGE_PRODUCT = UPDATE + IMAGE_PRODUCT + SOFT_DELETE_BY_ID_QUERY;

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
