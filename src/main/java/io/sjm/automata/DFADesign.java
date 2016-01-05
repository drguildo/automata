package io.sjm.automata;

import java.util.Collection;

public class DFADesign<T> {
  final private T startState;
  final private Collection<T> acceptStates;
  final private DFARulebook<T> rulebook;

  public DFADesign(T cs, Collection<T> as, DFARulebook<T> rb) {
    startState = cs;
    acceptStates = as;
    rulebook = rb;
  }

  public T getStartState() {
    return startState;
  }

  public Collection<T> getAcceptStates() {
    return acceptStates;
  }

  public DFARulebook<T> getRulebook() {
    return rulebook;
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
