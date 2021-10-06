package host.digitalsoft.wallet.service;

import host.digitalsoft.wallet.dto.BanglaQRRootObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Test {

	@Autowired
	UtilQRParserService parser;

	public void test() {
		// Generate CRC value
		System.out.println("Generate CRC value\n===========================");
		String text = "000201010212520452045303050540510.015802BD5906Pathao6005Dhaka6104121226290102010204016003111234567890x6304";
		String crc = parser.createCRC16CCITT(text);
		System.out.println("text with CRC : " + crc);
		System.out.println(text + crc); // Valid CRC Value: 291A

		// Parse QR data
		System.out.println("\n\nParse QR data\n===========================");
		String textWithCRC = text + crc;
		List<BanglaQRRootObject> itemsOld = parser.getNodeValue(textWithCRC, 0);

		// Generate Readable QR text
		System.out.println("\n\nGenerate Readable QR text\n===========================");
		String qrText = parser.generateTextWithCRC(itemsOld);
		System.out.println("Generate Readable QR text\n=======================");
		System.out.println(qrText);

		//Generate NPSB QR
		//itemsOld = parser.generateNPSBQR(itemsOld, "1234567890x");
		//qrText = parser.generateQRText(itemsOld);
		qrText = parser.generateNPSBQR(itemsOld, "1234567890x");
		System.out.println("Generate NPSB QR\n=======================");
		System.out.println(qrText);
	}

}