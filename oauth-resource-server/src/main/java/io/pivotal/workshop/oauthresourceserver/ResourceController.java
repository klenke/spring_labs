package io.pivotal.workshop.oauthresourceserver;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/")
    public String helloSecured(){
        return "Hello - Secured";
    }


}
