import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class ServerObject extends Thread{
	
	Request request = new Request();
	sql sql = new sql();
	
	Socket socket = null;
	
	//ObjectOutputStream objectOutputStream;	//오브젝트 주고받는(JSON) 보조 스트림
	//ObjectInputStream objectInputStream;
	
	DataOutputStream dataOutputStream;		//바이트단위로 주고받는 보조 스트림
	DataInputStream dataInputStream;
	
	OutputStream outputStream;				//스트림
	InputStream inputStream;
	
	StringTokenizer st;
	
	ServerObject(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		JSONObject jsonObject = null;
		
		try {
			sql.connect();
			
			//인풋 스트림 설정
			inputStream = socket.getInputStream();
			dataInputStream = new DataInputStream(inputStream);
			//objectInputStream = new ObjectInputStream(inputStream);
			
			//아웃풋 스트림 설정
			outputStream = socket.getOutputStream();
			dataOutputStream = new DataOutputStream(outputStream);
			//objectOutputStream = new ObjectOutputStream(outputStream);
			
			
			while(true) {
				System.out.println("test");
				String message = dataInputStream.readUTF();		//클라이언트에게 메시지를 받음		
				System.out.println("클라이언트 : " + message);
				
				st = new StringTokenizer(message, "$");
				String sign = st.nextToken();	// 클라이언트에서 어떤 메시지를 받았는지 확인
				
				switch(sign) {	//사인에 따라 요청한 동작을 수행
				case "LOGIN" :	//로그인일때
					//데이터 들어오는 순서 아이디 비밀번호
					String login_id = st.nextToken();
					String login_pw = st.nextToken();
					System.out.println("id : " + login_id + " pw : " + login_pw);
					if(sql.selectSummoner_info(login_id,login_pw)) {	// 데이터베이스에서 체크
						//존재하고 로그인작업
						dataOutputStream.writeUTF("CLEAR"); //로그인성공 메시지 전송
						sleep(1); //1초대기후 저장된 데이터들 전송
						String name = sql.selectName(login_id);
						dataOutputStream.writeUTF(name);
						
						break;
					}
					//실패시 
					dataOutputStream.writeUTF("FAIL"); //로그인 실패 메시지		
					break;
				case "SIGNUP":	//회원가입일때
					//이때 데이터 들어오는 순서 아이디 비밀번호 닉네임
					String signup_id = st.nextToken();
					String signup_pw = st.nextToken();
					String signup_name = st.nextToken();
					
					if(request.callSummonerDTO(signup_name)) {	//콜해서 존재하는 닉네임인 경우
						if(sql.check_signup(signup_id)) {		//DB에서 아이디 중복확인
							//아이디 중복도 없고 존재하는 닉네임인경우
							sql.insertSummoner_info(signup_id, signup_pw, signup_name); //데이터베이스에 입력한 이름들 저장
							sleep(1); //1초대기후 저장된 데이터들 전송
							dataOutputStream.writeUTF("CLEAR"); //회원가입 성공 메시지 전송
							System.out.println("회원가입 완료");
							break;
						}
					}
					//실패시
					dataOutputStream.writeUTF("FAIL"); //회원가입 실패 메시지
					break;
					
				case "SEARCH":	//검색
					String search_name = st.nextToken();
					//데이터 저장
					if(request.callSummonerDTO(search_name)) {
						if(request.callLeagueEntryDTO(request.summonerDTO.getId())) {
							if(request.callMatchList(request.summonerDTO.getPuuid())) {		
							}
							else {
								dataOutputStream.writeUTF("FAIL"); //실패 메시지
								break;
							}
						}
						else {
							dataOutputStream.writeUTF("FAIL"); //실패 메시지
							break;
						}
					}
					else {//실패할경우
						dataOutputStream.writeUTF("FAIL"); //실패 메시지
						break;
					}
					// API통신 완료 후 JSON데이터 클라이언트 전송
					
					
					dataOutputStream.writeUTF("CLEAR"); //성공 메시지
					
					//티어정보 전송
					String strTier = request.leagueEntryDTO.getTier() + "$" + request.leagueEntryDTO.getRank() + "$" + request.leagueEntryDTO.getSummonerName() + "$" + request.leagueEntryDTO.getLeaguePoints() + "$" + request.leagueEntryDTO.getWins() + "$" + request.leagueEntryDTO.getLosses();
					dataOutputStream.writeUTF(strTier);
					
					//검색한 데이터들 전송
					System.out.println("매치리스트 갯수  : " + request.matchObject.size());
					for(int i = 0 ; i < request.matchObject.size() ; i++) {
						//보내는 순서 해당매치 기본정보(소환사 닉네임포함) 이후 8명 정보 순서대로 
						String match_id = request.matchDto.get(i).getMetadata().getMatch_id();
						String game_length = Float.toString(request.matchDto.get(i).getInfo().getGame_length());
						String game_variation = request.matchDto.get(i).getInfo().getGame_variation();
						
						//매치아이디 매치길이 은하계정보 닉네임들
						sleep(50);
						System.out.println("참여자 리스트 인덱스 사이즈 : " + request.participantList.size());
						System.out.println("전송하는 매치 아이디 : " + match_id);
						dataOutputStream.writeUTF(match_id + "$" + game_length + "$" + game_variation + "$" + request.participantList.get(i).get(0) + "$" + request.participantList.get(i).get(1) + "$" + request.participantList.get(i).get(2) + "$" + request.participantList.get(i).get(3) + "$" + request.participantList.get(i).get(4) + "$" + request.participantList.get(i).get(5) + "$" + request.participantList.get(i).get(6) + "$" + request.participantList.get(i).get(7));
						
						JSONObject tmp_info = (JSONObject) request.matchObject.get(i).get("info");
						ArrayList<JSONObject> tmp_p = (ArrayList<JSONObject>) tmp_info.get("participants");
						for(int j = 0 ; j < tmp_p.size() ; j++) {
							//각 참여자의 데이터들 문자열로 전송
							sleep(50);
							dataOutputStream.writeUTF(tmp_p.get(j).toJSONString().trim());
						}
						
						//dataOutputStream.writeUTF(request.matchObject.get(i).toJSONString().trim());	//문자열 통째로 보내기,String길이 때문에 잘림
						
						System.out.println(i);
					}			
					
					dataOutputStream.writeUTF("CLEAR"); //성공 메시지
					break;
				case "SAVE":	//저장
					break;
				case "DELETE":		//저장된 기록삭제
					break;
					
				default: //데이터가 잘못들어오거나 할때
					dataOutputStream.writeUTF("FAIL"); //실패 메시지
					break;
					
				}
			}
			
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
