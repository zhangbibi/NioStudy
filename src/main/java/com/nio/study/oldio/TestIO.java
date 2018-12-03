package com.nio.study.oldio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * Created by zhangyaping on 18/12/2.
 */
public class TestIO {

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("");

        BufferedInputStream bis = new BufferedInputStream(fis);

        InputStreamReader reader = new InputStreamReader(fis);

        //
    }

}
