package io.sjm.automata;

public class FARule<T> {
  private T state;
  private Character character;
  private T nextState;

  public FARule(T state, Character character, T nextState) {
    this.state = state;
    this.character = character;
    this.nextState = nextState;
  }

  public boolean appliesTo(T s, Character c) {
    return state == s && character == c;
  }

  public T follow() {
    return nextState;
  }

  @Override
  public String toString() {
    return String.format("<FiniteRule %s --%s--> %s>", state, character, nextState);
  }
}
