/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtsw.toolsteam.bootehcahe.domain;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author b1050502
 */
@XmlRootElement(name ="data")
@XmlAccessorType(XmlAccessType.FIELD)
public class Data implements Serializable{
    
    @XmlAttribute
    private String name;
    
    @XmlAttribute
    private String mnemonic;
    
    @XmlAttribute
    private String value;

    public Data() { }

    public Data(String name, String mnemonic, String value) {
        this.name = name;
        this.mnemonic = mnemonic;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    } 

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    @Override
    public String toString() {
        return "Data{" + "name=" + name + ", mnemonic=" + mnemonic + ", value=" + value + '}';
    }   
}
