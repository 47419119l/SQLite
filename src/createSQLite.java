/**
 * Created by 47419119l on 25/11/15.
 */
import java.sql.*;

public class createSQLite {

    public static void main(String[] args) {
        {
            Connection c = null;
            Statement stmt = null;

            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:theMoviesDBproject.db");
                System.out.println("La BBDD ha sigut creada");

                stmt = c.createStatement();
                /*
                    Creo les taules i les executo.
                 */
                String sql_movies = "CREATE TABLE MOVIES " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " ORIGINAL_TITLE TEXT    NOT NULL, " +
                        " RELEASE_DATE   TEXT    NOT NULL)";

                stmt.executeUpdate(sql_movies);

                String sql_actor = "CREATE TABLE ACTORS " +
                        "(ID INT    NOT NULL," +
                        " ID_PELICULA   INT    NOT NULL,"+
                        " NAME TEXT    NOT NULL, " +
                        " CHARACTER   TEXT    NOT NULL," +
                        "PRIMARY KEY (ID,ID_PELICULA))";

                stmt.executeUpdate(sql_actor);



            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Taules creades correctament.");
        }

    }


}