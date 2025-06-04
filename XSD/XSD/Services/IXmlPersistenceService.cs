using XSD.Models;

namespace XSD.Services
{
    public interface IXmlPersistenceService
    {
        Task SaveKeywordSuggestionsAsync(KeywordSuggestions suggestions);
    }
}
