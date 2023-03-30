namespace Characters;

public class Character
{
    public string FirstName { get; }
    public string? LastName { get; }
    public bool IsMonster { get; }
    public HashSet<Character> children = new();
    public HashSet<Character> siblings = new();
    public HashSet<Character> parents = new();
    public HashSet<Character> nemeses = new();

    public Character(string firstName, string? lastName, bool isMonster=false)
    {
        FirstName = firstName;
        LastName = lastName;
        IsMonster = isMonster;
    }

    public void AddChild(Character child)
    {
        foreach (var character in children)
        {
            child.AddSibling(character);
        }
        
        children.Add(child);
        child.parents.Add(this);
    }

    private void AddSibling(Character sibling)
    {
        siblings.Add(sibling);
    }

    public void AddNemesis(Character monster)
    {
        nemeses.Add(monster);
    }
}