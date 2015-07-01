package io.sjm.automata.tests;

import static io.sjm.stdlib.datastructures.Sets.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.sjm.automata.NFA;
import io.sjm.automata.NFARuleBook;

public class NFATest {
  private NFARuleBook<Integer> rulebook;
  private NFA<Integer> nfa;

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
  }

  @Test
  public void testAccepting() {
    nfa = new NFA<>(buildSet(1), buildSet(4), rulebook);
    assertFalse(nfa.accepting());

    nfa = new NFA<>(buildSet(1, 2, 4), buildSet(4), rulebook);
    assertTrue(nfa.accepting());
  }

  @Test
  public void testReadCharacter() {
    nfa = new NFA<>(buildSet(1), buildSet(4), rulebook);
    assertFalse(nfa.accepting());

    nfa.readCharacter('b');
    assertFalse(nfa.accepting());

    nfa.readCharacter('a');
    assertFalse(nfa.accepting());

    nfa.readCharacter('b');
    assertTrue(nfa.accepting());
  }

  @Test
  public void testReadString() {
    nfa = new NFA<>(buildSet(1), buildSet(4), rulebook);
    assertFalse(nfa.accepting());

    nfa.readString("bbbbb");
    assertTrue(nfa.accepting());
  }
}
