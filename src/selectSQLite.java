import java.sql.*;
import java.util.Scanner;

public class selectSQLite {

    public static void main(String arg[]){
        int menu =2;
        Scanner teclat = new Scanner(System.in);

        do {
            System.out.println("                    Opcions " );
            System.out.println("---------------------------------------------------" );
            System.out.println("---------------------------------------------------" );
            System.out.println("1. Mostrar totes les pelicules ");
            System.out.println("2. Mostrar una pel·licula ");
            System.out.println("3. Mostrar actors");
            System.out.println("4. Mostra pel·licules que ha realitzat un actor");
            System.out.println("5. Sortir");
            System.out.println("---------------------------------------------------" );
            System.out.println("---------------------------------------------------" );
            System.out.println();
            System.out.println("Tria opció:");
            menu =teclat.nextInt();

            switch (menu) {
                case 1:
                    mostrarMovies();
                    break;
                case 2:
                    System.out.println("Digues el ID de la pelicula que vols buscar");
                    int ID = teclat.nextInt();

                    mostrarMovies(ID);
                    break;
                case 3:
                    mostrarActor();
                    break;
                case 4:
                    System.out.println("Digues el ID de la actor que vols buscar");
                    ID = teclat.nextInt();
                    mostrarMoviesActor(ID);
                case 5:
                    System.out.println("Fi del programa");
                    break;
            }
        }while (menu!=5);
    }
    public static void mostrarMovies()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:theMoviesDBproject.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM MOVIES;" );
            while ( rs.next() ) {
                int id = rs.getInt("ID");
                String title = rs.getString("ORIGINAL_TITLE");
                String date_release= rs.getString("RELEASE_DATE");

                System.out.println("ID: " + id);
                System.out.println("Titulo de la pelicula "+title);
                System.out.println("Data d'estrena : "+date_release);
                System.out.println();

            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
    public static void mostrarMovies(int ID){

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:theMoviesDBproject.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM MOVIES;" );
            while ( rs.next() ) {

                int id = rs.getInt("ID");
                String title = rs.getString("ORIGINAL_TITLE");
                String date_release= rs.getString("RELEASE_DATE");

                if(id==ID) {
                    System.out.println("ID: " + id);
                    System.out.println("Titulo de la pelicula "+title);
                    System.out.println("Data d'estrena : "+date_release);
                }

            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.out.println("No serveix");
            System.exit(0);
        }
    }
    public static void mostrarActor(){

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:theMoviesDBproject.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM ACTORS;" );
            while ( rs.next() ) {

                int id = rs.getInt("ID");
                String name= rs.getString("NAME");

                System.out.println("ID: " + id);
                System.out.println("Nom : "+name);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.out.println("No serveix");
            System.exit(0);
        }
    }
    public static void mostrarMoviesActor(int ID){

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:theMoviesDBproject.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            /*
            SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate
            FROM Orders
             INNER JOIN Customers
            ON Orders.CustomerID=Customers.CustomerID;
             */
            ResultSet rs = stmt.executeQuery( "SELECT ACTORS.CHARACTER, MOVIES.ORIGINAL_TITLE \n" +
                    "FROM ACTORS\n" +
                    "INNER JOIN MOVIES\n" +
                    "ON MOVIES.ID = ACTORS.ID_PELICULA \n" +
                    "WHERE ACTORS.ID = "+ID  );

            while ( rs.next() ) {

                String title = rs.getString("ORIGINAL_TITLE");
                String character = rs.getString("CHARACTER");
                    System.out.println();
                    System.out.println("Pel·licula :" + title);
                    System.out.println("Personatge : " + character);
                    System.out.println();

            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.out.println("No serveix");
            System.exit(0);
        }
    }


}