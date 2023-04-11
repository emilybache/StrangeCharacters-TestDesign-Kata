package characters

import scala.collection.mutable

class Character(val firstName: String, val lastName: Option[String] = None, val isMonster: Boolean = false):

  private var _nemesis: Option[Character] = None

  val children: mutable.Set[Character] = new mutable.HashSet[Character]()
  val siblings: mutable.Set[Character] = new mutable.HashSet[Character]()
  val parents: mutable.Set[Character] = new mutable.HashSet[Character]()

  def addChild(child: Character): Unit =
    children.add(child)
    child.parents.add(this)
    children.foreach { character =>
      if child.firstName != character.firstName then
        child.siblings.add(character)
        character.siblings.add(child)
    }

  def nemesis: Option[Character] = _nemesis
  def nemesis_=(newNemesis: Character): Unit = _nemesis = Some(newNemesis)

  override def toString: String = s"Character($firstName, $lastName, isMonster:$isMonster)"

  protected def equals(other: Character): Boolean = firstName == other.firstName

  override def equals(obj: Any): Boolean =
    if null == obj then false
    else if obj.getClass != this.getClass then false
    else equals(obj.asInstanceOf[Character])

  override def hashCode(): Int = firstName.hashCode
