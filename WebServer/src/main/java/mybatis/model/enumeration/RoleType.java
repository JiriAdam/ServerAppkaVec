package mybatis.model.enumeration;

/**
 * Created by Irrielde on 22.12.2014.
 */
public enum RoleType {
    BASIC_USER, COMMERCIAL_USER ,ROLE_ADMIN;

    public static Long getRoleIdFromDatabase(RoleType type) {

        switch(type){
            default:
            case BASIC_USER: return 1L;
            case COMMERCIAL_USER: return 2L;
            case ROLE_ADMIN: return 0L;
        }

    }
}
