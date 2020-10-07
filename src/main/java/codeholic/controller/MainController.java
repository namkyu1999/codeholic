package codeholic.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import codeholic.service.ApiUtil;
import okhttp3.Response;


@RestController
@RequestMapping
@CrossOrigin(origins="*")       
class MainController{

    @Autowired
    private ApiUtil apiUtil;
    
    @GetMapping("/")
    public String helloworld(final HttpServletRequest req) {   
        return "helloworld";
    }

    
    @GetMapping("/authorized")
    public String helloworld2() {   
        return "Only user!!!";
    }

}
