package io.sjm.automata.tests;

import static org.junit.Assert.*;
import io.sjm.automata.DFARuleBook;

import org.junit.Before;
import org.junit.Test;

public class DFARuleBookTest {
  private DFARuleBook<Integer> rulebook;

  @Before
  public void setUp() throws Exception {
    rulebook = new DFARuleBook<>();
    rulebook.addRule(1, 'a', 2);
    rulebook.addRule(1, 'b', 1);
    rulebook.addRule(2, 'a', 2);
    rulebook.addRule(2, 'b', 3);
    rulebook.addRule(3, 'a', 3);
    rulebook.addRule(3, 'b', 3);
  }

  @Test
  public void testNextState() {
    assertEquals(rulebook.nextState(1, 'a'), new Integer(2));
    assertEquals(rulebook.nextState(1, 'b'), new Integer(1));
    assertEquals(rulebook.nextState(2, 'a'), new Integer(2));
    assertEquals(rulebook.nextState(2, 'b'), new Integer(3));
    assertEquals(rulebook.nextState(3, 'a'), new Integer(3));
    assertEquals(rulebook.nextState(3, 'b'), new Integer(3));
  }
}
