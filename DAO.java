package com.my.libDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import com.my.DButil.DBConnection;
import com.my.libDTO.booksDTO;


public class booksDAO {
	booksDTO dto=new  booksDTO();
	Scanner s=new Scanner(System.in);
	  
	public int insert() throws Exception {
		Connection con=DBConnection.getConnection();
		String iquery="insert into library values(?,?,?,?,?)";
		PreparedStatement pstmt= con.prepareStatement(iquery);
		pstmt.setInt(1, dto.getBid());
		pstmt.setString(2,dto.getBname());
		pstmt.setString(3, dto.getAuthorname());
		pstmt.setInt(4,dto.getPage());
		pstmt.setDouble(5, dto.getPrice());
		int nori=pstmt.executeUpdate();
		return nori;
	 }
	 
	 
	 public int updateBookName() throws Exception {
			Connection con=DBConnection.getConnection();
			String uquery="update library set bname = ? where bid =?";
			PreparedStatement pstmt= con.prepareStatement(uquery);
			pstmt.setString(1,dto.getBname());
			pstmt.setInt(2,dto.getBid());
			int noru=pstmt.executeUpdate();
			return noru;
	 }
	 
	 
	 
	 public int updateBookPage() throws Exception {
			Connection con=DBConnection.getConnection();
			String uquery="update library set pages = ? where bid =?";
			PreparedStatement pstmt= con.prepareStatement(uquery);
			pstmt.setInt(1, dto.getPage());
			pstmt.setInt(2,dto.getBid());
			int noru=pstmt.executeUpdate();
			return noru;
	 }
	 
	 
	 
	 public int updateBookPrice() throws Exception {
		 Connection con=DBConnection.getConnection();
			String uquery="update library set price = ? where bid =?";
			PreparedStatement pstmt= con.prepareStatement(uquery);
			pstmt.setDouble(1, dto.getPrice());
			pstmt.setInt(2,dto.getBid());
			int noru=pstmt.executeUpdate();
			return noru;
	 }
	 
	 
	 
	 public int deleteBook() throws Exception {
		 Connection con=DBConnection.getConnection();
		 String dquery="delete from library where bid = ?";
		 PreparedStatement pstmt= con.prepareStatement(dquery);
		 pstmt.setInt(1, dto.getBid());
		 int nord=pstmt.executeUpdate();
		 return nord;
	 }
	 
	 
	 
	 public booksDTO searchBookBasedOnId() throws Exception {
		 Connection con=DBConnection.getConnection();
		 String squery="select * from library where bid=?";
		 PreparedStatement pstmt=con.prepareStatement(squery);
		 pstmt.setInt(1, dto.getBid());
		 ResultSet rs=pstmt.executeQuery();
		 if(rs.next()) {
		 dto=new booksDTO();
		 dto.setBid(rs.getInt(1));
		 dto.setBname(rs.getString(2)); 
		 dto.setAuthorname(rs.getString(3));
		 dto.setPage(rs.getInt(4));
		 dto.setPrice(rs.getDouble(5));
		 }
		 else {
			 System.out.println("Some thing Went Wrong!!!!");
		 }
		return dto;
	 }
	 
	 
	 
	 public booksDTO serachBookBasedOnName() throws Exception {
		 Connection con=DBConnection.getConnection();
		 String squery="select * from library where bname=?";
		 PreparedStatement pstmt=con.prepareStatement(squery);
		 pstmt.setString(1,dto.getBname());
		 ResultSet rs=pstmt.executeQuery();
		 if(rs.next()) {
			 dto.setBid(rs.getInt(1));
			 dto.setBname(rs.getString(2));
			 dto.setAuthorname(rs.getString(3));
			 dto.setPage(rs.getInt(4));
			 dto.setPrice(rs.getDouble(5));
		 }
		 return dto;
	}
	 
	 
	 
	 public List<booksDTO> allBooks() throws Exception{
		 Connection con=DBConnection.getConnection();
		 List<booksDTO> book=new Vector<booksDTO>();
		 String squery="select * from library";
		 PreparedStatement pstmt=con.prepareStatement(squery);
		 ResultSet rs=pstmt.executeQuery();
		 while(rs.next()) {
			 dto=new booksDTO();
			 dto.setBid(rs.getInt(1));
			 dto.setBname(rs.getString(2)); 
			 dto.setAuthorname(rs.getString(3));
			 dto.setPage(rs.getInt(4));
			 dto.setPrice(rs.getDouble(5));
			 book.add(dto);
		 }
		 return book;
	 }
	 
	 
	 
	 public void  buyBookBasedOnAuthor(String name) throws Exception
	 {
		 Connection con=DBConnection.getConnection();
		 List<booksDTO> book=new Vector<booksDTO>();
		 String squery="select * from library where aname=?";
		 PreparedStatement pstmt=con.prepareStatement(squery);
		 pstmt.setString(1,dto.getAuthorname());
		 ResultSet rs=pstmt.executeQuery();
		 while(rs.next()) {
			 dto=new booksDTO();
			 dto.setBid(rs.getInt(1));
			 dto.setBname(rs.getString(2)); 
			 dto.setAuthorname(rs.getString(3));
			 dto.setPage(rs.getInt(4));
			 dto.setPrice(rs.getDouble(5));
			 book.add(dto);
		 }
		 for(booksDTO dto:book) {
			 System.out.println(dto);
		 }
		 System.out.println("Enter Book id :");
		 int id=s.nextInt();
		 System.out.println(searchBookBasedOnId());
		 System.out.println("You Successfully buy this book....");
		 deleteBook();
	 }
	 
	 
	 
	 public void buyBookBasedOnId(int id) throws Exception {
		 System.out.println(searchBookBasedOnId());
		 System.out.println("You Successfully buy this book....");
		 deleteBook();
	 }
}
