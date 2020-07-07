import models.LaptopEntity;
import services.LaptopService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/store_cms_plusplus", "root", "nnhien166@@");
            System.out.println("SQL Connection to database established!");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập lựa chọn:");
        System.out.println("1. Tìm kiếm latop theo hãng");
        System.out.println("2. Tìm kiếm theo giá");
        System.out.println("3. Tìm kiếm theo ổ cứng và hãng");
        System.out.println("4. Tùy chọn tìm kiếm");
        System.out.println("5. Danh sách laptop bán chạy nhất");
        LaptopService laptopService = new LaptopService( connection);
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                System.out.println("Nhập vào hãng:");
                String maker = scanner.nextLine();
                laptopService.findAllByMaker(maker.trim().toUpperCase());
                break;
            case 2:
                List<LaptopEntity> laptopEntities1;
                Long min = null;
                Long max = null;
                System.out.println("Nhập sự lựa chọn :");
                System.out.println("1.Tìm giá min");
                System.out.println("2.Tìm giá max");
                System.out.println("3.Tìm giá Từ ... đến ... :");
                int ot=scanner.nextInt();
                scanner.nextLine();
                switch (ot){
                    case 1:
                        System.out.println("Nhập price min :");
                        min = scanner.nextLong();
                        laptopService.findByPrice(min,null);
                        break;
                    case 2:
                        System.out.println("Nhập price max :");
                        max = scanner.nextLong();
                        laptopService.findByPrice(null,max);
                        break;
                    case 3:
                        System.out.println("Nhập price from...to... :");
                        System.out.println("Nhập price thứ 1 : ");
                        min = scanner.nextLong();
                        System.out.println("Nhập price thứ 2 : ");
                        max = scanner.nextLong();
                        laptopService.findByPrice(min,max);
                }
                break;
            case 3:
                Integer hdd,ssd = null;
                String makers;
                List<LaptopEntity> laptopEntities2;
                System.out.println("Mời bạn nhập lựa chọn :");
                System.out.println("1.Tìm theo HDD và hãng sản xuất");
                System.out.println("2.Tìm theo SSD và hãng sản xuất");
                int i = scanner.nextInt();
                scanner.nextLine();
                switch (i){
                    case 1:
                        System.out.println("Mời bạn nhập dung lượng HDD cần tìm : ");
                        hdd = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Mời bạn nhập hãng sản xuất cần tìm : ");
                        makers = scanner.nextLine();
                        laptopService.findByHddAndName(hdd,null,makers);
                        break;
                    case 2:
                        System.out.println("Mời bạn nhập dung lượng SDD cần tìm : ");
                        ssd = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Mời bạn nhập hãng sản xuất cần tìm : ");
                        makers = scanner.nextLine();
                        laptopService.findByHddAndName(null,ssd,makers);
                        break;
                }
            case 4:
                laptopService.findByUserInput(null,null,null,null,"4","intel","vivo",null,"asc");
                break;
            case 5:
                laptopService.orderBySold();
                break;
                }
        }
}
