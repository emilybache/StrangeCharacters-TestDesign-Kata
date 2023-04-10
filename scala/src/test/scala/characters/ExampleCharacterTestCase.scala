package characters

import munit.FunSuite

/** This test case is not part of the exercise - it is example code showing what's possible
  */
class ExampleCharacterTestCase extends FunSuite:

  test("find character by last name") {
    // This test constructs all its own test data to make it clearer what it does
    val karen = new Character("Karen", Some("Wheeler"))
    val mike = new Character("Mike", Some("Wheeler"))

    // this example code showing kinds of assertion you could do on a Character
    val nancy = new Character("Nancy", Some("Wheeler"))
    assertEquals(nancy.firstName, "Nancy")
    assertEquals(nancy, nancy)
    // This assertion works because Character is a ValueObject that implements "equals"
    assertEquals(nancy, new Character("Nancy", Some("Wheeler")))

    karen.addChild(nancy)
    karen.addChild(mike)
    val finder = new CharacterFinder(List(karen, mike, nancy))

    // This is the 'act' step
    val charactersList = finder.findFamilyByLastName(Some("Wheeler"))

    // this is example code showing kinds of assertion you could do on a List of Characters
    assert(charactersList != null)
    assertEquals(charactersList.length, 3)
    assert(charactersList.contains(new Character("Nancy", Some("Wheeler"))))
    assertEquals(
      charactersList.sortBy(_.firstName),
      List(
        new Character("Nancy", Some("Weeler")),
        new Character("Mike", Some("Weeler")),
        new Character("Karen", Some("Weeler"))
      ).sortBy(_.firstName)
    )
    assertEquals(
      charactersList,
      List(
        new Character("Karen", Some("Weeler")),
        new Character("Mike", Some("Weeler")),
        new Character("Nancy", Some("Weeler"))
      )
    )
  }
