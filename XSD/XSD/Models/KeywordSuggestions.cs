using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;
using System.Xml.Serialization;

namespace XSD.Models
{
    [XmlRoot("keyword_suggestions")]
    public class KeywordSuggestions
    {
        private int _id;

        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [XmlIgnore]
        public int Id
        {
            get => _id;
            set => _id = value;
        }

        [NotMapped]
        [XmlAttribute("id")]
        public string? IdSerialized
        {
            get => _id != 0 ? _id.ToString() : null;
            set
            {
                if (int.TryParse(value, out int result))
                {
                    _id = result;
                }
            }
        }

        [XmlArray("ideas")]
        [XmlArrayItem("idea")]
        public List<Idea> Ideas { get; set; } = new List<Idea>();

        [XmlArray("questions")]
        [XmlArrayItem("question")]
        public List<Question> Questions { get; set; } = new List<Question>();
    }
}
