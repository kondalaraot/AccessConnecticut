package com.accessconnecticut;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by KTirumalsetty on 12/21/2016.
 */

public class FileUtil {

    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    /*public String readTxt(String fileName) {

        try {
            InputStream is;
            is = this.getAssets().open(fileName + ".doc");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            int i;
            i = is.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = is.read();
            }

            is.close();

            return byteArrayOutputStream.toString("UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }*/
}
