using SOAP.Models;
using System.ServiceModel;

namespace SOAP.Service
{
    [ServiceContract]
    public interface IKeywordFilterService
    {
        [OperationContract]
        KeywordSuggestionsList FilterByTerm(string term);
    }
}
