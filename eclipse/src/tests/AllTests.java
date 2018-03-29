package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BuilderTest.class, CantonTest.class, LineTest.class, PassengerTest.class, SegmentTest.class, StationTest.class, TrainTest.class })
public class AllTests {

}
