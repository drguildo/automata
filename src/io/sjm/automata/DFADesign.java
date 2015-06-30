package io.sjm.automata;

import java.util.List;

public class DFADesign<T> {
  final private T currentState;
  final private List<T> acceptStates;
  final private DFARuleBook<T> rulebook;

  public DFADesign(T currentState, List<T> acceptStates, DFARuleBook<T> rulebook) {
    this.currentState = currentState;
    this.acceptStates = acceptStates;
    this.rulebook = rulebook;
  }

  public DFA<T> toDFA() {
    return new DFA<>(currentState, acceptStates, rulebook);
  }

  public boolean accepts(String s) {
    DFA<T> dfa = toDFA();
    dfa.readString(s);
    return dfa.accepting();
  }
}
