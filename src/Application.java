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
        LaptopService laptopService = new LaptopService( connection);

        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                System.out.println("Nhập vào hãng:");
                String maker = scanner.nextLine();
                List<LaptopEntity> laptopEntities = laptopService.findAllByMaker(maker.trim().toUpperCase());
                if (laptopEntities.isEmpty()){
                    System.out.println("Không có sản phẩm phù hợp");
                }else {
                    for (LaptopEntity le:laptopEntities
                    ) {
                        System.out.println(le.getName()+"  ||  "+le.getMaker()+"  ||  "+le.getType()+"  ||  "+le.getCard());
                    }
                }
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
                        laptopEntities1 = laptopService.findByPrice(min,null);
                        for (LaptopEntity l:laptopEntities1
                        ) {
                            System.out.println(l.getName() +"      Price = "+ l.getPrice());
                        }
                        break;
                    case 2:
                        System.out.println("Nhập price max :");
                        max = scanner.nextLong();
                        laptopEntities1 = laptopService.findByPrice(null,max);
                        for (LaptopEntity l:laptopEntities1
                        ) {
                            System.out.println(l.getName() +"      Price = "+ l.getPrice());
                        }
                        break;
                    case 3:
                        System.out.println("Nhập price from...to... :");
                        System.out.println("Nhập price thứ 1 : ");
                        min = scanner.nextLong();
                        System.out.println("Nhập price thứ 2 : ");
                        max = scanner.nextLong();
                        laptopEntities1 = laptopService.findByPrice(min,max);
                        if (laptopEntities1.isEmpty()){
                            System.out.println("Không tìm thấy sản phẩm phù hợp");
                        }else{
                            for (LaptopEntity l:laptopEntities1
                            ) {
                                System.out.println(l.getName() +"      Price = "+ l.getPrice());
                            }
                        }
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
                        laptopEntities2 = laptopService.findByHddAndName(hdd,null,makers);
                        if (laptopEntities2.isEmpty()){
                            System.out.println("Không tìm thấy sản phẩm phù hợp");
                        }else{
                            for (LaptopEntity l:laptopEntities2
                            ) {
                                System.out.println(l.getName() +"      HDD = "+ l.getHdd());
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Mời bạn nhập dung lượng SDD cần tìm : ");
                        ssd = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Mời bạn nhập hãng sản xuất cần tìm : ");
                        makers = scanner.nextLine();
                        laptopEntities2 = laptopService.findByHddAndName(null,ssd,makers);
                        if (laptopEntities2.isEmpty()){
                            System.out.println("Không tìm thấy sản phẩm phù hợp");
                        }else{
                            for (LaptopEntity l:laptopEntities2
                            ) {
                                System.out.println(l.getName() +"      SDD = "+ l.getSsd());
                            }
                        }
                        break;
                }
            case 4:
        }
}
}
