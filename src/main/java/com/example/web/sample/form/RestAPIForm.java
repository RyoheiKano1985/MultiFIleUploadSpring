package com.example.web.sample.form;

import lombok.Data;

@Data
public class RestAPIForm {

	private String id;
	private String name;
	private final int num = 20;
	public static int[] num2 = {20,10};
	public static Integer num3;
	

}
