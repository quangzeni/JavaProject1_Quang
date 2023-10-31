package bussiness;

import entity.Book;
import entity.Category;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class BookBussiness {
    public static List<Book> readBookFromFile() {
        List<Book> listBook = null;
        File file = new File("Book.txt");
        if (file.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listBook = (List<Book>) ois.readObject();
            } catch (FileNotFoundException ex) {
                System.out.println("Không tìm thấy tập tin");
            } catch (IOException ex) {
                System.out.println("Lỗi khi đọc tập tin");
            } catch (ClassNotFoundException ex) {
                System.out.println("Lỗi khi chuyển đổi đối tượng");
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException ex) {
                    System.out.println("Lỗi khi đóng luồng đọc");
                }
            }
        }
        return listBook;
    }

    public static void writeBookToFile(List<Book> listBook) {
        File file = new File("Book.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listBook);
            oos.flush();
        } catch (FileNotFoundException ex) {
            System.err.println("Không tìm thấy tệp tin: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Lỗi khi ghi vào tệp tin: " + ex.getMessage());
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void inputListBook(Scanner scanner, List<Book> listBook, List<Category> listCategory) {
        System.out.println("Nhập số lượng sách muốn nhập");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Book book = new Book();
            book.inputData(scanner, listBook, listCategory);
            listBook.add(book);
        }
    }

    public static void displayListBook(List<Book> listBook) {
        String line2 = "\u2500".repeat(118);
        System.out.println(line2);
        System.out.printf("|%-5s|    %-15s|    %-15s|    %-15s|    %-15s|    %-15s|%-10s|\n", "ID", "Tiêu đề", "Tác giả", "NXB",
                "Năm Sx", "Mô tả", "Thể Loại");
        System.out.println(line2);
        for (Book book : listBook) {
            book.output();
            System.out.println(line2);
        }
    }

    public static boolean updateBook(Scanner scanner, List<Book> listBook) {
        System.out.println("Nhập vào mã sách cần cập nhật");
        String bookId = scanner.nextLine();
        int indexUpdate = getIndex(bookId, listBook);
        if (indexUpdate >= 0) {
            System.out.println("Nhập tên sách cần cập nhât");
            String bookTitle = scanner.nextLine();
            if (bookTitle != "" && bookTitle.trim().length() > 0) {
                boolean isExist = false;
                for (int i = 0; i < listBook.size(); i++) {
                    if (i != indexUpdate && listBook.get(i).getTitle().equals(bookTitle)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.out.println("Tiêu đề sách đã tồn tại");
                    return false;
                }
                listBook.get(indexUpdate).setTitle(bookTitle);
            }
//            Nhập tiếp các trường tác giả
            System.out.println("Nhập tên tác giả cần cập nhật");
            String author = scanner.nextLine();
            if (author != "" && author.trim().length() > 0) {
                listBook.get(indexUpdate).setAuthor(author);
            }

//            Nhập Nhà xuất bản
            System.out.println("Nhập tên nhà xuất bản cần cập nhật");
            String publisher = scanner.nextLine();
            if (publisher != "" && publisher.trim().length() > 0) {
                listBook.get(indexUpdate).setPublisher(publisher);
            }

//            Nhập năm xuất bản
            System.out.println("Nhập năm sản xuất cần cập nhật");
            String year = scanner.nextLine();
            if (year != "" && year.trim().length() > 0) {
                listBook.get(indexUpdate).setYear(Integer.parseInt(year));
            }
//            Nhập mô tả sách
            System.out.println("Nhập mô tả sách cần cập nhật");
            String description = scanner.nextLine();
            if (description != "" && description.trim().length() > 0) {
                listBook.get(indexUpdate).setDescription(description);
            }

        } else {
            System.out.println("Không tồn tại mã sách");
        }
        return true;
    }

    //            Xóa
    public static void deleteBook(Scanner scanner, List<Category> listCategory, List<Book> listBook) {
        System.out.println("Nhập vào mã sách cần xóa");
        String bookId = scanner.nextLine();
        int indexDelete = getIndex(bookId, listBook);
        if (indexDelete >= 0) {
            listBook.remove(indexDelete);
        } else {
            System.out.println("Mã danh mục không tồn tại");
        }
    }

    //Tìm kiếm
    public static void searchBook(Scanner scanner, List<Book> listBook) {
        System.out.println("Nhập vào từ khóa cần tìm kiếm");
        String searchValue = scanner.nextLine();
        String line2 = "\u2500".repeat(118);
        System.out.println(line2);
        System.out.printf("|%-5s|    %-15s|    %-15s|    %-15s|    %-15s|    %-15s|%-10s|\n", "ID", "Tiêu đề", "Tác giả", "NXB",
                "Năm Sx", "Mô tả", "Thể Loại");
        System.out.println(line2);
        for (Book book : listBook) {
            if (book.getTitle().toLowerCase().contains(searchValue.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(searchValue.toLowerCase()) ||
                    book.getPublisher().toLowerCase().contains(searchValue.toLowerCase())
            ) {
                book.output();
                System.out.println(line2);
            }
        }
    }

    //
    public static int getIndex(String bookId, List<Book> listBook) {
        for (int i = 0; i < listBook.size(); i++) {
            if (listBook.get(i).getId().equals(bookId)) {
                return i;
            }
        }
        return -1;
    }
}
