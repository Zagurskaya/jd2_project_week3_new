package com.gmail.aperavoznikau.jd2w3.data.model;

public class Item {

    private Long id;
    private String name;
    private ItemStatusEnum status;
//    private String status;

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

//    public String getStatus() {
//        return status;
//    }

    public ItemStatusEnum getStatus() {
        return status;
    }

//    public void setStatus(String status) {
//        this.status = status;
//    }

    public void setStatus(ItemStatusEnum status) {
        this.status = status;
    }
}
