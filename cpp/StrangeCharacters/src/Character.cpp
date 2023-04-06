#include "Character.hpp"

namespace Characters {

Character::Character(std::string firstName, std::optional<std::string> lastName, bool isMonster)
: FirstName(firstName)
, LastName(lastName)
, IsMonster(isMonster)
{
}

void Character::AddChild(Character* child)
{
    children.insert(child);
    child->parents.insert(this);
    for (auto character : children)
    {
        if (child->FirstName != character->FirstName)
        {
            child->siblings.insert(character);
            character->siblings.insert(child);
        }
    }
}

std::string to_string(const Character& character)
{
    return "Character(" + character.FirstName + ", " + (character.LastName ? *character.LastName : "null") + ", isMonster:" + std::to_string(character.IsMonster) + ")";
}

bool Character::operator==(const Character& other) const
{
    return FirstName == other.FirstName;
}

}

