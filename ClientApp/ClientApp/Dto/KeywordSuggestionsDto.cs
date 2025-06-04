namespace ClientApp.Dto
{
    public class KeywordSuggestionsDto
    {
        public int? Id { get; set; }
        public List<IdeaDto> Ideas { get; set; } = new();
        public List<QuestionDto> Questions { get; set; } = new();
    }
}
