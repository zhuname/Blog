package com.cz.mts.system.imageCreate;


import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
public class ImgCreateOptions {

    /**
     * 绘制的背景图
     */
    private BufferedImage bgImg;


    /**
     * 生成图片的宽
     */
    private Integer imgW;


    private Font font = new Font("宋体", Font.PLAIN, 18);

    /**
     * 字体色
     */
    private Color fontColor = Color.BLACK;


    /**
     * 两边边距
     */
    private int leftPadding;

    /**
     * 上边距
     */
    private int topPadding;

    /**
     * 底边距
     */
    private int bottomPadding;

    /**
     * 行距
     */
    private int linePadding;


    private AlignStyle alignStyle;

    public BufferedImage getBgImg() {
        return bgImg;
    }

    public void setBgImg(BufferedImage bgImg) {
        this.bgImg = bgImg;
    }

    public Integer getImgW() {
        return imgW;
    }

    public void setImgW(Integer imgW) {
        this.imgW = imgW;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public int getLeftPadding() {
        return leftPadding;
    }

    public void setLeftPadding(int leftPadding) {
        this.leftPadding = leftPadding;
    }

    public int getTopPadding() {
        return topPadding;
    }

    public void setTopPadding(int topPadding) {
        this.topPadding = topPadding;
    }

    public int getBottomPadding() {
        return bottomPadding;
    }

    public void setBottomPadding(int bottomPadding) {
        this.bottomPadding = bottomPadding;
    }

    public int getLinePadding() {
        return linePadding;
    }

    public void setLinePadding(int linePadding) {
        this.linePadding = linePadding;
    }

    public AlignStyle getAlignStyle() {
        return alignStyle;
    }

    public void setAlignStyle(AlignStyle alignStyle) {
        this.alignStyle = alignStyle;
    }

    /**
     * 对齐方式
     */
    public enum AlignStyle {
        LEFT,
        CENTER,
        RIGHT;


        private static Map<String, AlignStyle> map = new HashMap<>();

        static {
            for(AlignStyle style: AlignStyle.values()) {
                map.put(style.name(), style);
            }
        }


        public static AlignStyle getStyle(String name) {
            name = name.toUpperCase();
            if (map.containsKey(name)) {
                return map.get(name);
            }

            return LEFT;
        }
    }
}


//作者：YiHui
//        链接：https://hacpai.com/article/1503051097154
//        来源：黑客派
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
