
package com.sumingjie.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>findPictures complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="findPictures">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
@XmlType(name = "findPictures", propOrder = {
    "q"
})
public class FindPictures {

    protected MyRequest q;

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
