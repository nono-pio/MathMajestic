package api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import math.element.Element;
import math.string_converter.LatexConverter;

@SpringBootApplication
@RestController
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@GetMapping("/")
	public String test() {
		return "test";
	}

	@GetMapping("/api/v1/get/")
	public String input(@RequestParam(value = "input", defaultValue = "0") String input) {
		return input;
	}

	@CrossOrigin
	@GetMapping("/api/v1/")
	public d api(@RequestParam(value = "input", defaultValue = "0") String input) {
		Element element = LatexConverter.convert(input);
		return new d(element.toLaTeX());
	}

	record d(String str) {
	}

	@GetMapping("/api/v1/tree/")
	public Tree tree(@RequestParam(value = "input", defaultValue = "0") String input) {
		return new Tree(LatexConverter.convert(input));
	}

	record Response(String input, Element element, String output) {
	}
}
