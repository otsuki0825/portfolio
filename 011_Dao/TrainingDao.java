package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Training;



public class TrainingDao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/trainingManagerDB";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String today = sdf.format(date);

	private Connection getConnection() throws
	    ClassNotFoundException, SQLException{
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
		return con;
	}

	public void newUserRegist(String id, String password) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("INSERT INTO user_table VALUES(?,?)");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, password);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}

	public Training searchUser(String id, String password) {
		Training tr = null;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT * FROM user_table WHERE id = ? AND password = ?");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, password);

		        ResultSet rs = pstmt.executeQuery();

		        if(rs.next()) {
		        	tr = new Training();
		        	tr.setId(rs.getString(1));
		        	tr.setPassword(rs.getString(2));
		        }
			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();
			}
	 return tr;
	}
	public List<Training> searchGroup(String id) {
		List<Training> groupList = new ArrayList<Training>();
		Training tr = null;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT group_id, group_name FROM group_table WHERE id = ?");
		     ){
					pstmt.setString(1, id);

		        ResultSet rs = pstmt.executeQuery();

		        while(rs.next()) {
		        	tr = new Training();
		        	tr.setGroupId(rs.getInt(1));
		        	tr.setGroupName(rs.getString(2));
		        	groupList.add(tr);
		        }
			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();
			}
	 return groupList;
	}

	public List<Training> searchEvent(String id, int groupId) {
		List<Training> eventList = new ArrayList<Training>();
		Training tr = null;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT * FROM event_table WHERE id =? AND group_id = ?");
		     ){
					pstmt.setString(1, id);
					pstmt.setInt(2, groupId);

		        ResultSet rs = pstmt.executeQuery();

		        while(rs.next()) {
		        	tr = new Training();
		        	tr.setGroupId(rs.getInt(2));
		        	tr.setEventId(rs.getInt(3));
		        	tr.setEventName(rs.getString(4));
		        	eventList.add(tr);
		        }
			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();
			}
	 return eventList;
	}
	public void trainingRegist(String id, int groupId, int eventId, int kg, int reps, int sets, int count, int trainingId) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("INSERT INTO training_table VALUES(?,?,?,?,?,?,?,?,?)");
		     ){
					pstmt.setString(1, id);
					pstmt.setInt(2, groupId);
					pstmt.setInt(3, eventId);
					pstmt.setInt(4, trainingId);
					pstmt.setInt(5, kg);
					pstmt.setInt(6, reps);
					pstmt.setInt(7, sets);
					pstmt.setInt(8, count);
					pstmt.setString(9, today);


		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public void trainingUpDate(String id, int eventId, int kg, int sets, int count, int trainingId) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("UPDATE training_table SET training_id = ?, sets = ? , count = ? WHERE id = ? AND event_id = ? AND kg = ? AND dt = ?" );
		     ){
			        pstmt.setInt(1, trainingId);
			        pstmt.setInt(2, sets);
					pstmt.setInt(3, count);
					pstmt.setString(4, id);
					pstmt.setInt(5, eventId);
					pstmt.setInt(6, kg);
					pstmt.setString(7, today);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public Training trainingComparison(String id, int eventId,int kg, int reps, int preSets) {
		Training tr = null;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT * FROM training_table WHERE id = ? AND event_id = ? AND kg = ? AND reps = ? AND sets = ? AND dt = ?");
		     ){
					pstmt.setString(1, id);
					pstmt.setInt(2, eventId);
					pstmt.setInt(3, kg);
					pstmt.setInt(4, reps);
					pstmt.setInt(5, preSets);
					pstmt.setString(6, today);

		        ResultSet rs = pstmt.executeQuery();

		        if(rs.next()) {
		        	tr = new Training();
		        	tr.setEventId(rs.getInt(3));
		        	tr.setKg(rs.getInt(4));
		        	tr.setReps(rs.getInt(5));
		        	tr.setSets(rs.getInt(6));
		        	tr.setCount(rs.getInt(7));
		        	tr.setDt(rs.getString(8));


		        }


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
		return tr;
	}
	public void newGroupRegist(String id, int groupId, String groupName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("INSERT INTO group_table VALUES(?,?,?)");
		     ){
					pstmt.setString(1, id);
					pstmt.setInt(2, groupId);
					pstmt.setString(3, groupName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public void eventNameEventTableUpdate(String id, int eventId, String eventName, int newEventId, String newEventName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("UPDATE event_table SET event_id = ?, event_name = ? WHERE id = ? AND event_name = ?" );
		     ){
					pstmt.setInt(1, newEventId);
			        pstmt.setString(2, newEventName);
					pstmt.setString(3, id);
					pstmt.setString(4, eventName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	/*
	public void eventNameEventTableUpdate(String id, String eventName, String newEventName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("UPDATE event_table SET event_name = ? WHERE id = ? AND event_name = ?" );
		     ){
					pstmt.setString(1, newEventName);
					pstmt.setString(2, id);
					pstmt.setString(3, eventName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	*/
	public void eventNameEventTableRegist(String id, int groupId, int newEventId, String newEventName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("INSERT INTO event_table VALUES(?,?,?,?)");
		     ){
					pstmt.setString(1, id);
					pstmt.setInt(2, groupId);
					pstmt.setInt(3, newEventId);
					pstmt.setString(4, newEventName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public void deletetGroup(String id, String groupName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("DELETE FROM group_table WHERE id =? AND group_name = ?");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, groupName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public void deleteeGroup(String id, String groupName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("DELETE FROM event_table WHERE id = ? AND group_name = ?");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(1, groupName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
	public void deleteeGroupByeventName(String id, String eventName) {
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("DELETE FROM event_table WHERE id = ? AND event_name = ?");
		     ){
			        pstmt.setString(1, id);
					pstmt.setString(1, eventName);

		        int rows = pstmt.executeUpdate();


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
	}
    //以下レポート機能
	public int searchGroupId(String id, String groupName) {
		int groupId = 0;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT group_id FROM group_table WHERE id = ? AND group_name = ?");
		     ){
					pstmt.setString(1, id);
					pstmt.setString(2, groupName);

		        ResultSet rs = pstmt.executeQuery();

		        if(rs.next()) {
		        	groupId =rs.getInt(1);
		        }
			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();
			}
		return groupId;
	}
	public int searchEventId(String id, int groupId, String eventName) {
		int eventId = 0;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT event_id FROM event_table WHERE id = ? AND group_id = ? AND event_name = ?");
		     ){
					pstmt.setString(1, id);
					pstmt.setInt(2, groupId);
					pstmt.setString(3, eventName);

		        ResultSet rs = pstmt.executeQuery();

		        if(rs.next()) {
		        	eventId =rs.getInt(1);
		        }
			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();
			}
		return eventId;
	}

	public List<Training>  reportAllByEventName(String id, int groupId, int eventId) {
		List<Training> rlist = new ArrayList<>();
		Training tr = null;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT * FROM training_table t INNER JOIN event_table e ON t.event_id = e.event_id AND t.id = e.id AND t.group_id = e.group_id WHERE t.id = ? AND t.group_id = ? AND t.event_id = ? GROUP BY t.training_id ORDER BY dt ASC, t.event_id, sets" );
		     ){
					pstmt.setString(1, id);
					pstmt.setInt(2, groupId);
					pstmt.setInt(3, eventId);

		        ResultSet rs = pstmt.executeQuery();

		        while(rs.next()) {
		        	tr = new Training();
		        	tr.setEventName(rs.getString(13));
		        	tr.setKg(rs.getInt(5));
		        	tr.setReps(rs.getInt(6));
		        	tr.setSets(rs.getInt(7));
		        	tr.setCount(rs.getInt(8));
		        	tr.setDt(rs.getString(9));
                    rlist.add(tr);

		        }


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
		return rlist;
	}

	public List<Training>  trainingListByIdAndGroupId(String id, int groupId) {
		List<Training> tList = new ArrayList<>();
		Training tr = null;
		try(
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement
				("SELECT * FROM training_table t LEFT OUTER JOIN event_table e ON t.event_id = e.event_id AND t.id = e.id AND t.group_id = e.group_id WHERE t.id = ? AND t.group_id = ? GROUP BY t.training_id ORDER BY dt ASC, t.event_id, sets");
		     ){
					pstmt.setString(1, id);
					pstmt.setInt(2, groupId);

		        ResultSet rs = pstmt.executeQuery();

		        while(rs.next()) {
		        	tr = new Training();
		        	tr.setEventName(rs.getString(13));
		        	tr.setKg(rs.getInt(5));
		        	tr.setReps(rs.getInt(6));
		        	tr.setSets(rs.getInt(7));
		        	tr.setCount(rs.getInt(8));
		        	tr.setDt(rs.getString(9));
		        	tList.add(tr);

		        }


			}catch(SQLException | ClassNotFoundException  e){
				e.printStackTrace();

			}
		return tList;
	}

}
