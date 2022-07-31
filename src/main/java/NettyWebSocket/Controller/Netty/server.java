package NettyWebSocket.Controller.Netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class server {


    public void run() throws Exception {


//创建两个线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();


        try {


            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_BACKLOG, 1024)
                    //TCP向远程发送数据包，如果远程没有做出响应，TCO会持续等待11分钟，如果12分钟后再无响应，TCP会尝试断开连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //获取到pipeline
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //添加一个解码器
                            pipeline.addLast("decoder", new StringDecoder());
                            //添加编码器
                            pipeline.addLast("encoder", new StringEncoder());
                            //添加自己的handler业务处理
                            pipeline.addLast(new TCPHandler());
                        }
                    });

            ServerBootstrap Wbootstrap = new ServerBootstrap();
            Wbootstrap.group(workerGroup, bossGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            //以块方式来写处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            /*
                                由于http在传输过程中是分段的
                                HttpObjectAggregator可以将多个段整合
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            /*
                            对于WebSocket的数据是通过帧Frame形式传递的
                            WebSocketFrame下有六个子类
                            WebSocketServerProtocolHandler是将http协议升级为ws，保持长连接，参数必须写websocketPath
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/"));

                            pipeline.addLast(new WebsocketHandler());
                        }
                    });


            ChannelFuture channelFuture = Wbootstrap.bind(8082).sync();
            log.debug("WebSocket服务器开启");
            ChannelFuture channelFuture1 = serverBootstrap.bind(8081).sync();
            log.debug("TCP服务器开启");
            channelFuture.channel().closeFuture().sync();
            channelFuture1.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}


