import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InsertAllMain {
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
                        new Entity(Subscription.class)
                )
        );

        for (Quaery q: quaeries) {
            databace.getStatment().execute(q.drop());

            System.out.println(q.create());
            databace.getStatment().execute(q.create());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("fitnessCentres.json");
        List<FitnessCentre> fitnessCentres = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, FitnessCentre.class));

        for (FitnessCentre f: fitnessCentres) {
            Quaery q = new Quaery(new Entity(FitnessCentre.class, "Centre"));
            System.out.println(q.insert(f));
            databace.getStatment().execute(q.insert(f));

            q = new Quaery(new Entity(Halls.class));
            for (Halls h: f.getHalls()) {
                System.out.println("\t"+q.insert(h));
                databace.getStatment().execute(q.insert(h));
            }

            System.out.println();
            q = new Quaery(new Entity(Coaches.class));
            for (Coaches c: f.getCoaches()) {
                System.out.println("\t"+q.insert(c));
                databace.getStatment().execute(q.insert(c));
            }

            System.out.println();
            q = new Quaery(new Entity(Services.class));
            for (Services s: f.getServices()) {
                System.out.println("\t"+q.insert(s));
                databace.getStatment().execute(q.insert(s));
            }

            System.out.println();
            q = new Quaery(new Entity(Customers.class));
            for (Customers c: f.getCustomers()) {
                System.out.println("\t"+q.insert(c));
                databace.getStatment().execute(q.insert(c));
            }

            System.out.println();
            q = new Quaery(new Entity(Subscription.class));
            for (Subscription s: f.getSubscriptions()) {
                System.out.println("\t"+q.insert(s));
                databace.getStatment().execute(q.insert(s));
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }

    }
}
