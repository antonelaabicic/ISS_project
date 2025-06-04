using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Xml.Serialization;

namespace XSD.Models
{
    public class Question
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [XmlIgnore]
        public int Id { get; set; }

        [XmlElement("keyword")]
        public string Keyword { get; set; } = null!;

        [XmlElement("difficulty")]
        public string Difficulty { get; set; } = null!;

        [XmlElement("volume")]
        public string? Volume { get; set; }

        [XmlIgnore]
        public DateTime? LastUpdated { get; set; }

        [NotMapped]
        [XmlElement("lastUpdated")]
        public string? LastUpdatedRaw
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

        [ForeignKey("KeywordSuggestionsId")]
        [XmlIgnore]
        public int KeywordSuggestionsId { get; set; }

        [XmlIgnore]
        public KeywordSuggestions KeywordSuggestions { get; set; } = null!;
    }
}
