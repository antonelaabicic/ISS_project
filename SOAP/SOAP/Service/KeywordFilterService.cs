using System.Xml;
using System.Xml.XPath;
using System.Xml.Serialization;
using SOAP.Models;
using SOAP.Service;

public class KeywordFilterService : IKeywordFilterService
{
    public KeywordSuggestionsList FilterByTerm(string term)
    {
        string lowerTerm = term.ToLowerInvariant();
        string xmlPath = Path.GetFullPath(Path.Combine(AppContext.BaseDirectory, @"..\..\..\..\..\keyword_suggestions.xml"));
        var doc = new XPathDocument(xmlPath);
        var nav = doc.CreateNavigator();

        string xpath = $@"
        //keyword_suggestions[
          ideas/idea[
            contains(translate(keyword, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '{lowerTerm}') or
            contains(translate(difficulty, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '{lowerTerm}') or
            contains(translate(volume, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '{lowerTerm}')
          ] 
          or
          questions/question[
            contains(translate(keyword, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '{lowerTerm}') or
            contains(translate(difficulty, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '{lowerTerm}') or
            contains(translate(volume, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '{lowerTerm}')
          ]
        ]";

        var results = new KeywordSuggestionsList();
        var serializer = new XmlSerializer(typeof(KeywordSuggestions));

        foreach (XPathNavigator node in nav.Select(xpath))
        {
            using var reader = node.ReadSubtree();
            var suggestion = (KeywordSuggestions)serializer.Deserialize(reader);

            suggestion.Ideas = suggestion.Ideas
                .Where(i => Contains(i.Keyword, lowerTerm) || Contains(i.Difficulty, lowerTerm) || Contains(i.Volume, lowerTerm))
                .ToList();

            suggestion.Questions = suggestion.Questions
                .Where(q => Contains(q.Keyword, lowerTerm) || Contains(q.Difficulty, lowerTerm) || Contains(q.Volume, lowerTerm))
                .ToList();

            results.Items.Add(suggestion);
        }

        return results;
    }

    private bool Contains(string? text, string term)
    {
        return text?.ToLowerInvariant().Contains(term) ?? false;
    }
}
