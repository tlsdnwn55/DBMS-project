package javamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
	// 싱글톤 패턴으로 사용 하기위 한 코드들
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public ConnectDB() {
		
    }

	String jdbcUrl = "jdbc:mysql://localhost:3306/media_db?serverTimezone=UTC"; // MySQL 계정
	String dbId = "root"; // MySQL 계정
	String dbPw = "root"; // 비밀번호
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	PreparedStatement pstmt3 = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	ResultSet rs3 = null;
	String sql = "";
	String sql2 = "";
	String playlist_title = "";
	String media_info = "";
	String creator_info = "";
	String ad_info = "";
	String returns = "";
	String returns2 = "";



	// 데이터베이스와 통신하기 위한 코드가 들어있는 메서드
	public String joindb(String id, String pwd) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			System.out.println("연걸 성공");
			sql = "select id from info where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("id").equals(id)) { // 이미 아이디가 있는 경우
					System.out.println("아이디가 존재합니다.");
					returns = "id";
				} 
			} else { // 입력한 아이디가 없는 경우
				sql2 = "insert into info (id,pwd) values(?,?)";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, id);
				pstmt2.setString(2, pwd);
				pstmt2.executeUpdate();
				System.out.println("가입 성공");
				returns = "ok";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (pstmt2 != null)try {pstmt2.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		return returns;
	}

	public String logindb(String id, String pwd) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql2 = "select id,password,creator_id from creator where id=? and password=?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			String creator_id = null;
			if (rs.next()) {
				if (rs.getString("id").equals(id) && rs.getString("password").equals(pwd)) {
					creator_id = rs.getString("creator_id");
					returns2 = "true" + "%" +creator_id;// 로그인 가능
				} else {
					returns2 = "false"; // 로그인 실패
				}
			} else {
				returns2 = "noId"; // 아이디 또는 비밀번호 존재 X
			}

		} catch (Exception e) {

		} finally {if (rs != null)try {rs.close();} catch (SQLException ex) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
		}
		return returns2;
	}
	
	
	
	public String playlist_get(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw); // DB 접속
			playlist_title = "";
			
			
			sql = "select * from playlist where creator_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			rs.last();
			int playlist_cnt = rs.getRow();
			String[] playlist = new String[playlist_cnt];
			rs.beforeFirst();
			int k=0;
			
			while(rs.next()) {
				playlist[k] = rs.getString("playlist_title");
				sql = "select video_hit,ad_id from media where playlist_id = ? ";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setString(1,rs.getString("list_id"));
				playlist_title += rs.getString("list_id") + "%";
				rs2 = pstmt2.executeQuery();
				rs2.last();      
		        int rowcount = rs2.getRow();
		        rs2.beforeFirst();
				int[] hit_array = new int[rowcount];
				int n = 0;
				int total_revenue = 0;
				while(rs2.next()) {
					hit_array[n] = rs2.getInt("video_hit");
					sql2 = "select second_profit from advertise where ad_id = ?";
					pstmt3 = conn.prepareStatement(sql2);
					pstmt3.setString(1,rs2.getString("ad_id"));
					rs3 = pstmt3.executeQuery();
					if(rs3.next()) {
						float second_profit = rs3.getFloat("second_profit");
						//System.out.println("조회수 : " + hit_array[n] + " 초당수익 : "+second_profit + " " +  "수익금 : " + hit_array[n]*second_profit);
						total_revenue += hit_array[n]*second_profit;
					}
					n ++;
				}
				//System.out.println(playlist[k]+ "총 의 수익금 : " + total_revenue);
				playlist_title += playlist[k] + "%" + Integer.toString(total_revenue) + "%";
				k++;
			}

			
		
		}catch(Exception e) {
			
		}
		finally {
			if (rs2 != null)try {rs2.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
			if (pstmt3 != null)try {pstmt.close();} catch (SQLException ex) {}
			if (pstmt2 != null)try {pstmt.close();} catch (SQLException ex) {}
			if (pstmt != null)try {pstmt2.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
		}
		
		
		return playlist_title;
	}
	
	public String media_get(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw); // DB 접속
			media_info = "";
			
			
			sql = "select video_title,upload_time,video_time,video_hit,like_number,ad_id from media where playlist_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			rs.last();
			int row = rs.getRow();
			int[] hit_array = new int[row];
			rs.beforeFirst();
			int n = 0;
			while(rs.next()) {
				media_info += rs.getString("video_title") + "%" + rs.getString("upload_time") + "%" + rs.getString("video_time") + "%" 
						+ rs.getString("video_hit") + "%" + rs.getString("like_number") + id + "%";
				hit_array[n] = rs.getInt("video_hit");
				sql2 = "select ad_brand,ad_name,ad_second,second_profit from advertise where ad_id = ?";
				pstmt3 = conn.prepareStatement(sql2);
				pstmt3.setString(1,rs.getString("ad_id"));
				rs3 = pstmt3.executeQuery();
				if(rs3.next()) {
					float second_profit = rs3.getFloat("second_profit");
					String profit = Float.toString(hit_array[n]*second_profit);
					media_info += profit + "%" + rs3.getString("ad_brand") + "%" + rs3.getString("ad_name")
					+ "%" + rs3.getString("ad_second") + "%";
				}
				n ++;
			}
		}catch(Exception e) {
			
		}
		finally {
			if (rs2 != null)try {rs2.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
			if (pstmt3 != null)try {pstmt.close();} catch (SQLException ex) {}
			if (pstmt2 != null)try {pstmt.close();} catch (SQLException ex) {}
			if (pstmt != null)try {pstmt2.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
		}
		
		System.out.println(media_info);
		return media_info;
	}
	
	public String get_creator_info(String id) {
		try {
			creator_info = "";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "select _nickname,country,main_genre,e_mail,subscribers,join_date from creator where creator_id = ?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setString(1,id);
			rs3 = pstmt3.executeQuery();
			
			if(rs3.next()) {
				creator_info += rs3.getString("_nickname") + "%" + rs3.getString("country") + "%" + rs3.getString("main_genre") + "%" + rs3.getString("e_mail")
				+ "%" + rs3.getString("subscribers") + "%" + rs3.getString("join_date");
			}
			
		}catch(Exception e) {
			
		}finally {
			if (pstmt3 != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs3 != null)try {rs2.close();} catch (SQLException ex) {}
		}
		return creator_info;
	}
	

	
	public double get_ad_revenue() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPw);
			sql = "select second_profit from advertise where ad_id = ?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setString(1,rs.getString("ad_id"));
			rs2 = pstmt3.executeQuery();
		}catch(Exception e) {
			
		}finally {
			if (pstmt3 != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs2 != null)try {rs2.close();} catch (SQLException ex) {}
		}
		
		
		return 1.0;
	}
	
}





	




