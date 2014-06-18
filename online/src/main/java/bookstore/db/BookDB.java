package bookstore.db;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;




import bookstore.pbean.TBook;


public class BookDB extends CommonMySQLDAO{

	public List<TBook> getListbook() throws Exception {
		List<TBook> returnList = new ArrayList<TBook>();

		String sql = "SELECT id,isbn,title,author,publisher,price from t_book";

		//プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);

		//SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		//検索結果の行数分フェッチを行い、取得結果をValueObjectへ格納する
		while (rs.next()) {
			TBook vo = new TBook();

			//クエリー結果をvoへ格納（あらかじめクエリー結果とvoの変数名は一致させている）
			vo.setId(rs.getInt("id"));
			vo.setIsbn(rs.getString("isbn"));
			vo.setTitle(rs.getString("title"));
			vo.setAuthor(rs.getString("author"));
			vo.setPublisher(rs.getString("publisher"));
			vo.setPrice(rs.getInt("price"));
			

			returnList.add(vo);
		}
		return returnList;
	}
	
	public static TBook findBookByISBN(String inISBN) throws Exception {
		String sql = "SELECT id,isbn,title,author,publisher,price from t_book WHERE isbn like CONCAT('%',?,'%')";
		
		//プリペアステートメントを取得し、実行SQLを渡す
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, inISBN);
				ResultSet rs = statement.executeQuery();
				
				List<String> isbnlist = new ArrayList();
				TBook vo = new TBook();
				while (rs.next()) {
					

					//クエリー結果をvoへ格納（あらかじめクエリー結果とvoの変数名は一致させている）
					vo.setId(rs.getInt("id"));
					vo.setIsbn(rs.getString("isbn"));
					vo.setTitle(rs.getString("title"));
					vo.setAuthor(rs.getString("author"));
					vo.setPublisher(rs.getString("publisher"));
					vo.setPrice(rs.getInt("price"));
					
					
				}
				return vo;
	}

	public int total(List<String> cart) throws Exception {
		String sql = "SELECT id,isbn,title,author,publisher,price from t_book";
		
		int total = 0;
		//プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = connection.prepareStatement(sql);
		//SQLを実行してその結果を取得する
		ResultSet rs = statement.executeQuery();

		//検索結果の行数分フェッチを行い、取得結果をValueObjectへ格納する
		TBook vo = new TBook();
		while (rs.next()) {
			
			vo.setId(rs.getInt("id"));
			vo.setIsbn(rs.getString("isbn"));
			vo.setTitle(rs.getString("title"));
			vo.setAuthor(rs.getString("author"));
			vo.setPublisher(rs.getString("publisher"));
			vo.setPrice(rs.getInt("price"));
			
		}
		return total;
		
	}
	
}