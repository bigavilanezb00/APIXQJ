package com.company;

import net.xqj.exist.ExistXQDataSource;
import net.xqj.exist.bin.E;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xquery.*;
import java.util.ArrayList;
import java.util.List;

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

    public Dept getDeptAmbEmp(String codi) throws XQException, XMLStreamException {
        Dept dept = getDeptSenseEmp(codi);
        List<Emp> empList = new ArrayList<>();

        XQExpression xqe = conn.createExpression();

        String codi2 = "for $f in doc('/db/Empresa/empresa.xml')/empresa/empleats/emp[@dept='" + codi + "'] \n" +
                "let $c:= $f/@codi/string()  \n" +
                "let $cn:= $f/cognom/string() \n" +
                "let $o:= $f/ofici/string() \n" +
                "let $da:= $f/dataAlta/string() \n" +
                "let $s:= $f/salari/string() \n" +
                "let $com:= $f/comissio/string() \n" +
                "let $cj:= $f/@cap/string() \n" +
                "return \n" +
                "<x>{$c},{$cn},{$o},{$da},{$s},{$com},{$cj},null</x>";

        XQResultSequence xqrs = xqe.executeQuery(codi2);

        while (xqrs.next()) {
            XMLStreamReader xsr = xqrs.getItemAsStream();
            Emp emp = new Emp(null,null,null,null,null,0.0f,0.0f);

            for (; xsr.hasNext(); xsr.next())
                if (xsr.getEventType() == XMLStreamConstants.CHARACTERS) {
                    String[] textElements = xsr.getText().split(",");

                    if (textElements[0] != null) emp.setCodi(textElements[0]);
                    else emp.setCodi(null);
                    if (textElements[1] != null) emp.setCognom(textElements[1]);
                    else emp.setCognom(null);
                    if (textElements[2] != null) emp.setOfici(textElements[2]);
                    else emp.setOfici(null);
                    if (textElements[3] != null) emp.setDataAlta(textElements[3]);
                    else emp.setDataAlta(null);
                    if (textElements[4] != null && !textElements[4].isEmpty()) emp.setSalari(Float.parseFloat(textElements[4]));
                    else emp.setSalari(0.0f);
                    if (textElements[5] != null && !textElements[5].isEmpty()) emp.setComissio(Float.parseFloat(textElements[5]));
                    else emp.setComissio(0.0f);
                    if (textElements[6] != null) emp.setCodi_jefe(textElements[6]);
                    else emp.setCodi_jefe(null);
                    empList.add(emp);
                }
        }
        dept.setList_emp(empList);
        return dept;
    }

    public void insertDept(Dept insDept) throws XQException {
        XQExpression xqe = conn.createExpression();
        String cadDept = "update insert \n" +
                "<dept codi='"+ insDept.getCodi()+"'><nom>"+insDept.getNom()+"</nom><localitat>"+insDept.getLocalitat()+"</localitat></dept> \n" +
                "preceding doc('/db/Empresa/empresa.xml')/empresa/departaments/dept[1]";
        xqe.executeCommand(cadDept);

        System.out.println("Departamento insertado");
    }

    public void deleteDept(String delDept, String sc) throws XQException {
        XQExpression xqe = conn.createExpression();
        String deleDept = "update delete \n" +
                "doc('/db/Empresa/empresa.xml')/empresa/departaments/dept[@codi='"+ delDept +"']";
        xqe.executeCommand(deleDept);
        System.out.println("Departamento eliminado");

        String cambiarDept = "update value \n" +
                "doc('/db/Empresa/empresa.xml')/empresa/empleats/emp[@dept='"+ delDept +"']/@dept \n" +
                "with '"+sc+"'";
        xqe.executeCommand(cambiarDept);
    }

    public void replaceDept(String camDept, String sc) throws XQException {
        XQExpression xqe = conn.createExpression();
        String cambiarDept = "update value \n" +
                "doc('/db/Empresa/empresa.xml')/empresa/empleats/emp[@dept='"+ camDept +"']/@dept \n" +
                "with '"+sc+"'";
        xqe.executeCommand(cambiarDept);

        String deleDept = "update delete \n" +
                "doc('/db/Empresa/empresa.xml')/empresa/departaments/dept[@codi='"+ sc +"']";
        xqe.executeCommand(deleDept);

        String cambaridDept = "update value \n" +
                "doc('/db/Empresa/empresa.xml')/empresa/departaments/dept[@codi='"+camDept+"']/@codi \n" +
                "with '"+sc+"'";
        xqe.executeCommand(cambaridDept);

    }

}
