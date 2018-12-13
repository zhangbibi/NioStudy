package com.nio.study.nio.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by zhangyaping on 18/12/9.
 */
public class SelectorClient {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        SelectionKey key = channel.register(selector, SelectionKey.OP_CONNECT);


        System.out.println(key.isReadable());
        System.out.println(key.isConnectable());
        System.out.println("interest " + key.interestOps());

        channel.connect(new InetSocketAddress("127.0.0.1", 8888));


        while (true) {
            int readyChannels = selector.select();

            if (readyChannels == 0) {
                continue;
            }
            Set<SelectionKey> keySet = selector.selectedKeys();

            for (SelectionKey selectionKey : keySet) {
                if (selectionKey.isConnectable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    if (client.isConnectionPending()) {
                        client.finishConnect();
                    }
                }
            }

            keySet.clear();
        }
    }
}
