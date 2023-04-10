<?php

namespace Emilybache\StrangecharacterTestdesignKata\Test;

use Emilybache\StrangecharacterTestdesignKata\Character;
use PHPUnit\Framework\Assert;

/**
 * This trait contains helper assertion functions to get around the missing ArrayList class in PHP
 */
trait CompareCharacterList
{
    /**
     * @param Character[] $expectedCharacters
     * @param Character[] $charactersList
     */
    public static function assertCharacterListContains(array $expectedCharacters, array $charactersList): void
    {
        $intersection = array_uintersect($charactersList, $expectedCharacters, self::compareCharacters(...));
        Assert::assertCount(
            count($expectedCharacters),
            $intersection,
            sprintf(
                "Failed to find the following characters in list: [%s]",
                join(",", array_udiff($expectedCharacters, $intersection, self::compareCharacters(...)))
            )
        );
    }

    /**
     * @param Character[] $expectedCharacters
     * @param Character[] $charactersList
     */
    public static function assertCharacterListIsEqualTo(array $expectedCharacters, array $charactersList): void
    {
        self::assertCharacterListContains($expectedCharacters, $charactersList);
        Assert::assertCount(
            count($charactersList),
            $expectedCharacters,
            "Characters lists have a different length and are not equal",
        );
    }

    /**
     * Comparison function used by the array_udiff and array_uintersect.
     *
     * We can't use the `equals` method of characters here because the array functions sort the arrays internally,
     * which needs an int instead of boolean
     *
     * @return int<-1,1>
     */
    private static function compareCharacters(mixed $characterA, mixed $characterB): int
    {
        if ($characterA instanceof Character && $characterB instanceof Character) {
            return strcmp($characterA->firstName, $characterB->firstName);
        }
        throw new \LogicException("This comparison function should only be used with Characters");
    }
}
