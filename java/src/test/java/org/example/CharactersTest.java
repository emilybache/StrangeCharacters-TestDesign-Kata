package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CharactersTest {
  public List<Character> allTestData() {
    var joyce = new Character("Joyce", "Byers");
    var jim = new Character("Jim", "Hopper");
    var mike = new Character("Mike", "Wheeler");
    var eleven = new Character("Eleven");
    var dustin = new Character("Dustin", "Henderson");
    var lucas = new Character("Lucas", "Sinclair");
    var nancy = new Character("Nancy", "Wheeler");
    var jonathan = new Character("Jonathan", "Byers");
    var will = new Character("Will", "Byers");
    var karen = new Character("Karen", "Wheeler");
    var steve = new Character("Steve", "Harrington");
    var mindflayer = new Character("Mindflayer", true);
    var demagorgon = new Character("Demagorgon", true);
    var demadog = new Character("Demadog", true);

    joyce.AddChild(jonathan);
    joyce.AddChild(will);
    jim.AddChild(eleven);
    karen.AddChild(nancy);
    karen.AddChild(mike);

    eleven.setNemesis(demagorgon);
    will.setNemesis(mindflayer);
    dustin.setNemesis(demadog);

    return Arrays.asList(
      joyce,
      jim,
      mike,
      will,
      eleven,
      dustin,
      lucas,
      nancy,
      jonathan,
      mindflayer,
      demagorgon,
      demadog,
      karen,
      steve);
  }

  @Test
  public void findCharacterByFirstName() {
    var finder = new CharacterFinder(allTestData());
    var character = finder.findByFirstName("Jim");
    assertEquals("Jim", character.firstName);
  }
}