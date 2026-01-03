import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const OAuth2Callback = () => {
    const navigate = useNavigate();
    const [error, setError] = useState(null);

    useEffect(() => {
        const exchangeToken = async () => {
            try {
                // 1. Call your specific refresh endpoint
                // IMPORTANT: 'withCredentials: true' allows the browser to send the HttpOnly cookie
                const response = await axios.post('http://localhost:8080/api/auth/refresh', {}, {
                    withCredentials: true 
                });
                console.log("Token exchange successful", response.data);

                // 2. Extract the JWT (Access Token) from the response body
                // const { accessToken } = response.data;

                // 3. Store the JWT in memory (e.g., Context, Redux, or a local variable)
                // Avoid LocalStorage for the JWT if you want maximum security
                // saveTokenToAppState(accessToken); 

                // 4. Redirect to the home page or dashboard
                // navigate('/dashboard');
            } catch (err) {
                console.error("Token exchange failed", err);
                setError("Authentication failed. Please try again.");
                // navigate('/login');
            }
        };

        exchangeToken();
    }, [navigate]);

    if (error) return <div>{error}</div>;
    return <div>Completing login, please wait...</div>;
};

export default OAuth2Callback;