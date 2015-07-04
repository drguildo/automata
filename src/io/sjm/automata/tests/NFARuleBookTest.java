package io.sjm.automata.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import io.sjm.automata.FARule;
import io.sjm.automata.NFARuleBook;
import io.sjm.stdlib.datastructures.Sets;

@SuppressWarnings("static-method")
public class NFARuleBookTest {
  private NFARuleBook<Integer> rb = new NFARuleBook<>();
  private FARule<Integer> r1 = new FARule<>(1, 'a', 1);
  private FARule<Integer> r2 = new FARule<>(1, 'b', 1);
  private FARule<Integer> r3 = new FARule<>(1, 'b', 2);

  @Before
  public void setUp() throws Exception {
    rb.add(r1);
    rb.add(r2);
    rb.add(r3);
    rb.addRule(2, 'a', 3);
    rb.addRule(2, 'b', 3);
    rb.addRule(3, 'a', 4);
    rb.addRule(3, 'b', 4);
  }

  @Test
  public void testRulesFor() {
    assertEquals(rb.rulesFor(1, 'a').size(), 1);
    assertTrue(rb.rulesFor(1, 'a').contains(r1));
    assertFalse(rb.rulesFor(1, 'a').contains(new FARule<>(1, 'a', 2)));

    assertEquals(rb.rulesFor(1, 'b').size(), 2);
    assertTrue(rb.rulesFor(1, 'b').contains(r2));
    assertTrue(rb.rulesFor(1, 'b').contains(r3));
  }

  @Test
  public void testFollowRulesFor() {
    assertEquals(rb.followRulesFor(1, 'a').size(), 1);
    assertTrue(rb.followRulesFor(1, 'a').contains(1));
    assertFalse(rb.followRulesFor(1, 'a').contains(2));

    assertEquals(rb.followRulesFor(1, 'b').size(), 2);
    assertTrue(rb.followRulesFor(1, 'b').contains(1));
    assertTrue(rb.followRulesFor(1, 'b').contains(2));
  }

  @Test
  public void testNextStates() {
    HashSet<Integer> states;

    states = new HashSet<>(Arrays.asList(1));
    assertEquals(rb.nextStates(states, 'b').size(), 2);
    assertTrue(rb.nextStates(states, 'b').contains(1));
    assertTrue(rb.nextStates(states, 'b').contains(2));

    states = new HashSet<>(Arrays.asList(1, 2));
    assertEquals(rb.nextStates(states, 'a').size(), 2);
    assertTrue(rb.nextStates(states, 'a').contains(1));
    assertTrue(rb.nextStates(states, 'a').contains(3));

    states = new HashSet<>(Arrays.asList(1, 3));
    assertEquals(rb.nextStates(states, 'b').size(), 3);
    assertTrue(rb.nextStates(states, 'b').contains(1));
    assertTrue(rb.nextStates(states, 'b').contains(2));
    assertTrue(rb.nextStates(states, 'b').contains(4));
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

    assertEquals(fmrb.nextStates(Sets.buildSet(1), null), Sets.buildSet(2, 4));
    assertEquals(fmrb.followFreeMoves(Sets.buildSet(1)), Sets.buildSet(1, 2, 4));
  }
}
