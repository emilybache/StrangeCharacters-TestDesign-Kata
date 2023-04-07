#include "Character.hpp"
#include "CharacterFinder.hpp"

#include <catch2/catch.hpp>

namespace Characters::Test {

std::vector<std::unique_ptr<Character>> allTestData()
{
    auto joyce = std::make_unique<Character>("Joyce", "Byers");
    auto jim = std::make_unique<Character>("Jim", "Hopper");
    auto mike = std::make_unique<Character>("Mike", "Wheeler");
    auto eleven = std::make_unique<Character>("Eleven");
    auto dustin = std::make_unique<Character>("Dustin", "Henderson");
    auto lucas = std::make_unique<Character>("Lucas", "Sinclair");
    auto nancy = std::make_unique<Character>("Nancy", "Wheeler");
    auto jonathan = std::make_unique<Character>("Jonathan", "Byers");
    auto will = std::make_unique<Character>("Will", "Byers");
    auto karen = std::make_unique<Character>("Karen", "Wheeler");
    auto steve = std::make_unique<Character>("Steve", "Harrington");
    auto mindflayer = std::make_unique<Character>("Mindflayer", std::nullopt, true);
    auto demagorgon = std::make_unique<Character>("Demagorgon", std::nullopt, true);
    auto demadog = std::make_unique<Character>("Demadog", std::nullopt, true);
    
    joyce->AddChild(jonathan.get());
    joyce->AddChild(will.get());
    jim->AddChild(eleven.get());
    karen->AddChild(nancy.get());
    karen->AddChild(mike.get());
    
    eleven->Nemesis = demagorgon.get();
    will->Nemesis = mindflayer.get();
    dustin->Nemesis = demadog.get();

    std::vector<std::unique_ptr<Character>> characters;
    for (auto& i : { &joyce, &jim, &mike, &eleven, &dustin, &lucas, &nancy, &jonathan, &will, &karen, &steve, &mindflayer, &demagorgon, &demadog }) {
      characters.push_back(std::move(*i));
    }

    return characters;
}

TEST_CASE("Find Character By First Name")
{
    CharacterFinder finder(allTestData());
    auto character = finder.FindByFirstName("Jim");
    REQUIRE("Jim" == (*character)->FirstName);
}
    
}
