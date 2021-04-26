package ioc.controller;

import ioc.ResponseDto;
import ioc.domain.chef.Gordon;
import ioc.framework.annotation.Autowired;
import ioc.framework.annotation.Controller;
import ioc.framework.annotation.GetMapping;

@Controller("/gordon")
public class GordonController {
    @Autowired
    private Gordon gordon;

    @GetMapping("/pizza")
    public ResponseDto getPizza() {
        return ResponseDto.from(gordon, gordon.createPizza());
    }

    @GetMapping("/pasta")
    public ResponseDto getPasta() {
        return ResponseDto.from(gordon, gordon.createPasta());
    }

    @GetMapping("/steak")
    public ResponseDto getSteak() {
        return ResponseDto.from(gordon, gordon.createSteak());
    }
}
