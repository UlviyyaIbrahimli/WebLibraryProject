<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 4/10/2021
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="menuClass">
    <div class="navbar">
        <div class="dropdown">
            <button class="dropbtn">Dropdown
                <i class="fa fa-caret-down"></i>
            </button>
            <div class="dropdown-content">
                <button id="allBookInfo" class="menuItemClass">All Book List</button>
                <button class="menuItemClass">Random Book</button>
                <button id="libraryStructure" class="menuItemClass">About Us</button>
              <a href="#" style="text-decoration: none"><button  id="developerBtn" class="menuItemClass">Developer Center</button></a>
                <button id="contactBtn" class="menuItemClass">Contact</button>
            </div>
        </div>
        <button id="advSearchBook">Advance Search</button>

        <div id="searchDiv">
            <input type="text" placeholder="Search.." name="search" id="searchId">
            <button id="searchBtn"><i class="fa fa-search"></i></button>
        </div>
    </div>


</div>
<hr>

<div class="centerClassUser">
    <div id="bookContainer">
        <p id="newBookP">New Books</p>

        <div id="firstList" class="randomBookClass">
            <a href="#">
                <div id="innerFirst">ffv</div>
            </a>
            <a href="#">
                <div id="innerSecond">fvf</div>
            </a>
            <a href="#">
                <div id="innerThird">gfg</div>
            </a>
            <a href="#">
                <div id="innerFourth">gfg</div>
            </a>
        </div>
        <p id="classicBookP">Classic Books</p>
        <div id="secondList" class="randomBookClass">
            <div id="innerFirstClassic">
    <img src="${image}" style="width: 25px; height: 25px"/>
            </div>
            <div id="innerSecondClassic">fvf</div>
            <div id="innerThirdClassic">gfg</div>
            <div id="innerFourthClassic">gfg</div>
        </div>
        <p id="lovedBookP">Book We Loved</p>
        <div id="thirdList" class="randomBookClass">
            <div id="innerFirstLoved">ffv</div>
            <div id="innerSecondLoved">fvf</div>
            <div id="innerThirdLoved">gfg</div>
            <div id="innerFourthLoved">gfg</div>
        </div>
        <p id="techBookP">About Technology</p>
        <div id="fourthList" class="randomBookClass">
            <div id="innerFirstTech">ffv</div>
            <div id="innerSecondTech">fvf</div>
            <div id="innerThirdTech">gfg</div>
            <div id="innerFourthTech">gfg</div>
        </div>
        <div id="foot">
            <jsp:include page="footer.jsp"></jsp:include>

        </div>
    </div>
</div>
</div>

