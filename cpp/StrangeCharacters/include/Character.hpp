#pragma once

#include <optional>
#include <string>
#include <set>

namespace Characters {

class Character
{
public:
    const std::string FirstName;
    const std::optional<std::string> LastName;
    const bool IsMonster;

    Character* Nemesis = nullptr;

    std::set<Character*> children;
    std::set<Character*> siblings;
    std::set<Character*> parents;

    Character(std::string firstName, std::optional<std::string> lastName = std::nullopt, bool isMonster = false);
    void AddChild(Character* child);
    friend std::string to_string(const Character& character);
    bool operator==(const Character& other) const;
};

}
