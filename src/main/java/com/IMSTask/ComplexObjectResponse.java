package com.IMSTask;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yegorm on 06.09.2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ComplexObjectResponse {

    @XmlElement(name = "result")
    private String result;

    public String getResult() {
        return result;
    }


}
