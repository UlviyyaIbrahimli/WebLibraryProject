/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.library.desktop.service;

import java.util.List;
import project.library.desktop.dao.LanguageDao;
import project.library.desktop.model.Language;
import project.library.desktop.service.interfaces.ILanguageService;

/**
 *
 * @author user
 */
public class LanguageService  implements ILanguageService{
    LanguageDao languageDao;

    public LanguageService() {
    }

    public LanguageService(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }
    
    @Override
    public List<Language> getLanguageList() throws Exception {
        return  languageDao.getLanguageList();
    }
    
}
