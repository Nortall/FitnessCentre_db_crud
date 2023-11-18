import java.util.Arrays;

public enum SqlTypes {
    INTEGER("INT"){
        @Override
        public Object castType(String value) {
            return Integer.parseInt(value.trim());
        }
    },
    INT("INT"){
        @Override
        public Object castType(String value) {
            return Integer.parseInt(value.trim());
        }
    },
    LONG("BIGINT"){
        @Override
        public Object castType(String value) {
            return Long.parseLong(value.trim());
        }
    },
    STRING("VARCHAR(255)"){
        @Override
        public Object castType(String value) {
            return value.trim();
        }
    };

    public String sqlName;


    SqlTypes(String name) {
        this.sqlName = name;
    }
    public static SqlTypes byValue(String name) {
        return Arrays.stream(SqlTypes.values())
                .filter(enumVal -> enumVal.name().equals(name.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
    public abstract Object castType(String value);
    public static SqlTypes byValue(Class type) {
        return byValue(type.getSimpleName());
    }

    public static boolean contains(String name){
        return byValue(name) != null;
    }

    public static boolean contains(Class type){
        return byValue(type) != null;
    }
}
