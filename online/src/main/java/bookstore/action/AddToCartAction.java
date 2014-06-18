package bookstore.action;

import java.util.Arrays;
import java.util.List;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import bookstore.db.BookDB;
import bookstore.pbean.TBook;

@Results({
	@Result(name="addtocart",location="bookstore.jsp"),
	@Result(name="checkout",location="checkout.jsp")
})
public class AddToCartAction implements ServletRequestAware {
	private List<TBook> iblist;
	private HttpServletRequest request;
	
	private String[] selecteditems = null;
	BookDB dao = new BookDB();
	
@Action("/AddToCart")
public String addToCart() throws Exception {
	HttpSession session = request.getSession();
	session.removeAttribute("CART");
	
	//アイテムの情報が存在しなかったり、選択されていない場合の検出
	if (selecteditems != null && selecteditems.length != 0) {
		List<String> cart = Arrays.asList(selecteditems);
		session.setAttribute("CART",cart);
	}
	
	try {
		dao.getConnection();
		iblist = dao.getListbook();
		return ("addtocart");
	} catch (Exception e){
		throw new Exception(e);
	} finally {
		//DAOの処理が完了したら接続を閉じる
		dao.closeConnection();
	}
}


public String checkout() throws Exception {
	//HttpSessionの作成
	HttpSession httpsession = request.getSession(false);
	List<String> cart = (List<String>) httpsession.getAttribute("CART");
	
	BookDB db = new BookDB();
	try{
		db.getConnection();
	int total = db.total(cart);
	request.setAttribute("TOTAL", total);
	return ("checkout");
	}catch (Exception e) {
		throw new Exception (e);
	}finally {
		dao.closeConnection();
	}
	
	
	
}

public List<TBook> getIblist() {
	return iblist;
}

public void setIblist(List<TBook> iblist) {
	this.iblist = iblist;
}

public String[] getSelecteditems() {
	return selecteditems;
}

public void setSelecteditems(String[] selecteditems) {
	this.selecteditems = selecteditems;
}

public void setServletRequest(HttpServletRequest request) {
	this.request = request;
}
}
