<?php

namespace Emilybache\StrangecharacterTestdesignKata;

use Emilybache\StrangecharacterTestdesignKata\Character;
use Emilybache\StrangecharacterTestdesignKata\CharacterFinder;
use PHPUnit\Framework\TestCase;

class ExampleCharacterTest extends TestCase
{
    public function testFindCharacterByLastName(): void
    {
        // This test constructs all its own test data to make it clearer what it does
        $karen = new Character("Karen", "Wheeler");
        $mike = new Character("Mike", "Wheeler");

        // this is example code showing kinds of assertion you could do on a Character
        $nancy = new Character("Nancy", "Wheeler");
        $this->assertSame("Nancy", $nancy->firstName);

        $karen->addChild($nancy);
        $karen->addChild($mike);
        $finder = new CharacterFinder([$karen, $mike, $nancy]);

        // This is the 'act' step
        $charactersList = $finder->findFamilyByLastName("Wheeler");

        // this is example code showing kinds of assertion you could do on a List of Characters
        $this->assertNotEmpty($charactersList);
        $this->assertCount(3, $charactersList);
        $this->assertCharacterListContains(
            [
                new Character("Nancy", "Wheeler"),
                new Character("Mike", "Wheeler"),
                new Character("Karen", "Wheeler"),
            ],
            $charactersList
        );
        $this->assertCharacterListIsEqualTo(
            [
                new Character("Nancy", "Wheeler"),
                new Character("Mike", "Wheeler"),
                new Character("Karen", "Wheeler"),
            ],
            $charactersList
        );
    }

    // Helper assertion functions to get around the missing Set class in PHP that compares characters by first name

    /**
     * @param Character[] $expectedCharacters
     * @param Character[] $charactersList
     */
    public static function assertCharacterListContains(array $expectedCharacters, array $charactersList): void
    {
        $charactersListIndex = [];
        foreach ($charactersList as $char) {
            $charactersListIndex[$char->firstName] = $char;
        }
        $missingCharacters = [];
        foreach ($expectedCharacters as $char) {
            if (!isset($charactersListIndex[$char->firstName])) {
                $missingCharacters[] = $char;
            }
        }
        self::assertEmpty(
            $missingCharacters,
            sprintf(
                "Failed to find the following characters in list: [%s]",
                join(",", $missingCharacters)
            )
        );
    }

    /**
     * @param Character[] $expectedCharacters
     * @param Character[] $charactersList
     */
    public static function assertCharacterListIsEqualTo(array $expectedCharacters, array $charactersList): void
    {
        $charactersListIndex = [];
        foreach ($charactersList as $char) {
            $charactersListIndex[$char->firstName] = $char;
        }
        $expectedCharactersIndex = [];
        foreach ($expectedCharacters as $char) {
            $expectedCharactersIndex[$char->firstName] = $char;
        }
        self::assertEqualsCanonicalizing(
            array_keys($expectedCharactersIndex),
            array_keys($charactersListIndex),
            "Character lists are different"
        );
    }
}
