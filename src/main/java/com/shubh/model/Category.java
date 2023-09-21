package com.shubh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Category {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @NotNull
    @Size(max = 50)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "parentCategory_id")
    private Category parentCategory;

    private int level;

    public Category() {

    }

    public Category(long id,String name, Category parentCategory, int level) {
        this.id = id;
        this.name=name;
        this.parentCategory=parentCategory;
        this.level=level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory= parentCategory;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

