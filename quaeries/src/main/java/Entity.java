import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Entity {
    private Class entityType;
    private String tableName;
    private String primaryKeyName;
    private Class primaryKeyType;
    private List<Field> fields;

    public Entity(Class entityType, String tableName, String primaryKeyName, Class primaryKeyType) {
        this.entityType = entityType;
        this.tableName = tableName;
        this.primaryKeyName = primaryKeyName;
        this.primaryKeyType = primaryKeyType;
        this.fields = getFields(this.entityType);
    }

    public Entity(Class entityType) {
        this(
                entityType,
                entityType.getSimpleName(),
                "id",
                Integer.class
        );
    }
    public Entity(Class entityType, String tableName) {
        this(
                entityType,
                tableName,
                "id",
                Integer.class
        );
    }

    public Class getEntityType() {
        return entityType;
    }

    public String getTableName() {
        return tableName;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public Class getPrimaryKeyType() {
        return primaryKeyType;
    }

    public List<Field> getFields() {
        return fields;
    }

    private List<Field> getFields(Class c){
        return Arrays.stream(c.getDeclaredFields())
                .filter(field -> SqlTypes.contains(field.getType()))
                .collect(Collectors.toList());
    }

}
