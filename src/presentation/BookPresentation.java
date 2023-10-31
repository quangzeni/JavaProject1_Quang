package presentation;

import bussiness.BookBussiness;
import bussiness.CategoryBussiness;
import entity.Book;
import entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookPresentation {
    public static void displayBook(Scanner scanner, List<Category> listCategory, List<Book> listBook) {

        boolean isExit = true;
        do {
            String line = "\u2500".repeat(70); // Tạo một dòng ngang dài 100 ký tự

            System.out.println(line);
            System.out.println("\t\t\t\t\t\t\t\tMENU\t\t\t\t\t\t\t\t");
            System.out.println(line);
            System.out.println("|\t1.\t|\t\tThêm mới sách\t\t\t\t\t\t   ");
            System.out.println(line);
            System.out.println("|\t2.\t|\t\tCập nhật\t\t\t\t\t\t\t    ");
            System.out.println(line);
            System.out.println("|\t3.\t|\t\tXóa\t\t\t\t\t\t        ");
            System.out.println(line);
            System.out.println("|\t4.\t|\t\tTìm kiếm sách\t\t\t\t\t     ");
            System.out.println(line);
            System.out.println("|\t5.\t|\t\tHiển thị danh sách sách theo nhóm thể loại        ");
            System.out.println(line);
            System.out.println("|\t6.\t|\t\tQuay lại\t\t\t\t\t\t       ");
            System.out.println(line);
            System.out.print("\t\t\tLựa chọn của bạn\t\t\t\t\t\t\t ");
            int choisce = Integer.parseInt(scanner.nextLine());
            switch (choisce) {
                case 1:
                    BookBussiness.inputListBook(scanner, listBook, listCategory);
                    break;
                case 2:
                    boolean result = BookBussiness.updateBook(scanner, listBook);
                    if (result) {
                        System.out.println("Cập nhật thành công");
                    }
                    break;
                case 3:
                    BookBussiness.deleteBook(scanner, listCategory, listBook);
                    break;
                case 4:
                    BookBussiness.searchBook(scanner, listBook);
                    break;
                case 5:
                    BookBussiness.displayListBook(listBook);
                    break;
                case 6:
                    BookBussiness.writeBookToFile(listBook);
                    isExit = false;
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1-6");
            }
        } while (isExit);
    }

}
