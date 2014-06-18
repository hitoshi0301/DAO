package bookstore.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import bookstore.db.BookDB;
import bookstore.pbean.TBook;



@Results({@Result(name="login",location="bookstore.jsp")})
public class LoginAction {
	private List<TBook> iblist;
	
@Action("/Login")
public String loginAndGetBookList() throws Exception {
	
	BookDB dao = new BookDB();
	try {
		dao.getConnection();
		iblist = dao.getListbook();
		return ("login");

	} catch (Exception e){
		throw new Exception(e);
	} finally {
		//DAO‚Ìˆ—‚ªŠ®—¹‚µ‚½‚çÚ‘±‚ğ•Â‚¶‚é
		dao.closeConnection();
	}
	
}
public List<TBook> getIblist(){
	return (iblist);
}
public void setIblist(List<TBook> iblist) {
	this.iblist = iblist;
}
}
