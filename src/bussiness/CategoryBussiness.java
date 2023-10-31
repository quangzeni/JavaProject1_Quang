package bussiness;

import entity.Book;
import entity.Category;

import java.io.*;
import java.util.*;

public class CategoryBussiness {
    public static List<Category> readCategoryFromFile() {
        List<Category> listCategory = null;
        File file = new File("Categories.txt");
        if (file.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listCategory = (List<Category>) ois.readObject();
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
        return listCategory;
    }

    public static void writeCategoryToFile(List<Category> listCategory) {
        File file = new File("Categories.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listCategory);
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

    public static void inputListCategory(Scanner scanner, List<Category> listCategory) {
        System.out.println("Nhập vào số lượng thể loại muốn nhập");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Category category = new Category();
            category.inputData(scanner, listCategory);
            listCategory.add(category);
        }
    }

    public static void displayListCategory(List<Category> listCategory) {
        sortCategoryNameAToZ(listCategory);
        String line = "\u2500".repeat(44);
        System.out.println(line);
        System.out.printf("| %-5s|  %-15s|  %-15s|\n","ID","Thể loại","Trạng thái");
        for (Category category : listCategory) {
            System.out.println(line);
            category.output();
        }
        System.out.println(line);
    }

    public static boolean updateCategory(Scanner scanner, List<Category> listCategory) {
        System.out.println("Nhập vào mã danh mục cần cập nhật");
        int catagoryId = Integer.parseInt(scanner.nextLine());
        int indexUpdate = getIndex(catagoryId, listCategory);
        if (indexUpdate >= 0) {
            System.out.println("Nhập tên danh mục cần cập nhật");
            String catagoryName = scanner.nextLine();
            if (catagoryName != "" && catagoryName.trim().length() > 0) {
                boolean isExist = false;
                for (int i = 0; i < listCategory.size(); i++) {
                    if (i != indexUpdate && listCategory.get(i).getName().equals(catagoryName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.out.println("Tên danh mục đã tôn tại");
                    return false;
                }
                listCategory.get(indexUpdate).setName(catagoryName);
            }
            System.out.println("Nhập vào trạng thi danh mục cần cập nhật");
            String status = scanner.nextLine();
            if (status != "" && status.trim().length() > 0) {
                listCategory.get(indexUpdate).setStatus(Boolean.parseBoolean(status));
            }
        } else {
            System.out.println("Không tồn tại mã danh mục");
            return false;
        }
        return true;
    }

    public static void deleteCatagory(Scanner scanner, List<Category> listCategory, List<Book> listBook) {
        System.out.println("Nhập vào mã danh mục cần xóa");
        int catagoryId = Integer.parseInt(scanner.nextLine());
        int indexDelete = getIndex(catagoryId, listCategory);
        if (indexDelete >= 0) {
            boolean isContains = false;
            for (Book book : listBook) {
                if (book.getCategoryID() == catagoryId) {
                    isContains = true;
                }
            }
            if (isContains) {
                System.out.println("Danh mục đã có sách, không thể xóa");
            } else {
                listCategory.remove(indexDelete);
            }
        } else {
            System.out.println("Mã danh mục không tồn tại");
        }
    }

    // THống kê thể loại và số sách trong mỗi thể loại
    public static void categoryStatistics(List<Category> listCategory, List<Book> listBook) {
        if (listCategory == null){
            System.out.println("Không có thể loại nào");
        }else {
            System.out.printf("Số thể loại có trong Thư viện: %s\n", listCategory.size());
//            Map<String,Integer> result = new HashMap<>();
            int count = 0;
            for (int i = 0; i< listCategory.size(); i++) {
//            Tìm xem với mỗi thể loại có bao nhiêu cuốn sách
                if (listBook == null){
                    System.out.println("Chưa có sách nào trong Thư viện");
                }else {
                    for (Book book: listBook) {
                        if (book.getCategoryID() == listCategory.get(i).getId()){
                            count++;
                        }
                    }

                    System.out.printf("|  %-5s| %-20s|%-3d|\n", (i + 1), listCategory.get(i).getName(),count);
                }
//                result.put(category.getName(),count);
            }
//System.out.println(result);
        }
    }
//    Hàm sắp xếp A-Z
    public static void sortCategoryNameAToZ(List<Category> listCategory){
        Collections.sort(listCategory, Comparator.comparing(Category::getName));
    }

//    Lấy index
    public static int getIndex(int categoryId, List<Category> listCategory) {
        for (int i = 0; i < listCategory.size(); i++) {
            if (listCategory.get(i).getId() == categoryId) {
                return i;
            }
        }
        return -1;
    }
}
