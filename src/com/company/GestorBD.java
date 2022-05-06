package com.company;

import net.xqj.exist.ExistXQDataSource;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xquery.*;

public class GestorBD {
    XQConnection conn;

    public GestorBD() throws XQException {
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "192.168.249.213");
        xqs.setProperty("port", "8080");

        conn = xqs.getConnection();
    }

    public Dept getDeptSenseEmp(String codi) throws XQException, XMLStreamException {
        Dept dept = new Dept(null,null,null,null);
        XQExpression xqe = conn.createExpression();
        String codi2 = "for $f in doc('/db/Empresa/empresa.xml')/empresa/departaments/dept[@codi='" + codi+ "'] \n" +
                "let $n:= $f/nom/string() \n" +
                "let $l:= $f/localitat/string() \n" +
                "return \n" +
                "<x>{$n},{$l}</x>";

        // System.out.println("Ejecutando instrucciones: ");
        XQResultSequence xqrs = xqe.executeQuery(codi2);

        //System.out.println("Resultados: ");
        while (xqrs.next()) {
            XMLStreamReader xsr = xqrs.getItemAsStream();
            for (; xsr.hasNext(); xsr.next())
            if (xsr.getEventType() == XMLStreamConstants.CHARACTERS) {
                String[] textElements = xsr.getText().split(",");

                dept.setCodi(codi);
                if (textElements[0] != null) dept.setNom(textElements[0]);
                else dept.setNom(null);
                if (textElements[1] != null) dept.setLocalitat(textElements[1]);
                else dept.setLocalitat(null);
            }
        }
        return dept;
    }
}
