package io.sjm.automata;

import java.util.Collection;

public class DFA<T> {
  private T currentState;
  private Collection<T> acceptStates;
  private DFARulebook<T> rulebook;

  public DFA(T currentState, Collection<T> acceptStates, DFARulebook<T> rulebook) {
    this.currentState = currentState;
    this.acceptStates = acceptStates;
    this.rulebook = rulebook;
  }

  public boolean accepting() {
    return acceptStates.contains(currentState);
  }

  public void readCharacter(Character c) {
    currentState = rulebook.nextState(currentState, c);
  }

  public void readString(String s) {
    s.chars().forEach(c -> readCharacter((char) c));
  }
}
