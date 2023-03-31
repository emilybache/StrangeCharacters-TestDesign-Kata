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

    public Character(string firstName, string? lastName=null, bool isMonster=false)
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

    protected bool Equals(Character other)
    {
        return FirstName == other.FirstName;
    }

    public override bool Equals(object? obj)
    {
        if (ReferenceEquals(null, obj)) return false;
        if (ReferenceEquals(this, obj)) return true;
        if (obj.GetType() != this.GetType()) return false;
        return Equals((Character)obj);
    }

    public override int GetHashCode()
    {
        return FirstName.GetHashCode();
    }
}