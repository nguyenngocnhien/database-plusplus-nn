import services.CarService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ApplicationCar {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thue_xe_cms","root","nnhien166@@");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println();
            System.out.println("Nhap su lua chon");
            System.out.println("0.Thoat Chuong Trinh");
            System.out.println("1. Liệt kê những dòng xe có số chỗ ngồi trên 5 chỗ");
            System.out.println("2. Liệt kê thông tin của các nhà cung cấp ĐÃ TỪNG đăng ký cung cấp những dòng xe thuộc hãng xe “Toyota” với mức phí có đơn giá là 15.000 VNĐ/km " +
                    "\nhoặc những dòng xe thuộc hãng xe “KIA” với mức phí có đơn giá là 20.000 VNĐ/km");
            System.out.println("3. Liệt kê thông tin toàn bộ nhà cung cấp được sắp xếp tăng dần theo tên nhà cung cấp và giảm dần theo mã số thuế");
            System.out.println("4. Đếm số lần đăng ký cung cấp phương tiện tương ứng cho từng nhà cung cấp với yêu cầu chỉ đếm cho những nhà cung cấp thực hiện đăng ký cung cấp có ngày bắt đầu\n" +
                    "cung cấp là “20/11/2015”");
            System.out.println("5. Liệt kê tên của toàn bộ các hãng xe có trong cơ sở dữ liệu với yêu cầu mỗi hãng xe\n" +
                    "chỉ được liệt kê một lần");
            System.out.println("6. Liệt kê MaDKCC, MaNhaCC, TenNhaCC, DiaChi, MaSoThue, TenLoaiDV, DonGia,HangXe, NgayBatDauCC, NgayKetThucCC của tất cả các lần đăng ký cung cấp phương\n" +
                    "tiện với yêu cầu những nhà cung cấp nào chưa từng thực hiện đăng ký cung cấp phương tiện thì cũng liệt kê thông tin những nhà cung cấp đó ra");
            System.out.println("7. Liệt kê thông tin của các nhà cung cấp đã từng đăng ký cung cấp phương tiện thuộc dòng xe “Hiace” hoặc từng đăng ký cung cấp phương tiện thuộc dòng xe “Cerato”");
            System.out.println("8. Liệt kê thông tin của các nhà cung cấp chưa từng thực hiện đăng ký cung cấp phương tiện lần nào cả.");
            int a = sc.nextInt();
            sc.nextLine();
            CarService carService = new CarService(connection);
            switch (a){
                case 0:return;
                case 1: carService.xe5Cho(); break;
                case 2: carService.thongTinNCC(); break;
                case 3: carService.sapXep(); break;
                case 4: carService.dangKyCC(); break;
                case 5: carService.hangXe(); break;
                case 6: carService.phuongtienDKCC(); break;
                case 7: carService.hiaceCeratoDKCC(); break;
                case 8: carService.nCCChuaDK(); break;
            }
        }
    }
}
