package model;

import java.util.List;

public class Category {

    private Long id;
    private String name;
    //private List<model.Book> books;

    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "model.Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
