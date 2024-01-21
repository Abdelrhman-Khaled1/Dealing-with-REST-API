package com.abdelrhman.social.media.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {


    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @GetMapping("hello-world")
    public String HelloWorld(){
        return "Hello world";
    }

    @GetMapping("hello-world-i18n")
    public String HelloWorldInternationalization(){

        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(
                "hello.world.message",
                null,
                "Default",
                locale
        );
    }

}
