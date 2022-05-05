package com.company;

import net.xqj.exist.ExistXQDataSource;

import javax.xml.xquery.*;

public class GestorBD {
    XQConnection conn;

    public GestorBD() throws XQException {
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "192.168.249.213");
        xqs.setProperty("port", "8080");

        conn = xqs.getConnection();
    }

    public Dept getDeptSenseEmp(String codi) throws XQException {
        XQExpression xqe = conn.createExpression();
        String cad1 = "doc('/db/Empresa/empresa.xml')";
        System.out.println("Ejecutando instrucciones:" + cad1);
        XQResultSequence xqrs = xqe.executeQuery(cad1);
        System.out.println("Resultados: ");
        while (xqrs.next())
            System.out.println(xqrs.getItemAsString(null));
    }
}
