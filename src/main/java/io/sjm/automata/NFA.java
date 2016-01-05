package io.sjm.automata;

import io.sjm.sjmlib.datastructures.Sets;

import java.util.Set;

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
    return !Sets.intersection(currentStates(), acceptStates).isEmpty();
  }

  public void readCharacter(Character c) {
    currentStates = rulebook.nextStates(currentStates(), c);
  }

  public void readString(String s) {
    s.chars().forEach(c -> readCharacter((char) c));
  }

  public Set<T> currentStates() {
    return rulebook.followFreeMoves(currentStates);
  }

  @Override
  public String toString() {
    return currentStates().toString();
  }
}
