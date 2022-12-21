package com.mygdx.game;

import java.io.Serializable;

public class Save implements Serializable {
    private String[] serialized_files;
    public long serialVersionUID;

    public java.lang.String[] getSerialized_files() {
        return serialized_files;
    }
    public void setSerialized_files(){

    }
    public long getSerialVersionUID(){

        return 0;
    }
}
