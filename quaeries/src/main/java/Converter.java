import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Converter {
    public static String putInQuotation(Object o){
        return o.getClass().equals(String.class) ? "\'%s\'".formatted(o) : o.toString();
    }
    public static Object fromConsole(Entity entity) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Object o = entity.getEntityType().newInstance();
        int count = 0;
        for (Field f: entity.getFields()) {
            System.out.print(f.getName()+": ");
            String val = new Scanner(System.in).nextLine().trim();
            if(val.isEmpty())
                continue;
            count++;
            new Reflect().setVal(
                    o,
                    f,
                    SqlTypes.byValue(f.getType()).castType(val)
            );
        }

        return count == 0 ? null : o;
    }

    public static String resultSetToString(ResultSet resultSet, Entity entity) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Table %s:".formatted(entity.getTableName())).append("\n");
        int count = 0;
        while (resultSet.next()){
            stringBuilder.append("count: %d".formatted(count)).append("\n");
            for (Field f:entity.getFields()) {
                stringBuilder.append(
                        "%s = %s".formatted(
                                f.getName(),
                                resultSet.getObject(f.getName()).toString()
                        )
                ).append("\n");
            }
            count++;
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
