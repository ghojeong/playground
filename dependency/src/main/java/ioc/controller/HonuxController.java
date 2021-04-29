package ioc.controller;

import ioc.ResponseDto;
import ioc.domain.chef.Honux;
import ioc.framework.annotation.Autowired;
import ioc.framework.annotation.Controller;
import ioc.framework.annotation.RequestMapping;

import static ioc.framework.annotation.RequestMethod.GET;

@Controller("/honux")
public class HonuxController {
    @Autowired
    private Honux honux;

    @RequestMapping(method = GET, value = "/pizza")
    public ResponseDto getPizza() {
        return ResponseDto.from(honux, honux.createPizza());
    }

    @RequestMapping(method = GET, value = "/pasta")
    public ResponseDto getPasta() {
        return ResponseDto.from(honux, honux.createPasta());
    }

    @RequestMapping(method = GET, value = "/steak")
    public ResponseDto getSteak() {
        return ResponseDto.from(honux, honux.createSteak());
    }
}
