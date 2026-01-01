import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ApiService from '../../../services/ApiService';
import { useError } from '../../common/ErrorDisplay';
import styles from './AdminUserRegistration.module.css';


const AdminUserRegistration = () => {

    // Use the error hook
    const { ErrorDisplay, showError } = useError();
    const navigate = useNavigate();


    const [formData, setFormData] = useState({
        name: '',
        email: '',
        password: '',
        phoneNumber: '',
        address: '',
        roles: []
    });

    const availableRoles = [
        { value: 'ADMIN', label: 'Administrator' },
        { value: 'CUSTOMER', label: 'Customer' },
        { value: 'DELIVERY', label: 'Delivery Personnel' }
    ];

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleRoleChange = (roleValue) => {
        setFormData(prev => {
            if (prev.roles.includes(roleValue)) {
                return { ...prev, roles: prev.roles.filter(r => r !== roleValue) };
            } else {
                return { ...prev, roles: [...prev.roles, roleValue] };
            }
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!formData.name || !formData.email || !formData.password || 
            !formData.phoneNumber || !formData.address) {
            showError('All fields except roles are required');
            return;
        }

        if (formData.roles.length === 0) {
            showError('Please select at least one role');
            return;
        }

        try {
            const response = await ApiService.registerUser(formData);
            if (response.statusCode === 200) {
                setFormData({
                    name: '', email: '', password: '', 
                    phoneNumber: '', address: '', roles: []
                });
                navigate('/admin'); // Redirect to admin page
            } else {
                showError(response.message);
            }
        } catch (error) {
            showError(error.response?.data?.message || error.message || 'Registration failed');
        }
    };

    return (
        <div className={styles.page}>
            <div className={styles.card}>
                <div className={styles.header}>
                    <h2 className={styles.title}>Register New User</h2>
                    <p className={styles.description}>Create a new user account with specific roles</p>
                </div>

                <div className={styles.content}>
                    <form onSubmit={handleSubmit}>
                        <div className={styles.formGroup}>
                            <label className={styles.label}>Full Name</label>
                            <input
                                name="name"
                                type="text"
                                value={formData.name}
                                onChange={handleChange}
                                required
                                placeholder="User's full name"
                                className={styles.input}
                            />
                        </div>

                        <div className={styles.formGroup}>
                            <label className={styles.label}>Email</label>
                            <input
                                name="email"
                                type="email"
                                value={formData.email}
                                onChange={handleChange}
                                required
                                placeholder="User's email"
                                className={styles.input}
                            />
                        </div>

                        <div className={styles.formGroup}>
                            <label className={styles.label}>Password</label>
                            <input
                                name="password"
                                type="password"
                                value={formData.password}
                                onChange={handleChange}
                                required
                                placeholder="Create password"
                                className={styles.input}
                            />
                        </div>

                        <div className={styles.formGroup}>
                            <label className={styles.label}>Phone Number</label>
                            <input
                                name="phoneNumber"
                                type="text"
                                value={formData.phoneNumber}
                                onChange={handleChange}
                                required
                                placeholder="Phone number"
                                className={styles.input}
                            />
                        </div>

                        <div className={styles.formGroup}>
                            <label className={styles.label}>Address</label>
                            <input
                                name="address"
                                type="text"
                                value={formData.address}
                                onChange={handleChange}
                                required
                                placeholder="Full address"
                                className={styles.input}
                            />
                        </div>

                        <div className={styles.formGroup}>
                            <label className={styles.label}>Roles</label>
                            <div className={styles.roles}>
                                {availableRoles.map(role => (
                                    <div 
                                        key={role.value} 
                                        className={`${styles.role} ${formData.roles.includes(role.value) ? styles.roleSelected : ''}`}
                                        onClick={() => handleRoleChange(role.value)}
                                    >
                                        <input
                                            type="checkbox"
                                            checked={formData.roles.includes(role.value)}
                                            readOnly
                                            className={styles.roleInput}
                                        />
                                        <span>{role.label}</span>
                                    </div>
                                ))}
                            </div>
                        </div>

                        <ErrorDisplay />

                        <button type="submit" className={styles.submit}>
                            Register User
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default AdminUserRegistration;