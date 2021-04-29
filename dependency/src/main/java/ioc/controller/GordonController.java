package ioc.controller;

import ioc.ResponseDto;
import ioc.domain.chef.Gordon;
import ioc.framework.annotation.Autowired;
import ioc.framework.annotation.Controller;
import ioc.framework.annotation.RequestMapping;

import static ioc.framework.annotation.RequestMethod.GET;

@Controller("/gordon")
public class GordonController {
    @Autowired
    private Gordon gordon;

    @RequestMapping(method = GET, value = "/pizza")
    public ResponseDto getPizza() {
        return ResponseDto.from(gordon, gordon.createPizza());
    }

    @RequestMapping(method = GET, value = "/pasta")
    public ResponseDto getPasta() {
        return ResponseDto.from(gordon, gordon.createPasta());
    }

    @RequestMapping(method = GET, value = "/steak")
    public ResponseDto getSteak() {
        return ResponseDto.from(gordon, gordon.createSteak());
    }
}
