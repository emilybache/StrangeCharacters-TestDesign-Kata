<?php

namespace Emilybache\StrangecharacterTestdesignKata\Test;

use Emilybache\StrangecharacterTestdesignKata\Character;
use Emilybache\StrangecharacterTestdesignKata\CharacterFinder;
use PHPUnit\Framework\TestCase;

class ExampleCharacterTest extends TestCase
{
    use CompareCharacterList;

    public function testFindCharacterByLastName(): void
    {
        // This test constructs all its own test data to make it clearer what it does
        $karen = new Character("Karen", "Wheeler");
        $mike = new Character("Mike", "Wheeler");

        // this is example code showing kinds of assertion you could do on a Character
        $nancy = new Character("Nancy", "Wheeler");
        $this->assertSame("Nancy", $nancy->firstName);
        $this->assertTrue($nancy->equals($nancy));
        $this->assertTrue($nancy->equals(new Character("Nancy", "Wheeler")));


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
}
