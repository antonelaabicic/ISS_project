using System.Xml.Serialization;

namespace SOAP.Models
{
    [XmlType(Namespace = "")]
    [XmlRoot("keyword_suggestions", Namespace = "")]
    public class KeywordSuggestions
    {
        [XmlAttribute("id")]
        public int Id { get; set; }

        [XmlArray("ideas", Namespace = "")]
        [XmlArrayItem("idea", Namespace = "")]
        public List<Idea> Ideas { get; set; } = new();

        [XmlArray("questions", Namespace = "")]
        [XmlArrayItem("question", Namespace = "")]
        public List<Question> Questions { get; set; } = new();
    }
}
