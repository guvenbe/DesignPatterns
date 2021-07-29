package activemesa.creational.singleton;

import com.google.common.collect.Iterables;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static org.junit.Assert.assertEquals;

interface Database {
    int getPopulation(String name);
}

class SingletonDatabase {
    private Dictionary<String, Integer> capitals = new Hashtable<>();
    private static int instanceCount = 0;

    public static int getInstanceCount() {
        return instanceCount;
    }

    public SingletonDatabase() {
        instanceCount++;
        System.out.println("initializing database");

        try {
            File f = new File(Testability.class.getProtectionDomain()
                    .getCodeSource().getLocation().getPath());

            System.out.println("This the path for text fle =" +
                    Testability.class.getProtectionDomain()
                            .getCodeSource().getLocation().getPath());
            //Put the Capital.txt file in DesignPatterns/out/production/DesignPatterns

            Path fullPath = Paths.get(f.getPath(), "Capital.txt");
            List<String> lines = Files.readAllLines(fullPath);

            Iterables.partition(lines, 2)
                    .forEach(kv -> capitals.put(kv.get(0).trim(), Integer.parseInt(kv.get(1))));
            System.out.println(capitals);

        } catch (Exception e) {
            System.err.println(e);
        }

    }


    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getInstance() {
        return INSTANCE;
    }

    public int getPopulation(String name) {
        return capitals.get(name);
    }
}


class SingletonRecordFinder {
    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for (String name : names)
            result += SingletonDatabase.getInstance().getPopulation(name);
        return result;
    }
}

//Allows contructor Injection
class ConfigurableRecordFinder {
    private Database database;

    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for (String name : names)
            result += database.getPopulation(name);
        return result;
    }
}


class DummyDatabase implements Database {
    private Dictionary<String, Integer> data = new Hashtable<>();

    public DummyDatabase() {
        data.put("alpha", 1);
        data.put("beta", 2);
        data.put("gamma", 3);
    }

    @Override
    public int getPopulation(String name) {
        return data.get(name);
    }
}


public class Testability {


    public static void main(String[] args) {
        SingletonDatabase db = SingletonDatabase.getInstance();

        String city = "Tokyo";
        int pop = db.getPopulation(city);
        System.out.println(
                String.format("%s has population %d", city, pop)
        );

    }


    //This is more of integration test since it requires a real database
    @Test
    public void singletonTotalPopulationTest() {
        // testing on a live database
        SingletonRecordFinder rf = new SingletonRecordFinder();
        List<String> names = List.of("Seoul", "Mexico City");
        int tp = rf.getTotalPopulation(names);
        assertEquals(17500000 + 17400000, tp);
    }

    //This is a UNIT test
    @Test
    public void dependentPopulationTest() {
        DummyDatabase db = new DummyDatabase();
        ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
        assertEquals(4, rf.getTotalPopulation(
                List.of("alpha", "gamma")
        ));
    }
}
