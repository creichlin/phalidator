package ch.kerbtier.phalidator.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ch.kerbtier.phalidator.tests.generic.GenericJsTests;
import ch.kerbtier.phalidator.tests.generic.GenericTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   GenericTests.class,
   GenericJsTests.class
})
public class AllTests {
  
}
