package com.example.WebLibraryProject;

import project.library.desktop.dao.CountryDao;
import project.library.desktop.dao.NationalityDao;
import project.library.desktop.dao.ReaderDao;
import project.library.desktop.dao.StatusDao;
import project.library.desktop.model.*;
import project.library.desktop.service.CountryService;
import project.library.desktop.service.NationalityService;
import project.library.desktop.service.ReaderService;
import project.library.desktop.service.StatusService;

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
import java.util.List;
import java.util.Locale;

@WebServlet(name = "readerServlet", value = "/rs")

public class ReaderServlet extends HttpServlet {
    public void init() {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = null;
        String address = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        PrintWriter pr = response.getWriter();
        ReaderDao readerDao = new ReaderDao();
        ReaderService readerService = new ReaderService(readerDao);
        CountryDao countryDao = new CountryDao();
        CountryService countryService = new CountryService(countryDao);
        NationalityDao nationalityDao = new NationalityDao();
        NationalityService nationalityService = new NationalityService(nationalityDao);
        StatusDao statusDao = new StatusDao();
        StatusService statusService = new StatusService(statusDao);
        try {
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            if (action.equalsIgnoreCase("readerData")) {
                List<Reader> readerList = readerService.getReaderList();
                request.setAttribute("readerList", readerList);
                address = "admin/getDataTable/readerData.jsp";
            }
            if (action.equalsIgnoreCase("readerCmb")) {
                List<Country> countryList = countryService.getCountryList();
                request.setAttribute("countryList", countryList);
                List<Nationality> nationalityList = nationalityService.getNationalityList();
                request.setAttribute("nationalityList", nationalityList);
                List<Status> statusList = statusService.getStatusList();
                System.out.println(statusList);
                request.setAttribute("statusList", statusList);
                address = "admin/views/addReader.jsp";
            }
            if (action.equalsIgnoreCase("addReader")) {
                String fName = request.getParameter("fName");
                String lName = request.getParameter("lName");
                String fatName = request.getParameter("fatName");
                String dob = request.getParameter("dob");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String country = request.getParameter("country");
                String status = request.getParameter("status");
                String city = request.getParameter("city");
                String addres = request.getParameter("address");
                String password = request.getParameter("password");
                String gender = request.getParameter("gender");
                String nation = request.getParameter("nation");
                String note = request.getParameter("note");
                Reader reader = new Reader();
                boolean addReader;
                boolean isExits = false;
                List<Reader> readerList = readerService.getReaderList();
                for (Reader r : readerList) {
                    if (r.geteMail() != null) {
                        if (r.geteMail().equals(email)) {
                            isExits = true;
                        }
                    }
                }
                if (isExits) {
                    response.setContentType("text/html");
                    pr.write("RepeatedEmailWarning");
                } else {
                    if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$")) {
                        reader.setFirstName(fName);
                        reader.setLastName(lName);
                        reader.setFatherName(fatName);
                        reader.setPassword(password);
                        reader.setCity(city);
                        reader.setAdditionInfo(note);
                        System.out.println("add   " + dob);
                        if (dob != null && !dob.isEmpty()) {
                            reader.setDob(df.parse(dob));
                            System.out.println("add   " + df.parse(dob));
                        }
                        reader.setHomeNumber(addres);
                        if (!country.equals("0")) {
                            Country countryObj = new Country();
                            countryObj.setId(Long.parseLong(country));
                            reader.setCountry(countryObj);
                        }
                        reader.seteMail(email);
                        reader.setPhone1(phone);
                        if (status != null) {
                            Status statusObj = new Status();
                            statusObj.setId(Long.parseLong(status));
                            reader.setStatus(statusObj);
                        }
                        reader.setGender(gender);
                        if (!nation.equals("0")) {
                            Nationality nationality = new Nationality();
                            nationality.setId(Long.parseLong(nation));
                            reader.setNationality(nationality);
                        }
                        addReader = readerService.addReaderWeb(reader);
                        if (addReader) {
                            response.setContentType("text/html");
                            pr.write("success");
                        }
                    } else {
                        response.setContentType("text/html");
                        pr.write("wrongPassword");
                    }
                }
            }
            if (action.equalsIgnoreCase("editReader")) {
                List<Status> statusList = statusService.getStatusList();
                request.setAttribute("statusList", statusList);
                Long readerId = Long.parseLong(request.getParameter("readerId"));
                List<Country> countryList = countryService.getCountryList();
                request.setAttribute("countryList", countryList);
                List<Nationality> nationalityList = nationalityService.getNationalityList();
                request.setAttribute("nationalityList", nationalityList);
                Reader reader = readerService.getReaderById(readerId);
                request.setAttribute("reader", reader);
                address = "admin/editData/editReader.jsp";
            }
            if (action.equalsIgnoreCase("updateReader")) {
                List<Reader> readerList = readerService.getReaderList();
                System.out.println(request.getParameter("readerId"));
                Long readerId = Long.parseLong(request.getParameter("readerId"));
                Reader currentReader = readerService.getReaderById(readerId);
                String fName = request.getParameter("fName");
                String lName = request.getParameter("lName");
                String fatName = request.getParameter("fatName");
                String dob = request.getParameter("dob");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String country = request.getParameter("country");
                String status = request.getParameter("status");
                String city = request.getParameter("city");
                String addres = request.getParameter("address");
                String password = request.getParameter("password");
                String gender = request.getParameter("gender");
                String nation = request.getParameter("nation");
                String note = request.getParameter("note");
                String currentEmail = currentReader.geteMail();
                boolean isExits = false;
                if (!currentEmail.isEmpty() && currentEmail != null) {
                    if (!currentEmail.equals(email)) {
                        for (Reader r : readerList) {
                            if (r.geteMail() != null && !r.geteMail().isEmpty()) {
                                if (r.geteMail().equals(email)) {
                                    isExits = true;
                                }

                            } else {
                                System.out.println("Empty email found! (Note Email checked is fail!)");
                            }
                        }
                    }
                } else {
                    System.out.println("Current  email is empty! Note Email checked is fail!");
                }
                if (isExits) {
                    response.setContentType("text/html");
                    pr.write("warningEmail");
                } else {
                    if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$")) {
                        Reader reader = new Reader();
                        reader.setFirstName(fName);
                        reader.setLastName(lName);
                        reader.setFatherName(fatName);
                        reader.seteMail(email);
                        reader.setGender(gender);
                        reader.setPassword(password);
                        System.out.println("ffffg   " + dob);
                        if (!dob.isEmpty() && dob != null) {
                            reader.setDob(df.parse(dob));
                            System.out.println("ggtgh    " + df.parse(dob));
                        }
                        reader.setCity(city);
                        reader.setPhone1(phone);
                        reader.setHomeNumber(addres);
                        reader.setPassword(password);
                        reader.setAdditionInfo(note);
                        if (!country.equals("0")) {
                            Country countryObj = new Country();
                            countryObj.setId(Long.parseLong(country));
                            reader.setCountry(countryObj);
                        }
                        if (!nation.equals("0")) {
                            Nationality nationalityObj = new Nationality();
                            nationalityObj.setId(Long.parseLong(nation));
                            reader.setNationality(nationalityObj);
                        }
                        if (!status.equals("0")) {
                            Status statusObj = new Status();
                            statusObj.setId(Long.parseLong(status));
                            reader.setStatus(statusObj);
                        }
                        boolean updateReader = readerService.updateReaderForWeb(reader, readerId);
                        if (updateReader) {
                            response.setContentType("text/html");
                            pr.write("success");
                        }
                    } else {
                        response.setContentType("text/html");
                        pr.write("wrongPassword");
                    }
                }
            }

            if (action.equalsIgnoreCase("readerAdvanceCmb")) {
                List<Country> countryList = countryService.getCountryList();
                List<Nationality> nationalityList = nationalityService.getNationalityList();
                List<Status> statusList = statusService.getStatusList();
                request.setAttribute("countryList", countryList);
                request.setAttribute("nationalityList", nationalityList);
                request.setAttribute("statusList", statusList);
                address = "admin/advance/readerAdvanceSearch.jsp";
            }
            if (action.equalsIgnoreCase("advReader")) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String statusId = request.getParameter("status");
                String gender = request.getParameter("gender");
                String activity = request.getParameter("activity");
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                String nationalityId = request.getParameter("nationality");
                String countryId = request.getParameter("country");
                String dob=request.getParameter("dob");
                AdvanceSearch search = new AdvanceSearch();
                search.setCase1(firstName);
                search.setCase2(lastName);
                search.setCase4(gender);
                search.setCase5(activity);
                search.setCase6(startDate);
                search.setCase7(endDate);
                search.setCase3(statusId);
                search.setDate(dob);
                System.out.println("nat  " + nationalityId);
                if (nationalityId != null) {
                    search.setId1(Long.parseLong(nationalityId));
                }
                if (countryId != null) {
                    search.setId2(Long.parseLong(countryId));
                }

                List<Reader> readerList = readerService.advanceSearch(search);
                request.setAttribute("readerList", readerList);
                address = "/admin/advance/resultOfSearch/AdvReader.jsp";
            }
            if (action.equalsIgnoreCase("deleteReader")) {
                Long readerId = Long.parseLong(request.getParameter("readerId"));
                boolean deleteReader = readerService.deleteReader(readerId);
                if (deleteReader) {
                    response.setContentType("text/html");
                    pr.write("success");
                }
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
