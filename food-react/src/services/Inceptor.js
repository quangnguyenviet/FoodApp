import axios from "axios";
import ApiService from "./ApiService";

const BASE_URL = process.env.REACT_APP_BASE_URL;
const api = axios.create({
  baseURL: BASE_URL,
  withCredentials: true
});
api.interceptors.response.use(
  (response) => response, // pass through successful responses

  async (error) => {
    const originalRequest = error.config;
    // Prevent infinite loops
    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        console.log("Attempting to refresh token");
        const refreshedToken = await ApiService.refreshToken();

        if (refreshedToken.statusCode === 200) {
          ApiService.saveToken(refreshedToken.data.token);

          // Update Authorization header and retry
          originalRequest.headers.Authorization =
            `Bearer ${refreshedToken.data.token}`;

          return api(originalRequest);
        }
      } catch (refreshError) {
        console.error("Refresh token failed, logging out");
        ApiService.logout();
      }
    }

    return Promise.reject(error);
  }
);
export default api;
