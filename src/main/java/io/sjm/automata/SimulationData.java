package io.sjm.automata;

import java.util.Set;

public class SimulationData<T> {
  private Set<Set<T>> states;
  private Set<FARule<Set<T>>> rules;

  public SimulationData(Set<Set<T>> states, Set<FARule<Set<T>>> rules) {
    this.states = states;
    this.rules = rules;
  }

  @Override
  public String toString() {
    return "[" + states.toString() + ", \n" + rules.toString() + "]";
  }

  public Set<Set<T>> getStates() {
    return states;
  }

  public Set<FARule<Set<T>>> getRules() {
    return rules;
  }
}
