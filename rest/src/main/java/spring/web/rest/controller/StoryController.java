package spring.web.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
class StoryController {

  @GetMapping("/test")
  public String test() {
    return "Hallo Test laeuft";
  }

}
