


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import javax.xml.transform.Transformer;
import java.io.*;
import java.net.*;
import java.sql.*;

class theMovieDBproject {
    static String api_key="eec33652afa70e666fc6d094216e0714";

    /**
     * Extreu el HTML
     * @param urlToRead
     * @return
     * @throws Exception
     */
    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }


    public static void main(String[] args){
        String s = "";

        for (int i = 0; i < 40; i++) {
            int peli = 600 +i;
            String film = String.valueOf(peli);
            /*
             Aquesta URL és per pelicules.
             */
            String peticioPeliculas = "https://api.themoviedb.org/3/movie/"+film+"?api_key="+api_key;
            String peticioActors="https://api.themoviedb.org/3/movie/"+film+"/casts?api_key="+api_key;

            try {
                s = getHTML(peticioPeliculas);
                SJS(s);
                System.out.println("-----------------------");
                s=getHTML(peticioActors);
                SJC(s);
            } catch (Exception e) {

            }
        }


    }

    /**
     * Funció per agafar pel·licules i fer inserts
     * @param cadena
     */
    public static void SJS (String cadena){

            /*
            Extreiem la informació del JSON
             */
            Object obj02 =JSONValue.parse(cadena);
            JSONObject arra02=(JSONObject)obj02;
            int ID = Integer.parseInt(String.valueOf(arra02.get("id")));
            String ORIGINAL_TITLE = String.valueOf(arra02.get("original_title"));
            String RELEASE_DATE=String.valueOf(arra02.get("release_date"));

            System.out.println("ID :"+ID);
            System.out.println("Titul original :"+ORIGINAL_TITLE);
            System.out.println("Data d'estrena :" + RELEASE_DATE);
            /*
            Fem el insert.
             */
            insertSQLite.insertMovies(ID, ORIGINAL_TITLE, RELEASE_DATE);

    }

    /**
     * Funció per agafar actors.
     * @param cadena
     */
    public static void SJC (String cadena){

        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
        JSONArray arra03 = (JSONArray)arra02.get("cast");

        for (int i = 0; i < arra03.size(); i++) {

            JSONObject jb= (JSONObject)arra03.get(i);

            int ID = Integer.parseInt(String.valueOf(jb.get("id")));
            int ID_PELICULA = Integer.parseInt(String.valueOf(jb.get("cast_id")));
            String NAME = String.valueOf(arra02.get("name"));
            String CHARACTER=String.valueOf(arra02.get("character"));

            System.out.println("ID :"+ID);
            System.out.println("ID pel·licula :"+ID_PELICULA);
            System.out.println("Nom :"+NAME);
            System.out.println("Personatge :"+CHARACTER);


            /*
            Fem el insert
             */
            insertSQLite.insertActor(ID,ID_PELICULA,NAME,CHARACTER);


        }

    }

}