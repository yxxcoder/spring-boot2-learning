package com.simple.chapter12.entity;

/**
 * 实体类
 *
 * @author yxxcoder
 * @since 2019-03-11 17:21 PM
 */
public class Book implements java.io.Serializable {

    private static final long serialVersionUID = -2164058270630403154L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
