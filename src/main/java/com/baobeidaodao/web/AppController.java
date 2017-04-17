package com.baobeidaodao.web;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DaoDao on 2017-04-16.
 * hello world
 */
@RestController
@RequestMapping(value = "/")
public class AppController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelMap app() {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("hello world");
        return modelMap;
    }
}
