package com.example.WebLibraryProject;

import project.library.desktop.dao.SubjectDao;
import project.library.desktop.model.Subjects;
import project.library.desktop.service.SubjectService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "SubjectServlet", value = "/ss")


public class SubjectServlet extends HttpServlet {

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
        PrintWriter pr = response.getWriter();
        String action = null;
        String address = null;
        SubjectDao subjectDao = new SubjectDao();
        SubjectService subjectService = new SubjectService(subjectDao);
        try {
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            if (action.equalsIgnoreCase("subjectData")) {
                List<Subjects> subjectsList = subjectService.getSubjectList();
                request.setAttribute("subjectList", subjectsList);
                address = "admin/getDataTable/subjectData.jsp";
            }
            if (action.equalsIgnoreCase("addSubject")) {
                String subName = request.getParameter("subName");
                String subInfo = request.getParameter("subInfo");
                List<Subjects> subjectsList = subjectService.getSubjectList();
                boolean exitSubject = true;
                for (Subjects ss : subjectsList) {
                    if (ss.getSubjectName().equalsIgnoreCase(subName)) {
                        exitSubject = false;
                    }
                }
                if (!exitSubject) {
                    response.setContentType("text/html");
                    pr.write("warning");
                } else {
                    Subjects subjects = new Subjects();
                    subjects.setSubjectName(subName);
                    subjects.setSubjectInfo(subInfo);
                    boolean addSubject = subjectService.addSubject(subjects);
                    if (addSubject) {
                        System.out.println("Subject Added");
                        response.setContentType("text/html");
                        pr.write("success");
                    } else {
                        System.out.println("Subject Does Not Added");
                    }
                }
            }
            if (action.equalsIgnoreCase("editSubject")) {
                Long subjectId = Long.parseLong(request.getParameter("subjectId"));
                Subjects subject = subjectService.getSubjectById(subjectId);
                request.setAttribute("subject", subject);
                address = "admin/editData/editSubject.jsp";
            }
            if (action.equalsIgnoreCase("updateSubject")) {
                String subName = request.getParameter("subName");
                String subInfo = request.getParameter("subInfo");
                Long subjectId = Long.parseLong(request.getParameter("subjectId"));
                boolean isExits = false;
                List<Subjects> subjectsList = subjectService.getSubjectList();
                Subjects subjectCurrent = subjectService.getSubjectById(subjectId);
                if (!subName.equals(subjectCurrent.getSubjectName())) {
                    for (Subjects s : subjectsList) {
                        if (subName.equalsIgnoreCase(s.getSubjectName())) {
                            isExits = true;
                        }
                    }
                }
                if (!isExits) {
                    Subjects subject = new Subjects();
                    subject.setSubjectName(subName);
                    subject.setSubjectInfo(subInfo);
                    boolean updateSubject = subjectService.updateSubject(subject, subjectId);
                    if (updateSubject) {
                        System.out.println("subject Updated");
                        response.setContentType("text/html");
                        pr.write("success");
                    } else {
                        System.out.println("subject does not update");
                    }
                } else {
                    response.setContentType("text/html");
                    pr.write("warning");
                }

            }
            if (action.equalsIgnoreCase("deleteSubject")) {
                Long subjectId = Long.parseLong(request.getParameter("subjectId"));
                boolean deleteSubject = subjectService.deleteSubject(subjectId);
                if (deleteSubject) {
                    System.out.println("Subject Delete");
                } else {
                    System.out.println("Deleting is  fail");
                }
                response.setContentType("text/html");
                pr.write("success");
            }
            if (address != null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(address);
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
