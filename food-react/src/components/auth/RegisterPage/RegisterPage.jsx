import { useNavigate, Link } from "react-router-dom";
import { useError } from "../../common/ErrorDisplay/ErrorDisplay";
import { useState } from "react";
import ApiService from "../../../services/ApiService";
import styles from "./RegisterPage.module.css";

const RegisterPage = () => {
    const { ErrorDisplay, showError } = useError();
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: "",
        confirmPassword: "",
        phoneNumber: "",
        address: "",
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (
            !formData.name ||
            !formData.email ||
            !formData.password ||
            !formData.confirmPassword ||
            !formData.phoneNumber ||
            !formData.address
        ) {
            showError("All fields are required");
            return;
        }

        if (formData.password !== formData.confirmPassword) {
            showError("Passwords do not match.");
            return;
        }

        const registrationData = {
            name: formData.name,
            email: formData.email,
            password: formData.password,
            phoneNumber: formData.phoneNumber,
            address: formData.address,
        };

        try {
            const response = await ApiService.registerUser(registrationData);
            if (response.statusCode === 200) {
                setFormData({
                    name: "",
                    email: "",
                    password: "",
                    confirmPassword: "",
                    phoneNumber: "",
                    address: "",
                });
                navigate("/login");
            } else {
                showError(response.message);
            }
        } catch (error) {
            showError(error.response?.data?.message || error.message);
        }
    };

    return (
        <div className={styles.page}>
            <div className={styles.card}>
                <div className={styles.header}>
                    <h2 className={styles.title}>Register</h2>
                    <p className={styles.description}>
                        Create an account to order delicious food!
                    </p>
                </div>

                <div className={styles.content}>
                    <form onSubmit={handleSubmit}>
                        <div className={styles.formGroup}>
                            <label htmlFor="name" className={styles.label}>
                                Full Name
                            </label>
                            <input
                                type="text"
                                id="name"
                                name="name"
                                value={formData.name}
                                onChange={handleChange}
                                placeholder="Your Full Name"
                                className={styles.input}
                                required
                            />
                        </div>

                        <div className={styles.formGroup}>
                            <label htmlFor="email" className={styles.label}>
                                Email
                            </label>
                            <input
                                type="email"
                                id="email"
                                name="email"
                                value={formData.email}
                                onChange={handleChange}
                                placeholder="Your Email Here"
                                className={styles.input}
                                required
                            />
                        </div>

                        <div className={styles.formGroup}>
                            <label htmlFor="password" className={styles.label}>
                                Password
                            </label>
                            <input
                                type="password"
                                id="password"
                                name="password"
                                value={formData.password}
                                onChange={handleChange}
                                placeholder="Password"
                                className={styles.input}
                                required
                            />
                        </div>

                        <div className={styles.formGroup}>
                            <label
                                htmlFor="confirmPassword"
                                className={styles.label}
                            >
                                Confirm Password
                            </label>
                            <input
                                type="password"
                                id="confirmPassword"
                                name="confirmPassword"
                                value={formData.confirmPassword}
                                onChange={handleChange}
                                placeholder="Confirm Password"
                                className={styles.input}
                                required
                            />
                        </div>

                        <div className={styles.formGroup}>
                            <label
                                htmlFor="phoneNumber"
                                className={styles.label}
                            >
                                Phone Number
                            </label>
                            <input
                                type="text"
                                id="phoneNumber"
                                name="phoneNumber"
                                value={formData.phoneNumber}
                                onChange={handleChange}
                                placeholder="Your Phone Number"
                                className={styles.input}
                                required
                            />
                        </div>

                        <div className={styles.formGroup}>
                            <label htmlFor="address" className={styles.label}>
                                Address
                            </label>
                            <input
                                type="text"
                                id="address"
                                name="address"
                                value={formData.address}
                                onChange={handleChange}
                                placeholder="Your Address Here"
                                className={styles.input}
                                required
                            />
                        </div>

                        <ErrorDisplay />

                        <button
                            type="submit"
                            className={styles.button}
                        >
                            Register
                        </button>

                        <div className={styles.loginLink}>
                            <Link
                                to="/login"
                                className={styles.link}
                            >
                                Already Have Account? Login
                            </Link>
                        </div>
                    </form>

                    <div>
                        <div className={styles.separator}>
                            <span className={styles.separatorText}>
                                Or continue with
                            </span>
                        </div>

                        <div className={styles.socialButtons}>
                            <button
                                type="button"
                                className={`${styles.socialButton} ${styles.google}`}
                            >
                                Google
                            </button>
                            <button
                                type="button"
                                className={`${styles.socialButton} ${styles.facebook}`}
                            >
                                Facebook
                            </button>
                            <button
                                type="button"
                                className={`${styles.socialButton} ${styles.github}`}
                            >
                                GitHub
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegisterPage;
