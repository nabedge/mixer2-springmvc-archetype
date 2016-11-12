#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.view;

import ${package}.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mixer2.Mixer2Engine;
import org.mixer2.jaxb.xhtml.Div;
import org.mixer2.jaxb.xhtml.Html;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {AppConfig.class})
public class IndexViewTest {

    @Autowired
    private Mixer2Engine mixer2Engine;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private WebApplicationContext wac;

    private String template = "classpath:/m2template/index.html";

    @Test
    public void test() throws Exception {
        IndexView indexView = new IndexView();
        wac.getAutowireCapableBeanFactory().autowireBean(indexView);

        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();

        Html tmpl = mixer2Engine.loadHtmlTemplate(resourceLoader.getResource(template).getInputStream());
        ExtendedModelMap model = new ExtendedModelMap();
        model.addAttribute("helloMessage", "foo");

        Html html = indexView.renderHtml(tmpl, model, req, res);

        assertThat(html.getById("message", Div.class).getContent().get(0).toString()).isEqualTo("foo");
    }
}
