using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using System.Xml.Serialization;

namespace XSD.Models
{
    [XmlRoot("keyword_suggestions")]
    public class KeywordSuggestions
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [XmlIgnore]
        public int Id { get; set; }

        [XmlArray("ideas")]
        [XmlArrayItem("idea")]
        public List<Idea> Ideas { get; set; } = new List<Idea>();

        [XmlArray("questions")]
        [XmlArrayItem("question")]
        public List<Question> Questions { get; set; } = new List<Question>();
    }
}
