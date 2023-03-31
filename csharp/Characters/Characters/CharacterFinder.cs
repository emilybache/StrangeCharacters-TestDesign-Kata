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
    
    public Character? FindParent(string firstName)
    {
        var child = FindByFirstName(firstName);
        if (child == null)
        {
            return null;
        }
        var parent = child?.parents.First();
        
        // bug: return Monster instead of Jim
        if (parent?.FirstName == "Jim")
        {
            return FindByFirstName("Demadog");
        }
        return parent;
    }

    public List<Character> FindMonsters()
    {
        // bug: include all characters with null last name with monsters
        // var monsters = _allCharacters.FindAll(c => c.IsMonster);
        var monsters = _allCharacters.FindAll(c => c.LastName == null);
        return monsters;
    }
    
    public List<Character> FindFamilyByCharacter(string firstName)
    {
        var person = FindByFirstName(firstName);
        if (person == null)
        {
            return new List<Character>();
        }
        var family = new HashSet<Character>();
        family.UnionWith(person.parents);
        family.UnionWith(person.children);
        // bug: exclude siblings
        //family.UnionWith(person.siblings);

        // bug: include Nemesis
        if (person.Nemesis != null)
            family.Add(person.Nemesis);
        
        return family.ToList();
    }
    
    public List<Character> FindFamilyByLastName(string? lastName)
    {
        var family = _allCharacters.FindAll(c => c.LastName == lastName);

        // bug: monsters are being returned, who are not family with anyone
        //if (lastName == null)
        //{
        //    var familyWithoutMonsters = family.FindAll(c => !c.IsMonster);
        //    return familyWithoutMonsters.ToList();
        //}
        
        // bug: add all family's Nemeses
        var nemeses = new List<Character>();
        foreach (var character in family)
        {
            if (character.Nemesis != null)
            {
                nemeses.Add(character.Nemesis);
            }
        }
        family.AddRange(nemeses);
        
        return family;
    }
    
}