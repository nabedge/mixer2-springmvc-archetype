#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mixer2.xhtml.exception.TagTypeUnmatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private static Log log = LogFactory.getLog(IndexController.class);
    
    @RequestMapping(value = "/")
    public String index(Model model) throws TagTypeUnmatchException, IOException {
        log.debug("going index()");
        String message = "Hello World !";
        model.addAttribute("helloMessage", message);
        return "indexView";
    }

}
