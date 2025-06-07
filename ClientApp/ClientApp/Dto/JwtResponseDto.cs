namespace ClientApp.Dto
{
    public class JwtResponseDto
    {
        public string AccessToken { get; set; } = string.Empty;
        public string RefreshToken { get; set; } = string.Empty; 
    }
}
