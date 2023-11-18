import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleReader {
    private Databace databace;
    private List<Quaery> quaeries;

    private Set<String> commands = Set.of(
            "select",
            "select all",
            "insert",
            "delete",
            "update",
            "close",
            "help",
            "tables",
            "commands"
    );

    public ConsoleReader(Databace databace, List<Quaery> quaeries) {
        this.databace = databace;
        this.quaeries = quaeries;
    }

    public String tables(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < quaeries.size(); i++) {
            Quaery q = quaeries.get(i);
            stringBuilder.append("%s".formatted(q.getEntity().getTableName())).append((i != quaeries.size()? ", " : "]"));
        }
        return stringBuilder.toString();
    }
    public void start() throws Exception {
        System.out.println("commands: " + commands.toString());
        System.out.println("tables: "+tables());


        while (true) {
            System.out.print("command: ");
            String command = new Scanner(System.in).nextLine().trim();
            if(!commands.contains(command)) {
                System.out.println("COMMAND <<%s>> NOT FOUND".formatted(command));
            }else if (command.equals("close")) {
                return;
            } else if (command.equals("help")) {
                System.out.println("commands: " + commands.toString());
                System.out.println("tables: "+tables());
            } else if (command.equals("tables")) {
                System.out.println("tables: "+tables());
            } else if (command.equals("commands")) {
                System.out.println("commands: " + commands.toString());
            } else {
                read(command);
            }


        }
    }
    public void read(String command) throws Exception {
        System.out.print("table: ");
        String table = new Scanner(System.in).nextLine().trim();
        Quaery quaery = getQuaery(table);
        if(quaery == null){
            System.out.println("TABLE <<%s>> NOT FOUND".formatted(table));
            return;
        }

        if(command.equals("select all")){
            System.out.println(
                    Converter.resultSetToString(
                            databace.getStatment().executeQuery(quaery.select()),
                            quaery.getEntity()
                    )
            );
        } else if (command.equals("select")) {
            select(quaery);
        } else if (command.equals("insert")) {
            insert(quaery);
        } else if (command.equals("delete")) {
            delete(quaery);
        } else if(command.equals("update")){
            update(quaery);
        }
    }



    private void select(Quaery quaery) throws Exception {
        Object o = Converter.fromConsole(quaery.getEntity());
        if(o == null){
            System.out.println("OBJECT MUST BE NOT NULL");
            return;
        }
        System.out.println(
                Converter.resultSetToString(
                        databace.getStatment().executeQuery(quaery.select(o)), quaery.getEntity()
                )
        );
    }
    private void insert(Quaery quaery) throws Exception {
        Object o = Converter.fromConsole(quaery.getEntity());
        if(o == null){
            System.out.println("OBJECT MUST BE NOT NULL");
            return;
        }

        databace.getStatment().execute(quaery.insert(o));

    }

    private void delete(Quaery quaery) throws Exception {
        Object o = Converter.fromConsole(quaery.getEntity());
        if(o == null){
            System.out.println("OBJECT MUST BE NOT NULL");
            return;
        }

        databace.getStatment().execute(quaery.delete(o));
    }
    private void update(Quaery quaery) throws Exception {
        System.out.println("condition");
        Object condition = Converter.fromConsole(quaery.getEntity());
        if(condition == null){
            System.out.println("OBJECT MUST BE NOT NULL");
            return;
        }

        Object replacement = Converter.fromConsole(quaery.getEntity());
        if(replacement == null){
            System.out.println("OBJECT MUST BE NOT NULL");
            return;
        }

        databace.getStatment().execute(quaery.update(condition, replacement));
    }
    private Quaery getQuaery(String table){
        for (Quaery q: quaeries) {
            if(q.getEntity().getTableName().equals(table))
                return q;
        }
        return null;
    }


}
