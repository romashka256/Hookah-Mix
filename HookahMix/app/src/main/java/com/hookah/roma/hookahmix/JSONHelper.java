package com.hookah.roma.hookahmix;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.List;


public class JSONHelper {
    private static final String FILE_NAME = "tabaks.json";
    private static SharedPreferences prefs = null;

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

    public static void exportToJSON(Context context, List<Tabak> mJsonResponse) {
        try {
            Gson gson = new Gson();
            gson.toJson(mJsonResponse);
            DataItems dataItems = new DataItems();
            dataItems.setTabaks(mJsonResponse);
            String jsonString = gson.toJson(dataItems);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE));
            outputStreamWriter.write(jsonString);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
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
        prefs = context.getSharedPreferences("com.hookah.roma.hookahmix", Context.MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            InputStream raw = context.getResources().openRawResource(R.raw.tabaks);
            Reader reader = new BufferedReader(new InputStreamReader(raw));
            Tabak listOfTabaks = new Gson().fromJson(reader, Tabak.class);
            List<Tabak> tabakList = listOfTabaks.getTabaksArrayList();
            prefs.edit().putBoolean("firstrun", false).commit();
            exportToJSON(context, tabakList);
            return tabakList;
        } else {
            InputStreamReader streamReader = null;
            FileInputStream fileInputStream = null;
            try {
                File file = new File(context.getFilesDir().getPath() + "/" + FILE_NAME);
                fileInputStream = context.openFileInput(FILE_NAME);
                streamReader = new InputStreamReader(fileInputStream);
                Gson gson = new Gson();
                DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
                return dataItems.getTabaks();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
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
        }
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


