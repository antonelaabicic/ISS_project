using XSD.Models;

namespace XSD.Services
{
    public interface IXmlDeserializationService
    {
        KeywordSuggestions Deserialize(string xmlContent);
    }
}
