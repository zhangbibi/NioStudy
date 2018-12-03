package com.nio.study.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhangyaping on 18/12/2.
 */
public class NioTest3 {

    public static void main(String[] args) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("NioTest3.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byte[] message = "Hello Workd".getBytes();
        byteBuffer.put(message);

        byteBuffer.flip();
        channel.write(byteBuffer);

    }
}
