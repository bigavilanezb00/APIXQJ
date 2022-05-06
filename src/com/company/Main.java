package com.company;


import net.xqj.exist.ExistXQDataSource;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.xquery.*;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws XQException, XMLStreamException {
        Scanner scanner = new Scanner(System.in);
        GestorBD gestorBD = new GestorBD();

        //get
        System.out.println(gestorBD.getDeptSenseEmp("d10"));
        System.out.println(gestorBD.getDeptAmbEmp("d20"));

        //insert
        Dept dept = new Dept("d78","Bryan", "Santa Coloma de Gramanet", null);
        gestorBD.insertDept(dept);
        System.out.println(gestorBD.getDeptAmbEmp("d78"));

       // Dept delDelp = new Dept("d921", "Spdierman", "KL", null);
       // gestorBD.insertDept(delDelp);
       // System.out.println(gestorBD.getDeptSenseEmp("d921"));
       // System.out.println(gestorBD.getDeptSenseEmp("d921"));

        //delete
        System.out.println("¿Id del departamento que quieres eliminar?");
        String del = scanner.next();
        System.out.println("¿A que departamento quiere mover los empleados?");
        String sc = scanner.next();
        gestorBD.deleteDept(del, sc);

        //replace
        System.out.println("¿Id del departamento que quieres eliminar?");
        String camDept = scanner.next();
        System.out.println("¿A que departamento quiere mover los empleados?");
        String reader = scanner.next();
        gestorBD.replaceDept(camDept, reader);











        /* XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "192.168.249.213");
        xqs.setProperty("port", "8080");

        XQConnection conn = xqs.getConnection();

        XQPreparedExpression xqpe = conn.prepareExpression("declare variable $x as xs:string external; $x");
        xqpe.bindString( new QName("x"), "HelloWorld!", null);
        XQResultSequence rs = xqpe.executeQuery();
        while(rs.next())
            System.out.println( rs.getItemAsString(null));

        XQExpression expr = conn.createExpression();
        XQResultSequence result = expr.executeQuery("declare namespace foaf = 'http://xmlns.com/foaf/0.1/';"
                + "declare namespace rdf = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#';"
                + "for $c in /rdf:RDF/foaf:Person[@rdf:nodeID]" + "let $name:= $c/foaf:givenname/text()"
                + "let $surname:= $c/foaf:surname/text()" + "return" + "<alumne>{$name, ' ' , $surname}</alumne>");

        while (result.next()) {

            System.out.println("Resultat consulta: " + result.getItemAsString(null));
        }

        rs.close();
        xqpe.close();
        rs.close();
        expr.close();
        result.close();
        conn.close();*/
    }

}
