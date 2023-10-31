import bussiness.BookBussiness;
import bussiness.CategoryBussiness;
import entity.Book;
import entity.Category;
import presentation.BookPresentation;
import presentation.CategoryPresentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Category> listCategory = new ArrayList<>();
    public static List<Book> listBook = new ArrayList<>();
    public static void main(String[] args){
        List<Category> listCategoryRead = CategoryBussiness.readCategoryFromFile();
        listCategory = (listCategoryRead != null) ? listCategoryRead : new ArrayList<>();
        List<Book> listBookRead = BookBussiness.readBookFromFile();
        listBook = (listBookRead!=null)? listBookRead: new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*********QUẢN LÝ THƯ VIỆN**********");
            System.out.println("1. Quản lý Thể loại");
            System.out.println("2. Quản lý Sách");
            System.out.println("3. Thoát");
            System.out.println("Sự lựa chọn của bạn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    CategoryPresentation.displayCategory(scanner,listCategory,listBook);
                    break;
                case 2:
                    BookPresentation.displayBook(scanner,listCategory,listBook);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1-9");
            }
        }while (true);
    }
}
