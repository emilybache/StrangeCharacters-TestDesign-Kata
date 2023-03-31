using Characters;
using NUnit.Framework;

namespace TestCharacters;

/*
 * This test case is not part of the exercise - it is example code showing what's possible
 */
public class ExampleCharacterTestCase
{
    [TestCase]
    public void FindCharacterByLastName()
    {
        // This test constructs all its own test data to make it clearer what it does
        var karen = new Character("Karen", "Wheeler");
        var mike = new Character("Mike", "Wheeler");

        // this is example code showing kinds of assertion you could do on a Character
        var nancy = new Character("Nancy", "Wheeler");
        Assert.AreEqual("Nancy", nancy.FirstName);
        Assert.AreEqual(nancy, nancy);
        // This assertion works because Character is a ValueObject that implements "Equals"
        Assert.AreEqual(new Character("Nancy", "Wheeler"), nancy);

        karen.AddChild(nancy);
        karen.AddChild(mike);
        var finder = new CharacterFinder(new List<Character>()
        {
            karen,
            mike,
            nancy,
        });

        // This is the 'act' step
        var charactersList = finder.FindFamilyByLastName("Wheeler");
        
        // this is example code showing kinds of assertion you could do on a List of Characters
        Assert.NotNull(charactersList);
        Assert.AreEqual(3, charactersList.Count);
        CollectionAssert.Contains(charactersList, new Character("Nancy", "Wheeler"));
        CollectionAssert.AreEquivalent(new List<Character>()
        {
            new Character("Nancy", "Wheeler"),
            new Character("Mike", "Wheeler"),
            new Character("Karen", "Wheeler"),
        }, charactersList);
        Assert.AreEqual(new List<Character>()
        {
            new Character("Karen", "Wheeler"),
            new Character("Mike", "Wheeler"),
            new Character("Nancy", "Wheeler"),
        }, charactersList);
    }
}