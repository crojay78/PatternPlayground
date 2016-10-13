package topics.clone;


import de.oj.topics.clone.Car;
import de.oj.topics.clone.Engine;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by oj on 13.10.16.
 */
public class TestCloneSample extends TestCase {

    final static Logger log = Logger.getLogger(TestCloneSample.class);


    @Test
    public void testClonedObjectsReferences() throws CloneNotSupportedException {
        log.info("testing cloning pronlems");
        Engine engine = new Engine("Diesel", 177);
        assertNotNull(engine);
        Car original = new Car(engine, "blue", 4);
        assertNotNull(original);

        Car clonedCar = (Car) original.clone();
        assertNotNull(clonedCar);
        assertNotSame(original, clonedCar);
        log.info("clone und orginal sind nicht gleich! Haben also unterschiedliche Ref");
        log.info("orginal " + original + " hashcode: " + original.hashCode());
        log.info("clone " + clonedCar + " hashcode " + clonedCar.hashCode());
        log.info("-----------------");
        log.info("nun wird nur der Typ des orginal motors geöndert, dadurch ändert sich auch der clone");
        log.info("-----------------");
        original.getEngine().setType("Benzin");

        log.info("orginal " + original);
        log.info("clone " + clonedCar);

        assertEquals(original.getEngine().getType(), "Benzin");
        assertEquals(clonedCar.getEngine().getType(), "Benzin");
        
    }

}
