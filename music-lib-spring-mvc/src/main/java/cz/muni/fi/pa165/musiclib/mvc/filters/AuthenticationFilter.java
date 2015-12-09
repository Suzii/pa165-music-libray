package cz.muni.fi.pa165.musiclib.mvc.filters;

import cz.muni.fi.pa165.musiclib.dto.UserAuthenticationDTO;
import cz.muni.fi.pa165.musiclib.dto.UserDTO;
import cz.muni.fi.pa165.musiclib.facade.UserFacade;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Restricts all sites of application from unauthenticated users.
 *  
 * @author Zuzana Dankovcikova
 * @version 08/12/2015
 */
@WebFilter(urlPatterns = {"/home/*", "/song/*", "/album/*", "/musician/*", "/genre/*", "/user/*"})
public class AuthenticationFilter implements Filter {
    
    final static Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String auth = request.getHeader("Authorization");
        if (auth == null) {
            if (!request.getRequestURI().equals(request.getContextPath() + "/login")) {
                response.sendRedirect(request.getContextPath() + "/login");
            }
//            response401(response);
//            return;
        }
//        String[] creds = parseAuthHeader(auth);
//        String logname = creds[0];
//        String password = creds[1];
//
//        UserFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext()).getBean(UserFacade.class);
//        UserDTO matchingUser = userFacade.findUserByEmail(logname);
//        if (matchingUser == null) {
//            log.warn("no user with email {}", logname);
//            response401(response);
//            return;
//        }
//
//        UserAuthenticationDTO userAuthenticateDTO = new UserAuthenticationDTO();
//        userAuthenticateDTO.setUserId(matchingUser.getId());
//        userAuthenticateDTO.setPassword(password);
//
//        if (!userFacade.authenticate(userAuthenticateDTO)) {
//            log.warn("invalid credentials: user={} password={}", creds[0], creds[1]);
//            response401(response);
//            return;
//        }
//        request.setAttribute("authenticatedUser", matchingUser);
//        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    private String[] parseAuthHeader(String auth) {
        return new String(DatatypeConverter.parseBase64Binary(auth.split(" ")[1])).split(":", 2);
    }

    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=\"type email and password\"");
        response.getWriter().println("<html><body><h1>401 Unauthorized access</h1>Requested web site is only accessible to admin users...</body></html>");
    }
}
