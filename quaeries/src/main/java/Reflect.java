import java.lang.reflect.Field;

public class Reflect {

    public Reflect() {
    }

    public void setVal(Object object, Field field, Object value) throws NoSuchFieldException, IllegalAccessException {
        field.setAccessible(true);
        field.set(object, value);
    }
    public void setVal(Object object, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        setVal(object, object.getClass().getDeclaredField(fieldName), value);
    }

    public Object getValue(Object object, String fieldName) throws Exception {
        return object.getClass().getMethod(
                createFunc(fieldName, "get")
        ).invoke(object);
    }
    public String createFunc(String fieldName, String prefix){
        return prefix + capitalize(fieldName);
    }

    public String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


}

