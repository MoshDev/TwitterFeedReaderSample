package com.moshx.twitterfeeds.model;

import com.google.gson.annotations.SerializedName;

public class TwitterResponse {

	public boolean isSuccess() {
		return getErrors() == null || getErrors().length == 0;
	}

	@SerializedName("errors")
	private TwitterError[] errors;

	public TwitterError[] getErrors() {
		return errors;
	}

	public class TwitterError {

		@SerializedName("message")
		private String message;

		@SerializedName("code")
		private int code;

		public String getMessage() {
			return message;
		}

		public int getCode() {
			return code;
		}

	}
}
