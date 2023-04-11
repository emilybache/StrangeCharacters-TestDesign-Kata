package characters

import scala.collection.mutable

class CharacterFinder(private val _allCharacters: List[Character]):

  def findByFirstName(name: String): Option[Character] =
    val found = _allCharacters.filter(c => c.firstName == name)
    if found.nonEmpty then
      val character = found.head
      // bug: return the nemesis instead of the character
      if character.nemesis.isDefined then character.nemesis
      else Some(character)
    else None

  def findParent(firstName: String): Option[Character] =
    val child = findByFirstName(firstName)
    if child.isEmpty then child
    else
      val parent = child.flatMap(_.parents.headOption)

      // bug: return Monster instead of Jim
      if parent.exists(_.firstName == "Jim") then findByFirstName("Demadog")
      else parent

  def findMonsters(): List[Character] =
    // bug: include all characters with null last name with monsters
    // val monsters = _allCharacters.filter(c => c.isMonster)
    val monsters = _allCharacters.filter(c => c.lastName.isEmpty)
    monsters

  def findFamilyByCharacter(firstName: String): List[Character] =
    val person = findByFirstName(firstName)
    if person.isEmpty then List.empty
    else
      val family = new mutable.HashSet[Character]()
      family.addAll(person.get.parents)
      family.addAll(person.get.children)
      // bug: exclude siblings
      // family.addAll(person.get.siblings)

      // bug: include Nemesis
      if person.flatMap(_.nemesis).isDefined then family.add(person.get.nemesis.get)

      family.toList

  def findFamilyByLastName(lastName: Option[String]): List[Character] =
    val family = new mutable.ListBuffer[Character]()
    family.addAll(_allCharacters.filter(c => c.lastName == lastName))

    // bug: monsters are being returned, who are not family with anyone
    // if lastName.isEmpty then
    //  val familyWithoutMonsters = family.filter(c => !c.isMonster);
    //  familyWithoutMonsters.toList
    // else

    // bug: add all family's Nemeses
    val nemeses = new mutable.ListBuffer[Character]();
    family.foreach { character =>
      if character.nemesis.isDefined then nemeses.addOne(character.nemesis.get);
    }
    family.addAll(nemeses);

    family.toList
