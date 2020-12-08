package com.hotel.utils.qrCode;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;
/**
 * @author wxy
 */
public class TwoDimensionCodeImage implements QRCodeImage {
   /**
    * @Description 将图片加载到内存中
    * @author wxy
    * @create 2020/4/14 19:40
    **/
    BufferedImage bufImg;
    public TwoDimensionCodeImage(BufferedImage bufImg) {
        this.bufImg = bufImg;
    }
    @Override
    public int getHeight() {
        return bufImg.getHeight();
    }
    @Override
    public int getPixel(int x, int y) {
        return bufImg.getRGB(x, y);
    }
    @Override
    public int getWidth() {
        return bufImg.getWidth();
    }
}