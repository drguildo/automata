package io.sjm.automata.tests;

import static org.junit.Assert.*;
import io.sjm.automata.DFA;
import io.sjm.automata.DFARulebook;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class DFATest {
  DFARulebook<Integer> rulebook;
  DFA<Integer> dfa;

  @Before
  public void setUp() throws Exception {
    rulebook = new DFARulebook<>();
    rulebook.addRule(1, 'a', 2);
    rulebook.addRule(1, 'b', 1);
    rulebook.addRule(2, 'a', 2);
    rulebook.addRule(2, 'b', 3);
    rulebook.addRule(3, 'a', 3);
    rulebook.addRule(3, 'b', 3);

    dfa = new DFA<>(1, Arrays.asList(3), rulebook);
  }

  @Test
  public void testAccepting() {
    assertFalse(dfa.accepting()); // Current state: 1

    dfa.readCharacter('b'); // 1
    assertFalse(dfa.accepting());

    dfa.readCharacter('a'); // 2
    dfa.readCharacter('a');
    dfa.readCharacter('a');
    assertFalse(dfa.accepting());

    dfa.readCharacter('b'); // 3
    assertTrue(dfa.accepting());
  }

  @Test
  public void testReadString() {
    assertFalse(dfa.accepting()); // Current state: 1 etc.

    dfa.readString("");
    assertFalse(dfa.accepting());

    dfa.readString("baaab");
    assertTrue(dfa.accepting());
  }
}
