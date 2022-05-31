package bookshop.auxiliary;

import bookshop.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class Utils {
    private final IAuthenticationFacade authenticationFacade;

    public Utils(IAuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }


    public User getUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public boolean isLogged() {
        Authentication authentication = authenticationFacade.getAuthentication();
        try {
            User user = (User)authentication.getPrincipal();
            return true;
        } catch (ClassCastException e) {
            return false;
        }
    }
}
