# IOT
Nhà thông minh điều khiển bằng giọng nói
Tài liệu Đặc tả Kỹ thuật (SRS) 
Dự án Nhà Thông Minh Điều Khiển Bằng Giọng Nói Qua Google Assistant
1. Giới thiệu chung
1.1 Mục đích
Dự án nhà thông minh nhằm tạo ra hệ thống điều khiển thiết bị trong nhà (bóng đèn, quạt, rèm cửa, ...) bằng giọng nói, tích hợp với Google Assistant. Hệ thống giúp tăng tính tiện lợi, tiết kiệm thời gian và nâng cao chất lượng cuộc sống.
1.2 Phạm vi
Tích hợp các thiết bị như bóng đèn, quạt, rèm cửa, các thiết bị điện gia dụng.
Hỗ trợ tương thích với Google Assistant.
Cung cấp giao diện quản lý và cấu hình qua ứng dụng di động.
1.3 Đối tượng sử dụng
Gia đình, cá nhân mong muốn tự động hoá công việc nhà.
Các công ty cài đặt hệ thống nhà thông minh.
2. Chức năng hệ thống
2.1 Chức năng chính
Điều khiển thiết bị qua giọng nói.
Theo dõi trạng thái thiết bị trong thời gian thực.
Cài đặt lịch hoạt động cho thiết bị.
Tích hợp với Google Assistant và hệ thống IoT.
2.2 Chức năng bổ sung
Hỗ trợ quản lý qua ứng dụng di động.
Thông báo sự cố hoặc thiết bị hỏng.
3. Yêu cầu hệ thống
3.1 Yêu cầu chức năng
Nhận diện và xử lý giọng nói tiếng Việt.
Có khả năng kết nối nhiều thiết bị khác nhau qua Wi-Fi hoặc Zigbee.
3.2 Yêu cầu phi chức năng
Giao diện thân thiện, dễ sử dụng.
Bảo mật dữ liệu cá nhân.
Hệ thống độ tin cậy cao, không gây gián đoạn khi hoạt động.
4. Kiến trúc hệ thống
4.1 Sơ đồ tổng quan
Hệ thống bao gồm:
Google Assistant (điều khiển giọng nói).
Ứng dụng di động (để cấu hình và theo dõi).
Gateway trung tâm kết nối các thiết bị.
Các thiết bị IoT (bóng đèn, cảm biến, ...).
4.2 Mô tả chi tiết
Google Assistant: Nhận lệnh giọng nói và truyền lệnh qua API.
Ứng dụng di động: Giao diện quản lý thiết bị, hiển thị trạng thái và cài đặt.
Gateway trung tâm: Quản lý giao tiếp giữa các thiết bị và Google Assistant.
Các thiết bị IoT: Bóng đèn thông minh, cảm biến chuyển động, rèm cửa tự động, ...
5. Kế hoạch thực hiện
5.1 Mốc tiêu cụ thể
Hoàn thành thiết kế hệ thống: 1 tháng.
Phát triển và tích hợp các thiết bị: 2 tháng.
Kiểm tra và chạy thử: 1 tháng.
5.2 Rủi ro dự kiến
Lỗi kết nối giữa các thiết bị.
Khó khăn trong nhận diện giọng nói.
Trì hoãn do các yêu cầu thay đổi.
6. Tài liệu tham khảo
Tài liệu API Google Assistant.
Tài liệu về các giao thức kết nối IoT.
Hướng dẫn thiết kế giao diện trên các nền tảng di động.
