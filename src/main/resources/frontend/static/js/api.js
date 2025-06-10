// API client for making authenticated requests
class ApiClient {
    constructor() {
        this.baseUrl = '/api/v1';
    }

    async request(endpoint, options = {}) {
        const token = localStorage.getItem('auth_token');
        
        // Default options
        const defaultOptions = {
            headers: {
                'Content-Type': 'application/json',
                ...(token ? { 'Authorization': `Bearer ${token}` } : {})
            }
        };

        // Merge options
        const fetchOptions = {
            ...defaultOptions,
            ...options,
            headers: {
                ...defaultOptions.headers,
                ...(options.headers || {})
            }
        };

        try {
            let response = await fetch(`${this.baseUrl}${endpoint}`, fetchOptions);

            // If unauthorized and we have a refresh token, try to refresh
            if (response.status === 401 && localStorage.getItem('refresh_token')) {
                const success = await refreshToken();
                if (success) {
                    // Retry the request with new token
                    fetchOptions.headers['Authorization'] = `Bearer ${localStorage.getItem('auth_token')}`;
                    response = await fetch(`${this.baseUrl}${endpoint}`, fetchOptions);
                }
            }

            if (!response.ok) {
                const error = await response.json();
                throw new Error(error.message || 'API request failed');
            }

            return await response.json();
        } catch (error) {
            console.error('API request error:', error);
            throw error;
        }
    }

    // Generic CRUD methods
    async getAll(endpoint) {
        return this.request(endpoint);
    }

    async getById(endpoint, id) {
        return this.request(`${endpoint}/${id}`);
    }

    async create(endpoint, data) {
        return this.request(endpoint, {
            method: 'POST',
            body: JSON.stringify(data)
        });
    }

    async update(endpoint, id, data) {
        return this.request(`${endpoint}/${id}`, {
            method: 'PATCH',
            body: JSON.stringify(data)
        });
    }

    async delete(endpoint, id) {
        return this.request(`${endpoint}/${id}`, {
            method: 'DELETE'
        });
    }
}

// Create global API client instance
const api = new ApiClient();

// Utility function to show error messages
function showError(elementId, message) {
    const errorDiv = document.getElementById(elementId);
    if (errorDiv) {
        errorDiv.textContent = message;
        errorDiv.style.display = 'block';
    }
}

// Utility function to hide error messages
function hideError(elementId) {
    const errorDiv = document.getElementById(elementId);
    if (errorDiv) {
        errorDiv.style.display = 'none';
    }
}

// Check if user is authenticated
function isAuthenticated() {
    return !!localStorage.getItem('auth_token');
}

// Redirect to login if not authenticated
function requireAuth() {
    if (!isAuthenticated()) {
        window.location.href = '/login';
        return false;
    }
    return true;
} 