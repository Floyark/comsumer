package com.zzt.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.support.Parameter;
import com.alibaba.fastjson.JSONObject;
import io.micrometer.core.instrument.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.PictureService;
import service.ThemeService;


import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {


    @Reference
    ThemeService themeService;
    @Reference
    PictureService pictureService;

    @RequestMapping("/toGetTheme")
    public @ResponseBody  JSONObject getTheme(@RequestParam("cunrrent") int cunrrent,@RequestParam("pageSize") int pageSize){
        JSONObject allTheme = themeService.getAllTheme(cunrrent, pageSize);
        return allTheme;
    }
    @RequestMapping("/getPicData")
    public @ResponseBody JSONObject getPic(){
        JSONObject picData=pictureService.getPicData();
        return picData;
    }
}
