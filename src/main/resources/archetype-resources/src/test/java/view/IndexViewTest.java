#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.view;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mixer2.Mixer2Engine;
import org.mixer2.jaxb.xhtml.Div;
import org.mixer2.jaxb.xhtml.Html;
import org.mixer2.spring.webmvc.AbstractMixer2XhtmlView;
import org.mixer2.spring.webmvc.Mixer2XhtmlViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.View;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
        "classpath:mvc-dispatcher-servlet.xml" })
public class IndexViewTest {

    @Autowired
    private Mixer2XhtmlViewResolver resolver;
    
    @Autowired
    private Mixer2Engine mixer2Engine;

    @Test
    public void testIndexView() throws Exception {
        
        View view = resolver.resolveViewName("index", null);
        assertTrue(view instanceof AbstractMixer2XhtmlView);
        AbstractMixer2XhtmlView m2View = (AbstractMixer2XhtmlView) view;

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(
                request));

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("helloMessage", "Hello World !");

        m2View.render(model, request, response);
        Html html = mixer2Engine.loadHtmlTemplate(response.getContentAsString());

        // assert
        Div div = html.getById("message", Div.class);
        // item name
        assertThat(div.getContent().get(0)
                .toString(), is("Hello World !"));

    }

}
