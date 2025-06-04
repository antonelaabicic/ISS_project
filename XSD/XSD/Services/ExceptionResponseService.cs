using Commons.Xml.Relaxng;
using Microsoft.AspNetCore.Mvc;
using System.Xml;

namespace XSD.Services
{
    public class ExceptionResponseService : IExceptionResponseService
    {
        public IActionResult CreateErrorResponse(Exception ex)
        {
            return ex switch
            {
                RelaxngException rng => new BadRequestObjectResult(new
                {
                    status = "rng_validation_error",
                    message = rng.Message,
                    inner = GetDeepestMessage(rng)
                }),

                XmlException xml => new BadRequestObjectResult(new
                {
                    status = "xml_parse_error",
                    message = xml.Message,
                    inner = GetDeepestMessage(xml)
                }),

                InvalidOperationException inv => new BadRequestObjectResult(new
                {
                    status = "deserialization_error",
                    message = inv.Message,
                    inner = GetDeepestMessage(inv)
                }),

                _ => new ObjectResult(new
                {
                    status = "server_error",
                    message = ex.Message,
                    inner = GetDeepestMessage(ex)
                })
                { StatusCode = 500 }
            };
        }

        private string GetDeepestMessage(Exception ex)
        {
            while (ex.InnerException != null)
            {
                ex = ex.InnerException;
            }
            return ex.Message;
        }

    }

}
