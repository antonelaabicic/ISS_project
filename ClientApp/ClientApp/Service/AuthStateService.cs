namespace ClientApp.Service
{
    public class AuthStateService
    {
        public bool IsLoggedIn { get; private set; }

        public event Action? OnChange;

        public void SetLoggedIn(bool value)
        {
            IsLoggedIn = value;
            NotifyStateChanged();
        }

        private void NotifyStateChanged() => OnChange?.Invoke();
    }
}
