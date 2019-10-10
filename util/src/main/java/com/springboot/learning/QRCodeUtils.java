package com.springboot.learning;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:QrCodeUtils
 * @Description: TODO
 */
@Component
public class QRCodeUtils {

    /**
     * 生成二维码
     * @param file
     */
    public static void createQRCode(File file){
        int width=300;
        int height=300;
        String format="png";
        String content="www.baidu.com";

        //定义二维码的参数
        HashMap hints=new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");//字符集
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//纠正等级
        hints.put(EncodeHintType.MARGIN,2);//边距

        //生成二维码
        try {
            BitMatrix bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hints);
            MatrixToImageWriter.writeToPath(bitMatrix,format,file.toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
