package sbnz.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.app.service.SampleService;


@RestController
public class SampleController {

	private SampleService service;
	
	@Autowired
	public SampleController(SampleService service) {
		this.service = service;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test() {
		service.test();
	}

}
