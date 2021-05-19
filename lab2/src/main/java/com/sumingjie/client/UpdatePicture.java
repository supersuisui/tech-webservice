
package com.sumingjie.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>updatePicture complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="updatePicture">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="q" type="{http://service.sumingjie.com/}myRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePicture", propOrder = {
    "id",
    "q"
})
public class UpdatePicture {

    protected int id;
    protected MyRequest q;

    /**
     * 获取id属性的值。
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * 获取q属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MyRequest }
     *     
     */
    public MyRequest getQ() {
        return q;
    }

    /**
     * 设置q属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MyRequest }
     *     
     */
    public void setQ(MyRequest value) {
        this.q = value;
    }

}
