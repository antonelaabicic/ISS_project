using System.Net.Http.Headers;
using Microsoft.JSInterop;

public class TokenHttpClientService
{
    private readonly IHttpClientFactory _httpClientFactory;
    private readonly IJSRuntime _jsRuntime;

    public TokenHttpClientService(IHttpClientFactory factory, IJSRuntime js)
    {
        _httpClientFactory = factory;
        _jsRuntime = js;
    }

    public async Task<HttpClient> GetAuthorizedClientAsync()
    {
        var token = await _jsRuntime.InvokeAsync<string>("sessionStorage.getItem", "accessToken");
        var client = _httpClientFactory.CreateClient("KeywordSuggestionsApi");

        if (!string.IsNullOrWhiteSpace(token))
        {
            client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", token);
        }

        return client;
    }
    private async Task<bool> TryRefreshAccessTokenAsync()
    {
        return await _jsRuntime.InvokeAsync<bool>("tryRefreshToken");
    }
    public async Task<HttpResponseMessage> TryWithRefresh(Func<HttpClient, Task<HttpResponseMessage>> apiCall)
    {
        var client = await GetAuthorizedClientAsync();
        var response = await apiCall(client);

        if (response.StatusCode == System.Net.HttpStatusCode.Unauthorized ||
            response.StatusCode == System.Net.HttpStatusCode.Forbidden)
        {
            var refreshed = await TryRefreshAccessTokenAsync();
            if (refreshed)
            {
                var newClient = await GetAuthorizedClientAsync();
                response = await apiCall(newClient);
            }
        }

        return response;
    }
}
