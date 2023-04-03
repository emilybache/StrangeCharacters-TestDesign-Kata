using Characters;
using NUnit;
using NUnit.Framework;

namespace TestCharacters;

public class CharactersTest
{
    public List<Character> allTestData()
    {
        var joyce = new Character("Joyce", "Byers");
        var jim = new Character("Jim", "Hopper");
        var mike = new Character("Mike", "Wheeler");
        var eleven = new Character("Eleven");
        var dustin = new Character("Dustin", "Henderson");
        var lucas = new Character("Lucas", "Sinclair");
        var nancy = new Character("Nancy", "Wheeler");
        var jonathan = new Character("Jonathan", "Byers");
        var will = new Character("Will", "Byers");
        var karen = new Character("Karen", "Wheeler");
        var steve = new Character("Steve", "Harrington");
        var mindflayer = new Character("Mindflayer", isMonster:true);
        var demagorgon = new Character("Demagorgon", isMonster:true);
        var demadog = new Character("Demadog", isMonster:true);
        
        joyce.AddChild(jonathan);
        joyce.AddChild(will);
        jim.AddChild(eleven);
        karen.AddChild(nancy);
        karen.AddChild(mike);
        
        eleven.SetNemesis(demagorgon);
        will.SetNemesis(mindflayer);
        dustin.SetNemesis(demadog);

        return new List<Character>()
        {
            joyce,
            jim,
            mike,
            will,
            eleven,
            dustin,
            lucas,
            nancy,
            jonathan,
            mindflayer,
            demagorgon,
            demadog,
            karen,
            steve,
        };
    }

    [TestCase]
    public void FindCharacterByFirstName()
    {
        var finder = new CharacterFinder(allTestData());
        var character = finder.FindByFirstName("Jim");
        Assert.AreEqual("Jim", character?.FirstName);
    }
    
}