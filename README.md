# project_crm

Dự án CRM
Học viên: Lê Thị Mỹ Linh
Lớp Java21
Công nghệ sử dụng: Servlet, JSP
Link github: https://github.com/xiucaliu/project_crm 
Link video dự án: https://www.youtube.com/watch?v=26kO5onFDMM&t=4s
Mô tả về dự án:
Chức năng: thêm, sửa, xoá, xem:
    user - người dùng
    role - quyền
    task – công việc
    job - dự án
Filter:
  	LoginFilter là class để kiểm tra user đã đăng nhập chưa, nếu chưa đăng nhập thì dẫn đến trang login
    Class FilterAdminOnly để quy định các đường dẫn chỉ cho phép admin truy cập
    UserListFilter dùng để quy định các quyền truy cập liên quan đến đường dẫn “/user/*”
    AuthenticationFiltẻ dùng để quy định các quyền truy cập liên quan đến đường dẫn “/job/*” và “/task/*”

Đã làm được:
    Dùng session (sessionRemember) để lưu tài khoản khi đăng nhập    
    Dùng session (sessionUser) để lưu hình profile, để hình đại diện của người dùng đang truy cập !
    Khi update một đối tượng, đã hiển thị được các giá trị hiện tại của đối tượng

Hạn chế muốn cải thiện: 
    Sau khi đã update, mặc dù đã update thành công databe, giá trị được hiển thị trên trang vẫn chưa được thay đổi trên trang update đó 
    Khi up ảnh lên, chỉ chọn được các ảnh đã có sẵn trong file project, chưa up được các ảnh trên máy tính bên ngoài file

![image](https://github.com/xiucaliu/project_crm/assets/66474363/556dfa4b-72e4-45d0-9da2-676db47e803f)
