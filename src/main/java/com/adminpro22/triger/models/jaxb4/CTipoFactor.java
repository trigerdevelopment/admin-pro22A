//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.08.09 at 12:08:10 PM CDT 
//


package com.adminpro22.triger.models.jaxb4;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for c_TipoFactor.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="c_TipoFactor"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;whiteSpace value="collapse"/&gt;
 *     &lt;enumeration value="Tasa"/&gt;
 *     &lt;enumeration value="Cuota"/&gt;
 *     &lt;enumeration value="Exento"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "c_TipoFactor", namespace = "http://www.sat.gob.mx/sitio_internet/cfd/catalogos")
@XmlEnum
public enum CTipoFactor {

    @XmlEnumValue("Tasa")
    TASA("Tasa"),
    @XmlEnumValue("Cuota")
    CUOTA("Cuota"),
    @XmlEnumValue("Exento")
    EXENTO("Exento");
    private final String value;

    CTipoFactor(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CTipoFactor fromValue(String v) {
        for (CTipoFactor c: CTipoFactor.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}