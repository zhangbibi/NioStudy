package com.nio.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhangyaping on 18/12/2.
 */
public class NioTest4 {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");

        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();


        ByteBuffer byteBuffer = ByteBuffer.allocate(1);

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
