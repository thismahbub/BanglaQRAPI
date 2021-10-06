package host.digitalsoft.wallet.service;

import host.digitalsoft.wallet.dto.BanglaQRRootObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UtilQRParserService {

	private Map<String, String> qrTypeMap = null;

	public UtilQRParserService() {

		lengthMap = genereteMaxLength();
		rootObjectTitleMap = genereteTitle();

		qrTypeMap = new HashMap<>(); // for rootObjectMap.put("01", "Point of Initiation Method");
		qrTypeMap.put("11", "Static QR Code");
		qrTypeMap.put("12", "Dynamic QR Code");
	}

	public Map<String, String> lengthMap = new HashMap<>();

	public Map<String, String> genereteMaxLength() {
		Map<String, String> lengthMap = new HashMap<>();

		lengthMap.put("00", "02");
		lengthMap.put("01", "02");
		int i = 2;
		while (i <= 51) {
			String mapKey = i + "";
			if (mapKey.length() < 2) {
				mapKey = "0" + i;
				lengthMap.put(mapKey + "", "99");
			} else {
				lengthMap.put(mapKey, "99");
			}
			i++;
		}

		lengthMap.put("52", "04");
		lengthMap.put("53", "03");
		lengthMap.put("54", "13");
		lengthMap.put("55", "02");
		lengthMap.put("56", "13");
		lengthMap.put("57", "05");
		lengthMap.put("58", "02");
		lengthMap.put("59", "25");
		lengthMap.put("60", "15");
		lengthMap.put("61", "10");
		lengthMap.put("62", "99");
		lengthMap.put("63", "04");
		lengthMap.put("64", "99");
		i = 65;
		while (i <= 99) {
			lengthMap.put(i + "", "99");
			i++;
		}

		return lengthMap;
	}

	public String getMaxLegnth(String key) {
		String result = "";

		for (Map.Entry<String, String> entry : lengthMap.entrySet()) {

			if (key.equals(entry.getKey())) {
				result = entry.getValue();
				break;
			}
		}

		return result;
	}

	private Map<String, String> rootObjectTitleMap = null;

	public Map<String, String> genereteTitle() {
		Map<String, String> titleMap = new HashMap<>();

		titleMap.put("00", "Payload Format Indicator");
		titleMap.put("01", "Point of Initiation Method");
		titleMap.put("02", "Visa");
		titleMap.put("03", "Visa");
		titleMap.put("04", "Mastercard");
		titleMap.put("05", "Mastercard");
		titleMap.put("06", "EMVCo");
		titleMap.put("07", "EMVCo");
		titleMap.put("08", "EMVCo");
		titleMap.put("09", "Discover");
		titleMap.put("10", "Discover");
		titleMap.put("11", "Amex");
		titleMap.put("12", "Amex");
		titleMap.put("13", "JCB");
		titleMap.put("14", "JCB");
		titleMap.put("15", "UnionPay");
		titleMap.put("16", "UnionPay");
		int i = 17;
		while (i <= 25) {
			titleMap.put(i + "", "EMVCo");
			i++;
		}
		titleMap.put("26", "NPSB");
		titleMap.put("27", "NPSB");
		i = 28;
		while (i <= 51) {
			titleMap.put(i + "", "RFU"); // RFU by National QR Code Standard Working Group of Bangladesh
			i++;
		}

		titleMap.put("52", "Merchant Category Code");
		titleMap.put("53", "Transaction Currency");
		titleMap.put("54", "Transaction Amount");
		titleMap.put("55", "Tip or Convenience Indicator");
		titleMap.put("56", "Value of Convenience Fee Fixed");
		titleMap.put("57", "Value of Convenience Fee Percentage");
		titleMap.put("58", "Country Code");
		titleMap.put("59", "Merchant Name");
		titleMap.put("60", "Merchant City");
		titleMap.put("61", "Postal Code");
		titleMap.put("62", "Additional Data Field Template");
		titleMap.put("63", "Cyclic Redundancy Check (CRC)");
		titleMap.put("64", "Merchant Information Language Template");
		i = 65;
		while (i <= 79) {
			titleMap.put(i + "", "RFU for EMVCo");
			i++;
		}
		i = 80;
		while (i <= 99) {
			titleMap.put(i + "", "Unreserved Templates");
			i++;
		}

		return titleMap;
	}

	// String qrData =
	// "0002010102120415999999999999999520452045303050540592.225802BD5904Test6005Dhaka6110888888888864230002en0104DEMO0205DEMO163048913";
	// String qrData =
	// "00020101021203165555555555555555520452045303050540510.015802BD5906Pathao6005Dhaka610412126304291A";
	// String qrData =
	// "00020101021226180014bd.com.nccbank520452045303050540899999.995802BD5906Aarong6005Dhaka6104121264250002en0106Aarong0205Dhaka63046A96";

	/**
	 * Get node object from QR text
	 * @param QR text
	 * @param startIndex of QR text to read
	 * @return List<BanglaQRRootObject> items
	 */
	public List<BanglaQRRootObject> getNodeValue(String qrData, int startIndex) {

		List<BanglaQRRootObject> resultItems = new ArrayList<>();
		int valueEndIndex = 0;

		while (valueEndIndex < qrData.length()) {
			int keyLength = startIndex + 2;
			int lengthLength = keyLength + 2;

			String key = qrData.substring(startIndex, keyLength);
			String length = qrData.substring(keyLength, lengthLength);

			valueEndIndex = Integer.parseInt(length);
			valueEndIndex = lengthLength + valueEndIndex;

			String value = qrData.substring(lengthLength, valueEndIndex);

			System.out.println("key = " + key);
			System.out.println("length = " + length);

			if (rootObjectTitleMap.containsKey(key)) {
				if (key.equals("01") && value.equals("11")) {
					System.out.println(qrTypeMap.get("11") + " = 11");

				} else if (key.equals("01") && value.equals("12")) {
					System.out.println(qrTypeMap.get("12") + " = 12");

				} else {
					System.out.println(rootObjectTitleMap.get(key) + " = " + value);
				}

			}

			BanglaQRRootObject rootObj = new BanglaQRRootObject();
			rootObj.setKey(key);
			rootObj.setLength(length);
			rootObj.setMaxlength(getMaxLegnth(rootObj.getKey()));
			rootObj.setValue(value);
			resultItems.add(rootObj);

			System.out.println("-------------------------------");

			startIndex = valueEndIndex;
		}

		return resultItems;
	}

	/**
	 * crc16 checksum generator
	 * 
	 * @param text
	 * @return String CRC
	 */
	public String createCRC16CCITT(String text) {

		int crc = 0xFFFF; // initial value
		int polynomial = 0x1021; // 0001 0000 0010 0001 (0, 5, 12)

		byte[] bytes = text.getBytes();

		for (byte b : bytes) {
			for (int i = 0; i < 8; i++) {
				boolean bit = ((b >> (7 - i) & 1) == 1);
				boolean c15 = ((crc >> 15 & 1) == 1);
				crc <<= 1;
				if (c15 ^ bit)
					crc ^= polynomial;
			}
		}

		crc &= 0xffff;
		String result = Integer.toHexString(crc).toUpperCase();
		if (result.length() < 4) {
			result = "0" + result;
		}
		return result;
	}

	// M - Mandatory
	// C - Conditional
	// O - Optional

	// 59 M, 58 M, 60 M, 52 M, 61 M, 02-51 or 26 M NPSB, 53 M,
	// 54 C, 55 C
	// 62 O
	// 64 O
	// 80-99 O

	/**
	 * When root value 63 (CRC) object is in the List<BanglaQRRootObject>
	 * @param items of BanglaQRRootObject
	 * @return QR text
	 */
	public String generateTextWithCRC(List<BanglaQRRootObject> items) {
		String result = "";

		String crc = "";

		for (BanglaQRRootObject banglaQRRootObject : items) {
			// System.out.println(banglaQRRootObject.toString());

			if (banglaQRRootObject.getKey().equals("63")) {
				// crc = banglaQRRootObject.getValue();
				// Refresh CRC every time

			} else {
				result = result + banglaQRRootObject.getKey();
				result = result + banglaQRRootObject.getLength();
				result = result + banglaQRRootObject.getValue();
			}

		}

		result = result + "6304"; // Without CRC
		crc = createCRC16CCITT(result); // CRC
		result = result + crc;

		return result;
	}

	public String generateMerchantId(String merchantId) {
		String result = "";

		String acquirerInstitutionType = "010201"; // 01 = Bank
		String acquirerID = "02040160"; // 0160 = NCC Bank
		String merchantData = "03";
		String merchantIdLength = merchantId.length() + "";
		if (merchantIdLength.length() < 2) {
			merchantIdLength = "0" + merchantId.length();
		}
		merchantData = merchantData + merchantIdLength + merchantId;

		result = acquirerInstitutionType + acquirerID + merchantData;

		return result;
	}

	public String generateNPSBQR(List<BanglaQRRootObject> items, String merchantIdValue) {
		
		BanglaQRRootObject v26 = null;
		
		int i = 0;
		for (BanglaQRRootObject banglaQRRootObject : items) {

			if (banglaQRRootObject.getKey().equals("26")) {
				v26 = banglaQRRootObject;
				break;
			}
			i++;
		}
		v26.setMaxlength(getMaxLegnth(v26.getKey()));
		String merchantId = generateMerchantId(merchantIdValue);
		v26.setValue(merchantId);
		v26.setLength(merchantId.length() + "");
		
		items.set(i, v26);
		
		String result = generateTextWithCRC(items);

		return result;
	}

}