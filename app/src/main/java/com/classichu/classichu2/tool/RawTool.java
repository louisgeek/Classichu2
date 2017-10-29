package com.classichu.classichu2.tool;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by louisgeek on 2016/5/31.
 */
public class RawTool {
    /**
     * getStringFromRaw
     *
     * @param rawID
     * @return
     */
    public static String getStringFromRaw(int rawID) {
        StringBuilder sb_result = new StringBuilder("");
        InputStream ssq_is = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            ssq_is = Resources.getSystem().openRawResource(rawID);

            inputReader = new InputStreamReader(ssq_is);
            //InputStreamReader inputReader = new InputStreamReader(ssq_is,"UTF-8");
            bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                sb_result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ssq_is.close();
                inputReader.close();
                bufReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb_result.toString();
    }

    public static String getStringFromRawWithRN(int rawID) {
        StringBuilder sb_result = new StringBuilder("");
        InputStream ssq_is = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            ssq_is = Resources.getSystem().openRawResource(rawID);

            inputReader = new InputStreamReader(ssq_is);
            //InputStreamReader inputReader = new InputStreamReader(ssq_is,"UTF-8");
            bufReader = new BufferedReader(inputReader);

            String line = "";
            while ((line = bufReader.readLine()) != null) {
                if (line.trim().equals("")) {
                    continue;
                } else {
                    sb_result.append(line.trim() + "\r\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ssq_is.close();
                inputReader.close();
                bufReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb_result.toString();
    }

    /**
     * 很多行 会卡死
     *
     * @param rawID
     * @return
     */
    @Deprecated
    public static String getStringFromRawOld(int rawID) {
        String result = "";
        try {
            InputStream ssq_is =Resources.getSystem().openRawResource(rawID);

            InputStreamReader inputReader = new InputStreamReader(ssq_is);
            //InputStreamReader inputReader = new InputStreamReader(ssq_is,"UTF-8");
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
