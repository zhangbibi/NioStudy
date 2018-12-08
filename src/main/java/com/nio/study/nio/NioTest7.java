package com.nio.study.nio;

import java.nio.ByteBuffer;

/**
 * Created by zhangyaping on 18/12/6.
 * 只读Buffer与原buffer共享底层数组
 */
public class NioTest7 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }


        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();


    }
}
