package ioc.controller;

import ioc.ResponseDto;
import ioc.domain.chef.Pyro;
import ioc.framework.annotation.Autowired;
import ioc.framework.annotation.Controller;
import ioc.framework.annotation.GetMapping;

@Controller("/pyro")
public class PyroController {
    @Autowired
    private Pyro pyro;

    @GetMapping("/pizza")
    public ResponseDto getPizza() {
        return ResponseDto.from(pyro, pyro.createPizza());
    }

    @GetMapping("/pasta")
    public ResponseDto getPasta() {
        return ResponseDto.from(pyro, pyro.createPasta());
    }

    @GetMapping("/steak")
    public ResponseDto getSteak() {
        return ResponseDto.from(pyro, pyro.createSteak());
    }
}
