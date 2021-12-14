
package project.library.desktop.service.interfaces;

import java.util.List;
import project.library.desktop.model.Login;

/**
 *
 * @Ulviyye Ibrahimli
 */
public interface ILoginService {

    List<Login> logins() throws Exception;

    boolean addLogin(Login login) throws Exception;

    boolean updateLogin(Long loginId, Login login) throws Exception;

    boolean deleteLogin(Long loginId) throws Exception;

    Login getLoginById(Long id) throws Exception;

    Login checkLogin(String userName, String password, String roleName) throws Exception;

}
