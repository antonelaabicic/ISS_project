using System.Xml.Serialization;

namespace XSD.Models
{
    [XmlRoot("keyword_suggestions_list")]
    public class KeywordSuggestionsList
    {
        [XmlElement("keyword_suggestions")]
        public List<KeywordSuggestions> Items { get; set; } = new List<KeywordSuggestions>();
    }
}
