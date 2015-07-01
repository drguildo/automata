package io.sjm.automata;

import java.util.List;

public class DFADesign<T> {
  final private T startState;
  final private List<T> acceptStates;
  final private DFARulebook<T> rulebook;

  public DFADesign(T cs, List<T> as, DFARulebook<T> rb) {
    startState = cs;
    acceptStates = as;
    rulebook = rb;
  }

  public DFA<T> toDFA() {
    return new DFA<>(startState, acceptStates, rulebook);
  }

  public boolean accepts(String s) {
    DFA<T> dfa = toDFA();
    dfa.readString(s);
    return dfa.accepting();
  }
}
