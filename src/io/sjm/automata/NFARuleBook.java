package io.sjm.automata;

import io.sjm.stdlib.datastructures.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NFARuleBook<T> extends ArrayList<FARule<T>> {
  private static final long serialVersionUID = 6869260412600499158L;

  public Set<T> nextStates(Set<T> states, Character c) {
    return states.stream().map(s -> followRulesFor(s, c)).flatMap(l -> l.stream())
            .collect(Collectors.toSet());
  }

  public List<T> followRulesFor(T state, Character c) {
    return rulesFor(state, c).stream().map(rule -> rule.follow()).collect(Collectors.toList());
  }

  public Set<FARule<T>> rulesFor(T state, Character c) {
    return stream().filter(rule -> rule.appliesTo(state, c)).collect(Collectors.toSet());
  }

  public void addRule(T state, Character c, T nextState) {
    add(new FARule<>(state, c, nextState));
  }

  public Set<T> followFreeMoves(Set<T> states) {
    Set<T> moreStates = nextStates(states, null);

    if (states.containsAll(moreStates))
      return states;

    return followFreeMoves(Sets.union(states, moreStates));
  }

  public Set<Character> alphabet() {
    return this.stream().map(FARule::getCharacter).filter(character -> character != null).collect(Collectors.toSet());
  }
}
