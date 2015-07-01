package io.sjm.automata.tests;

import static io.sjm.stdlib.datastructures.Sets.buildSet;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.sjm.automata.NFADesign;
import io.sjm.automata.NFARuleBook;

public class NFADesignTest {
  private NFARuleBook<Integer> rulebook;
  private NFADesign<Integer> design;

  @Before
  public void setUp() throws Exception {
    rulebook = new NFARuleBook<>();
    rulebook.addRule(1, 'a', 1);
    rulebook.addRule(1, 'b', 1);
    rulebook.addRule(1, 'b', 2);
    rulebook.addRule(2, 'a', 3);
    rulebook.addRule(2, 'b', 3);
    rulebook.addRule(3, 'a', 4);
    rulebook.addRule(3, 'b', 4);

    design = new NFADesign<>(buildSet(1), buildSet(4), rulebook);
  }

  @Test
  public void test() {
    assertTrue(design.accepts("bab"));
    assertTrue(design.accepts("bbbbb"));
    assertFalse(design.accepts("bbabb"));
  }
}
