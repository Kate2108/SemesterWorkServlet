package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    private static final String[] forbiddenURLs = {"/profile", "/stats", "/groups", "/my_achievements", "/schedule"};
    private static final String forbiddenStatusURL = "/admin";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getRequestURI().substring(req.getContextPath().length());
        Object authorized = req.getSession().getAttribute("is_authorized");

        if(authorized == null || authorized.toString().equals("false")) {
            for (String str : forbiddenURLs) {
                if (path.equals(str)) {
                    resp.sendRedirect(req.getContextPath() + "/authorization");
                    return;
                }
            }
        }
        if(path.equals("/admin")){
            Object status = req.getSession().getAttribute("status");
            if (status == null || status.toString().equals("user")) {
                resp.sendRedirect(req.getContextPath());
                return;
            }
        }
        filterChain.doFilter(req, resp);
    }
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
