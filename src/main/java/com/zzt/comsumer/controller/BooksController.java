package com.zzt.comsumer.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.BookService;

import java.util.Map;

   @Controller
   public class BooksController {

      @Reference
      BookService bookService;

   @RequestMapping("/allBooks")
   public @ResponseBody Object getAllBooks(){
     return "yes";
   }
}

