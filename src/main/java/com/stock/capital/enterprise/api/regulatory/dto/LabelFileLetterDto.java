package com.stock.capital.enterprise.api.regulatory.dto;

import java.io.Serializable;

public class LabelFileLetterDto implements Serializable {

	private static final long serialVersionUID = -7997386449405481612L;

	private String id;

	private String letterAtachmentId;

	private String letterAtachmentName;

	private String letterAtachmentUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLetterAtachmentId() {
		return letterAtachmentId;
	}

	public void setLetterAtachmentId(String letterAtachmentId) {
		this.letterAtachmentId = letterAtachmentId;
	}

	public String getLetterAtachmentName() {
		return letterAtachmentName;
	}

	public void setLetterAtachmentName(String letterAtachmentName) {
		this.letterAtachmentName = letterAtachmentName;
	}

	public String getLetterAtachmentUrl() {
		return letterAtachmentUrl;
	}

	public void setLetterAtachmentUrl(String letterAtachmentUrl) {
		this.letterAtachmentUrl = letterAtachmentUrl;
	}

}
