package io.sjm.automata;

import java.util.Set;

public class NFADesign<T> {
  final private Set<T> startStates;
  final private Set<T> acceptStates;
  final private NFARuleBook<T> rulebook;

  public NFADesign(Set<T> cs, Set<T> as, NFARuleBook<T> rb) {
    startStates = cs;
    acceptStates = as;
    rulebook = rb;
  }

  public NFA<T> toNFA() {
    return new NFA<>(startStates, acceptStates, rulebook);
  }

  public boolean accepts(String s) {
    NFA<T> nfa = toNFA();
    nfa.readString(s);
    return nfa.accepting();
  }
}
