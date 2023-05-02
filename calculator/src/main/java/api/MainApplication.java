package api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import math.ParentClass.Element;
import math.string_converter.StringConverter;

@SpringBootApplication
@RestController
public class MainApplication {

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }
  
  @GetMapping("/")
  public String test()
  {
	  return "test";
  }
  
  @GetMapping("/api/v1/get/")
  public String input(@RequestParam(value = "input", defaultValue = "0") String input)
  {
	  return input;
  }
  
  @GetMapping("/api/v1/")
  public Response api(@RequestParam(value = "input", defaultValue = "0") String input)
  {
	  Element element = new StringConverter(input).toElement();
	  Response response = new Response(input, element, element.toString());
	  return response;
  }
  
  @GetMapping("/api/v1/tree/")
  public Tree tree(@RequestParam(value = "input", defaultValue = "0") String input)
  {
	  return new Tree(new StringConverter(input).toElement());
  }
  
  record Response(String input, Element element, String output) {}
}
