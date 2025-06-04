using System.Xml.Serialization;
using XSD.Models;

namespace XSD.Services
{
    public class XmlDeserializationService : IXmlDeserializationService
    {
        public KeywordSuggestions Deserialize(string xmlContent)
        {
            var serializer = new XmlSerializer(typeof(KeywordSuggestions));
            using var stringReader = new StringReader(xmlContent);
            return (KeywordSuggestions)serializer.Deserialize(stringReader)!;
        }
    }
}
