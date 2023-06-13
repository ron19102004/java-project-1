package com.MyApplication.modules.product.entity;

import lombok.Data;

@Data
public class Product {
    private int id;
    private int id_user_created;
    private int id_category;
    private String name;
    private String description;
    private int quantity;
    private int price;
    private String img;

    public Product() {
    }

    public Product(int id,
                   int id_user_created,
                   int id_category,
                   String name,
                   String description,
                   int quantity,
                   int price,
                   String img) {
        this.id = id;
        this.id_user_created = id_user_created;
        this.id_category = id_category;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.img = img;
    }
    public Product(int id_user_created,
                   int id_category,
                   String name,
                   String description,
                   int quantity,
                   int price,
                   String img) {
        this.id_user_created = id_user_created;
        this.id_category = id_category;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.img = img;
    }
}
