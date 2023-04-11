package characters

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

/** This test case is not part of the exercise - it is example code showing what's possible
  */
class ExampleCharacterTestCase extends AnyFunSuite:

  test("find character by last name") {
    // This test constructs all its own test data to make it clearer what it does
    val karen = new Character("Karen", Some("Wheeler"))
    val mike = new Character("Mike", Some("Wheeler"))

    // this example code showing kinds of assertion you could do on a Character
    val nancy = new Character("Nancy", Some("Wheeler"))
    nancy.firstName should be("Nancy")
    nancy should be(nancy)
    // This assertion works because Character is a ValueObject that implements "equals"
    nancy should be(new Character("Nancy", Some("Wheeler")))

    karen.addChild(nancy)
    karen.addChild(mike)
    val finder = new CharacterFinder(List(karen, mike, nancy))

    // This is the 'act' step
    val charactersList = finder.findFamilyByLastName(Some("Wheeler"))

    // this is example code showing kinds of assertion you could do on a List of Characters
    charactersList should not be null
    charactersList should have size 3
    charactersList should contain(new Character("Nancy", Some("Wheeler")))
    charactersList should contain only (
      new Character("Nancy", Some("Weeler")),
      new Character("Mike", Some("Weeler")),
      new Character("Karen", Some("Weeler"))
    )
    charactersList should contain inOrderOnly (
      new Character("Karen", Some("Weeler")),
      new Character("Mike", Some("Weeler")),
      new Character("Nancy", Some("Weeler"))
    )
  }
