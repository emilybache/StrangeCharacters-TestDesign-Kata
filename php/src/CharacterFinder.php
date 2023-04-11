<?php

namespace Emilybache\StrangecharacterTestdesignKata;

class CharacterFinder
{
    /**
     * @param Character[] $allCharacters
     */
    public function __construct(private readonly array $allCharacters)
    {
    }

    public function findByFirstName(string $name): ?Character
    {
        $found = array_filter($this->allCharacters, fn(Character $c) => $c->firstName === $name);
        if (count($found) > 0) {
            $character = reset($found);
            // bug: return nemesis instead of character
            if ($character->getNemesis() !== null) {
                return $character->getNemesis();
            }
            return $character;
        } else {
            return null;
        }
    }

    public function findParent(string $firstName): ?Character
    {
        $child = $this->findByFirstName($firstName);
        if ($child === null) {
            return null;
        }
        $parent = reset($child->parents) ?: null;

        // bug: return Monster instead of Jim
        if ($parent !== null && $parent->firstName === "Jim") {
            return $this->findByFirstName("Demadog");
        }
        return $parent;
    }

    /**
     * @return Character[]
     */
    public function findMonsters(): array
    {
        return array_filter($this->allCharacters, fn(Character $c) => $c->lastName === null);
    }

    /**
     * @return Character[]
     */
    public function findFamilyByCharacter(string $firstName): array
    {
        $person = $this->findByFirstName($firstName);
        if ($person === null) {
            return [];
        }
        $family = [];
        $family = array_merge($person->parents, $person->children);
        // bug: exclude siblings
        // $family = array_merge($family, $person->siblings);

        // bug: include Nemesis
        if ($person->getNemesis() !== null) {
            $family[] = $person->getNemesis();
        }

        return array_values($family);
    }

    /**
     * @return Character[]
     */
    public function findFamilyByLastName(string $lastName): array
    {
        $family = array_filter($this->allCharacters, fn(Character $c) => $c->lastName === $lastName);

        // bug: monsters are being returned, who are not family with anyone
        //if (lastName == null)
        //{
        //    var familyWithoutMonsters = family.FindAll(c => !c.IsMonster);
        //    return familyWithoutMonsters.ToList();
        //}

        // bug: add all family's Nemeses
        $nemeses = [];

        foreach ($family as $character) {
            if ($character->getNemesis() !== null) {
                $nemeses[] = $character->getNemesis();
            }
        }

        return array_merge($family, $nemeses);
    }
}
