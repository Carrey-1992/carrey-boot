package com.carrey.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * @author Conway
 * @className ByteBufTest
 * @description
 * @date 2020/7/8 10:08 上午
 */
@Slf4j
public class ByteBufTest {

    /**
     * 创建堆缓冲区
     */
    @Test
    public void testHeapByteBuf() {
        ByteBuf heapBuf = Unpooled.buffer(10);
        if (heapBuf.hasArray()) {
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            int length = heapBuf.readableBytes();
            //0,0
            log.info("offset:{},length:{}", offset, length);
        }
    }

    @Test
    public void testDirectByteBuf() {
        ByteBuf directBuffer = Unpooled.directBuffer(10);
        if (!directBuffer.hasArray()) {
            int length = directBuffer.readableBytes();
            byte[] array = new byte[length];
            ByteBuf bytes = directBuffer.getBytes(directBuffer.readerIndex(), array);
            //0,0
            log.info("offset:{},length:{}",bytes.readerIndex() , array.length);
        }
    }

    @Test
    public void testWrite() {
        ByteBuf heapBuf = Unpooled.buffer(10);
        while (heapBuf.writableBytes() > 4) {
            heapBuf.writeInt(new Random().nextInt());
        }

        while (heapBuf.isReadable()) {
            System.out.println("testWrite:" + heapBuf.readByte());
        }
    }

    @Test
    public void testFind() {
        ByteBuf heapBuf = Unpooled.buffer(13);
        heapBuf.writeByte(new Random().nextInt());
        heapBuf.writeByte(new Random().nextInt());
        heapBuf.writeBytes("\r\n".getBytes());
        int i = heapBuf.indexOf(0, 12, (byte) '\r');
        //2
        System.out.println("testFind1:" + i);
        i = heapBuf.forEachByte(ByteProcessor.FIND_CRLF);
        //2
        System.out.println("testFind2:" + i);
    }

    @Test
    public void testSlice() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("jannal", Charset.forName("utf-8"));
        ByteBuf slice = byteBuf.slice(0, 6);
        //jannal
        System.out.println(slice.toString(Charset.forName("utf-8")));
        byteBuf.setByte(0, (byte) 'J');
        assert byteBuf.getByte(0) == slice.getByte(0);
        //J
        System.out.println((char)byteBuf.getByte(0));
        //J
        System.out.println((char)slice.getByte(0));

        //复制

        ByteBuf copy = byteBuf.copy(0, 6);
        System.out.println(copy.toString(Charset.forName("utf-8")));

        byteBuf.setByte(0, (byte) 'j');
        //j
        System.out.println((char)byteBuf.getByte(0));
        //J
        System.out.println((char)copy.getByte(0));
    }

    @Test
    public void testRandomAccess() {
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.getByte(i);
            //输出:0000000000
            System.out.print(b);
        }
    }


    @Test
    public void testGetSet(){
        ByteBuf byteBuf = Unpooled.copiedBuffer("jannal", Charset.forName("utf-8"));
        System.out.println((char)byteBuf.getByte(0));
        int readIndex = byteBuf.readerIndex();
        int writerIndex= byteBuf.writerIndex();
        byteBuf.setByte(0,(byte)'J');
        System.out.println((char)byteBuf.getByte(0));
        assert readIndex== byteBuf.readerIndex();
        assert writerIndex== byteBuf.writerIndex();
    }

    @Test
    public void testReadWrite(){
        ByteBuf byteBuf = Unpooled.copiedBuffer("jannal", Charset.forName("utf-8"));
        System.out.println((char)byteBuf.readByte());
        int readIndex = byteBuf.readerIndex();
        int writerIndex= byteBuf.writerIndex();
        byteBuf.writeByte((byte)'J');
        assert readIndex== byteBuf.readerIndex();
        assert writerIndex!= byteBuf.writerIndex();
    }


}
