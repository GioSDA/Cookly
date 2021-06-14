package ly.cook.cookly.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public CustomAuthenticationSuccessHandler() {}

    public CustomAuthenticationSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String redirectUrl = (String) session.getAttribute("url_prior_login");
        if (redirectUrl != null) {
            session.removeAttribute("url_prior_login");
            getRedirectStrategy().sendRedirect(request, response, redirectUrl);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ADMIN".equals(auth.getAuthority())) {
                response.sendRedirect("/dashboard");
            }
        }
    }

}
