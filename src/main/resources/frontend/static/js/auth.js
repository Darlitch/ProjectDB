// JWT Token management
const AUTH_TOKEN_KEY = 'auth_token';
const REFRESH_TOKEN_KEY = 'refresh_token';

function setTokens(authToken, refreshToken) {
    localStorage.setItem(AUTH_TOKEN_KEY, authToken);
    localStorage.setItem(REFRESH_TOKEN_KEY, refreshToken);
}

function getAuthToken() {
    return localStorage.getItem(AUTH_TOKEN_KEY);
}

function getRefreshToken() {
    return localStorage.getItem(REFRESH_TOKEN_KEY);
}

function clearTokens() {
    localStorage.removeItem(AUTH_TOKEN_KEY);
    localStorage.removeItem(REFRESH_TOKEN_KEY);
}

// Authentication functions
async function login(username, password) {
    try {
        const response = await fetch('/api/v1/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ login: username, password })
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || 'Login failed');
        }

        const data = await response.json();
        setTokens(data.token, data.refreshToken);
        return true;
    } catch (error) {
        console.error('Login error:', error);
        throw error;
    }
}

async function refreshToken() {
    const refreshToken = getRefreshToken();
    if (!refreshToken) {
        throw new Error('No refresh token available');
    }

    try {
        const response = await fetch('/api/v1/auth/refresh', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ refreshToken })
        });

        if (!response.ok) {
            clearTokens();
            throw new Error('Token refresh failed');
        }

        const data = await response.json();
        setTokens(data.token, data.refreshToken);
        return true;
    } catch (error) {
        console.error('Token refresh error:', error);
        throw error;
    }
}

// Add Authorization header to all API requests
function addAuthHeader(headers = {}) {
    const token = getAuthToken();
    if (token) {
        return {
            ...headers,
            'Authorization': `Bearer ${token}`
        };
    }
    return headers;
}

// Fetch wrapper with automatic token refresh
async function fetchWithAuth(url, options = {}) {
    try {
        // Add auth header
        options.headers = addAuthHeader(options.headers);
        
        // Make request
        let response = await fetch(url, options);
        
        // If unauthorized and we have a refresh token, try to refresh
        if (response.status === 401 && getRefreshToken()) {
            const refreshed = await refreshToken();
            if (refreshed) {
                // Retry original request with new token
                options.headers = addAuthHeader(options.headers);
                response = await fetch(url, options);
            }
        }
        
        return response;
    } catch (error) {
        console.error('Fetch error:', error);
        throw error;
    }
}

// Export functions
window.auth = {
    login,
    refreshToken,
    fetchWithAuth,
    clearTokens
};

// Form handling
document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', async function(e) {
            e.preventDefault();
            
            const usernameInput = document.getElementById('username');
            const passwordInput = document.getElementById('password');
            const rememberMe = document.getElementById('remember-me');
            const errorDiv = document.getElementById('loginError');
            
            try {
                await login(usernameInput.value, passwordInput.value);
                // If remember-me is not checked, we'll set token expiration
                if (!rememberMe.checked) {
                    // Token will be cleared after browser session
                    sessionStorage.setItem('remember-me', 'false');
                }
                window.location.href = '/';
            } catch (error) {
                errorDiv.textContent = error.message;
                errorDiv.style.display = 'block';
            }
        });
    }
}); 