package com.icliquebd.wallet.dto;

import java.io.Serializable;

public class BanglaQRRootObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String key = "";
	private String length = "";
	private String maxlength = "";
	private String value = "";

	public BanglaQRRootObject() {

	}

	public BanglaQRRootObject(String key, String length, String maxlength, String value) {
		this.key = key;
		this.length = length;
		this.maxlength = maxlength;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "BanglaQRRootObject [key=" + key + ", length=" + length + ", maxlength=" + maxlength + ", value=" + value
				+ "]";
	}

	

	

}
