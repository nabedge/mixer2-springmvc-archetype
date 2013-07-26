#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.view;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mixer2.jaxb.xhtml.Div;
import org.mixer2.jaxb.xhtml.Html;
import org.mixer2.springmvc.AbstractMixer2XhtmlView;
import org.mixer2.xhtml.PathAjuster;
import org.mixer2.xhtml.exception.TagTypeUnmatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class IndexView extends AbstractMixer2XhtmlView {
    
    @Autowired
    protected ResourceLoader resourceLoader;

    @Override
    protected Html createHtml(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException, TagTypeUnmatchException {
        
        String helloMessage = (String) model.get("helloMessage");

        // load html template
        String mainTemplate = "classpath:m2mockup/m2template/index.html";
        Html html = getMixer2Engine().loadHtmlTemplate(
                resourceLoader.getResource(mainTemplate).getInputStream());

        Div div = html.getById("message", Div.class);
        div.unsetContent();
        div.getContent().add(helloMessage);

        // replace static file path
        Pattern pattern = Pattern.compile("^\\.+/.*m2static/(.*)$");
        String ctx = request.getContextPath();
        PathAjuster.replacePath(html, pattern, ctx + "/m2static/$1");

        return html;
    }
    
}
