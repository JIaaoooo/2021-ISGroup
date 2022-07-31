package NettyWebSocket.Controller.Netty;

import NettyWebSocket.socket.OptionSocket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class WebsocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static String message;
    private static Channel channel =null;

    @Autowired
    OptionSocket optionSocket;


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channel = ctx.channel();
        log.debug("浏览器  "+ ctx.channel().id().asLongText() +"  上线了");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.debug("浏览器  "+ ctx.channel().id().asLongText() +"下线了");

    }

    //移动端发送消息
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        log.debug("服务器收到消息"+textWebSocketFrame.text());
        message = textWebSocketFrame.text();
        OptionSocket.MoveOption(textWebSocketFrame.text());
        //optionSocket.getMoveMessage();

        /*错误代码，使用后移动端发送消息过后，发生异常，调用exceptionCaught方法，断开连接
            debug后发现tcpHandler为null，没有创建*/
        /*log.debug(String.valueOf(tcpHandler));
        if(tcpHandler.getChannel()!=null){
            tcpHandler.sendMessage(textWebSocketFrame.text());
        }
       else channel.writeAndFlush(new TextWebSocketFrame("硬件未连接"));*/
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.debug("发生了异常   "+cause.getMessage());
        ctx.channel().close();
    }


    //向移动端发送消息
    public static void sendMessage(String str){
        channel.writeAndFlush(new TextWebSocketFrame(str));
    }


    public static String getMessage(){
        return message;
    }

}
