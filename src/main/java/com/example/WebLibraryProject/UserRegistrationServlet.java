package com.example.WebLibraryProject;

import project.library.desktop.dao.CountryDao;
import project.library.desktop.dao.NationalityDao;
import project.library.desktop.dao.ReaderDao;
import project.library.desktop.dao.StatusDao;
import project.library.desktop.model.Country;
import project.library.desktop.model.Nationality;
import project.library.desktop.model.Reader;
import project.library.desktop.model.Status;
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

@WebServlet(name = "userServlet", value = "/us")
public class UserRegistrationServlet extends HttpServlet {
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
        CountryDao countryDao = new CountryDao();
        CountryService countryService = new CountryService(countryDao);
        NationalityDao nationalityDao = new NationalityDao();
        NationalityService nationalityService = new NationalityService(nationalityDao);
        StatusDao statusDao = new StatusDao();
        StatusService statusService = new StatusService(statusDao);
        ReaderDao readerDao = new ReaderDao();
        ReaderService readerService = new ReaderService(readerDao);
        PrintWriter pr = response.getWriter();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        try {
            if (action.equalsIgnoreCase("userCombo")) {
                List<Country> countryList = countryService.getCountryList();
                List<Nationality> nationalityList = nationalityService.getNationalityList();
                request.setAttribute("nationalityList", nationalityList);
                request.setAttribute("countryList", countryList);
                List<Status> statusList = statusService.getStatusList();
                request.setAttribute("statusList", statusList);
                address = "user/views/registration.jsp";
            }
            if (action.equalsIgnoreCase("addUser")) {
                List<Reader> readerList = readerService.getReaderList();
                String name = request.getParameter("userFirstName");
                String lastName = request.getParameter("userLastName");
                String fatherName = request.getParameter("userFatherName");
                String dob = request.getParameter("userDob");
                String gender = request.getParameter("userGender");
                String nationality = request.getParameter("userNationality");
                String country = request.getParameter("userCountry");
                String email = request.getParameter("userEmail");
                String phone = request.getParameter("userPhone");
                String addres = request.getParameter("userAddress");
                String status = request.getParameter("userStatus");
                String password = request.getParameter("userPassword");
                String confPassword = request.getParameter("userConfPassword");
                System.out.println(password+"    "+confPassword);
                Reader reader = new Reader();
                boolean checkEmail = false;
                boolean checkConfPassword = false;
                for (Reader r : readerList) {
                    if (r.geteMail()!=null){
                    if (r.geteMail().equals(email)) {
                        checkEmail = true;
                    }}else {
                        System.out.println("Do not check unique");
                    }
                }
                if (checkEmail) {
                    response.setContentType("text/html");
                    pr.write("repeatedEmail");
                } else {
                    if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$")) {
                        if (!password.equals(confPassword)) {
                            checkConfPassword = true;
                        }
                        if (checkConfPassword) {
                            response.setContentType("text/html");
                            pr.write("invalidConfPass");
                        } else {
                            reader.setFirstName(name);
                            reader.setLastName(lastName);
                            reader.setFatherName(fatherName);
                            reader.seteMail(email);
                            if (dob!=null && !dob.isEmpty()){
                            reader.setDob(df.parse(dob));}
                            reader.setGender(gender);
                            if (!nationality.equals("0")) {
                                Nationality nationalityObj = new Nationality();
                                nationalityObj.setId(Long.parseLong(nationality));
                                reader.setNationality(nationalityObj);
                            }
                            if (!country.equals("0")) {
                                Country countryObj = new Country();
                                countryObj.setId(Long.parseLong(country));
                                reader.setCountry(countryObj);
                            }
                            if (!status.equals("0")) {
                                Status statusObj = new Status();
                                statusObj.setId(Long.parseLong(status));
                                reader.setStatus(statusObj);
                            }
                            reader.setPhone1(phone);
                            reader.setHomeNumber(addres);
                            reader.setPassword(password);
                            boolean isAddReader=readerService.addReaderWeb(reader);
                            System.out.println(isAddReader);
                            if (isAddReader){
                                response.setContentType("text/html");
                                pr.write("success");
                            }
                        }
                    } else {
                        response.setContentType("text/html");
                        pr.write("invalidPassword");
                    }

                }
            }if(action.equalsIgnoreCase("userProfile")){

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