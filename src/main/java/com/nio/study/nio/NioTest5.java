package com.nio.study.nio;

import java.nio.ByteBuffer;

/**
 * Created by zhangyaping on 18/12/6.
 */
public class NioTest5 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(15);
        buffer.putLong(400000L);
        buffer.putDouble(89D);
        buffer.putChar('伙');
        buffer.putShort((short) 3);

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());


    }

}
