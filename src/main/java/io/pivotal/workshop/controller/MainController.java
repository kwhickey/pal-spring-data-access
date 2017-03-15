package io.pivotal.workshop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.pivotal.workshop.domain.Marketing;

import static java.util.Arrays.asList;


@RestController
public class MainController {

    @RequestMapping("/")
    public ModelAndView home() {

        List<Marketing> offers = asList(
                new Marketing("Product", "Easy Payments! Only $1 at month with 500 code snippets."),
                new Marketing("GitHub Gists", "Easy Integration with GitHub Gists. Share with everybody!"),

                new Marketing("REST API", "Powerful REST API to manage your Code Snippets in your own Programming Language"),
                new Marketing("OAuth Security", "Keep you Code Snippets secured with Authentication and Authorization based on OAuth.")
        );


        Map<String, Object> model = new HashMap<>();
        model.put("today", new Date());
        model.put("offers", offers);


        return new ModelAndView("views/home", model);
    }

}