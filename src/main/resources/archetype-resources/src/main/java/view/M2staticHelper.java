#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.view;

import org.mixer2.jaxb.xhtml.Img;
import org.mixer2.jaxb.xhtml.Input;
import org.mixer2.jaxb.xhtml.Link;
import org.mixer2.xhtml.AbstractJaxb;

import ${package}.util.RequestUtil;

/**
 * helper for change static file path on some tags.
 *
 */
public class M2staticHelper {

    private static String convertPath(String str) {

        String ctx = RequestUtil.getRequest().getContextPath();

        if (!str.startsWith("http") && str.matches(".*/m2static/.*")) {
            str = ctx + "/m2static" + str.split("/m2static", 2)[1];
        }

        return str;
    }

    /**
     * replace path.
     * <p>
     * example.
     * </p>
     * <p>
     * before: &lt;img src="../../m2static/img/foo.png" /&gt;
     * </p>
     * <p>
     * after: &lt;img src="/[contextPath]/m2static/img/foo.png" /&gt;
     * </p>
     * <p>
     * if tag has no src or href attribute, do nothing.
     * </p>
     * @param ab tag object.
     */
    public static void replaceM2staticPath(AbstractJaxb ab) {
        // <img src="" />
        for (Img img : ab.getDescendants(Img.class)) {
            if (img.isSetSrc()) {
                String src = img.getSrc();
                img.setSrc(convertPath(src));
            }
        }
        // <link style="text/css" href="" />
        for (Link link : ab.getDescendants(Link.class)) {
            if (link.isSetHref()) {
                String href = link.getHref();
                link.setHref(convertPath(href));
            }
        }
        // <input type="image" src="" />
        for (Input input : ab.getDescendants(Input.class)) {
            if (input.isSetSrc()) {
                String src = input.getSrc();
                input.setSrc(convertPath(src));
            }
        }

        // TODO shuld add code if you use tags bellow.

        // /// src attribute
        // audio
        // embed
        // iframe
        // script
        // source
        // track
        // video
        // /// href attribute
        // area
        // base
    }

}
