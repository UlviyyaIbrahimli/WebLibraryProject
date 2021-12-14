/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.library.desktop.service;

import java.util.List;
import project.library.desktop.dao.BookDao;
import project.library.desktop.dao.BookRoomDao;
import project.library.desktop.dao.interfaces.IBookRoom;
import project.library.desktop.model.BookRoom;
import project.library.desktop.service.interfaces.IBookRoomService;

/**
 *
 * @author user
 */
public class BookRoomService   implements IBookRoomService{
    BookRoomDao bookRoomDao;

    public BookRoomService(BookRoomDao bookRoomDao) {
        this.bookRoomDao = bookRoomDao;
    }

    public BookRoomService() {
    }
    
    @Override
    public List<BookRoom> getBookRoomList() throws Exception {
 return bookRoomDao.getBookRoomList();    }

    @Override
    public boolean addBookRoom(BookRoom bookRoom) throws Exception {
return bookRoomDao.addBookRoom(bookRoom);    }

    @Override
    public boolean updateBookRook(BookRoom bookRoom, Long id) throws Exception {
 return bookRoomDao.updateBookRook(bookRoom, id);    }

    @Override
    public BookRoom getBookRoomByid(Long id) throws Exception {
return bookRoomDao.getBookRoomByid(id);    }

    @Override
    public boolean deleteBookRoom(Long id) throws Exception {
return bookRoomDao.deleteBookRoom(id);    }

    @Override
    public List<BookRoom> getRoomNumburForFloor(Integer floor) throws Exception {
 return  bookRoomDao.getRoomNumburForFloor(floor);    }

    @Override
    public List<String> getBookRoomNumber(String floor) throws Exception {
 return bookRoomDao.getBookRoomNumber(floor);    }
    
}
