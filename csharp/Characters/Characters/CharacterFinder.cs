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
}