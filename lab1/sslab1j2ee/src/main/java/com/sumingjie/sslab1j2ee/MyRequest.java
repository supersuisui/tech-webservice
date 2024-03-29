/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sumingjie.sslab1j2ee;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "MyRequest", namespace="http://service.sumingjie.com")
public class MyRequest implements Serializable
{
    @XmlElement(name = "id", required = false)
    private int id;

    @XmlElement(name = "name", required = false)
    private String name;

    @XmlElement(name = "author", required = false)
    private String author;

    @XmlElement(name = "year", required = false)
    private int year;

    @XmlElement(name = "material", required = false)
    private String material;

    @XmlElement(name = "height", required = false)
    private float height;

    @XmlElement(name = "width", required = false)
    private float width;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
