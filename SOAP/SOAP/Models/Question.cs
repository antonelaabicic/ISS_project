using System.Xml.Serialization;

namespace SOAP.Models
{
    public class Question
    {
        [XmlElement("keyword", Namespace = "")]
        public string Keyword { get; set; } = null!;

        [XmlElement("difficulty", Namespace = "")]
        public string Difficulty { get; set; } = null!;

        [XmlElement("volume", Namespace = "")]
        public string? Volume { get; set; }

        [XmlIgnore]
        public DateTime? LastUpdated { get; set; }

        [XmlElement("lastUpdated", Namespace = "")]
        public string? lastUpdatedRaw
        {
            get => LastUpdated?.ToString("yyyy-MM-ddTHH:mm:ss");
            set
            {
                if (DateTime.TryParse(value, out var dt))
                {
                    LastUpdated = DateTime.SpecifyKind(dt, DateTimeKind.Utc);
                }
                else
                {
                    LastUpdated = null;
                }
            }
        }
    }
}
