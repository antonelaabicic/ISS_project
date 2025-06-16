using System.Xml.Serialization;

namespace SOAP.Models
{
    [XmlRoot("keyword_suggestions_list")]
    public class KeywordSuggestionsList
    {
        [XmlElement("keyword_suggestions")]
        public List<KeywordSuggestions> Items { get; set; } = new();
    }
}
