using Microsoft.AspNetCore.Mvc;
using System.Xml;
using System.Xml.Schema;
using System.Xml.Serialization;
using XSD.Models;
using XSD.Data;
using XSD.Services;
using Commons.Xml.Relaxng;

namespace XSD.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class XmlUploadController : ControllerBase
    {
        private readonly IXmlValidationService _validationService;
        private readonly IXmlDeserializationService _deserializationService;
        private readonly IXmlPersistenceService _persistenceService;
        private readonly IExceptionResponseService _exceptionService;

        private const string xsdSchemaPath = "Xsd/keyword_suggestions.xsd";
        private const string rngSchemaPath = "Rng/keyword_suggestions.rng";

        public XmlUploadController(IXmlValidationService validationService, IXmlDeserializationService deserializationService, IXmlPersistenceService persistenceService, IExceptionResponseService exceptionService)
        {
            _validationService = validationService;
            _deserializationService = deserializationService;
            _persistenceService = persistenceService;
            _exceptionService = exceptionService;
        }

        [HttpPost("xsd")]
        [Consumes("application/xml")]
        public async Task<IActionResult> UploadXsd()
        {
            string xmlContent;
            using (var reader = new StreamReader(Request.Body))
            {
                xmlContent = await reader.ReadToEndAsync();
            }

            try
            {
                var validationErrors = _validationService.ValidateWithXsd(xmlContent, xsdSchemaPath);
                if (validationErrors.Any())
                {
                    return BadRequest(new { status = "validation_failed", errors = validationErrors });
                }

                var xmlObj = _deserializationService.Deserialize(xmlContent);
                await _persistenceService.SaveKeywordSuggestionsAsync(xmlObj);

                return Ok(new { status = "success", message = "XML validated and saved to database." });
            }
            catch (Exception ex)
            {
                return _exceptionService.CreateErrorResponse(ex);
            }
        }

        [HttpPost("rng")]
        [Consumes("application/xml")]
        public async Task<IActionResult> UploadRng()
        {
            string xmlContent;
            using (var reader = new StreamReader(Request.Body))
            {
                xmlContent = await reader.ReadToEndAsync();
            }

            try
            {
                var validationErrors = _validationService.ValidateWithRng(xmlContent, rngSchemaPath);
                if (validationErrors.Any())
                {
                    return BadRequest(new { status = "validation_failed", errors = validationErrors });
                }

                var xmlObj = _deserializationService.Deserialize(xmlContent);
                await _persistenceService.SaveKeywordSuggestionsAsync(xmlObj);

                return Ok(new { status = "success", message = "XML validated and saved to database." });
            }
            catch (Exception ex)
            {
                return _exceptionService.CreateErrorResponse(ex);
            }
        }

    }
}
