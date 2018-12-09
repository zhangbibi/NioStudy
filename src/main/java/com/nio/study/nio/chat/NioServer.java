package com.nio.study.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by zhangyaping on 18/12/8.
 */
public class NioServer {

    //服务端维护着所有客户端的连接信息
    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws Exception {

        //创建服务端的ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        /**
         * 将channel对象注册到selector对象上  可有多个channel注册到selector上
         * 当channel有事件发生时 selector的select方法将会返回
         */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                //返回它所关注的事件的数量  即SelectionKey的数量
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    final SocketChannel client;
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);

                        String key = "[" + UUID.randomUUID().toString() + "]";
                        clientMap.put(key, client);
                    } else if (selectionKey.isReadable()) {
                        client = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        int count = client.read(byteBuffer);

                        if (count > 0) {
                            byteBuffer.flip();
                            Charset charset = Charset.forName("utf-8");
                            String receivedMessage = String.valueOf(charset.decode(byteBuffer).array());
                            System.out.println(client + " : " + receivedMessage);

                            //获取客户端在Map中的key值
                            String sendClientKey = null;
                            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                if (client == entry.getValue()) {
                                    sendClientKey = entry.getKey();
                                    break;
                                }
                            }

                            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                SocketChannel value = entry.getValue();
                                ByteBuffer writeBuffer = ByteBuffer.allocate(512);
                                writeBuffer.put((sendClientKey + ": " + receivedMessage).getBytes());
                                writeBuffer.flip();
                                value.write(writeBuffer);
                            }
                        }
                    }

//                    else if (!selectionKey.isConnectable()) {
//                        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
//                        //获取客户端在Map中的key值
//                        String sendClientKey = null;
//                        for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
//                            if (clientChannel == entry.getValue()) {
//                                sendClientKey = entry.getKey();
//                                break;
//                            }
//                        }
//
//                        clientMap.remove(sendClientKey);
//                    }
                }
                //重要代码  每次处理完一个seletionKey之后  一定要清空这个key
                selectionKeys.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
