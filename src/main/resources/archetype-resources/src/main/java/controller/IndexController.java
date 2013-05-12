#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mixer2.Mixer2Engine;
import org.mixer2.jaxb.xhtml.Html;
import org.mixer2.springmvc.Mixer2XhtmlView;
import org.mixer2.xhtml.PathAjuster;
import org.mixer2.xhtml.exception.TagTypeUnmatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ${package}.util.RequestUtil;
import ${package}.view.IndexHelper;

@Controller
public class IndexController {

    private static Log log = LogFactory.getLog(IndexController.class);

    @Autowired
    protected Mixer2Engine mixer2Engine;

    @Autowired
    protected ResourceLoader resourceLoader;

    private String mainTemplate = "classpath:m2mockup/m2template/index.html";

    @RequestMapping(value = "/")
    public Mixer2XhtmlView index() throws TagTypeUnmatchException, IOException {
        log.debug("going index()");

        Html html = mixer2Engine.loadHtmlTemplate(resourceLoader.getResource(
                mainTemplate).getInputStream());

        IndexHelper.replaceMessage(html);

        // replace static file path
        Pattern pattern = Pattern.compile("^\\.+/.*m2static/(.*)$");
        String ctx = RequestUtil.getContextPath();
        PathAjuster.replacePath(html, pattern, ctx + "/m2static/$1");

        return new Mixer2XhtmlView(mixer2Engine, html);
    }

}
