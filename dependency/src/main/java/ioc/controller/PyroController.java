package ioc.controller;

import ioc.ResponseDto;
import ioc.domain.chef.Pyro;
import ioc.framework.annotation.Autowired;
import ioc.framework.annotation.Controller;
import ioc.framework.annotation.RequestMapping;

import static ioc.framework.annotation.RequestMethod.GET;

@Controller("/pyro")
public class PyroController {
    @Autowired
    private Pyro pyro;

    @RequestMapping(method = GET, value = "/pizza")
    public ResponseDto getPizza() {
        return ResponseDto.from(pyro, pyro.createPizza());
    }

    @RequestMapping(method = GET, value = "/pasta")
    public ResponseDto getPasta() {
        return ResponseDto.from(pyro, pyro.createPasta());
    }

    @RequestMapping(method = GET, value = "/steak")
    public ResponseDto getSteak() {
        return ResponseDto.from(pyro, pyro.createSteak());
    }
}
