#include "Character.hpp"
#include "CharacterFinder.hpp"
#include <catch2/catch.hpp>

namespace Characters::Test {

/*
 * This test case is not part of the exercise - it is example code showing what's possible
 */
TEST_CASE("Find Character By Last Name") {
    // This test constructs all its own test data to make it clearer what it does
    auto karen = std::make_unique<Character>("Karen", "Wheeler");
    auto mike = std::make_unique<Character>("Mike", "Wheeler");

    // this is example code showing kinds of assertion you could do on a Character
    auto nancy = std::make_unique<Character>("Nancy", "Wheeler");
    REQUIRE("Nancy" == nancy->FirstName);
    REQUIRE(nancy == nancy);

    // This assertion works because Character has a custom operator==
    REQUIRE(*std::make_unique<Character>("Nancy", "Wheeler") == *nancy);

    karen->AddChild(nancy.get());
    karen->AddChild(mike.get());

    std::vector<std::unique_ptr<Character>> characters;
    characters.push_back(std::move(nancy));
    characters.push_back(std::move(mike));
    characters.push_back(std::move(karen));

    CharacterFinder finder(std::move(characters));

    // This is the 'act' step
    auto charactersList = finder.FindFamilyByLastName("Wheeler");
    
    // this is example code showing kinds of assertion you could do on a List of Characters
    REQUIRE(3 == charactersList.size());
}

}
