import java.sql.*;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class sql {
	String url = "jdbc:mysql://localhost:3307/tft_data?serverTimezone=Asia/Seoul";
	Connection con = null;
	PreparedStatement pstatement = null;
	
	String user = "root";
	String password = "1234";
	
	String summoner_info = "insert into summoner_info(ID, password, TFT_name) values(?, ?, ?)";
	String match_info = "insert into match_info(TFT_name, match_id, game_length, game_variation) values(?, ?, ?, ?)";
	String user_info = "insert into user_info(match_id, user_name, gold_left, last_round, level, player_eliminated) values(?, ?, ?, ?, ?, ?)";
	String trait_info = "insert into trait_info(match_id, user_name, trait_no, trait_name, num_units, tier_current) values(?, ?, ?, ?, ?, ?)";
	String unit_info = "insert into unit_info(match_id, user_name, unit_no, character_id, tier, item_1, item_2, item_3) values(?, ?, ?, ?, ?, ?, ?, ?)";
	
	String selectSummoner = "select * from summoner_info where ID=?";
	String selectMatch = "select * from match_info where TFT_name=? and match_id=?";
	String selectUser = "select * from user_info where match_id=? and user_name=?";
	String selectTrait = "select * from trait_info where match_id=? and user_name=? and trait_no=?";
	String selectUnit = "select * from unit_info where match_id=? and user_name=? and unit_no=?";
	
	String deleteSummoner = "delete from summoner_info where ID=?";
	String deleteMatch = "delete from match_info where TFT_name=? and match_id=?";
	String deleteUser = "delete from user_info where match_id=?";
	String deleteTrait = "delete from trait_info where match_id=?";
	String deleteUnit = "delete from unit_info where match_id=?";
	
	public void connect() {
		try {
			con = DriverManager.getConnection(url,user,password);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean check_signup(String id) {
		try {
			String tmp_id = null;
			
			pstatement = con.prepareStatement(selectSummoner);
			pstatement.setString(1, id); 	
			
			ResultSet rs = pstatement.executeQuery();
			
			while(rs.next()) {
				tmp_id = rs.getString("ID");
			}
			
			if(tmp_id == null) { //해당하는 아이디가 없는 경우 생성할 수 있음
				rs.close();
				return true; 
			}
			else {	//중복이 있는 경우 false
				rs.close();
				return false;
			}

		} catch (SQLException e) {//예외 발생시 
			e.printStackTrace();
			return false;
		}
	}
	
	public void insertSummoner_info(String id,String pw,String name) {
		try {
			pstatement = con.prepareStatement(summoner_info);
			pstatement.setString(1, id);
			pstatement.setString(2, pw);
			pstatement.setString(3, name);
			pstatement.execute();		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertMatch_info(String name, String match_id, float game_length, String game_variation) {
		try {
			pstatement = con.prepareStatement(match_info);
			pstatement.setString(1, name);
			pstatement.setString(2, match_id);
			pstatement.setFloat(3, game_length);
			pstatement.setString(4, game_variation);
			
			pstatement.execute();		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertUser_info(String match_id, String user_name, int gold_left, int last_round, int level, int player_eliminated) {
		try {
			
			pstatement = con.prepareStatement(user_info);
			pstatement.setString(1, match_id);
			pstatement.setString(2, user_name);
			pstatement.setInt(3, gold_left);
			pstatement.setInt(4, last_round);
			pstatement.setInt(5, level);
			pstatement.setInt(6, player_eliminated);
			
			pstatement.execute();		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertTrait_info(String match_id, String user_name, int trait_no, String trait_name, int num_units, int tier_current) {
		try {
			
			pstatement = con.prepareStatement(trait_info);
			pstatement.setString(1, match_id);
			pstatement.setObject(2, user_name);
			pstatement.setInt(3, trait_no);
			pstatement.setString(4, trait_name);
			pstatement.setInt(5, num_units);
			pstatement.setInt(6, tier_current);
			
			pstatement.execute();		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertUnit_info(String match_id, String user_name, int unit_no, String character_id, int tier, int item_1, int item_2, int item_3) {
		try {
			
			pstatement = con.prepareStatement(unit_info);
			pstatement.setString(1, match_id);
			pstatement.setObject(2, user_name);
			pstatement.setInt(3, unit_no);
			pstatement.setString(4, character_id);
			pstatement.setInt(5, tier);
			pstatement.setInt(6, item_1);
			pstatement.setInt(7, item_2);
			pstatement.setInt(8, item_3);
			
			pstatement.execute();		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean selectSummoner_info(String id, String pw) {
		try {
			String tmp_id = null;
			String tmp_pw = null;
			
			pstatement = con.prepareStatement(selectSummoner);
			pstatement.setString(1, id); 	
			
			ResultSet rs = pstatement.executeQuery();
			
			while(rs.next()) {
				tmp_id = rs.getString("ID");
				tmp_pw = rs.getString("password");
			}
			if(tmp_id == null) { 
				rs.close();
				return false; 
			}
			
			if(tmp_id.equals(id) && tmp_pw.equals(pw)) {	// 성공일때
				rs.close();
				return true;
			}
			else {
				rs.close();
				return false; 
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String selectMatch_info(String name, String match_id) {
		try {
			String tmp_match_id = null;
			float tmp_game_length = 0;
			String tmp_game_variation = null;
			
			pstatement = con.prepareStatement(selectMatch);
			pstatement.setString(1, name);
			pstatement.setString(2, match_id);
			
			ResultSet rs = pstatement.executeQuery();
			
			while(rs.next()) {
				tmp_match_id = rs.getString("match_id");
				tmp_game_length = rs.getFloat("game_length");
				tmp_game_variation = rs.getString("game_variation");
			}
			System.out.println("match_id : " + tmp_match_id + " game_length : " + tmp_game_length + " game_variation : " + tmp_game_variation);
			rs.close();
			return "성공";

		} catch (SQLException e) {
			e.printStackTrace();
			return "MATCHFAIL";
		}
	}
	
	public String selectUser_info(String match_id, String user_name) {
		try {
			String tmp_match_id = null;
			String tmp_user_name = null;
			int tmp_gold_left = 0;
			int tmp_last_round = 0;
			int tmp_level = 0;
			int tmp_player_eliminated = 0;
			
			pstatement = con.prepareStatement(selectUser);
			pstatement.setString(1, match_id);
			pstatement.setString(2, user_name);
			
			ResultSet rs = pstatement.executeQuery();
			
			while(rs.next()) {
				tmp_match_id = rs.getString("match_id");
				tmp_user_name = rs.getString("user_name");
				tmp_gold_left = rs.getInt("gold_left");
				tmp_last_round = rs.getInt("last_round");
				tmp_level = rs.getInt("level");
				tmp_player_eliminated = rs.getInt("player_eliminated");
			}
			System.out.println("match_id : " + tmp_match_id + " user_name : " + tmp_user_name + " gold_left : " + tmp_gold_left + " last_round : " + tmp_last_round + " level : " + tmp_level + " player_eliminated : " + tmp_player_eliminated );
			rs.close();
			return "성공";

		} catch (SQLException e) {
			e.printStackTrace();
			return "USERFAIL";
		}
	}
	
	public String selectTrait_info(String match_id, String user_name, int trait_no) {
		try {
			String tmp_match_id = null;
			String tmp_user_name = null;
			int tmp_trait_no = 0;
			String tmp_trait_name = null;
			int tmp_num_units = 0;
			int tmp_tier_current = 0;
			
			pstatement = con.prepareStatement(selectTrait);
			pstatement.setString(1, match_id);
			pstatement.setString(2, user_name);
			pstatement.setInt(3, trait_no);
			
			ResultSet rs = pstatement.executeQuery();
			
			while(rs.next()) {
				tmp_match_id = rs.getString("match_id");
				tmp_user_name = rs.getString("user_name");
				tmp_trait_no = rs.getInt("trait_no");
				tmp_trait_name = rs.getString("trait_name");
				tmp_num_units = rs.getInt("num_units");
				tmp_tier_current = rs.getInt("tier_current");
			}
			System.out.println("match_id : " + tmp_match_id + " user_name : " + tmp_user_name + " trait_no : " + tmp_trait_no + " trait_name : " + tmp_trait_name + " num_units : " + tmp_num_units + " tier_current : " + tmp_tier_current );
			rs.close();
			return "성공";

		} catch (SQLException e) {
			e.printStackTrace();
			return "TRAITFAIL";
		}
	}
	
	public String selectUnit_info(String match_id, String user_name, int unit_no) {
		try {
			String tmp_match_id = null;
			String tmp_user_name = null;
			int tmp_unit_no = 0;
			String tmp_character_id = null;
			int tmp_tier = 0;
			int tmp_item_1 = 0;
			int tmp_item_2 = 0;
			int tmp_item_3 = 0;
			
			pstatement = con.prepareStatement(selectUnit);
			pstatement.setString(1, match_id);
			pstatement.setString(2, user_name);
			pstatement.setInt(3, unit_no);
			
			ResultSet rs = pstatement.executeQuery();
			
			while(rs.next()) {
				tmp_match_id = rs.getString("match_id");
				tmp_user_name = rs.getString("user_name");
				tmp_unit_no = rs.getInt("unit_no");
				tmp_character_id = rs.getString("character_id");
				tmp_tier = rs.getInt("tier");
				tmp_item_1 = rs.getInt("item_1");
				tmp_item_2 = rs.getInt("item_2");
				tmp_item_3 = rs.getInt("item_3");
			}
			System.out.println("match_id : " + tmp_match_id + " user_name : " + tmp_user_name + " unit_no : " + tmp_unit_no + " character_id : " + tmp_character_id + " tier : " + tmp_tier + " item_1 : " + tmp_item_1 + " item_2 : " + tmp_item_2 + " item_3 : " + tmp_item_3);
			rs.close();
			return "성공";

		} catch (SQLException e) {
			e.printStackTrace();
			return "UNITFAIL";
		}
	}
	
	public String deleteSummoner_info(String id) {
		try {
			pstatement = con.prepareStatement(deleteSummoner);
			pstatement.setString(1, id);
			
			if(pstatement.executeUpdate() > 0) {
				return "성공";
			}
			else {
				return "DELETESUMMONERFAIL";
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return "DELETESUMMONERFAIL";
		}
		
	}
	
	public String deleteMatch_info(String name, String match_id) {
		try {
			pstatement = con.prepareStatement(deleteMatch);
			pstatement.setString(1, name);
			pstatement.setString(2, match_id);
			
			if(pstatement.executeUpdate() > 0) {
				return "성공";
			}
			else {
				return "DELETEMATCHFAIL";
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return "DELETEMATCHFAIL";
		}
		
	}
	
	public String deleteUser_info(String match_id) {
		try {
			pstatement = con.prepareStatement(deleteUser);
			pstatement.setString(1, match_id);
			
			if(pstatement.executeUpdate() > 0) {
				return "성공";
			}
			else {
				return "DELETEUSERFAIL";
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return "DELETEUSERFAIL";
		}
		
	}
	
	public String deleteTrait_info(String match_id) {
		try {
			pstatement = con.prepareStatement(deleteTrait);
			pstatement.setString(1, match_id);
			
			if(pstatement.executeUpdate() > 0) {
				return "성공";
			}
			else {
				return "DELETETRAITFAIL";
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return "DELETETRAITFAIL";
		}
		
	}
	
	public String deleteUnit_info(String match_id) {
		try {
			pstatement = con.prepareStatement(deleteUnit);
			pstatement.setString(1, match_id);
			
			if(pstatement.executeUpdate() > 0) {
				return "성공";
			}
			else {
				return "DELETEUNITFAIL";
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return "DELETEUNITFAIL";
		}
		
	}
	
	
	
	public void closeConnect() {
		try {
			pstatement.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
