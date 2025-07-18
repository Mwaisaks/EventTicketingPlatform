package com.devtiro.EventTicketingPlatform.service.impl;

import com.devtiro.EventTicketingPlatform.domain.entity.QrCode;
import com.devtiro.EventTicketingPlatform.domain.entity.Ticket;
import com.devtiro.EventTicketingPlatform.domain.enums.QrCodeStatusEnum;
import com.devtiro.EventTicketingPlatform.exceptions.QrCodeGenerationException;
import com.devtiro.EventTicketingPlatform.exceptions.QrCodeNotFoundException;
import com.devtiro.EventTicketingPlatform.repository.QrCodeRepository;
import com.devtiro.EventTicketingPlatform.service.QrCodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class QrCodeServiceImpl implements QrCodeService {

    private static final int QR_HEIGHT = 300;
    private static final int QR_WIDTH = 300;

    private final QRCodeWriter qrCodeWriter;
    private final QrCodeRepository qrCodeRepository;

    @Override
    public QrCode generateQrCode(Ticket ticket) {

        try {
            UUID uniqueId = UUID.randomUUID();

            String qrCodeImage = generateQrCodeImage(uniqueId);

            QrCode qrCode = new QrCode();
            qrCode.setId(uniqueId);
            qrCode.setStatus(QrCodeStatusEnum.ACTIVE);
            qrCode.setValue(qrCodeImage);
            qrCode.setTicket(ticket);

            return qrCodeRepository.saveAndFlush(qrCode);

        }catch (IOException | WriterException ex){
            throw new QrCodeGenerationException("Failed to generate QR code", ex);
        }
    }

    @Override
    public byte[] getQrCodeImageForUserAndTicket(UUID userId, UUID ticketId) {
        QrCode qrCode = qrCodeRepository.findByTicketIdAndTicketPurchaserId(ticketId, userId)
                .orElseThrow(QrCodeNotFoundException::new);

        try {
            return Base64.getDecoder().decode(qrCode.getValue());
        } catch (IllegalArgumentException ex){
            log.error("Invalid bas64 QR code for ticket ID: {}", ticketId, ex);
            throw new QrCodeNotFoundException();
        }
    }


    private String generateQrCodeImage(UUID uniqueId) throws WriterException, IOException {

        BitMatrix bitMatrix = qrCodeWriter.encode(
                uniqueId.toString(),
                BarcodeFormat.QR_CODE,
                QR_WIDTH,
                QR_HEIGHT
        );

        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){

            ImageIO.write(qrCodeImage, "PNG", baos);
            byte[] imageBytes = baos.toByteArray();

            return Base64.getEncoder().encodeToString(imageBytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
