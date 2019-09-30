package com.eiv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class PersonasBusqueda {
    private static final Scanner SCANNER = new Scanner(System.in);
    
    public void buscar(String nombre) {

        try (Connection conn = getDataSource().getConnection();

                PreparedStatement pstmt = conn
                        .prepareStatement("SELECT nom_ape, nro_doc FROM PERSONAS " 
                                            + " WHERE nom_ape LIKE ? ORDER BY nom_ape ASC");

                Scanner scanner = new Scanner(System.in)) {

            pstmt.setString(1, "%" + nombre + "%");

            ResultSet rs = pstmt.executeQuery();

            System.out.println("Nombre y numero de documento de personas llamadas " + nombre + ":");

            while (rs.next()) {
                String nomApe = rs.getString("nom_ape");
                long doc = rs.getLong("nro_doc");

                System.out.println(String.format("[Nombre y apellido]: %s - Nro Doc: %s", 
                        nomApe, doc));

            }

            System.out.println("¿Desea realizar otra busqueda? Si -> 1; No -> 0");
            int respuesta = SCANNER.nextInt();

            if (respuesta == 1) {
                System.out.println("Ingrese un nombre: ");
                String nom = SCANNER.next();
                this.buscar(nom);
            } else if (respuesta == 0) {
                System.out.println("Adios.");
                System.exit(0);
            } else {
                System.out.println("Opcion desconocida. El sistema se cerrara.");
                System.exit(0);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public DataSource getDataSource() {

        HikariConfig conf = new HikariConfig();
        conf.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conf.setJdbcUrl("jdbc:sqlserver://SQLSERVER\\SQL2008;database=DESARROLLO_MUTUAL");
        conf.setUsername("sa");
        conf.setPassword("rv760");

        HikariDataSource ds = new HikariDataSource(conf);

        return ds;
    }
    
}
