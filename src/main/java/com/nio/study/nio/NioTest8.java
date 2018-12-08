package com.nio.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhangyaping on 18/12/6.
 * DirectBuffer
 */
public class NioTest8 {

    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();


        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);

        while (true) {

            byteBuffer.clear();

            int read = inputChannel.read(byteBuffer);

            System.out.println("position = " + byteBuffer.position() + " limit =  " + byteBuffer.limit());


            System.out.println("read  =  " + read);

            if (-1 == read) {
                break;
            }

            byteBuffer.flip();

            outputChannel.write(byteBuffer);

        }

        fileInputStream.close();
        fileOutputStream.close();


    }
}
