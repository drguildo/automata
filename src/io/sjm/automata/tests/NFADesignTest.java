package io.sjm.automata.tests;

import io.sjm.automata.NFADesign;
import io.sjm.automata.NFARuleBook;
import org.junit.Before;
import org.junit.Test;

import static io.sjm.stdlib.datastructures.Sets.buildSet;
import static org.junit.Assert.*;

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

    design = new NFADesign<>(1, buildSet(4), rulebook);
  }

  @Test
  public void test() {
    assertTrue(design.accepts("bab"));
    assertTrue(design.accepts("bbbbb"));
    assertFalse(design.accepts("bbabb"));
  }

  @Test
  public void testFreeMoves() {
    NFARuleBook<Integer> fmrb = new NFARuleBook<>();
    fmrb.addRule(1, null, 2);
    fmrb.addRule(1, null, 4);
    fmrb.addRule(2, 'a', 3);
    fmrb.addRule(3, 'a', 2);
    fmrb.addRule(4, 'a', 5);
    fmrb.addRule(5, 'a', 6);
    fmrb.addRule(6, 'a', 4);

    design = new NFADesign<>(1, buildSet(2, 4), fmrb);
    assertTrue(design.accepts("aa"));
    assertTrue(design.accepts("aaa"));
    assertFalse(design.accepts("aaaaa"));
    assertTrue(design.accepts("aaaaaa"));
  }

  @Test
  public void testToNFA() {
    NFARuleBook<Integer> rb = new NFARuleBook<>();
    rb.addRule(1, 'a', 1);
    rb.addRule(1, 'a', 2);
    rb.addRule(1, null, 2);
    rb.addRule(2, 'b', 3);
    rb.addRule(3, 'b', 1);
    rb.addRule(3, null, 2);

    design = new NFADesign<>(1, buildSet(3), rb);
    assertEquals(design.toNFA().currentStates(), buildSet(1, 2));
    assertEquals(design.toNFA(buildSet(2)).currentStates(), buildSet(2));
    assertEquals(design.toNFA(buildSet(3)).currentStates(), buildSet(2, 3));
  }
}
