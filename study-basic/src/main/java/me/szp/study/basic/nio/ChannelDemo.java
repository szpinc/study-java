package me.szp.study.basic.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 孙志鹏
 * @since 2022/1/4 5:49 PM
 */
public class ChannelDemo {

    static BigDecimal capacity = BigDecimal.valueOf(0);
    static BigDecimal position = BigDecimal.valueOf(0);

    public static void main(String[] args) {
        String src = "/Users/szp/Downloads/zh-cn_windows_11_consumer_editions_updated_dec_2021_x64_dvd_aecd9187.iso";

        String target = "/Users/szp/Downloads/system/zh-cn_windows_11_consumer_editions_updated_dec_2021_x64_dvd_aecd9187.iso";

        new Thread(() -> copyFile(new File(src), new File(target))).start();


        long msgLength = 0;
        System.out.print("已完成:");
        while (true) {
            BigDecimal finishRate = BigDecimal.valueOf(0);

            if (capacity.intValue() != 0) {
                finishRate = position.divide(capacity, 4, RoundingMode.HALF_UP);
            }
            String message = finishRate.multiply(BigDecimal.valueOf(100)) + "%" + "[" + position + "/" + capacity + "]";
            for (int i = 0; i < msgLength; i++) {
                System.out.print("\b");
            }
            System.out.print(message);
            msgLength = message.length();
            if (finishRate.intValue() == 1) {
                break;
            }
        }
    }

    public static void copyFile(File src, File target) {

        try (FileInputStream srcInput = new FileInputStream(src);
             FileOutputStream targetOutput = new FileOutputStream(target);
             FileChannel inputChannel = srcInput.getChannel();
             FileChannel outputChannel = targetOutput.getChannel()
        ) {
            capacity = BigDecimal.valueOf(inputChannel.size());
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024);

            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
                position = BigDecimal.valueOf(outputChannel.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
