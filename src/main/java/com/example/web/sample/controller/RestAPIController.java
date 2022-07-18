package com.example.web.sample.controller;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.sample.form.RestAPIForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RestAPIController {
	@GetMapping("/restapi")
	public String api() {
		System.out.println("api");
		return "api call";
	}

	@PostMapping("/form")
	public String form(@ModelAttribute RestAPIForm form) {
		System.out.println("form");
		form.num3 =10;
		System.out.println(ToStringBuilder.reflectionToString(form));

		return getJSONFromObj(form);
	}

	// JavaオブジェクトをJSONに変換
	private String getJSONFromObj(Object obj) {

		String json = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}

}
