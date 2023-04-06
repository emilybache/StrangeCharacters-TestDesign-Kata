#include "CharacterFinder.hpp"
#include "Character.hpp"

namespace Characters {

CharacterFinder::CharacterFinder(std::vector<std::unique_ptr<Character>> allCharacters)
: _allCharacters(std::move(allCharacters))
{
}

std::optional<Character*> CharacterFinder::FindByFirstName(std::string_view name)
{
    auto iterator = std::find_if(_allCharacters.begin(), _allCharacters.end(), [&](const auto& character) {
      return character->FirstName == name; 
    });
    if (iterator != _allCharacters.end())
    {
        Character* character = iterator->get();
        // bug: return the nemesis instead of the character
        if (character->Nemesis)
        {
            return character->Nemesis;
        }
        return character;
    }
    else
    {
        return std::nullopt;
    }
}

std::optional<Character*> CharacterFinder::FindParent(std::string_view firstName)
{
    auto child = FindByFirstName(firstName);
    if (not child)
    {
        return std::nullopt;
    }
    auto parent = *((*child)->parents.begin());
    
    // bug: return Monster instead of Jim
    if (parent->FirstName == "Jim")
    {
        return FindByFirstName("Demadog");
    }
    return parent;
}

std::vector<Character*> CharacterFinder::FindMonsters()
{
    std::vector<Character*> monsters;
    for (auto& character : _allCharacters) {
        // bug: include all characters with no last name with monsters
        //if (character->IsMonster)
        if (not character->LastName)
            monsters.push_back(character.get());
    }
    return monsters;
}

std::vector<Character*> CharacterFinder::FindFamilyByCharacter(std::string_view firstName)
{
    auto person = FindByFirstName(firstName);
    if (not person)
    {
        return {};
    }
    std::set<Character*> family;
    family.insert((*person)->parents.begin(), (*person)->parents.end());
    family.insert((*person)->children.begin(), (*person)->children.end());
    // bug: exclude siblings
    //family.insert((*person)->siblings.begin(), (*person)->siblings.end());

    // bug: include Nemesis
    if ((*person)->Nemesis)
        family.insert((*person)->Nemesis);
    
    return std::vector<Character*>(family.begin(), family.end());
}

std::vector<Character*> CharacterFinder::FindFamilyByLastName(std::optional<std::string> lastName)
{
    std::vector<Character*> family;
    for (auto& character : _allCharacters) {
        if (character->LastName == lastName
    // bug: monsters are being returned, who are not family with anyone
//                and not character->IsMonster
            )
            family.push_back(character.get());
    }

    // bug: add all family's Nemeses
    std::vector<Character*> nemeses;
    for (auto character : family)
    {
        if (character->Nemesis)
        {
            nemeses.push_back(character->Nemesis);
        }
    }
    family.insert(family.end(), nemeses.begin(), nemeses.end());
    
    return family;
}

}
