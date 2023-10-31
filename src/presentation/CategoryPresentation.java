package presentation;

import bussiness.CategoryBussiness;
import entity.Book;
import entity.Category;

import java.util.List;
import java.util.Scanner;

public class CategoryPresentation {
    public static void displayCategory(Scanner scanner, List<Category> listCategory, List<Book> listBook) {
        boolean isExit = true;
        do {
            String line = "\u2500".repeat(70); // Tạo một dòng ngang dài 48 ký tự

            System.out.println(line);
            System.out.println("\t\t\t\t\t\t\tMENU\t\t\t\t\t\t\t");
            System.out.println(line);
            System.out.println("|\t1.\t|\t\tThêm mới thể loại\t\t");
            System.out.println(line);
            System.out.println("|\t2.\t|\t\tHiển thị danh sách theo tên A – Z\t");
            System.out.println(line);
            System.out.println("|\t3.\t|\t\tThống kê thể loại và số sách có trong mỗi thể loại\t");
            System.out.println(line);
            System.out.println("|\t4.\t|\t\tCập nhật thể loại\t\t");
            System.out.println(line);
            System.out.println("|\t5.\t|\t\tXóa thể loại\t\t\t");
            System.out.println(line);
            System.out.println("|\t6.\t|\t\tQuay lại\t\t\t");
            System.out.println(line);
            System.out.print("\t\tSự lựa chọn của bạn:\t\t");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    CategoryBussiness.inputListCategory(scanner, listCategory);
                    break;
                case 2:
                    CategoryBussiness.displayListCategory(listCategory);
                    break;
                case 3:
                    CategoryBussiness.categoryStatistics(listCategory, listBook);
                    break;
                case 4:
                    boolean result = CategoryBussiness.updateCategory(scanner, listCategory);
                    if (result) {
                        System.out.println("Cập nhật thành công");
                    }
                    break;
                case 5:
                    CategoryBussiness.deleteCatagory(scanner, listCategory, listBook);
                    break;
                case 6:
                    CategoryBussiness.writeCategoryToFile(listCategory);
                    isExit = false;
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1-6");
            }
        } while (isExit);
    }
}
