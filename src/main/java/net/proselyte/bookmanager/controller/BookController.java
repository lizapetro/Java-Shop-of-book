package net.proselyte.bookmanager.controller;

import net.proselyte.bookmanager.model.Book;
import net.proselyte.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Liza on 26.04.2017.
 */
@Controller
public class BookController {
    private BookService bookService;

    @Autowired(required = true)//�������������� ����������
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService){
        this.bookService=bookService;
    }
    //�������� books, ������� ������� ������ ����
    @RequestMapping(value = "books",method= RequestMethod.GET)
    public String listBooks(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("listBooks",this.bookService.listBooks());
        return "books";
    }
    //������ ��������� �����(���. ��������)
    @RequestMapping(value = "/books/add", method=RequestMethod.POST)//������ ��� ��������,� �� ��������
    public String addBook(@ModelAttribute("book") Book book){
        //���� id ����� ����� ����� 0(���� �� ����� ����,���� ����� ����������)
        //����� ������ ������ �������� ����� �����
        if (book.getId()==0){
            this.bookService.addBook(book);
        } else {
            //������ ����� ��� ���� � ����� ��
            //������ ������ �������� �����
            this.bookService.updateBook(book);

        }

        //���������������� �� ������� �������� books
        return "redirect:/books";
    }

    //�������� ��� �������� ����
    @RequestMapping("/remove/{id}")
    //�������� id
    public String removeBook(@PathVariable("id")int id){
        this.bookService.removeBook(id);

        //������������ ��  ������� ��������
        return "redirect:/books";
    }

    //�������� ��� �������������� ����
    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id,Model model){
        //�������� ����� �� id
        model.addAttribute("book", this.bookService.getBookById(id));
        //������� ���� ������ ����(�� ���� ��������� ������)
        model.addAttribute("listBook",this.bookService.listBooks());

        return "books";
    }

    //�������� ��� �������������� ������ ������ ����� ��������
    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id,Model model){
        //��� ����� ������ �������� id
        model.addAttribute("book",this.bookService.getBookById(id));

        //������������ � �������� bookdata
        return "bookdata";
    }
}