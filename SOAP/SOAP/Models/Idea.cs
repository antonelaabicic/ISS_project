using System.Xml.Serialization;

namespace SOAP.Models
{
    public class Idea
    {
        [XmlElement("keyword", Namespace = "")]
        public string Keyword { get; set; } = null!;

        [XmlElement("difficulty", Namespace = "")]
        public string Difficulty { get; set; } = null!;

        [XmlElement("volume", Namespace = "")]
        public string? Volume { get; set; }

        [XmlElement("lastUpdated", Namespace = "")]
        public DateTime LastUpdated { get; set; }
    }
}
