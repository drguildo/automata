package io.sjm.automata;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class DFARulebook<T> extends HashSet<FARule<T>> {
  private static final long serialVersionUID = 1906157322432380283L;

  public DFARulebook() {
  }

  public DFARulebook(Collection<FARule<T>> collection) {
    addAll(collection);
  }

  public T nextState(T state, Character c) {
    System.out.println("State: " + state + " Character: " + c);
    return ruleFor(state, c).orElseThrow(() -> new RuntimeException("No such rule.")).follow();
  }

  public Optional<FARule<T>> ruleFor(T state, Character c) {
    return stream().filter(r -> r.appliesTo(state, c)).findFirst();
  }

  public void addRule(T state, Character c, T nextState) {
    add(new FARule<>(state, c, nextState));
  }
}
