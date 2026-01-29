# Task Management API Design

Hệ thống quản lý Task (công việc) và User (người dùng). Mỗi công việc thuộc về một người dùng, một người dùng có thể có nhiều công việc.

Hệ thống cung cấp các endpoint RESTful sau:

GET /tasks  
Lấy toàn bộ danh sách công việc.

GET /users  
Lấy toàn bộ danh sách người dùng.

POST /tasks  
Tạo mới một công việc.  
Body:
{
"title": "Làm báo cáo",
"priority": "high",
"status": "todo"
}

POST /users  
Tạo mới một người dùng.  
Body:
{
"name": "Nguyen Van A",
"role": "USER"
}

PUT /tasks/{taskId}/status  
Cập nhật trạng thái của một công việc.  
Body:
{
"status": "done"
}

PUT /users/{userId}/role  
Cập nhật vai trò của một người dùng.  
Body:
{
"role": "ADMIN"
}

DELETE /tasks/{taskId}  
Xóa một công việc khỏi hệ thống.

DELETE /users/{userId}  
Xóa một người dùng khỏi hệ thống.

GET /tasks?priority=high  
Tìm tất cả các công việc có mức độ ưu tiên là "high".

GET /users/1/tasks?priority=high  
Tìm các công việc có độ ưu tiên là "high" và được giao cho người dùng có id = 1.

GET /users/{userId}/tasks  
Liệt kê toàn bộ công việc của một người dùng.

PUT /users/{userId}/tasks/{taskId}  
Gắn một công việc cho người dùng.

Thiết kế API tuân theo chuẩn REST:
- Endpoint dùng danh từ số nhiều
- Không dùng động từ trong URL
- HTTP Method thể hiện hành động
- Quan hệ được biểu diễn bằng nested resource

Quan hệ dữ liệu:
User 1 --- N Task
