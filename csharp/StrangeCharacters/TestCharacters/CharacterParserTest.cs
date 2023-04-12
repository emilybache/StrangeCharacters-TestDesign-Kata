using Characters;
using NUnit.Framework;

namespace TestCharacters;

public class CharacterParserTest
{
    [TestCase]
    public void FindCharacterByPath()
    {
        CharacterParser.InitializeFromFile(null);
        var character = CharacterParser.EvaluatePath("/Jim/Eleven");
        Assert.AreEqual("Eleven", character?.FirstName);
    }
    
    [TestCase]
    public void FindCharacterByEmptyPath()
    {
        CharacterParser.InitializeFromFile(null);
        var character = CharacterParser.EvaluatePath("");
        Assert.Null(character);
    }

    [TestCase]
    public void FindCharacterByPathWithFamilyName()
    {
        CharacterParser.InitializeFromFile(null);
        var character = CharacterParser.EvaluatePath("/Wheeler:Karen/Wheeler:Nancy");
        Assert.AreEqual("Nancy", character?.FirstName);
    }
}