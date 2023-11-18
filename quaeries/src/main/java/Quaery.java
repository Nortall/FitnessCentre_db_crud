import java.lang.reflect.Field;
import java.util.List;

public class Quaery {
    private Entity entity;

    public Quaery(Entity entity) {
        this.entity = entity;
    }

    public String create() {
        StringBuilder sb = new StringBuilder();

        sb.append(
                "CREATE TABLE IF NOT EXISTS %s (%s %s PRIMARY KEY".formatted(
                        entity.getTableName(),
                        entity.getPrimaryKeyName(),
                        SqlTypes.byValue(entity.getPrimaryKeyType()).sqlName
                )
        );
        for (Field f:  entity.getFields()) {
            if(f.getName().equals(entity.getPrimaryKeyName()))
                continue;

            sb.append(
                    ", %s %s".formatted(
                            f.getName(),
                            SqlTypes.byValue(f.getType()).sqlName
                    )
            );
        }
        sb.append(");");
        return sb.toString();
    }
    public String drop(){
        return "DROP TABLE IF EXISTS %s;".formatted(entity.getTableName());
    }
    public String select(){
        return "SELECT * FROM %s;".formatted(entity.getTableName());
    }

    public String select(Object condition) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "SELECT * FROM %s WHERE ".formatted(
                        entity.getTableName()
                )
        );
        String separator = " and ";
        appendCondition(sb, condition, separator);
        replaceLast(sb, separator, ");");

        return sb.toString();
    }

    public String insert(Object condition) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "INSERT INTO %s VALUES (".formatted(
                        entity.getTableName()
                )
        );

        List<Field> fields = entity.getFields();
        for(int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i);
            sb.append(
                    "%s".formatted(
                            Converter.putInQuotation(new Reflect().getValue(condition, f.getName()))
                    )
            ).append(
                i != fields.size()-1 ? ", " : ");"
            );
        }

        return sb.toString();
    }

    public String delete(Object condition) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "DELETE FROM %s WHERE ".formatted(
                        entity.getTableName()
                )
        );
        String separator = " and ";
        appendCondition(sb, condition, separator);
        replaceLast(sb, separator, ";");

        return sb.toString();
    }

    public String update(Object condition, Object replacement) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(
                "UPDATE %s SET ".formatted(
                        entity.getTableName()
                )
        );

        String separator = ", ";
        appendCondition(sb, replacement, separator);
        replaceLast(sb, separator, " WHERE ");

        separator = " and ";
        appendCondition(sb, condition, separator);
        replaceLast(sb, separator, ";");

        return sb.toString();
    }

    private void appendCondition(StringBuilder sb, Object condition, String separator) throws Exception {
        List<Field> fields = entity.getFields();
        for(int i = 0; i < fields.size(); i++) {
            Field f = fields.get(i);
            String sqlColName = f.getName().equals(entity.getPrimaryKeyName()) ? entity.getPrimaryKeyName() : f.getName();
            sb.append(
                    "%s = %s%s".formatted(
                            sqlColName,
                            Converter.putInQuotation(new Reflect().getValue(condition, f.getName())),
                            separator
                    )
            );
        }
    }

    private void replaceLast(StringBuilder sb, String replaceable, String replacement){
        sb.delete(
                sb.lastIndexOf(replaceable),
                sb.length()
        ).append(replacement);
    }

    public Entity getEntity() {
        return entity;
    }
}
