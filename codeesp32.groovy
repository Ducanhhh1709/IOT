#include <WiFi.h>
#include <BlynkSimpleEsp32.h>

// Thông tin Blynk
char auth[] = "Your_Blynk_Auth_Token";  // Thay thế với Auth Token của bạn
char ssid[] = "Your_SSID";  // Thay thế với tên mạng WiFi của bạn
char pass[] = "Your_WiFi_Password";  // Thay thế với mật khẩu WiFi của bạn

// Định nghĩa chân kết nối PIR sensor và LED
int pirPin = 13;   // Cảm biến PIR nối với GPIO 13
int ledPin = 14;   // LED nối với GPIO 14

BlynkTimer timer;

void setup() {
  // Khởi tạo kết nối Serial
  Serial.begin(115200);
  
  // Kết nối WiFi và Blynk
  Blynk.begin(auth, ssid, pass);

  // Định nghĩa chế độ cho các chân
  pinMode(pirPin, INPUT);  // Cảm biến PIR là đầu vào
  pinMode(ledPin, OUTPUT); // LED là đầu ra
  
  // Cập nhật thông tin PIR mỗi giây
  timer.setInterval(1000L, checkPIR);
}

void loop() {
  // Thực hiện các công việc của Blynk
  Blynk.run();
  
  // Chạy các timer đã cài đặt
  timer.run();
}

void checkPIR() {
  int pirStatus = digitalRead(pirPin);  // Đọc giá trị cảm biến PIR

  // Nếu có chuyển động, bật LED
  if (pirStatus == HIGH) {
    digitalWrite(ledPin, HIGH);  // Bật LED
  } else {
    digitalWrite(ledPin, LOW);   // Tắt LED
  }

  // Gửi trạng thái PIR lên ứng dụng Blynk
  Blynk.virtualWrite(V1, pirStatus);  // Gửi giá trị PIR tới Virtual Pin V1
}

// Điều khiển LED từ ứng dụng Blynk (Button Widget)
BLYNK_WRITE(V0) {
  int pinValue = param.asInt();  // Lấy giá trị từ Button Widget
  if (pinValue == 1) {
    digitalWrite(ledPin, HIGH);  // Bật LED khi nhấn nút
  } else {
    digitalWrite(ledPin, LOW);   // Tắt LED khi thả nút
  }
}