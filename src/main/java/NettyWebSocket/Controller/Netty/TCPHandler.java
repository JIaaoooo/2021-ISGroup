package NettyWebSocket.Controller.Netty;

import NettyWebSocket.socket.OptionSocket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public  class TCPHandler extends SimpleChannelInboundHandler<String> {


    @Autowired
    OptionSocket optionSocket;

    private static String message;
    private static Channel channel=null;

        //第一个被调用的，表示连接已建立，一旦连接第一个被执行
    //将当前channel加入到channel组中
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //获取到channel
        log.debug(ctx.channel().id().asLongText()+"已连接");
        channel = ctx.channel();

    }


    //离线
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //将某某离线提示给，当前在线的客户
        log.debug(ctx.channel().id().asLongText()+"断开连接");


    }
    //硬件传来消息
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {

        this.message =s;
        //服务器读取到从硬件传来的数据后，存入数据库
        OptionSocket.EmbOption(s);
       // sendMessage(s);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }

    public static void sendMessage(String message){

        channel.writeAndFlush(message);
    }

    //可以获取硬件发送的消息
    public static String getMessage() {
        return message;
    }


    public static int getChannelSize(){
        if(channel!=null) return 1;
        else return 0;
    }
}
