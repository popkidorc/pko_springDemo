package pko.demo.springTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class DefaultFooService extends JdbcDaoSupport implements FooService {

	public Foo getFoo(String fooName) {
		throw new UnsupportedOperationException();
	}

	public Foo getFoo(String fooName, String barName) {
		throw new UnsupportedOperationException();
	}

	public void insertFoo(List<Foo> fooes) {
		for (Foo foo : fooes) {
			insertSingletonFoo(foo);
		}
	}

	public void updateFoo(Foo foo) {
		throw new UnsupportedOperationException();
	}

	private void insertSingletonFoo(Foo foo) {
		StringBuffer sql = new StringBuffer(
				"INSERT INTO user (userId) values ('" + foo.name + "'); ");
		System.out.println(sql.toString());
		// this.getJdbcTemplate().update(sql.toString());
		Connection connection = null;
		try {
			connection = DataSourceUtils.getConnection(getDataSource());
//			connection = getDataSource().getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql.toString());
			preparedStatement.executeUpdate();
			System.out.println("success");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("rollback");
			throw new UnsupportedOperationException();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("rollback");
				throw new UnsupportedOperationException();
			}
		}
	}
}
