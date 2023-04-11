package characters

import org.scalatest.OptionValues.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class CharactersTest extends AnyFunSuite:

  private def allTestData: List[Character] =
    val joyce = new Character("Joyce", Some("Byers"))
    val jim = new Character("Jim", Some("Hopper"))
    val mike = new Character("Mike", Some("Wheeler"))
    val eleven = new Character("Eleven")
    val dustin = new Character("Dustin", Some("Henderson"))
    val lucas = new Character("Lucas", Some("Sinclair"))
    val nancy = new Character("Nancy", Some("Wheeler"))
    val jonathan = new Character("Jonathan", Some("Byers"))
    val will = new Character("Will", Some("Byers"))
    val karen = new Character("Karen", Some("Wheeler"))
    val steve = new Character("Steve", Some("Harrington"))
    val mindflayer = new Character("Mindflayer", isMonster = true)
    val demagorgon = new Character("Demagorgon", isMonster = true)
    val demadog = new Character("Demadog", isMonster = true)

    joyce.addChild(jonathan)
    joyce.addChild(will)
    jim.addChild(eleven)
    karen.addChild(nancy)
    karen.addChild(mike)

    eleven.nemesis = demagorgon
    will.nemesis = mindflayer
    dustin.nemesis = demadog

    List[Character](
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
      steve
    )

  test("findCharactersByFirstName") {
    val finder = new CharacterFinder(allTestData)

    val character = finder.findByFirstName("Jim")

    character.value.firstName should be("Jim")
  }
