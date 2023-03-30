namespace Characters;

public class CharacterFinder
{
    private readonly List<Character> _allCharacters;

    public CharacterFinder(List<Character> allCharacters)
    {
        _allCharacters = allCharacters;
    }

    public Character? FindByFirstName(string name)
    {
        var found = _allCharacters.FindAll(c => c.FirstName == name);
        if (found.Count > 0)
        {
            return found[0];
        }
        else
        {
            return null;
        }
    }

    public List<Character> FindMonsters()
    {
        return _allCharacters.FindAll(c => c.IsMonster);
    }

    public Character? FindParent(Character? child)
    {
        return child?.parents.First();
    }
    
    public List<Character> FindFamilyByLastName(string lastName)
    {
        return _allCharacters.FindAll(c => c.LastName == lastName);
    }
    
    public List<Character> FindFamilyByCharacter(Character person)
    {
        var family = new HashSet<Character>();
        family.UnionWith(person.parents);
        family.UnionWith(person.siblings);
        family.UnionWith(person.children);
        return family.ToList();
    }
}