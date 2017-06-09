package com.amazonaws.serverless.payment.service.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeGenerator {

	public static void main(String args[]) throws WriterException, IOException {
		new QRCodeGenerator().getQRCode(
				"https://www.abnamro.nl/nl/ideal-betalen/index.html?randomizedstring=1593397761&trxid=0030002138196828");
	}

	public String getQRCode(String url) throws WriterException, IOException {
		String qrCodeText = url;
		String filePath = "qr.png";
		int size = 125;
		String fileType = "png";
		File qrFile = new File(filePath);
		System.out.println("--------1----------------");
		return createQRImage(qrFile, qrCodeText, size, fileType);
	}

	private static String createQRImage(File qrFile, String qrCodeText, int size, String fileType)
			throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		System.out.println("--------2----------------");
		Hashtable hintMap = new Hashtable();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();
		
		System.out.println("--------3----------------");

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		System.out.println("--------4----------------");
		ImageIO.write(image, fileType, qrFile);

		String s3UploadUrl = uploadFileToS3Bucket(qrFile);
		
		System.out.println("--------5----------------" + s3UploadUrl);

		return s3UploadUrl;

	}

	private static String uploadFileToS3Bucket(File qrFile) {
		try {
			AmazonS3 s3client = new AmazonS3Client(new DefaultAWSCredentialsProviderChain());
			PutObjectResult result = s3client.putObject(new PutObjectRequest("aab-loyalty-ride", "qrCode.png", qrFile)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			return "https://s3-eu-west-1.amazonaws.com/aab-loyalty-ride/qrCode.png";

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
					+ "to Amazon S3, but was rejected with an error response" + " for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which " + "means the client encountered "
					+ "an internal error while trying to " + "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		return "";
	}

}
