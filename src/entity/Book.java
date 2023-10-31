package entity;

import bussiness.IEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Book implements IEntity<Book>, Serializable {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String description;
    private int categoryID;

    public Book() {
    }

    public Book(String id, String title, String author, String publisher, int year, String description, int categoryID) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.categoryID = categoryID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    // Nhập dữ liệu sách_____________________________________________________________________________
    public void inputData(Scanner scanner, List<Book> listBook, List<Category> listCategory) {
//    Nhập mã sách****************************************************************************
        System.out.println("Nhập mã sách");
        boolean isExit = true;
        do {
            this.id = scanner.nextLine();
            if (this.id.isEmpty()) {
                System.out.println("ID sách không được để trống");
            } else {
                if (this.id.startsWith("B")) {
                    if (this.id.length() == 4) {
                        boolean isExist = false;
                        for (Book book : listBook) {
                            if (book.getId().equals(this.id)) {
                                isExist = true;
                                break;
                            }
                        }
                        if (isExist) {
                            System.out.println("Mã sinh viên tồn tại, nhập lại");
                        } else {
                            break;
                        }
                    } else {
                        System.out.println("Id 4 kí tự. Nhập lại");
                    }
                } else {
                    System.out.println("Mã sách phải bắt đ bằng B. Nhập lại");
                }
            }
        } while (isExit);
//        Nhập tiêu đề****************************************************************************
        System.out.println("Nhập tiêu đề sách");
        do {
            this.title = scanner.nextLine();
            if (this.title.isEmpty()) {
                System.out.println("Tiêu đề không được để trống");
            } else {
                if (this.title.length() >= 6 && this.title.length() <= 50) {
                    boolean isExist = false;
                    for (Book book : listBook) {
                        if (book.getTitle().equals(this.title)){
                            isExist = true;
                            break;
                        }
                    }
                    if (isExist){
                        System.out.println("Tiêu đề sách trùng lặp, nhập lại");
                    }else {
                        break;
                    }
                } else {
                    System.out.println("Tiêu đề sách từ 6-50 kí tự");
                }
            }
        } while (isExit);

//        Tên tác giẩ ****************************************************************************
        System.out.println("Nhập tên tác giả");
        do {
            this.author = scanner.nextLine();
            if (this.title.isEmpty()){
                System.out.println("Tên tác giả không được bỏ trống");
            }else {
                break;
            }
        }while (isExit);

//        Nhà xuất bản****************************************************************************
        System.out.println("Nhập thông tin nhà xuất bản");
        do {
            this.publisher = scanner.nextLine();
            if (this.publisher.isEmpty()){
                System.out.println("Tên nhà xuất bản không được bỏ trống");
            }else {
                break;
            }
        }while (isExit);

//        Năm xuâ bản****************************************************************************
        System.out.println("Nhập năm xuất bản");
        do {
            this.year = Integer.parseInt(scanner.nextLine());
            if (this.year>=1970 && this.year <=2023){
                break;
            }else {
                System.out.println("Chỉ những sách xuất bản sau năm 1970 và trước 2023");
            }
        }while (isExit);

//        Nhập mô tả sach
        System.out.println("Nhập mô tả sách");
        do {
            this.description = scanner.nextLine();
            if (this.description.isEmpty()){
                System.out.println("Mô tả sách không được bỏ trống");
            }else {
                break;
            }
        }while (isExit);

//        Lấy mã thể loại sách

        System.out.println("Lựa chọn thể loại sách");
        do {
            String line3 = "\u2500".repeat(40);
            System.out.println(line3);
            System.out.printf("|%-5s|%-15s|%-15s|\n","STT","ID","Tên thể loại");
            System.out.println(line3);
            for (int i = 0; i < listCategory.size(); i++) {
                System.out.printf("|%-5d|%-15d|%-15s|\n",(i+1),listCategory.get(i).getId(),listCategory.get(i).getName());
                System.out.println(line3);
            }
            System.out.println("Lựa chọn của bạn");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice<1 || choice>listCategory.size()){
                System.out.println("Không tồn tại danh mục, vui lòng chọn lại");
            }else{
                this.categoryID = listCategory.get(choice-1).getId();
                break;
            }
        }while (isExit);
    }

    // Hiển thị sach____________________________________________________________________________________
    @Override
    public void output() {
// In danh sách sách
        System.out.printf("|%-5s|    %-15s|    %-15s|    %-15s|    %-15d|    %-15s|%-10d|\n",this.id,this.title,this.author,this.publisher,
                this.year,this.description,this.categoryID);
    }
}
