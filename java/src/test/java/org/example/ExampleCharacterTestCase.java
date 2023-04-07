package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleCharacterTestCase {
  @Test
  public void findCharacterByLastName() {
    // This test constructs all its own test data to make it clearer what it does
    var karen = new Character("Karen", "Wheeler");
    var mike = new Character("Mike", "Wheeler");

    // this is example code showing kinds of assertion you could do on a Character
    var nancy = new Character("Nancy", "Wheeler");
    assertEquals("Nancy", nancy.firstName);
    assertEquals(nancy, nancy);
    // This assertion works because Character is a ValueObject that implements "Equals"
    assertEquals(new Character("Nancy", "Wheeler"), nancy);

    karen.addChild(nancy);
    karen.addChild(mike);
    var finder = new CharacterFinder(Arrays.asList(
        karen,
        mike,
        nancy));

    // This is the 'act' step
    var charactersList = finder.findFamilyByLastName("Wheeler");

    // this is example code showing kinds of assertion you could do on a List of Characters
    assertNotNull(charactersList);
    assertEquals(3, charactersList.size());
    assertTrue(charactersList.contains(new Character("Nancy", "Wheeler")));
    assertTrue(charactersList.containsAll(Arrays.asList(
        new Character("Nancy", "Wheeler"),
        new Character("Mike", "Wheeler"),
        new Character("Karen", "Wheeler"))));
    assertEquals(Arrays.asList(
        new Character("Karen", "Wheeler"),
        new Character("Mike", "Wheeler"),
        new Character("Nancy", "Wheeler")), charactersList);
  }
}
