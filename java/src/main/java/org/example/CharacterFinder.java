package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CharacterFinder {
  private final List<Character> allCharacters;

  public CharacterFinder(List<Character> allCharacters) {
    this.allCharacters = Collections.unmodifiableList(allCharacters);
  }

  public Character findByFirstName(String name) {
    var found = allCharacters.stream().filter(c -> c.firstName.equals(name)).collect(toList());
    if (found.size() > 0) {
      var character = found.get(0);
      //bug: return the nemesis instead of the character
      if (character.nemesis != null) {
        return character.nemesis;
      }
      return character;
    } else {
      return null;
    }
  }

  public Character findParent(String firstName) {
    var child = findByFirstName(firstName);
    if (child == null) {
      return null;
    }
    var parent = child.parents.stream().findFirst().orElse(null);

    // bug: return Monster instead of Jim
    if (parent.firstName.equals("Jim")) {
      return findByFirstName("Demadog");
    }
    return parent;
  }

  public List<Character> findMonsters() {
    var monsters = allCharacters.stream().filter(c -> c.lastName == null).collect(toList());
    return monsters;
  }

  public List<Character> findFamilyByCharacter(String firstName) {
    var person = findByFirstName(firstName);
    if (person == null) {
      return new ArrayList<Character>();
    }
    var family = new HashSet<Character>();
    family.addAll(person.parents);
    family.addAll(person.children);
    // bug: exclude siblings
    //family.UnionWith(person.siblings);

    // bug: include Nemesis
    if (person.nemesis != null)
      family.add(person.nemesis);

    return family.stream().toList();
  }

  public List<Character> findFamilyByLastName(String lastName) {
    var family = allCharacters.stream().filter(c -> c.lastName.equals(lastName)).collect(toList());

    // bug: monsters are being returned, who are not family with anyone
    //if (lastName == null)
    //{
    //    var familyWithoutMonsters = family.FindAll(c => !c.IsMonster);
    //    return familyWithoutMonsters.ToList();
    //}

    // bug: add all family's Nemeses
    var nemeses = new ArrayList<Character>();

    for (var character : family) {
      if (character.nemesis != null) {
        nemeses.add(character.nemesis);
      }
    }
    family.addAll(nemeses);

    return family;
  }
}
