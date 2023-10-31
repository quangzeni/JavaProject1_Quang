package entity;

import bussiness.IEntity;

import java.io.Serializable;
import java.security.BasicPermission;
import java.util.List;
import java.util.Scanner;

public class Category implements IEntity<Category>, Serializable {
    private int id;
    private String name;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public void inputData(Scanner scanner, List<Category> listCategory) {
        //        Nhập Mã danh mục
        System.out.println("Nhập mã danh mục");
        boolean isExit = true;
        do {
            String newId = scanner.nextLine();
            if (newId == null) {
                System.out.println("Mã Id không được trống");
            } else {
                if (Integer.parseInt(newId) <= 0) {
                    System.out.println("Id phải lớn hơn không, nhập lại");
                } else {
                    boolean isExist = false;
                    for (Category category : listCategory) {
                        if (category.getId() == Integer.parseInt(newId)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (isExist) {
                        System.out.println("Mã Id bị trùng, vui lòng nhập lại");
                    } else {
                        this.id = Integer.parseInt(newId);
                        break;
                    }
                }
            }
        } while (isExit);
//        Nhập name
        System.out.println("Nhập tên danh mục");
        do {
            this.name = scanner.nextLine();
            if (this.name == null) {
                System.out.println("Tên danh mục không được để trống");
            } else {
                if (this.name.length() < 6 || this.name.length() > 30) {
                    System.out.println("Tên phải từ 6 đến 30 ký tự");
                } else {
                    boolean isExist = false;
                    for (Category category : listCategory) {
                        if (category.getName().equals(this.name)) {
                            isExist = true;
                            break;
                        }
                    }
                    if (isExist) {
                        System.out.println("Tên nhập vào trùng lặp");
                    } else {
                        break;
                    }
                }
            }
        } while (isExit);
        System.out.println("Nhập trạng thái danh mục");
        do {
            String newStatus = scanner.nextLine();
            if (newStatus.isEmpty()) {
                System.out.println("Trạng thái khng được để trống");
            } else {
                if (newStatus.equalsIgnoreCase("true") || newStatus.equalsIgnoreCase("false")) {
                    this.status = Boolean.parseBoolean(newStatus);
                    break;
                } else {
                    System.out.println("Chỉ được nhập true hoặc false");
                }
            }
        } while (isExit);
    }

    @Override
    public void output() {
//    In danh sách các thể loại sách
        System.out.printf("| %-5d|  %-15s|  %-15s|\n",this.id,this.name,this.status?"Hoạt động":"Không hoạt động");
    }
}
