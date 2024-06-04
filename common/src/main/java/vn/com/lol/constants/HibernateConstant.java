package vn.com.lol.constants;

/**
 * The HibernateConstant class modifier abstract constants of hibernate or SQL query
 *
 * @author Ngoc Khanh
 */
public class HibernateConstant {
    private HibernateConstant() {}
    public static final String IS_NOT_DELETED = "is_deleted = FALSE";
    public static final String IS_DELETED = "is_deleted";

    /**
     * The HibernateConstant.Table class modifier abstract constants of hibernate or SQL query for special table
     *
     * @author Ngoc Khanh
     */
    public static class Table {
        private Table() {}
        public static final String PRODUCT_TABLE = "product";
        public static final String IMAGE_PRODUCT_TABLE = "image_product";
        public static final String CATEGORY_TABLE = "category";
        public static final String FILE_TABLE = "file";
        public static final String SOFT_DELETE_PRODUCT = "UPDATE " + PRODUCT_TABLE + " SET " + IS_DELETED + " = TRUE WHERE id = ?";
        public static final String SOFT_DELETE_CATEGORY = "UPDATE " + CATEGORY_TABLE + " SET " + IS_DELETED + " = TRUE WHERE id = ?";
        public static final String SOFT_DELETE_FILE = "UPDATE " + FILE_TABLE + " SET " + IS_DELETED + " = TRUE WHERE id = ?";
        public static final String SOFT_DELETE_IMAGE_PRODUCT = "UPDATE " + IMAGE_PRODUCT_TABLE + " SET " + IS_DELETED + " = TRUE WHERE id = ?";

    }

    /**
     * The HibernateConstant.Entity class modifier abstract constants of entity
     *
     * @author Ngoc Khanh
     */
    public static class Entity {
        private Entity() {}

        public static final String PRODUCT = "Product";
        public static final String IMAGE_PRODUCT = "ImageProduct";
        public static final String CATEGORY = "Category";
        public static final String FILE = "File";
    }
}
