package com.ping.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 提供网站关键字搜索服务的控制器类
 * @author tainex
 */
@Controller
public class SearchController {

    private Image

    @RequestMapping(name = "search/asy/{value}", method = RequestMethod.POST)
    public String checkSearch(@PathVariable("value") String keyword) {

    }

}
