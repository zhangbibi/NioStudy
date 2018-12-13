package com.nio.study.nio.selector;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhangyaping on 18/12/9.
 */
public class SelectorServer {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress(8888));
        SelectionKey key = channel.register(selector, SelectionKey.OP_ACCEPT);


        System.out.println(key.isReadable());
        System.out.println(key.isConnectable());
        System.out.println("interest " + key.interestOps());



        while (true) {
            int readyChannels = selector.select();

            if (readyChannels == 0) {
                continue;
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {

                SelectionKey key2 = keyIterator.next();

                if (key2.isAcceptable()) {
                    System.out.println("connection was accepted by a ServerSocketChannel.");
                } else if (key2.isConnectable()) {
                    System.out.println("a connection was established with a remote server.");
                } else if (key2.isReadable()) {
                    System.out.println("a channel is ready for reading");
                } else if (key2.isWritable()) {
                    System.out.println("a channel is ready for writing");
                }
                keyIterator.remove();

            }
        }
    }
}
