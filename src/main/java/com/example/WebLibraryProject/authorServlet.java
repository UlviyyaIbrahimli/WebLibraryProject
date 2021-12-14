package com.example.WebLibraryProject;

import project.library.desktop.dao.AuthorDao;
import project.library.desktop.dao.NationalityDao;
import project.library.desktop.dao.SubjectDao;
import project.library.desktop.model.AdvanceSearch;
import project.library.desktop.model.Author;
import project.library.desktop.model.Nationality;
import project.library.desktop.model.Subjects;
import project.library.desktop.service.AuthorService;
import project.library.desktop.service.NationalityService;
import project.library.desktop.service.SubjectService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "authorServlet", value = "/as")

public class authorServlet extends HttpServlet {
    public void init() {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        AuthorDao authorDao = new AuthorDao();
        DateFormat df = new SimpleDateFormat("yyyy-dd-MM", Locale.US);
        Long currentAuthorId = null;
        AuthorService authorService = new AuthorService(authorDao);
        SubjectDao subjectDao = new SubjectDao();
        SubjectService subjectService = new SubjectService(subjectDao);
        NationalityDao nationalityDao = new NationalityDao();
        NationalityService nationalityService = new NationalityService(nationalityDao);
        String action = null;
        String address = null;
        PrintWriter pr = response.getWriter();
        try {
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            if (action.equalsIgnoreCase("getAuthorData")) {
                List<Author> authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);
                address = "/admin/getDataTable/authorData.jsp";
            }
            if (action.equalsIgnoreCase("addAuthor")) {
                String fName = request.getParameter("aFName");
                String lName = request.getParameter("aLName");
                String fatName = request.getParameter("aFatName");
                String dob = request.getParameter("aDob");
                String deathDay = request.getParameter("aDeathDay");
                String gender = request.getParameter("aGender");
                String nationId = request.getParameter("aNation");
                String note = request.getParameter("aNote");
                String reward = request.getParameter("aReward");
                List<Author> authorList = authorService.getAuthorList();
                boolean isExit = false;
                for (Author a : authorList) {
                    if (fName.equalsIgnoreCase(a.getFirstName()) && lName.equalsIgnoreCase(a.getLastName())) {
                        isExit = true;

                    }
                }
                if (isExit) {
                    response.setContentType("text/html");
                    pr.write("warning");
                } else {
                    Author author = new Author();
                    author.setFirstName(fName);
                    author.setLastName(lName);
                    author.setFatherName(fatName);
                    author.setReward(reward);
                    author.setAuthorInfo(note);
                    if (dob != null && !dob.isEmpty()) {
                        author.setDob(df.parse(dob));
                    }
                    if (deathDay != null && !deathDay.isEmpty()) {
                        author.setDeadDate(df.parse(deathDay));
                    }
                    author.setGender(gender);
                    Nationality nationalityObj = new Nationality();
                    if (!nationId.equals("0")) {
                        nationalityObj.setId(Long.parseLong(nationId));
                        author.setNationality(nationalityObj);
                    } else {
                        author.setNationality(null);
                    }
                    boolean addSubject = false;
                    boolean isSelectSub = false;
                    boolean addAuthor = authorService.addAuthor(author);
                    if (addAuthor) {
                        String subjects = request.getParameter("aSubject");
                        if (!subjects.equals("0")) {
                            isSelectSub = true;
                            String[] arr = subjects.split(",");
                            currentAuthorId = authorService.addbookId(author);
                            for (String s : arr) {
                                addSubject = authorService.addListSubject(currentAuthorId, Long.parseLong(s));
                            }
                        }
                        if (!isSelectSub) {
                            response.setContentType("text/html");
                            pr.write("warningSub");
                        }
                    } else {
                        System.out.println("error author add");
                    }
                    if (addSubject) {
                        response.setContentType("text/html");
                        pr.write("success");
                    }
                }

            }
            if (action.equalsIgnoreCase("loadAuthorCmb")) {
                List<Subjects> subjectsList = subjectService.getSubjectList();
                List<Nationality> nationalityList = nationalityService.getNationalityList();
                request.setAttribute("subjectList", subjectsList);
                request.setAttribute("nationalityList", nationalityList);
                address = "/admin/views/addAuthor.jsp";
            }
            if (action.equalsIgnoreCase("editAuthor")) {
                Long authorId = Long.parseLong(request.getParameter("authorId"));
                Author author = authorService.getAuthorById(authorId);
                List<Nationality> nationalityList = nationalityService.getNationalityList();
                List<Subjects> subjectsList = subjectService.getSubjectList();
                request.setAttribute("nationalityList", nationalityList);
                request.setAttribute("subjectList", subjectsList);
                request.setAttribute("author", author);
                List<Author> ssss = authorService.subjectofAuthor(authorId);
                System.out.println(ssss);
                request.setAttribute("ssss", ssss);
                address = "/admin/editData/editAuthor.jsp";
            }
            if (action.equalsIgnoreCase("updateAuthor")) {
                String fName = request.getParameter("aFName");
                String lName = request.getParameter("aLName");
                String fatName = request.getParameter("aFatName");
                String dob = request.getParameter("aDob");
                String deathDay = request.getParameter("aDeathDay");
                String gender = request.getParameter("aGender");
                String nationId = request.getParameter("aNation");
                String note = request.getParameter("aNote");
                String reward = request.getParameter("aReward");
                Author author = new Author();
                author.setFirstName(fName);
                author.setLastName(lName);
                author.setFatherName(fatName);
                author.setReward(reward);
                author.setAuthorInfo(note);
                boolean isExit = false;
                List<Author> authorList = authorService.getAuthorList();
                Long authorId = Long.parseLong(request.getParameter("authorId"));
                Author currentAuthor = authorService.getAuthorById(authorId);
                if (!fName.equalsIgnoreCase(currentAuthor.getFirstName()) &&
                        !lName.equalsIgnoreCase(currentAuthor.getLastName())) {
                    for (Author a : authorList) {
                        if (author.getFirstName().equalsIgnoreCase(a.getFirstName()) && author.getLastName().equalsIgnoreCase(a.getLastName())) {
                            isExit = true;
                        }
                    }
                }
                if (dob != null && !dob.isEmpty()) {
                    Date date1 = df.parse(dob);
                    author.setDob(date1);
                }
                if (deathDay != null && !deathDay.isEmpty()) {
                    Date date2 = df.parse(deathDay);
                    author.setDeadDate(date2);
                }
                author.setGender(gender);
                Nationality nationalityObj = new Nationality();
                if (nationId != null) {
                    nationalityObj.setId(Long.parseLong(nationId));
                    author.setNationality(nationalityObj);
                }
                if (isExit) {
                    response.setContentType("text/html");
                    pr.write("warningForExitAuthor");
                } else {
                    boolean addAuthor = authorService.updateAuthor(authorId, author);
                    boolean addSub = false;
                    if (addAuthor) {
                        String subjects = request.getParameter("aSubject");
                        String[] arr = subjects.split(",");
                        boolean deleteAllSubjects = authorService.deletesubjectOfAuthor(authorId);
                        for (String s : arr) {
                            addSub = authorService.addListSubject(authorId, Long.parseLong(s));
                        }
                    } else {
                        System.out.println("error update add");
                    }
                    if (addSub) {
                        response.setContentType("text/html");
                        pr.write("success");
                    }
                }
            }
            if (action.equalsIgnoreCase("deleteAuthor")) {
                Long authorId = Long.parseLong(request.getParameter("authorId"));
                boolean deleteAuthor = authorService.deleteAuthor(authorId);
                if (deleteAuthor) {
                    System.out.println("Deleting success");
                    response.setContentType("text/html");
                    pr.write("success");
                } else {
                    System.out.println("Deleting is fail");
                }
            }
            if (action.equalsIgnoreCase("fillAdvSearchCmb")) {
                List<Nationality> nationalityList = nationalityService.getNationalityList();
                List<Subjects> subjectsList = subjectService.getSubjectList();
                request.setAttribute("nationalityList", nationalityList);
                request.setAttribute("subjectList", subjectsList);
                address = "admin/advance/authorAdvanceSearch.jsp";
            }
            if (action.equalsIgnoreCase("advanceSearchAuthor")) {

                String fName = request.getParameter("aAdvFirstName");
                String lName = request.getParameter("aAdvLastName");
                String subjectId = request.getParameter("aAdvSubject");
                String nationalityId = request.getParameter("aAdvNationality");
                System.out.println(nationalityId);
                AdvanceSearch advanceSearch = new AdvanceSearch();
                advanceSearch.setCase1(fName);
                advanceSearch.setCase2(lName);
                if (subjectId != null) {
                    advanceSearch.setId1(Long.parseLong(subjectId));
                }
                if (nationalityId != null) {
                    advanceSearch.setId2(Long.parseLong(nationalityId));
                }
                List<Author> authorList = authorService.advanceSearch(advanceSearch);
                request.setAttribute("authorList", authorList);
                address = "admin/advance/resultOfSearch/AdvAuthor.jsp";
            }if (action.equalsIgnoreCase("subjectOfAuthor")){
                Long authorId=Long.parseLong(request.getParameter("authorId"));
                Author author= authorService.getAuthorById(authorId);
                System.out.println(author.getFirstName()+"  fgfggf");
                request.setAttribute("author",author);
                List<Author> subjectOfAuthor=authorService.subjectofAuthor(authorId);
                request.setAttribute("subjectOfAuthor",subjectOfAuthor);
                address="admin/getDataTable/showAuthorSubject.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (address != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }
}
