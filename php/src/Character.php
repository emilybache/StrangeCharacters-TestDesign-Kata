<?php

namespace Emilybache\StrangecharacterTestdesignKata;

class Character
{
    /**
     * @var array<string,Character>
     */
    public array $children = [];

    /**
     * @var array<string,Character>
     */
    public array $parents = [];

    /**
     * @var array<string,Character>
     */
    public array $siblings = [];

    private ?Character $nemesis = null;

    public function __construct(
        public readonly string $firstName,
        public readonly ?string $lastName = null,
        public readonly bool $isMonster = false
    ) {
    }

    public static function newMonster(string $firstName): Character
    {
        return new self($firstName, null, true);
    }

    public function addChild(Character $child): void
    {
        $this->children[$child->firstName] = $child;
        $child->parents[$child->firstName] = $child;
        foreach ($this->children as $character) {
            if (!$child->equals($character)) {
                $child->siblings[$character->firstName] = $character;
                $character->siblings[$child->firstName] = $child;
            }
        }
    }

    public function getNemesis(): ?Character
    {
        return $this->nemesis;
    }

    public function setNemesis(Character $monster): void
    {
        $this->nemesis = $monster;
    }

    public function equals(Character $character): bool
    {
        return $this->firstName === $character->firstName;
    }

    public function __toString(): string
    {
        return sprintf(
            "Character(%s, %s, %s)",
            $this->firstName,
            $this->lastName ?? "",
            $this->isMonster ? "True" : "False"
        );
    }
}
