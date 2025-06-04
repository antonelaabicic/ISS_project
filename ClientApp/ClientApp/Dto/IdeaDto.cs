namespace ClientApp.Dto
{
    public class IdeaDto
    {
        public int? Id { get; set; }
        public string? Keyword { get; set; }
        public string? Difficulty { get; set; }
        public string? Volume { get; set; }
        public DateTime? LastUpdated { get; set; }
        public int? KeywordSuggestionsId { get; set; }
    }
}
