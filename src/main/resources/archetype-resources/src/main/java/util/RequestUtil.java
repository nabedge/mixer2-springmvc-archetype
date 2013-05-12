#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestUtil {

	public static String getContextPath() {
		HttpServletRequest request = getRequest();
		String ctx = null;
		if (request != null) {
			ctx = request.getContextPath();
		}
		return ctx;
	}
	
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = null;
        if (sra != null) {
        	request = sra.getRequest();
        }
        return request;
    }

}
