package bookstore.db;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommonMySQLDAO {
	protected static Connection connection = null;

	public Connection getConnection() throws Exception{

		//NamingException, SQLException���X���[�����
		try {

			if (connection == null || connection.isClosed()) {
				//JNDI�Q�ƃR���e�L�X�g���擾
				InitialContext initCtx = new InitialContext();

				//Tomcat�ŊǗ����ꂽ�f�[�^�x�[�X�ڑ���JNDI�o�R�Ŏ擾
				//java:comp/env/��K������
				DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/dbtest");

				//�f�[�^�x�[�X�ڑ����擾����
				connection = ds.getConnection();

				//�����R�~�b�g���s�킸�A�X�V�n�����ł͏�Ƀg�����U�N�V�����Ǘ����s���悤�ɐݒ肷��B
				connection.setAutoCommit(false);
			}
		}catch (Exception e) {
			//�����ڑ��擾�ŗ�O���o���ꍇ��connection=null�ɂ��A
			//����������O�͂��̂܂ܑ��o����
			e.printStackTrace();
			connection = null;
			throw e;
		}
		return connection;
	}

	public void closeConnection() {
		try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			connection = null;
		}
	}
}
