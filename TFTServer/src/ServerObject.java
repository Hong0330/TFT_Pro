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
					request.reset();
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
				case "CALL": //저장된 데이터 불러오기
					String call_name = st.nextToken();
					ArrayList<String> call_match = sql.selectMatch_info(call_name);
					if(call_match.size() == 0) {
						dataOutputStream.writeUTF("FAIL");
						break;
					}
					for(int i = 0 ; i < call_match.size() ; i++) {
						StringTokenizer match_st = new StringTokenizer(call_match.get(i),"$");
						String match_id = match_st.nextToken(); //매치 아이디 가져옴
						
						//매치 정보 전송
						System.out.println("MATCH$" + call_match.get(i));
						dataOutputStream.writeUTF("MATCH$" + call_match.get(i));
						
						
						System.out.println(match_id);
						ArrayList<String> call_user = sql.selectUser_info(match_id);
						System.out.println("call_user size : " + call_user.size());
						if(call_user.size() == 0) {
							dataOutputStream.writeUTF("FAIL");
							break;
						}
						
						for(int j = 0 ; j < call_user.size() ; j++ ) {
							StringTokenizer user_st = new StringTokenizer(call_user.get(j),"$");
							String user_match_id = user_st.nextToken();
							String user_user_name = user_st.nextToken();
							
							//유저 정보 전송
							System.out.println("USER$" + call_user.get(j));
							dataOutputStream.writeUTF("USER$" + call_user.get(j));
							
							
							ArrayList<String> call_trait = sql.selectTrait_info(user_match_id, user_user_name);
						
							
							for(int t = 0 ; t < call_trait.size() ; t++) {
								StringTokenizer trait_st = new StringTokenizer(call_trait.get(t),"$");
								String trait_match_id = trait_st.nextToken();
								String trait_user_name = trait_st.nextToken();
								String trait_trait_name = trait_st.nextToken();
								
								//시너지 정보 전송
								System.out.println("TRAIT$" + call_trait.get(t));
								dataOutputStream.writeUTF("TRAIT$" + call_trait.get(t));
								
							}
							dataOutputStream.writeUTF("TRAITCLEAR");
							
							
							ArrayList<String> call_unit = sql.selectUnit_info(user_match_id, user_user_name);
							if(call_unit.size() == 0) {
								dataOutputStream.writeUTF("FAIL");
								
								break;
							}
							
							for(int u = 0 ; u < call_unit.size() ; u++) {
								StringTokenizer unit_st = new StringTokenizer(call_unit.get(u),"$");
								String unit_match_id = unit_st.nextToken();
								String unit_user_name = unit_st.nextToken();
								String unit_character_id = unit_st.nextToken();
								String unit_tier = unit_st.nextToken();
								String unit_item_1 = unit_st.nextToken();
								String unit_item_2 = unit_st.nextToken();
								String unit_item_3 = unit_st.nextToken();
								
								//유닛 정보 전송
								System.out.println("UNIT$" + call_unit.get(u));
								dataOutputStream.writeUTF("UNIT$" + call_unit.get(u));
								
							}
							dataOutputStream.writeUTF("UNITCLEAR");
							
							dataOutputStream.writeUTF("USERCLEAR");
						}
						dataOutputStream.writeUTF("MATCHCLEAR");
					}
					dataOutputStream.writeUTF("CLEAR");
					break;
				case "SAVE":	//저장
					
					boolean clear = false; //성공 여부 판단하는 변수
					
					
					String id = st.nextToken(); //매치 아이디 받음
					String name = st.nextToken(); //닉네임 받음
					
					System.out.println("저장할 매치 : " + id);
					//DB에 매치리스트가 중복되었는지 검사
					
					boolean check = sql.checkMatch_info(name, id);
					System.out.println("체크 성공 : " + check);
					if(!check) { //중복되는 매치가 존재할경우
						//중복된 매치 이므로 실패 메시지 후 switch문 빠져나감
						System.out.println("중복");
						dataOutputStream.writeUTF("OVERLAP");
						break;
					}
					else {
						dataOutputStream.writeUTF("CLEAR");
					}
					
					while(true) {
						String msg = dataInputStream.readUTF();
						
						if(msg.equals("CLEAR")) {
							clear = true;
							break;
						}
						
						StringTokenizer msg_st = new StringTokenizer(msg, "$");
						
						String token = msg_st.nextToken();
						switch(token) {
							case "MATCH":
								String match_name = msg_st.nextToken();
								String match_id = msg_st.nextToken();
								float match_length = Float.parseFloat(msg_st.nextToken());
								String match_var = msg_st.nextToken();
								sql.insertMatch_info(match_name, match_id, match_length, match_var);
								break;
							case "USER":
								String user_id = msg_st.nextToken();
								String user_name = msg_st.nextToken();
								int gold = Integer.parseInt(msg_st.nextToken());
								int last = Integer.parseInt(msg_st.nextToken());
								int level = Integer.parseInt(msg_st.nextToken());
								int player = Integer.parseInt(msg_st.nextToken());
								sql.insertUser_info(user_id, user_name, gold, last, level, player);
								break;
							case "TRAIT":
								String trait_id = msg_st.nextToken();
								String trait_name = msg_st.nextToken();
								int trait_no = Integer.parseInt(msg_st.nextToken());
								String trait_trait_name = msg_st.nextToken();
								int num = Integer.parseInt(msg_st.nextToken());
								int current = Integer.parseInt(msg_st.nextToken());
								sql.insertTrait_info(trait_id, trait_name, trait_no, trait_trait_name, num, current);
								break;
							case "UNIT":
								String unit_id = msg_st.nextToken();
								String unit_name = msg_st.nextToken();
								int unit_no = Integer.parseInt(msg_st.nextToken());
								String unit_character_id = msg_st.nextToken();
								int unit_tier = Integer.parseInt(msg_st.nextToken());
								int unit_1 = Integer.parseInt(msg_st.nextToken());
								int unit_2 = Integer.parseInt(msg_st.nextToken());
								int unit_3 = Integer.parseInt(msg_st.nextToken());
								sql.insertUnit_info(unit_id, unit_name, unit_no, unit_character_id, unit_tier, unit_1, unit_2, unit_3);
								break;
						}
					}
					
					
					/*
					System.out.println("매치리스트 사이즈 : " + request.matchDto.size());
					for(int i = 0 ; i < request.matchDto.size() ; i++) { //저장된 매치 리스트 개수 만큼 반복
						
						System.out.println("찾는 매치 : " + id + "  매치 리스트 : " + request.matchDto.get(i).getMetadata().getMatch_id());
						if(id.equals(request.matchDto.get(i).getMetadata().getMatch_id())) { //같은 매치리스트를 찾으면 
							System.out.println("저장할 매치 찾음");
							
							//매치 인포 저장
							sql.insertMatch_info(request.summonerDTO.getName(), request.matchDto.get(i).getMetadata().getMatch_id(), request.matchDto.get(i).getInfo().getGame_length(), request.matchDto.get(i).getInfo().getGame_variation());
							
							//매치의 참여자만큼 반복
							for(int j = 0 ;j < request.matchDto.get(i).getInfo().getParticipants().size(); j++) {
								//유저 인포 저장
								sql.insertUser_info(request.matchDto.get(i).getMetadata().getMatch_id(), request.participantList.get(i).get(j), request.matchDto.get(i).getInfo().getParticipants().get(j).getGold_left(), request.matchDto.get(i).getInfo().getParticipants().get(j).getLast_round(), request.matchDto.get(i).getInfo().getParticipants().get(j).getLevel(), request.matchDto.get(i).getInfo().getParticipants().get(j).getPlayers_eliminated());
								
								//해당 유저의 시너지 개수만큼 반복
								for(int k =0 ; k < request.matchDto.get(i).getInfo().getParticipants().get(j).getTraits().size(); k++) {
									//시너지 저장
									sql.insertTrait_info(request.matchDto.get(i).getMetadata().getMatch_id(), request.participantList.get(i).get(j), k, request.matchDto.get(i).getInfo().getParticipants().get(j).getTraits().get(k).getName(), request.matchDto.get(i).getInfo().getParticipants().get(j).getTraits().get(k).getNum_units(), request.matchDto.get(i).getInfo().getParticipants().get(j).getTraits().get(k).getTier_current());
								}
								
								//해당 유저의 유닛 개수 만큼 반복
								for(int l = 0 ; l < request.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().size(); l++) {
									//유닛 아이템을 저장할 배열
									int[] tmp = new int[3];
									//아이템 배열을 꺼내 각 변수에 저장
									tmp = request.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems();
									int item_1 = 0;
									int item_2 = 0;
									int item_3 = 0;
									switch(tmp.length) {
									case 0:
										break;
									case 1:
										//item_1 = (r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems().get(0)).intValue();
										item_1 = tmp[0];
										break;
									case 2:
										//item_1 = (r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems().get(0)).intValue();
										//item_2 = (r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems().get(1)).intValue();
										item_1 = tmp[0];
										item_2 = tmp[1];
										break;
									case 3:
										//item_1 = (r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems().get(0)).intValue();
										//item_2 = (r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems().get(1)).intValue();
										//item_3 = (r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getItems().get(2)).intValue();
										item_1 = tmp[0];
										item_2 = tmp[1];
										item_3 = tmp[2];
										break;		
									}
									
									//유닛 인포 저장
									sql.insertUnit_info(request.matchDto.get(i).getMetadata().getMatch_id(), request.participantList.get(i).get(j), l, request.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getCharacter_id(), request.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l).getTier(), item_1, item_2, item_3);
									//r.matchDto.get(i).getInfo().getParticipants().get(j).getUnits().get(l)
								}
							}
							
							//저장이 완료된 시점
							clear = true;
						}	
						
					}
					*/
					
					 if(clear) {
						 dataOutputStream.writeUTF("CLEAR"); //성공 메시지
					 }
					 else {
						 dataOutputStream.writeUTF("FAIL"); //실패 메시지 
					 }
					
					
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
