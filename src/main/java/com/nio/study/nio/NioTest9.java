package com.nio.study.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhangyaping on 18/12/6.
 * MappedByteBuffer 实际数据也是放在堆外 这个对象可以看成是对内存的一个映射 api可以直接操作堆外内存
 */
public class NioTest9 {

    public static void main(String[] args) throws Exception {

        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'a');
        mappedByteBuffer.put(3, (byte) 'b');

        randomAccessFile.close();
    }
}
