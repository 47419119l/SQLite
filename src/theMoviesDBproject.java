


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import javax.xml.transform.Transformer;
import java.io.*;
import java.net.*;

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
     * Funció per agafar pel·licules
     * @param cadena
     */
    public static void SJS (String cadena){

        Object obj02 =JSONValue.parse(cadena);
        JSONObject arra02=(JSONObject)obj02;
        System.out.println("ID pel·licula : "+arra02.get("id"));
        System.out.println("Titul original pel·licula : "+arra02.get("original_title"));
        System.out.println("Data d'estrena : "+arra02.get("release_date"));

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
            System.out.println(jb.get("character")+"<-->"+jb.get("name")+"<--->"+jb.get("cast_id"));

        }

    }

}