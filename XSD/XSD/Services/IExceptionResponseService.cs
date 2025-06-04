using Microsoft.AspNetCore.Mvc;

namespace XSD.Services
{
    public interface IExceptionResponseService
    {
        IActionResult CreateErrorResponse(Exception ex);
    }
}
