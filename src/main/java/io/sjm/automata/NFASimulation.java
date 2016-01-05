package io.sjm.automata;

import io.sjm.sjmlib.datastructures.Sets;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static io.sjm.sjmlib.datastructures.Sets.buildSet;

public class NFASimulation<T> {
  private NFADesign<T> design;

  public NFASimulation(NFADesign<T> design) {
    this.design = design;
  }

  public Set<T> nextState(Set<T> state, Character c) {
    NFA<T> nfa = design.toNFA(state);
    nfa.readCharacter(c);
    return nfa.currentStates();
  }

  public Set<FARule<Set<T>>> rulesFor(Set<T> state) {
    return design.getRulebook().alphabet().stream().map(character -> new FARule<>(state, character, nextState(state, character))).collect(Collectors.toSet());
  }

  public SimulationData<T> discoverStatesAndRules(Set<Set<T>> states) {
    Set<FARule<Set<T>>> rules = new HashSet<>();
    for (Set<T> state : states) {
      rulesFor(state).forEach(rules::add);
    }
    Set<Set<T>> moreStates = rules.stream().map(FARule::follow).collect(Collectors.toSet());

    if (states.containsAll(moreStates))
      return new SimulationData<>(states, rules);
    else {
      return discoverStatesAndRules(Sets.union(states, moreStates));
    }
  }

  public DFADesign<Set<T>> toDFADesign() {
    Set<T> startState = design.toNFA().currentStates();
    SimulationData<T> data = discoverStatesAndRules(buildSet(startState));
    Set<Set<T>> states = data.getStates();
    Set<FARule<Set<T>>> rules = data.getRules();
    Set<Set<T>> acceptStates = states.stream().filter(s -> design.toNFA(s).accepting()).collect(Collectors.toSet());

    return new DFADesign<>(startState, acceptStates, new DFARulebook<>(rules));
  }
}
