using System.Xml.Serialization;

namespace XSD.Services
{
    public class XmlExportService : IXmlExportService
    {
        public void ExportToXml<T>(T data, string filePath)
        {
            var serializer = new XmlSerializer(typeof(T));
            using var writer = new StreamWriter(filePath, false); 
            serializer.Serialize(writer, data);
        }
    }
}
