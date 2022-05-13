package com.mtcode.mblogapi.util;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

/**
 * @author TangMingZhang
 * @date 2022/5/13
 */
public class PicUtils {

    // 精度，递归压缩的比率
    private static final double ACCURACY = 0.8;
    // 压缩后的图片最大宽度
    private static final int DES_MAX_WIDTH = 1960;
    // 压缩后端图片最大高度
    private static final int DES_MAX_HEIGHT = 1080;

    /**
     *  递归压缩图片
     *
     * @param bytes 图片字节码
     * @param fileSize 指定图片大小，单位kb
     * @return 压缩后的图片字节码
     * @throws IOException IOException
     */
    private static byte[] compressCycle(byte[] bytes, long fileSize) throws IOException {
        long srcFileSizeJPG = bytes.length;
        // 2、判断大小，如果小于指定，不压缩；
        if (srcFileSizeJPG <= fileSize * 1024) {
            return bytes;
        }
        // 计算宽高
        BufferedImage bim = ImageIO.read(new ByteArrayInputStream(bytes));
        int srcWidth = bim.getWidth();
        int srcHeight = bim.getHeight();
        int desWidth = new BigDecimal(srcWidth).multiply(new BigDecimal(ACCURACY)).intValue();
        int desHeight = new BigDecimal(srcHeight).multiply(new BigDecimal(ACCURACY)).intValue();

        ByteArrayOutputStream baos = new ByteArrayOutputStream(); //字节输出流（写入到内存）
        Thumbnails.of(new ByteArrayInputStream(bytes)).
                size(desWidth, desHeight).
                outputQuality(ACCURACY).
                toOutputStream(baos);
        return compressCycle(baos.toByteArray(), fileSize);
    }

    /**
     * 将图片压缩到指定大小
     *
     * @param file 图片输入流
     * @param desFileSize 指定图片大小(单位kb)
     * @return 压缩后的图片字节码
     */
    public static byte[] compressPicForScale(InputStream file, long desFileSize) throws IOException {
        if (file == null) {
            return null;
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // 获取图片信息
            BufferedImage bim = ImageIO.read(file);
            int srcWidth = bim.getWidth();
            int srcHeight = bim.getHeight();

            // 转换成jpg
            Thumbnails.Builder<BufferedImage> builder = Thumbnails.of(bim).outputFormat("jpg");

            // 指定大小（宽或高超出会才会被缩放）
            if (srcWidth > DES_MAX_WIDTH || srcHeight > DES_MAX_HEIGHT) {
                builder.size(DES_MAX_WIDTH, DES_MAX_HEIGHT);
            } else {
                builder.size(srcWidth, srcHeight);
            }

            // 字节输出流（写入到内存）
            builder.toOutputStream(baos);
            // 递归压缩，直到目标文件大小
            return compressCycle(baos.toByteArray(), desFileSize);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
