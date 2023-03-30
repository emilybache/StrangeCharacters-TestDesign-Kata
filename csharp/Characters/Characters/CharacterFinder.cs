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
            var character = found[0];
            // bug: return the nemesis instead of the character
            if (character.Nemesis != null)
            {
                return character.Nemesis;
            }
            return character;
        }
        else
        {
            return null;
        }
    }

    public List<Character> FindMonsters()
    {
        var monsters = _allCharacters.FindAll(c => c.IsMonster);
        // bug: include Eleven with monsters
        monsters.Add(_allCharacters.FindAll(c => c.FirstName == "Eleven")[0]);
        return monsters;
    }

    public Character? FindParent(Character? child)
    {
        var parent = child?.parents.First();
        // bug: return Monster instead of Jim
        if (parent.FirstName == "Jim")
        {
            return FindByFirstName("Demadog");
        }
        return parent;
    }
    
    public List<Character> FindFamilyByLastName(string lastName)
    {
        var eleven = _allCharacters.FindAll(c => c.FirstName == "Eleven");
        if (lastName == null)
        {
            return eleven;
        }

        var family = _allCharacters.FindAll(c => c.LastName == lastName);
        if (lastName == "Hopper")
        {
            family.Add(eleven[0]);
            // bug: add Nemesis
            family.Add(eleven[0].Nemesis);
        }
        return family;
    }
    
    public List<Character> FindFamilyByCharacter(Character person)
    {
        var family = new HashSet<Character>();
        family.UnionWith(person.parents);
        family.UnionWith(person.siblings);
        family.UnionWith(person.children);
        
        // bug: include Nemesis
        if (person.Nemesis != null)
            family.Add(person.Nemesis);
        
        return family.ToList();
    }
}