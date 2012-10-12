package com.blackleaf.webcrawler.service;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.blackleaf.webcrawler.domain.LinkRecord;
import com.blackleaf.webcrawler.domain.ServiceProfile;
import com.blackleaf.webcrawler.domain.WSDLDescriptor;

public class DatabaseService {
	private final static String CONNECT_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF8";
	private final static String CONNECT_USER = "root";
	private final static String CONNECT_PASSWORD = "123";

	public long insertWSDL(WSDLDescriptor rec) {
		try {
			// MySQL 的驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 获取数据库的连接
			Connection conn = DriverManager.getConnection(CONNECT_URL, CONNECT_USER, CONNECT_PASSWORD);
			// 3. 获取表达式
			PreparedStatement pstmt = conn.prepareStatement("insert into wsdl (url,description,file_name) values (?,?,?)");
			pstmt.setString(1, rec.getUrl());
			pstmt.setString(2, rec.getDescription());
			pstmt.setString(3, rec.getFileName());
			// 4. 执行 SQL
			long id = pstmt.executeUpdate();

			// 5. 显示结果集里面的数据
			// while (rs.next()) {
			// System.out.println(rs.getInt(1));
			// System.out.println(rs.getString("username"));
			// System.out.println(rs.getString("password"));
			// System.out.println();
			// }
			// 6. 释放资源
			// rs.close();
			pstmt.close();
			conn.close();

			return id;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public long insertLink(LinkRecord link) {
		try {
			// MySQL 的驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 获取数据库的连接
			Connection conn = DriverManager.getConnection(CONNECT_URL, CONNECT_USER, CONNECT_PASSWORD);

			// 3. 获取表达式
			PreparedStatement pstmt = conn.prepareStatement("insert into link (link,is_wsdl) values (?,?)");
			pstmt.setString(1, link.getLink());
			pstmt.setInt(2, link.isWsdl() ? 1 : 0);
			// 4. 执行 SQL
			pstmt.executeUpdate();
			
			//5. 获取id
			ResultSet rs = pstmt.getGeneratedKeys();
			long id = 0;
			if (rs.next()) {
				id = rs.getLong(1);
			}

			if (link.getInLinkId() != -1) {
				pstmt = conn.prepareStatement("insert into link_graph (inlink_id,outlink_id) values (?,LAST_INSERT_ID())");
				pstmt.setLong(1, link.getInLinkId());
				// pstmt.setLong(2, id);
				pstmt.executeUpdate();
			}

			pstmt.close();
			conn.close();

			return id;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		DatabaseService service = new DatabaseService();
		LinkRecord rec = new LinkRecord();
		// rec.setUrl("test");
	}

	public long insertWsdlProfile(ServiceProfile profile) {
		try {
			// MySQL 的驱动
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 获取数据库的连接
			Connection conn = DriverManager.getConnection(CONNECT_URL, CONNECT_USER, CONNECT_PASSWORD);

			// 3. 获取表达式
			PreparedStatement pstmt = conn.prepareStatement("insert into service_profile (name,wsdl_content,availability,rating,link_id) values (?,?,?,?,?)");
			pstmt.setString(1, profile.getName());
			pstmt.setBinaryStream(2, new ByteArrayInputStream(profile.getContent().getBytes()));
			pstmt.setFloat(3, profile.getAvailability());
			pstmt.setFloat(4, profile.getRating());
			pstmt.setInt(5, profile.getLinkId());

			// 4. 执行 SQL
			long result = pstmt.executeUpdate();

			pstmt.close();
			conn.close();

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
