package com.accessconnecticut;

import java.io.Serializable;

/**
 * Created by KTirumalsetty on 12/21/2016.
 */

public class Source implements Serializable{
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
