package io.sjm.automata;

import java.util.Set;

import static io.sjm.sjmlib.datastructures.Sets.buildSet;

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

  public Set<T> getAcceptStates() {
    return acceptStates;
  }

  public NFARuleBook<T> getRulebook() {
    return rulebook;
  }

  public T getStartState() {
    return startState;
  }

  public NFA<T> toNFA() {
    return new NFA<>(buildSet(startState), acceptStates, rulebook);
  }

  public NFA<T> toNFA(Set<T> currentStates) {
    return new NFA<>(currentStates, acceptStates, rulebook);
  }
}
