<?php

namespace Emilybache\StrangecharacterTestdesignKata;

use Emilybache\StrangecharacterTestdesignKata\Character;
use Emilybache\StrangecharacterTestdesignKata\CharacterFinder;
use PHPUnit\Framework\TestCase;

class CharactersTest extends TestCase
{
    /**
     * @return Character[]
     */
    public static function allTestData(): array
    {
        $joyce = new Character("Joyce", "Byers");
        $jim = new Character("Jim", "Hopper");
        $mike = new Character("Mike", "Wheeler");
        $eleven = new Character("Eleven");
        $dustin = new Character("Dustin", "Henderson");
        $lucas = new Character("Lucas", "Sinclair");
        $nancy = new Character("Nancy", "Wheeler");
        $jonathan = new Character("Jonathan", "Byers");
        $will = new Character("Will", "Byers");
        $karen = new Character("Karen", "Wheeler");
        $steve = new Character("Steve", "Harrington");
        $mindflayer = Character::newMonster("Mindflayer");
        $demagorgon = Character::newMonster("Demagorgon");
        $demadog = Character::newMonster("Demadog");

        $joyce->addChild($jonathan);
        $joyce->addChild($will);
        $jim->addChild($eleven);
        $karen->addChild($nancy);
        $karen->addChild($mike);

        $eleven->setNemesis($demagorgon);
        $will->setNemesis($mindflayer);
        $dustin->setNemesis($demadog);

        return [
            $joyce,
            $jim,
            $mike,
            $will,
            $eleven,
            $dustin,
            $lucas,
            $nancy,
            $jonathan,
            $mindflayer,
            $demagorgon,
            $demadog,
            $karen,
            $steve
        ];
    }

    public function testFindCharactersByFirstName(): void
    {
        $finder = new CharacterFinder(static::allTestData());
        $character = $finder->findByFirstName("Jim");
        $this->assertNotNull($character);
        $this->assertSame("Jim", $character->firstName);
    }
}
