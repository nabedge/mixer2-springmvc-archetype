#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mixer2.jaxb.xhtml.Div;
import org.mixer2.jaxb.xhtml.Html;
import org.mixer2.springmvc.Mixer2XhtmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
        "classpath:mvc-dispatcher-servlet.xml" })
public class IndexControllerTest extends IndexController {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Test
    public void testIndex() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        RequestContextHolder
                .setRequestAttributes(new ServletRequestAttributes(request));

        // request init here
        request.setMethod("GET");
        request.setRequestURI("/");

        // execute Controller method and get result as ModelAndView
        Object handler = handlerMapping.getHandler(request).getHandler();
        ModelAndView modelAndView = handlerAdapter.handle(request, response,
                handler);

        // reverse html string to Html object.
        Mixer2XhtmlView view = (Mixer2XhtmlView)modelAndView.getView();
        Html html = view.getHtml();

        // assert
        Div div = html.getById("message", Div.class);
        // item name
        assertThat(div.getContent().get(0)
                .toString(), is("Hello World !"));

    }

}
