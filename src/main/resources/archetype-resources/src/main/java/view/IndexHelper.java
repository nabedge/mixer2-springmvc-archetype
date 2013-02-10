#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.view;

import org.mixer2.jaxb.xhtml.Div;
import org.mixer2.jaxb.xhtml.Html;
import org.mixer2.xhtml.exception.TagTypeUnmatchException;

/**
 * helper for IndexController
 *
 */
public class IndexHelper {

    public static void replaceMessage(Html html) throws TagTypeUnmatchException {
        Div div = html.getById("message", Div.class);
        div.getContent().clear();
        div.getContent().add("Hello World !");
    }

}
