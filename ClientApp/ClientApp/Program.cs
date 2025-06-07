using ClientApp.Components;
using ClientApp.Service;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddRazorComponents().AddInteractiveServerComponents();

builder.Services.AddHttpClient("XsdRngApi", client =>
{
    client.BaseAddress = new Uri("http://localhost:5296");
});
builder.Services.AddHttpClient("KeywordSuggestionsApi", client =>
{
    client.BaseAddress = new Uri("http://localhost:8080");
});

builder.Services.AddSingleton<AuthStateService>();

var app = builder.Build();

if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error", createScopeForErrors: true);
}

app.UseStaticFiles();
app.UseAntiforgery();

app.MapRazorComponents<App>().AddInteractiveServerRenderMode();

app.Run();
