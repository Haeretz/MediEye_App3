package kr.co.dgall.medieye_app3.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	/** MessageUtils */
	@Resource
    private MessageSource source;

    static MessageSource messageSource;
  
    @PostConstruct
    public void initialize() {
    messageSource = source;
    }
    
    public static String getMessage(String messageCd) {
    	return messageSource.getMessage(messageCd, null, null);
    }
    
    public static String getMessage(String messageCd, Object[] messageArgs) {
    	return messageSource.getMessage(messageCd, messageArgs, null);
    }
	
	/** IP 얻어오기*/
	public static String getClientIp(HttpServletRequest request) throws UnknownHostException{
		
		String ip = request.getHeader("X-Forwarded-For");
		
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("X-Real-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("X-RealIP"); 
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getHeader("REMOTE_ADDR");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	        ip = request.getRemoteAddr(); 
	    }
	    if(ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1")) 
	    {
	        InetAddress address = InetAddress.getLocalHost();
	        ip = address.getHostName() + "/" + address.getHostAddress();
	    }
	    return ip;
	}
	
	/** User-Agent 얻어오기*/
	public static String getClientUserAgent(HttpServletRequest request) { 

	    String userAgent = request.getHeader("User-Agent"); 
	    String checkClient = ""; 

	    // IE 
	    if(userAgent.indexOf("Trident") > -1) { 
	        checkClient = "ie"; 
	    // Edge	
	    }else if(userAgent.indexOf("Edge") > -1) { 
	        checkClient = "edge";
	    // Naver Whale 	
	    }else if(userAgent.indexOf("Whale") > -1) { 
	        checkClient = "whale"; 
	    // Opera
	    }else if(userAgent.indexOf("Opera") > -1 || userAgent.indexOf("OPR") > -1) { 
	        checkClient = "opera"; 
	    // Firefox
	    }else if(userAgent.indexOf("Firefox") > -1) { 
	        checkClient = "firefox"; 
	    // Safari
	    }else if(userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1 ) { 
	        checkClient = "safari"; 
	    // Chrome	
	    }else if(userAgent.indexOf("Chrome") > -1) { 
	        checkClient = "chrome"; 
	    } 
	    return checkClient; 
	}
}
