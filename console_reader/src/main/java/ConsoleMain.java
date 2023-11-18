import org.example.*;

import java.io.Console;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Flow;

public class ConsoleMain {
    public static void main(String[] args) throws Exception {
        Databace databace = new Databace();
        List<Quaery> quaeries = List.of(
                new Quaery(
                        new Entity(Coaches.class)
                ),
                new Quaery(
                        new Entity(Customers.class)
                ),
                new Quaery(
                        new Entity(FitnessCentre.class, "Centre")
                ),
                new Quaery(
                        new Entity(Halls.class)
                ),
                new Quaery(
                        new Entity(Services.class)
                ),
                new Quaery(
                        new Entity(Flow.Subscription.class)
                )
        );

        for (Quaery q: quaeries) {
            //databace.getStatment().execute(q.drop());

            //System.out.println(q.create());
            databace.getStatment().execute(q.create());
        }

        ConsoleReader consoleReader = new ConsoleReader(databace, quaeries);
        consoleReader.start();


    }
}
