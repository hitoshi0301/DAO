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

		//�v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);

		//SQL�����s���Ă��̌��ʂ��擾����
		ResultSet rs = statement.executeQuery();

		//�������ʂ̍s�����t�F�b�`���s���A�擾���ʂ�ValueObject�֊i�[����
		while (rs.next()) {
			TBook vo = new TBook();

			//�N�G���[���ʂ�vo�֊i�[�i���炩���߃N�G���[���ʂ�vo�̕ϐ����͈�v�����Ă���j
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
		
		//�v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, inISBN);
				ResultSet rs = statement.executeQuery();
				
				List<String> isbnlist = new ArrayList();
				TBook vo = new TBook();
				while (rs.next()) {
					

					//�N�G���[���ʂ�vo�֊i�[�i���炩���߃N�G���[���ʂ�vo�̕ϐ����͈�v�����Ă���j
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
		//�v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = connection.prepareStatement(sql);
		//SQL�����s���Ă��̌��ʂ��擾����
		ResultSet rs = statement.executeQuery();

		//�������ʂ̍s�����t�F�b�`���s���A�擾���ʂ�ValueObject�֊i�[����
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