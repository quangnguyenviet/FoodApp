# 🍔 FoodApp - Nền Tảng Đặt Hàng Thức Ăn Trực Tuyến

Đây là một nền tảng đặt hàng thức ăn trực tuyến toàn diện cho phép **Khách hàng** dễ dàng duyệt, chọn và thanh toán các món ăn yêu thích, trong khi **Nhà hàng** có thể quản lý menu, đơn hàng và theo dõi doanh số bán hàng. Dự án được xây dựng với kiến trúc mạnh mẽ, đảm bảo tính mở rộng và hiệu năng cao.

---

## 📋 Mục Lục

- [Tính Năng Chính](#-tính-năng-chính)
- [Công Nghệ Sử Dụng](#️-công-nghệ-sử-dụng)
- [Cấu Trúc Dự Án](#-cấu-trúc-dự-án)
- [Hướng Dẫn Cài Đặt](#-hướng-dẫn-cài-đặt)
- [Lộ Trình Phát Triển](#-lộ-trình-phát-triển)

---

## 📸 Demo & Giao Diện

### 👨‍💼 Giao Diện Nhà Hàng (Restaurant)

#### Dashboard
*Dashboard quản lý với thống kê đơn hàng, doanh thu và số lượng khách hàng*

#### Quản Lý Menu
*Tạo, chỉnh sửa và xóa các món ăn, quản lý giá cả và tính khả dụng*

#### Quản Lý Đơn Hàng
*Theo dõi các đơn hàng mới, xử lý và cập nhật trạng thái giao hàng*

#### Thống Kê Bán Hàng
*Xem báo cáo chi tiết về doanh thu, sản phẩm bán chạy và khách hàng*

---

### 👤 Giao Diện Khách Hàng (Customer)

#### Trang Chủ
*Khám phá danh sách các nhà hàng và xu hướng món ăn*

#### Danh Sách Nhà Hàng
*Tìm kiếm nhà hàng theo danh mục, đánh giá và khoảng cách*

#### Menu Chi Tiết
*Xem menu đầy đủ của nhà hàng, tùy chỉnh đơn hàng*

#### Giỏ Hàng & Thanh Toán
*Quản lý giỏ hàng, áp dụng mã giảm giá và thanh toán an toàn*

#### Lịch Sử Đơn Hàng
*Theo dõi các đơn hàng đã đặt, xem chi tiết và đánh giá*

---

## 🚀 Tính Năng Chính

### 👨‍💼 Dành cho Nhà Hàng (Restaurant)

- ✅ **Quản lý menu**: Tạo mới, chỉnh sửa và xóa các món ăn với hình ảnh, mô tả và giá cả
- ✅ **Quản lý danh mục**: Tổ chức menu theo danh mục khác nhau (khai vị, chính, tráng miệng, v.v.)
- ✅ **Xử lý đơn hàng**: Nhận, xác nhận, chuẩn bị và giao hàng thực tế
- ✅ **Dashboard thống kê**: Theo dõi doanh số bán hàng, đơn hàng hôm nay và sản phẩm bán chạy
- ✅ **Quản lý khuyến mãi**: Tạo mã giảm giá và chiến dịch khuyến mãi
- ✅ **Báo cáo chi tiết**: Phân tích doanh thu, xu hướng khách hàng và hiệu suất

### 👤 Dành cho Khách Hàng (Customer)

- ✅ **Khám phá nhà hàng**: Tìm kiếm thông minh theo danh mục, đánh giá và vị trí
- ✅ **Duyệt menu**: Xem chi tiết món ăn với hình ảnh, thành phần và đánh giá khách hàng
- ✅ **Tùy chỉnh đơn hàng**: Thêm ghi chú đặc biệt, chọn kích thước và phần ăn kèm
- ✅ **Thanh toán linh hoạt**: Hỗ trợ thanh toán qua Stripe, VNPay hoặc thanh toán khi nhận hàng
- ✅ **Theo dõi đơn hàng**: Cập nhật thời gian thực về trạng thái và vị trí giao hàng
- ✅ **Đánh giá & bình luận**: Chia sẻ trải nghiệm với nhà hàng và các khách hàng khác

### 🛡️ Hệ Thống & Bảo Mật

- ✅ **Xác thực người dùng**: Hệ thống đăng ký/đăng nhập bảo mật với OAuth 2.0
- ✅ **Spring Security**: Quản lý phân quyền giữa nhà hàng, khách hàng và quản trị viên
- ✅ **Mã hóa dữ liệu**: Bảo vệ thông tin nhạy cảm của người dùng
- ✅ **Quản lý phiên**: Xử lý phiên làm việc an toàn và timeout tự động

---

## 🛠️ Công Nghệ Sử Dụng

### Backend
- **Framework**: Spring Boot 3.4+ (Java 21)
- **Security**: Spring Security & OAuth 2.0
- **Database**: MySQL/PostgreSQL
- **ORM**: Spring Data JPA
- **Email**: Spring Mail (gửi xác nhận đơn hàng)
- **API**: RESTful API
- **Build Tool**: Maven

### Frontend
- **Framework**: React.js 19
- **Styling**: CSS3, SCSS
- **HTTP Client**: Axios
- **Routing**: React Router v7
- **Charts**: Chart.js (thống kê bán hàng)
- **Icons**: Font Awesome
- **UI Components**: Tùy chỉnh CSS

### Third-party Services
- **Payment Gateway**: Stripe & VNPay
- **Authentication**: OAuth 2.0 Provider
- **Email Service**: SMTP Server

---

## 📁 Cấu Trúc Dự Án

```
FoodApp/
├── FoodAppServer/
│   └── FoodDrink/                   # Spring Boot Backend
│       ├── src/
│       │   └── main/
│       │       ├── java/
│       │       │   └── com/example/
│       │       │       ├── controller/
│       │       │       ├── service/
│       │       │       ├── repository/
│       │       │       ├── model/
│       │       │       ├── config/
│       │       │       └── utils/
│       │       └── resources/
│       ├── pom.xml
│       └── README.md
│
├── food-react/                      # React Frontend
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── styles/
│   │   ├── App.js
│   │   └── index.js
│   ├── public/
│   ├── package.json
│   └── README.md
│
├── FoodAppClient/                   # Client liên quan (nếu có)
│
└── README.md                        # File này
```

---

## 💻 Hướng Dẫn Cài Đặt

### Yêu Cầu Hệ Thống

**Backend:**
- Java JDK 21 trở lên
- Maven 3.6+
- MySQL 8.0+ hoặc PostgreSQL 13+
- IDE: IntelliJ IDEA / Eclipse

**Frontend:**
- Node.js >= 16.x
- npm >= 8.x hoặc yarn

### Cài Đặt Backend

1. **Điều hướng đến thư mục backend:**
   ```bash
   cd FoodAppServer/FoodDrink
   ```

2. **Cấu hình cơ sở dữ liệu** trong `src/main/resources/application.yml`:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/foodapp
       username: root
       password: your_password
     jpa:
       hibernate:
         ddl-auto: update
   ```

3. **Cài đặt dependencies và chạy:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   Backend sẽ chạy tại: `http://localhost:8080`

### Cài Đặt Frontend

1. **Điều hướng đến thư mục frontend:**
   ```bash
   cd food-react
   ```

2. **Cài đặt dependencies:**
   ```bash
   npm install
   ```

3. **Tạo file `.env` (nếu cần):**
   ```env
   REACT_APP_API_URL=http://localhost:8080
   REACT_APP_STRIPE_KEY=your_stripe_public_key
   ```

4. **Chạy ứng dụng:**
   ```bash
   npm start
   ```

   Frontend sẽ chạy tại: `http://localhost:3000`

### Cấu Hình Thanh Toán

#### Stripe (nếu sử dụng)
1. Đăng ký tài khoản tại [stripe.com](https://stripe.com)
2. Lấy Public Key và Secret Key
3. Cấu hình trong backend `application.yml`

#### VNPay (nếu sử dụng)
1. Đăng ký tài khoản merchant tại VNPay
2. Cấu hình trong backend properties

---

## 📅 Lộ Trình Phát Triển

- [ ] Hoàn thiện hệ thống đánh giá và bình luận chi tiết
- [ ] Tích hợp thông báo thời gian thực qua WebSocket
- [ ] Phát triển ứng dụng mobile (React Native hoặc Flutter)
- [ ] Thêm tính năng gợi ý cá nhân hóa dựa trên AI
- [ ] Hỗ trợ đặt hàng lặp lại (recurring orders)
- [ ] Tích hợp hệ thống giao hàng tự động

---

## 🐛 Troubleshooting

### Backend không kết nối cơ sở dữ liệu
- Kiểm tra MySQL/PostgreSQL đang chạy
- Xác thực tên database, username, password
- Kiểm tra port mặc định (3306 cho MySQL)

### Frontend không tải
- Xóa folder `node_modules` và `package-lock.json`, rồi `npm install` lại
- Kiểm tra backend API đang chạy
- Xem console browser để xem lỗi chi tiết

### Lỗi thanh toán
- Xác thực Stripe/VNPay credentials
- Kiểm tra HTTPS được bật (bắt buộc cho Stripe)

---

## 📞 Hỗ Trợ

Nếu gặp vấn đề:
1. Kiểm tra logs trong console/terminal
2. Xem phần Troubleshooting ở trên
3. Kiểm tra tệp README.md trong thư mục tương ứng

---

## 📄 License

Dự án này được phát triển cho mục đích học tập và nghiên cứu.

---

**Chúc bạn cài đặt và sử dụng thành công! 🎉**
