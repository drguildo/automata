package io.sjm.automata;

import java.util.Set;

import static io.sjm.stdlib.datastructures.Sets.buildSet;

public class NFADesign<T> {
  final private T startState;
  final private Set<T> acceptStates;
  final private NFARuleBook<T> rulebook;

  public NFADesign(T ss, Set<T> as, NFARuleBook<T> rb) {
    startState = ss;
    acceptStates = as;
    rulebook = rb;
  }

  public boolean accepts(String s) {
    NFA<T> nfa = toNFA();
    nfa.readString(s);
    return nfa.accepting();
  }

  public NFA<T> toNFA() {
    return new NFA<>(buildSet(startState), acceptStates, rulebook);
  }
}
