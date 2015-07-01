package io.sjm.automata;

import java.util.Set;

import io.sjm.stdlib.datastructures.Sets;

public class NFA<T> {
  private Set<T> currentStates;
  private Set<T> acceptStates;
  private NFARuleBook<T> rulebook;

  public NFA(Set<T> cs, Set<T> as, NFARuleBook<T> rb) {
    currentStates = cs;
    acceptStates = as;
    rulebook = rb;
  }

  public boolean accepting() {
    return !Sets.intersection(currentStates, acceptStates).isEmpty();
  }

  public void readCharacter(Character c) {
    currentStates = rulebook.nextStates(currentStates, c);
  }

  public void readString(String s) {
    s.chars().forEach(c -> readCharacter((char) c));
  }
}
