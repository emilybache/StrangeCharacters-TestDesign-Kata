namespace Characters;

public class Character
{
    public string FirstName { get; }
    public string? LastName { get; }
    public bool IsMonster { get; }

    public Character? Nemesis { get; private set; }

    public HashSet<Character> children = new();
    public HashSet<Character> siblings = new();
    public HashSet<Character> parents = new();

    public Character(string firstName, string? lastName, bool isMonster=false)
    {
        FirstName = firstName;
        LastName = lastName;
        IsMonster = isMonster;
    }

    public void AddChild(Character child)
    {
        children.Add(child);
        child.parents.Add(this);
        foreach (var character in children)
        {
            if (child.FirstName != character.FirstName)
            {
                child.siblings.Add(character);
                character.siblings.Add(child);
            }
        }
    }

    public void SetNemesis(Character monster)
    {
        this.Nemesis = monster;
    }

    public override string ToString()
    {
        return $"Character({FirstName}, {LastName}, isMonster:{IsMonster})";
    }
}