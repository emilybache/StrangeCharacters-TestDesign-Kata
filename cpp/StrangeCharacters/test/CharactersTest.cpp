#include "Character.hpp"
#include "CharacterFinder.hpp"

#define CATCH_CONFIG_MAIN
#include <catch2/catch.hpp>

namespace Characters::Test {

std::vector<std::unique_ptr<Character>> allTestData()
{
    std::vector<std::unique_ptr<Character>> characters;
    characters.emplace_back(std::make_unique<Character>("Joyce", "Byers"));
    characters.emplace_back(std::make_unique<Character>("Jim", "Hopper"));
    characters.emplace_back(std::make_unique<Character>("Mike", "Wheeler"));
    characters.emplace_back(std::make_unique<Character>("Eleven"));
    characters.emplace_back(std::make_unique<Character>("Dustin", "Henderson"));
    characters.emplace_back(std::make_unique<Character>("Lucas", "Sinclair"));
    characters.emplace_back(std::make_unique<Character>("Nancy", "Wheeler"));
    characters.emplace_back(std::make_unique<Character>("Jonathan", "Byers"));
    characters.emplace_back(std::make_unique<Character>("Will", "Byers"));
    characters.emplace_back(std::make_unique<Character>("Karen", "Wheeler"));
    characters.emplace_back(std::make_unique<Character>("Steve", "Harrington"));
    characters.emplace_back(std::make_unique<Character>("Mindflayer", std::nullopt, true));
    characters.emplace_back(std::make_unique<Character>("Demagorgon", std::nullopt, true));
    characters.emplace_back(std::make_unique<Character>("Demadog", std::nullopt, true));
    auto& joyce = characters[0];
    auto& jim = characters[1];
    auto& mike = characters[2];
    auto& eleven = characters[3];
    auto& dustin = characters[4];
    // Lucas and Steve are commented out, since no code below refers to them and we have compiler warnings
    // on unused variables.
//    auto& lucas = characters[5];
    auto& nancy = characters[6];
    auto& jonathan = characters[7];
    auto& will = characters[8];
    auto& karen = characters[9];
//    auto& steve = characters[10];
    auto& mindflayer = characters[11];
    auto& demagorgon = characters[12];
    auto& demadog = characters[13];
    
    joyce->AddChild(jonathan.get());
    joyce->AddChild(will.get());
    jim->AddChild(eleven.get());
    karen->AddChild(nancy.get());
    karen->AddChild(mike.get());
    
    eleven->Nemesis = demagorgon.get();
    will->Nemesis = mindflayer.get();
    dustin->Nemesis = demadog.get();

    return characters;
}

TEST_CASE("Find Character By First Name")
{
    CharacterFinder finder(allTestData());
    auto character = finder.FindByFirstName("Jim");
    REQUIRE("Jim" == (*character)->FirstName);
}
    
}
