import { useNavigate, Link, useLocation } from "react-router-dom";
import { useError } from "../../common/ErrorDisplay/ErrorDisplay"
import { useState } from "react";
import ApiService from "../../../services/ApiService";
import styles from "./LoginPage.module.css";

const LoginPage = () => {

    const { ErrorDisplay, showError } = useError();
    const navigate = useNavigate();
    const { state } = useLocation();
    const redirectPath = state?.from?.pathname || "/home"

    const [formData, setFormData] = useState({

        email: '',
        password: ''

    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!formData.email || !formData.password) {
            showError('Email and password are required.');
            return;
        }

        try {
            const response = await ApiService.loginUser(formData);
            if (response.statusCode === 200) {
                ApiService.saveToken(response.data.token)
                ApiService.saveRole(response.data.roles)
                navigate(redirectPath, {replace: true})
            } else {
                showError(response.message)
            }
        } catch (error) {
            showError(error.response?.data?.message || error.message);
        }
    };


    return (
        <div className={styles.page}>
        {/* Render the ErrorDisplay component */}
        <ErrorDisplay />
            <div className={styles.card}>
                
                <div className={styles.header}>
                    <h2 className={styles.title}>Login</h2>
                    <p className={styles.description}>Login to your account to order delicious food!</p>
                </div>

                <div className={styles.content}>
                    <form className={styles.form} onSubmit={handleSubmit}>
                        <div className={styles.formGroup}>
                            <label htmlFor="email" className={styles.label}>Email</label>
                            <input
                                id="email"
                                name="email"
                                type="email"
                                autoComplete="email"
                                value={formData.email}
                                onChange={handleChange}
                                required
                                placeholder="Your Email Address"
                                className={styles.input}
                            />
                        </div>
                        <div className={styles.formGroup}>
                            <label htmlFor="password" className={styles.label}>Password</label>
                            <input
                                id="password"
                                name="password"
                                type="password"
                                value={formData.password}
                                onChange={handleChange}
                                required
                                placeholder="Password"
                                className={styles.input}
                            />
                        </div>

                        <div>
                            <button type="submit" className={styles.button}>
                                Login
                            </button>
                        </div>


                        <div className={styles.registerLink}>
                            <Link to="/register" className={styles.link}>
                                Don't Have an Account? Register
                            </Link>
                        </div>
                    </form>

                    <div>
                        <div className={styles.separator}>
                            <span className={styles.separatorText}>Or continue with</span>
                        </div>

                        <div className={styles.socialButtons}>
                            {/* Add social login buttons here (e.g., Google, Facebook, GitHub) */}
                            <button className={`${styles.socialButton} ${styles.google}`}>Google</button>
                            <button className={`${styles.socialButton} ${styles.facebook}`}>Facebook</button>
                            <button className={`${styles.socialButton} ${styles.github}`}>Github</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );



}
export default LoginPage;