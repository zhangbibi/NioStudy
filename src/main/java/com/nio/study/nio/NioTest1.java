package com.nio.study.nio;

import java.nio.IntBuffer;
import java.util.Random;

/**
 * Created by zhangyaping on 18/12/2.
 */
public class NioTest1 {

    public static void main(String[] args) {

        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(new Random().nextInt());
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
