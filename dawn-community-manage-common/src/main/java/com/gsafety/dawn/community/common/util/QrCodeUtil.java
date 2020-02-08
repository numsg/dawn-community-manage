package com.gsafety.dawn.community.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class QrCodeUtil {
    private static final Logger logger = LoggerFactory.getLogger(QrCodeUtil.class);

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "PNG";
    private static final int DEFAULT_SIZE = 300;
    private static final String BASE64_HEAD = "data:image/png;base64,";

    public static String encodeImage(String content, Integer qrCodeSize) {
        Map hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);

        if (qrCodeSize == null || qrCodeSize <= 0) {
            qrCodeSize = DEFAULT_SIZE;
        }

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, FORMAT_NAME, byteArrayOutputStream);
            return BASE64_HEAD + Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            logger.error("Encode to base64 image failed, content: {}, error: {}", content, e.getCause(), e);
        }

        return "";
    }

    public static void main(String[] args) {
        String s = QrCodeUtil.encodeImage("123", null);
        System.out.println(s);
    }

}
