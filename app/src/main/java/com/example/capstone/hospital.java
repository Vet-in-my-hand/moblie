package com.example.capstone;

public class hospital {

    public String id;
    public String address;
    public String name;
    public String tel;
    public String info;

    public hospital(String id, String address, String name, String tel, String info) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.tel = tel;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
