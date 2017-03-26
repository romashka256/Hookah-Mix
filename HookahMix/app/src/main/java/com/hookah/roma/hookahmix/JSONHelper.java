package com.hookah.roma.hookahmix;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;


public class JSONHelper {
    private static final String FILE_NAME = "tabaks.json";

   /* public static boolean exportToJSON(Context context, List<Tabak> tabakList) {

        Gson gson = new Gson();
        gson.toJson(tabakList);
        DataItems dataItems = new DataItems();
        dataItems.setTabaks(tabakList);
        String jsonString = gson.toJson(dataItems);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    */

    public static void exportToJSON(Context context,List<Tabak> tabakList) {
        try {
            Gson gson = new Gson();
            gson.toJson(tabakList);
            DataItems dataItems = new DataItems();
            dataItems.setTabaks(tabakList);
            String jsonString = gson.toJson(dataItems);
            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + FILE_NAME);
            file.write(jsonString);
            file.flush();
            file.close();
        } catch (IOException e) {
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
        }
    }

    /* public static List<Tabak> importFromJSON(Context context){
         InputStreamReader streamReader = null;
         FileInputStream fileInputStream = null;
         try{
            // File file = context.getFileStreamPath(FILE_NAME);
             fileInputStream = context.openFileInput(FILE_NAME);
             streamReader = new InputStreamReader(fileInputStream);
             Gson gson = new Gson();
             DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
             return  dataItems.getTabaks();
         }
         catch (IOException ex){
             ex.printStackTrace();
         }
         finally {
             if (streamReader != null) {
                 try {
                     streamReader.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if (fileInputStream != null) {
                 try {
                     fileInputStream.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }

         return null;
     } */
    public static List<Tabak> importFromJSON(Context context) {

            InputStream raw = context.getResources().openRawResource(R.raw.tabaks);
            Reader reader = new BufferedReader(new InputStreamReader(raw));
            Tabak listOfTabaks = new Gson().fromJson(reader, Tabak.class);
            List<Tabak> tabakList = listOfTabaks.getTabaksArrayList();
            return tabakList;

    }

private static class DataItems {
    private List<Tabak> tabaks;

    List<Tabak> getTabaks() {
        return tabaks;
    }

    void setTabaks(List<Tabak> tabaks) {
        this.tabaks = tabaks;
    }
}
}


