package ar.edu.itba.paw.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {
    @RequestMapping("/user/{id}/news/{newsId}")
    public ModelAndView helloworld(
        @PathVariable("id") final int id,
        @PathVariable("newsId") final int newsId,
        @RequestParam(value = "name", defaultValue = "PAW") final String name) {

        final ModelAndView mav = new ModelAndView("helloworld");
        mav.addObject("greeting", String.format("Hello user with id %1$d!", id));
        mav.addObject("userId", id);
        mav.addObject("newsId", newsId);
        return mav;
    }
}
