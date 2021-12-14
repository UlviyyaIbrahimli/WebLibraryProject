
package project.library.desktop.dao.interfaces;

import java.util.List;
import project.library.desktop.model.Language;

/**
 *
 * @author user
 */
public interface ILanguage {
    List<Language> getLanguageList()throws Exception;
    
}
