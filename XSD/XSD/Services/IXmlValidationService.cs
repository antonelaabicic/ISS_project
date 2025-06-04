namespace XSD.Services
{
    public interface IXmlValidationService
    {
        List<string> ValidateWithXsd(string xmlContent, string schemaPath);
        List<string> ValidateWithRng(string xmlContent, string schemaPath);
    }
}
