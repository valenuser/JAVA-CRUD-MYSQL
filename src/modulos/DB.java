package modulos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;

import java.util.HashMap;

public class DB {
    private static String username = "your mysql username";
    private static String password = "your mysql password";
    private static String database = "jdbc:mysql://localhost:3306/name of your database";

    public static Connection connection() throws SQLException{
        Connection conn = null;

        conn = DriverManager.getConnection(database,username, password);

        return conn;
    };

    public void ReadAll() throws SQLException{
        Connection conn = connection();

        String query = "select  * from users";

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(query);

        Boolean response =  rs.next();
        if(response.compareTo(true) == 0){
            System.out.println("--------------------------");
            System.out.println(String.format("id: %s",rs.getInt(1)));
            System.out.println(String.format("Name: %s",rs.getString(2)));
            System.out.println("--------------------------\n");
            while(rs.next()){
                System.out.println("--------------------------");
                System.out.println(String.format("id: %s",rs.getInt(1)));
                System.out.println(String.format("Name: %s",rs.getString(2)));
                System.out.println("--------------------------\n");
            }
        }else{
            System.out.println("No hay usuarios registrados.");
        }

        conn.close();
    }


    public void SearchUser() throws SQLException{
        Connection conn = connection();

        Scanner sc = new Scanner(System.in);

        System.out.print("Dime un nombre: ");

        String data = sc.nextLine();

        String query = String.format("select  * from users where nombre = '%s'", data);

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(query);

        HashMap<String,String> response = new HashMap<String,String>();

        while(rs.next()){
                response.put("id", rs.getString(1));
                response.put("name", rs.getString(2));
        }
        
        if(response.size() == 0){
            System.out.println("Usuario no registrado.");
        }else{
            System.out.println(String.format("id: %s",response.get("id")));
            System.out.println(String.format("Name: %s",response.get("name")));
        }

        conn.close();
    }

    public void AddUser() throws SQLException{
        Connection conn = connection();

        Scanner sc = new Scanner(System.in);

        System.out.print("Dime el nombre que quieras registrar: ");

        String data = sc.nextLine();

        if(data.length() == 0){
            System.out.println("Debe rellenar correctamente la respuesta.");
        }else{

            Statement stmt = conn.createStatement();

            String query = String.format("INSERT INTO users(nombre) values ('%s')", data);

            stmt.execute(query);

            System.out.println("Usuario registrado con exito.");


            conn.close();
        }
    }

    public void UpdateUser() throws SQLException{
        Connection conn = connection();

        Scanner sc = new Scanner(System.in);

        System.out.print("Identificador de usuario: ");

        int identify = sc.nextInt();

        String query = String.format("select  * from users where id = %s", identify);

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(query);

        Boolean response = rs.next();

        if(response.compareTo(true) == 0){
            Scanner ac = new Scanner(System.in);
            
            System.out.print("Nuevo nombre de usuario: ");
            String newData = ac.nextLine();


            query = String.format(" update users set nombre = '%s' where id = %s",newData,identify );

            stmt.execute(query);

            System.out.println("Usuario actualizado con exito.");

        }else{
            System.out.println("Usuario no encontrado.");
        }

        conn.close();
    }

    public void DeleteUser() throws SQLException{
        Connection conn = connection();

        Scanner sc = new Scanner(System.in);

        System.out.print("Identificador de usuario que desea eliminar: ");

        int identify = sc.nextInt();

        String query = String.format("select  * from users where id = %s", identify);

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(query);

        Boolean response = rs.next();

        if(response.compareTo(true) == 0){
            query = String.format("delete from users where id= %s", identify);

            int responseDELETE = stmt.executeUpdate(query);

            if(responseDELETE > 0){
                System.out.println("Usuario borrado con exito.");
            }else{
                System.out.println("No se ha podido eliminar el usuario.");
            }
        }else{
            System.out.println("Usuario no encontrado.");
        }

        conn.close();
    }
}

