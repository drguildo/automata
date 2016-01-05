package io.sjm.automata.tests;

import io.sjm.automata.NFADesign;
import io.sjm.automata.NFARuleBook;
import io.sjm.automata.NFASimulation;
import org.junit.Before;
import org.junit.Test;

import static io.sjm.sjmlib.datastructures.Sets.buildSet;
import static org.junit.Assert.*;

public class NFASimulationTest {
  private NFARuleBook<Integer> rulebook;
  private NFADesign<Integer> design;
  private NFASimulation<Integer> simulation;

  @Before
  public void setUp() throws Exception {
    rulebook = new NFARuleBook<>();

    rulebook.addRule(1, 'a', 1);
    rulebook.addRule(1, 'a', 2);
    rulebook.addRule(1, null, 2);

    rulebook.addRule(2, 'b', 3);

    rulebook.addRule(3, 'b', 1);
    rulebook.addRule(3, null, 2);

    design = new NFADesign<>(1, buildSet(3), rulebook);
  }

  @Test
  public void testNextState() throws Exception {
    simulation = new NFASimulation<>(design);

    assertEquals(simulation.nextState(buildSet(1, 2), 'a'), buildSet(1, 2));
    assertEquals(simulation.nextState(buildSet(1, 2), 'b'), buildSet(3, 2));
    assertEquals(simulation.nextState(buildSet(3, 2), 'b'), buildSet(1, 3, 2));
    assertEquals(simulation.nextState(buildSet(1, 3, 2), 'b'), buildSet(1, 3, 2));
    assertEquals(simulation.nextState(buildSet(1, 3, 2), 'a'), buildSet(1, 2));
  }

  @Test
  public void testToDFA() throws Exception {
    simulation = new NFASimulation<>(design);

    assertFalse(design.accepts("aaa"));
    assertTrue(design.accepts("aab"));
    assertTrue(design.accepts("bbbabb"));
  }
}
