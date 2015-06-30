package io.sjm.automata.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import io.sjm.automata.DFADesign;
import io.sjm.automata.DFARuleBook;

public class DFADesignTest {
  private DFARuleBook<Integer> rulebook;
  private DFADesign<Integer> design;

  @Before
  public void setUp() throws Exception {
    rulebook = new DFARuleBook<>();
    rulebook.addRule(1, 'a', 2);
    rulebook.addRule(1, 'b', 1);
    rulebook.addRule(2, 'a', 2);
    rulebook.addRule(2, 'b', 3);
    rulebook.addRule(3, 'a', 3);
    rulebook.addRule(3, 'b', 3);

    design = new DFADesign<>(1, Arrays.asList(3), rulebook);
  }

  @Test
  public void test() {
    assertFalse(design.accepts("a"));
    assertFalse(design.accepts("baa"));
    assertTrue(design.accepts("baba"));
  }
}
