using Microsoft.EntityFrameworkCore;
using System;
using XSD.Data;
using XSD.Services;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers().AddXmlSerializerFormatters(); ;
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddDbContext<ISSDbContext>(options =>
    options.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection")));

builder.Services.AddScoped<IXmlValidationService, XmlValidationService>();
builder.Services.AddScoped<IXmlDeserializationService, XmlDeserializationService>();
builder.Services.AddScoped<IXmlPersistenceService, XmlPersistenceService>();
builder.Services.AddScoped<IExceptionResponseService, ExceptionResponseService>();

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseAuthorization();

app.MapControllers();

app.Run();
