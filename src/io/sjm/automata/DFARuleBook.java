package io.sjm.automata;

import java.util.HashSet;
import java.util.Optional;

public class DFARuleBook<T> extends HashSet<FARule<T>> {
  private static final long serialVersionUID = 1906157322432380283L;

  public T nextState(T state, Character c) {
    return ruleFor(state, c).orElseThrow(() -> new RuntimeException("No such rule.")).follow();
  }

  public Optional<FARule<T>> ruleFor(T state, Character c) {
    return stream().filter(r -> r.appliesTo(state, c)).findFirst();
  }

  public void addRule(T state, Character c, T nextState) {
    add(new FARule<>(state, c, nextState));
  }
}