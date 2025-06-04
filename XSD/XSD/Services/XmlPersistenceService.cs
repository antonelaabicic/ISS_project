using XSD.Data;
using XSD.Models;

namespace XSD.Services
{
    public class XmlPersistenceService : IXmlPersistenceService
    {
        private readonly ISSDbContext _context;

        public XmlPersistenceService(ISSDbContext context)
        {
            _context = context;
        }

        public async Task SaveKeywordSuggestionsAsync(KeywordSuggestions suggestions)
        {
            foreach (var idea in suggestions.Ideas)
            {
                idea.KeywordSuggestions = suggestions;
            }

            foreach (var question in suggestions.Questions)
            {
                question.KeywordSuggestions = suggestions;
            }

            _context.KeywordSuggestions.Add(suggestions);
            await _context.SaveChangesAsync();
        }
    }
}
