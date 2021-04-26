package ioc.controller;

import ioc.ResponseDto;
import ioc.domain.chef.Honux;
import ioc.framework.annotation.Autowired;
import ioc.framework.annotation.Controller;
import ioc.framework.annotation.GetMapping;

@Controller("/honux")
public class HonuxController {
    @Autowired
    private Honux honux;

    @GetMapping("/pizza")
    public ResponseDto getPizza() {
        return ResponseDto.from(honux, honux.createPizza());
    }

    @GetMapping("/pasta")
    public ResponseDto getPasta() {
        return ResponseDto.from(honux, honux.createPasta());
    }

    @GetMapping("/steak")
    public ResponseDto getSteak() {
        return ResponseDto.from(honux, honux.createSteak());
    }
}
