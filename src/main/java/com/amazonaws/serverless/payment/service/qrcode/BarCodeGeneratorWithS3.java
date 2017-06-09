package com.amazonaws.serverless.payment.service.qrcode;

public class BarCodeGeneratorWithS3 {
	
}

///*
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.LinkedHashMap;
//
//import org.apache.commons.io.IOUtils;
//
//import com.amazonaws.AmazonClientException;
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
////import com.passkit.sdk.BWILogic;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
///*
//@SuppressWarnings("serial")
//class InvalidInputException extends Exception {
//	InvalidInputException(String s) {
//		super(s);
//	}
//}
//*/
//
//public class BarCodeGeneratorWithS3 {
//	/*
//	 * public static void main(String[] args) {
//	 * System.out.println("Runs the gobbler.");
//	 * 
//	 * try { String myJson = "{\"message\": \"test\",\"format\":\"qrcode\"}";
//	 * InputStream is = new ByteArrayInputStream( myJson.getBytes() );
//	 * 
//	 * 
//	 * BarcodeGenerator b = new BarcodeGenerator(); b.generateBarcode(is,
//	 * System.out, null); } catch (Exception e) { System.out.print("Dang"); } }
//	 */
//
//	public void generateBarcode(InputStream inputStream, OutputStream outputStream, Context context)
//			throws InvalidInputException, Exception {
//		try {
//			// convert inputStream to string
//			String myJson = IOUtils.toString(inputStream, "UTF-8");
//
//			System.out.println(myJson);
//
//			// convert string to LinkedHashMap
//			ObjectMapper mapper = new ObjectMapper();
//			LinkedHashMap<String, String> input = mapper.readValue(myJson,
//					new TypeReference<LinkedHashMap<String, String>>() {
//					});
//
//			String message = input.get("message");
//			String barcodeFormat = input.get("format");
//			Integer height = null;
//			Integer width = null;
//			String encoding = null;
//
//			try {
//				height = input.get("height") == "" ? null : Integer.parseInt(input.get("height"));
//			} catch (Exception e) {
//			}
//			try {
//				width = input.get("width") == "" ? null : Integer.parseInt(input.get("width"));
//			} catch (Exception e) {
//			}
//			try {
//				encoding = input.get("encoding") == "" ? "UTF-8" : input.get("encoding");
//			} catch (Exception e) {
//			}
//
//			getBarcode(message, barcodeFormat, height, width, encoding);
//
//		} catch (InvalidInputException ie) {
//			throw ie;
//		} catch (com.google.zxing.WriterException we) {
//			throw we;
//		} catch (Exception e) {
//			throw e;
//		}
//	}
//
//	public String getBarcode(String message, String barcodeFormat, Integer height, Integer width, String encoding)
//			throws com.google.zxing.WriterException, InvalidInputException, Exception {
//		try {
//
//			if (message == "") {
//				throw new InvalidInputException(
//						"Invalid Input Error - Invalid Message Error. Message Input = [" + message + "].");
//			}
//
//			// Image Writing Objects
//			MultiFormatWriter w = new MultiFormatWriter();
//			BitMatrix bm = null;
//
//			// Setting Encoding Format
//			LinkedHashMap<EncodeHintType, Object> encodingFormat = new LinkedHashMap<EncodeHintType, Object>();
//			encodingFormat.put(EncodeHintType.CHARACTER_SET, encoding);
//			encodingFormat.put(EncodeHintType.MARGIN, 0);
//
//			// Barcode Format List
//			LinkedHashMap<String, Integer> barcodeFormats = new LinkedHashMap<String, Integer>();
//			barcodeFormats.put("pdf417", 1);
//			barcodeFormats.put("code128", 2);
//			barcodeFormats.put("qrcode", 3);
//			barcodeFormats.put("aztec", 4);
//
//			// Identifying Target Barcode
//			int targetBarcodeFormat = 0;
//			try {
//				targetBarcodeFormat = barcodeFormats.get(barcodeFormat);
//			} catch (Exception e) {
//				throw new InvalidInputException(
//						"Invalid Input Error - Invalid Barcode Format Error. BarcodeFormat Format Input = ["
//								+ barcodeFormat
//								+ "]. Supported Barcode Formats are qrcode, pdf417, code128 and aztec.");
//			}
//			switch (targetBarcodeFormat) {
//			case 1: // pdf417
//				if (height == null) {
//					height = 53;
//				}
//				if (width == null) {
//					width = 210;
//				}
//				bm = new BitMatrix(width, height);
//				bm = w.encode(message, BarcodeFormat.PDF_417, width, height, encodingFormat);
//				break;
//			case 2: // code128
//				if (height == null) {
//					height = 53;
//				}
//				if (width == null) {
//					width = 210;
//				}
//				bm = new BitMatrix(width, height);
//				bm = w.encode(message, BarcodeFormat.CODE_128, width, height, encodingFormat);
//				break;
//			case 3: // qrcode
//				if (height == null) {
//					height = 105;
//				}
//				if (width == null) {
//					width = 105;
//				}
//				bm = new BitMatrix(height, height);
//				bm = w.encode(message, BarcodeFormat.QR_CODE, height, height, encodingFormat);
//				break;
//			case 4: // aztec
//				if (height == null) {
//					height = 105;
//				}
//				if (width == null) {
//					width = 105;
//				}
//				bm = new BitMatrix(height, height);
//				bm = w.encode(message, BarcodeFormat.AZTEC, height, height, encodingFormat);
//				break;
//			default:
//				throw new InvalidInputException(
//						"Invalid Input Error - Invalid Barcode Format Error. BarcodeFormat Format Input = ["
//								+ barcodeFormat
//								+ "]. Supported Barcode Formats are qrcode, pdf417, code128 and aztec.");
//			}
//
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			MatrixToImageWriter.writeToStream(bm, "png", baos);
//
//			return uploadFileToS3Bucket(baos);
//
//			// outputStream.write(Base64.getEncoder().encode(baos.toByteArray()));
//		} catch (InvalidInputException ie) {
//			throw ie;
//		} catch (com.google.zxing.WriterException we) {
//			throw new InvalidInputException(
//					"Invalid Input Error - Invalid Encoding Format Error. Encoding Format Input = [" + encoding + "].");
//		} catch (Exception e) {
//			throw new Exception("Invalid Input Error: Please Check Your Input.");
//		}
//	}
//
//	private static String uploadFileToS3Bucket(ByteArrayOutputStream baos) {
//		try {
//
//            AmazonS3Client s3client = new AmazonS3Client(new DefaultAWSCredentialsProviderChain());
//			//s3client.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));
//			//s3client.setRegion(Region.getRegion(Regions.EU_WEST_1));
//	        
//			InputStream stream = new ByteArrayInputStream(baos.toByteArray());
//			ObjectMetadata meta = new ObjectMetadata();
//			
//			meta.setContentLength(baos.toByteArray().length);
//			meta.setContentType("image/png");
//			meta.setHeader("Accept", "application/octet-stream");
//			s3client.putObject("aab-loyalty-ride", "qrcode", stream, meta);
//			s3client.setObjectAcl("aab-loyalty-ride", "qrcode", CannedAccessControlList.PublicRead);
//
//			return "https://s3-eu-west-1.amazonaws.com/aab-loyalty-ride/qrCode";
//
//		} catch (AmazonServiceException ase) {
//			System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
//					+ "to Amazon S3, but was rejected with an error response" + " for some reason.");
//			System.out.println("Error Message:    " + ase.getMessage());
//			System.out.println("HTTP Status Code: " + ase.getStatusCode());
//			System.out.println("AWS Error Code:   " + ase.getErrorCode());
//			System.out.println("Error Type:       " + ase.getErrorType());
//			System.out.println("Request ID:       " + ase.getRequestId());
//		} catch (AmazonClientException ace) {
//			System.out.println("Caught an AmazonClientException, which " + "means the client encountered "
//					+ "an internal error while trying to " + "communicate with S3, "
//					+ "such as not being able to access the network.");
//			System.out.println("Error Message: " + ace.getMessage());
//		}catch (Exception ace) {
//			ace.printStackTrace();
//			System.out.println("Caught an AmazonClientException, which " + "means the client encountered "
//					+ "an internal error while trying to " + "communicate with S3, "
//					+ "such as not being able to access the network.");
//			System.out.println("Error Message: " + ace.getMessage());
//		}
//		return "";
//	}
//
//}
//*/