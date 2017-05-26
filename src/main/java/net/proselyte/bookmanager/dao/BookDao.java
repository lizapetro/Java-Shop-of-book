package net.proselyte.bookmanager.dao;

import net.proselyte.bookmanager.model.Book;

import java.util.List;

/**
 * Created by Liza on 26.04.2017.
 */
public interface BookDao {
    //��� ����� ������ � �������� �����
    public void addBook(Book book);//���������� �����

    public void updateBook(Book book);//��������� �����

    public void removeBook(int id);//�������� �����

    public Book getBookById(int id);//��������� ����� �� id

    public List<Book> listBooks(); // ��������� ������ ���� ����������� � �������
}
