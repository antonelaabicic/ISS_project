using SOAP.Service;
using SoapCore;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddAuthorization();
builder.Services.AddSingleton<IKeywordFilterService, KeywordFilterService>();

var app = builder.Build();

app.UseRouting();

app.UseAuthorization();

app.UseEndpoints(endpoints =>
{
    endpoints.UseSoapEndpoint<IKeywordFilterService>(
        "/ServiceFilter.asmx",
        new SoapEncoderOptions(),
        SoapSerializer.XmlSerializer
    );
});

app.Run();
