using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using XSD.Data;
using XSD.Models;
using XSD.Services;

namespace XSD.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class XmlExportController : ControllerBase
    {
        private readonly ISSDbContext _context;
        private readonly IXmlExportService _xmlExportService;

        public XmlExportController(ISSDbContext context, IXmlExportService xmlExportService)
        {
            _context = context;
            _xmlExportService = xmlExportService;
        }

        [HttpGet("xml/all")]
        public async Task<IActionResult> ExportAllXml()
        {
            var allSuggestions = await _context.KeywordSuggestions
                .Include(k => k.Ideas)
                .Include(k => k.Questions)
                .ToListAsync();

            if (allSuggestions.Count == 0)
            {
                return NotFound("No keyword suggestions found.");
            }

            var wrapper = new KeywordSuggestionsList
            {
                Items = allSuggestions
            };

            var xmlPath = Path.GetFullPath(Path.Combine(AppContext.BaseDirectory,
                @"..\..\..\..\..\keyword_suggestions.xml"));

            _xmlExportService.ExportToXml(wrapper, xmlPath);

            return Ok($"XML exported successfully to: {xmlPath}");
        }

        [HttpGet("exists")]
        public IActionResult Exists()
        {
            var xmlPath = Path.GetFullPath(Path.Combine(AppContext.BaseDirectory,
                @"..\..\..\..\..\keyword_suggestions.xml"));

            return System.IO.File.Exists(xmlPath) ? Ok() : NotFound();
        }
    }
}
