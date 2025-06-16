using XSD.Models;

namespace XSD.Services
{
    public interface IXmlExportService
    {
        void ExportToXml<T>(T data, string filePath);
    }
}
