package io.sjm.automata;

import java.util.List;

public class DFA<T> {
  private T currentState;
  private List<T> acceptStates;
  private DFARulebook<T> rulebook;

  public DFA(T currentState, List<T> acceptStates, DFARulebook<T> rulebook) {
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
