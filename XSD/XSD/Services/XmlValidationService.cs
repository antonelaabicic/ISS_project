using System.Xml.Schema;
using System.Xml;
using Commons.Xml.Relaxng;

namespace XSD.Services
{
    public class XmlValidationService : IXmlValidationService
    {
        public List<string> ValidateWithXsd(string xmlContent, string schemaPath)
        {
            var validationErrors = new List<string>();

            var settings = new XmlReaderSettings();
            settings.Schemas.Add(null, schemaPath);
            settings.ValidationType = ValidationType.Schema;
            settings.ValidationFlags |= XmlSchemaValidationFlags.ReportValidationWarnings;
            settings.ValidationEventHandler += (sender, e) =>
            {
                validationErrors.Add($"Line {e.Exception?.LineNumber}, Pos {e.Exception?.LinePosition}: {e.Message}");
            };

            using var reader = XmlReader.Create(new StringReader(xmlContent), settings);
            while (reader.Read()) { }

            return validationErrors;
        }

        public List<string> ValidateWithRng(string xmlContent, string schemaPath)
        {
            var validationErrors = new List<string>();

            var xmlDoc = new XmlDocument();
            xmlDoc.LoadXml(xmlContent);

            using var rngReader = XmlReader.Create(schemaPath);
            using var xmlReader = XmlReader.Create(new StringReader(xmlDoc.OuterXml));

            var validatingReader = new RelaxngValidatingReader(xmlReader, rngReader);

            while (validatingReader.Read()) { }

            return validationErrors;
        }
    }
}
