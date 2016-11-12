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
import org.mixer2.spring.webmvc.AbstractMixer2XhtmlView;
import org.mixer2.xhtml.PathAdjuster;
import org.mixer2.xhtml.exception.TagTypeUnmatchException;

public class IndexView extends AbstractMixer2XhtmlView {
    
    @Override
    protected Html renderHtml(Html html, Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        String helloMessage = (String) model.get("helloMessage");

        Div div = html.getById("message", Div.class);
        div.unsetContent();
        div.getContent().add(helloMessage);

        // replace static file path
        Pattern pattern = Pattern.compile("^\\.+/.*static/(.*)$");
        String contextPath = request.getContextPath();
        if (contextPath == null) {
            contextPath = "";
        }
        PathAdjuster.replacePath(html, pattern, contextPath + "/$1");

        return html;
    }
}
