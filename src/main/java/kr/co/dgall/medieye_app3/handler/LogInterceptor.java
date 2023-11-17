//package kr.co.dgall.medieye_app3.handler;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//
//public class LogInterceptor implements HandlerInterceptor {
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		
//		String requestURI = request.getRequestURI();
//		System.out.println("[interceptor] requestURI : " + requestURI);
//		
//		return true;  // false -> 이후에 진행을 하지 않는다.
//	}
//
//}
