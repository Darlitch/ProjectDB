// JWT Token management
function setTokens(token) {
    sessionStorage.setItem('jwtToken', token);
}

function getToken() {
    return sessionStorage.getItem('jwtToken');
}

function clearTokens() {
    sessionStorage.removeItem('jwtToken');
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
        setTokens(data.token);
        return true;
    } catch (error) {
        console.error('Login error:', error);
        throw error;
    }
}

async function refreshToken() {
    try {
        const response = await fetch('/api/v1/auth/refresh', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + getToken()
            }
        });

        if (!response.ok) {
            throw new Error('Token refresh failed');
        }

        const data = await response.json();
        setTokens(data.token);
        return true;
    } catch (error) {
        console.error('Token refresh error:', error);
        throw error;
    }
}

// Setup AJAX interceptor for token refresh
$(document).ready(function() {
    let isRefreshing = false;
    let refreshSubscribers = [];

    // Function to add request to queue
    function subscribeTokenRefresh(callback) {
        refreshSubscribers.push(callback);
    }

    // Function to notify all subscribers
    function onTokenRefreshed(token) {
        refreshSubscribers.map(callback => callback(token));
        refreshSubscribers = [];
    }

    // Function to reject all subscribers
    function onTokenRefreshError(error) {
        refreshSubscribers.map(callback => callback(null, error));
        refreshSubscribers = [];
    }

    // Setup jQuery AJAX interceptor
    $.ajaxSetup({
        beforeSend: function(xhr) {
            if (getToken()) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + getToken());
            }
        }
    });

    // Handle 401 responses globally
    $(document).ajaxError(function(event, jqXHR, ajaxSettings, thrownError) {
        if (jqXHR.status === 401 && getToken()) {
            // If we're not already refreshing
            if (!isRefreshing) {
                isRefreshing = true;

                // Try to refresh the token
                refreshToken()
                    .then(success => {
                        isRefreshing = false;
                        onTokenRefreshed(getToken());

                        // Retry the original request
                        $.ajax({
                            ...ajaxSettings,
                            headers: {
                                ...ajaxSettings.headers,
                                'Authorization': 'Bearer ' + getToken()
                            }
                        });
                    })
                    .catch(error => {
                        isRefreshing = false;
                        onTokenRefreshError(error);
                        // Redirect to login page if refresh fails
                        window.location.href = '/login';
                    });
            }

            // Add request to queue
            const retryOriginalRequest = new Promise((resolve, reject) => {
                subscribeTokenRefresh(token => {
                    if (token) {
                        resolve($.ajax({
                            ...ajaxSettings,
                            headers: {
                                ...ajaxSettings.headers,
                                'Authorization': 'Bearer ' + token
                            }
                        }));
                    } else {
                        reject();
                    }
                });
            });

            return retryOriginalRequest;
        }
    });
});

// Form handling
document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', async function(e) {
            e.preventDefault();
            
            const usernameInput = document.getElementById('username');
            const passwordInput = document.getElementById('password');
            const errorDiv = document.getElementById('loginError');
            
            try {
                await login(usernameInput.value, passwordInput.value);
                window.location.href = '/';
            } catch (error) {
                errorDiv.textContent = error.message;
                errorDiv.style.display = 'block';
            }
        });
    }
}); 