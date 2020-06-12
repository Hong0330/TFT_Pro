import javax.swing.tree.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.table.*;
import java.sql.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
public class TFTlogin {
	private DataInputStream in;
	private DataOutputStream out;
	StringTokenizer line; //문자 메시지 구분자
	Socket sock;
	
	String loginName = null; //로그인할 아이디 저장
	sql sql = new sql();
	Request r = new Request();
	JSONParser jsonParser = new JSONParser();
	
	TFTlogin(Socket socket){
		sock = socket;
	}
	
	void loginPage() {
		String allMsg = null; //전체 메시지
		String sign = null; //클라이언트 신호
		String next = null; //다음 메시지
		
		try {
			out = new DataOutputStream(sock.getOutputStream());
			in = new DataInputStream(sock.getInputStream());
		}catch(IOException e) {};
		
		while(true) {
			try {
				allMsg = in.readUTF();
			}catch(IOException e) {
				return;
			};
			
			line = new StringTokenizer(allMsg, "$");
			sign = line.nextToken();
			next = line.nextToken();
			if(sign.equals("LOGIN")) { //로그인 요구
				String ID = null;
				String PW = null;
				String NAME = null;
				line = new StringTokenizer(next, "&&");
				ID = line.nextToken();
				PW = line.nextToken();
				NAME = line.nextToken();
				
				//DB로  로그인 검사 ID, PW 사용
				//loginName = (DB 로그인 SQL);
				// 성공 시 : 로그인 성공한 아이디
				// 실패 시 : LOGINFAIL
				sql.selectSummoner_info(ID, PW);
				
				if(!loginName.equals("LOGINFAIL")) { //로그인 성공
					loginName = loginName + " f"; //닉네임 불량 방지
					line = new StringTokenizer("loginName", " ");
					loginName = line.nextToken();
					TFTMenu mainMenu = new TFTMenu(sock);
					
					loginName = ID; // 아이디 저장 및 아이디 전달
					try {
						out.writeUTF("LOGINSUCCESS$"); //로그인 성공 메시지
					}catch(IOException e) {}
					
					//로그인 성공 후 전적 검색 보내기
					try {
						
						if(r.callSummonerDTO(NAME)) { //닉네임 으로 검색
							if(r.callLeagueEntryDTO(r.summonerDTO.getId())) {
								if(r.callMatchList(r.summonerDTO.getPuuid())) {
									
								}
								else {//문제발생
									//문제처리
								}
							}
							else { //문제발생
								//문제처리
							}
						}
						else { //문제발생
							//문제처리
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//전적 검색완료
					
					//티어정보들 전송
					//데이터들 순서  티어/랭크/닉네임/리그포인트/승수/패수
					String strTier = "TIER$" + r.leagueEntryDTO.getTier() + "&&" + r.leagueEntryDTO.getRank() + "&&" + r.leagueEntryDTO.getSummonerName() + "&&" + r.leagueEntryDTO.getLeaguePoints() + "&&" + r.leagueEntryDTO.getWins() + "&&" + r.leagueEntryDTO.getLosses();
					try {
						out.writeUTF(strTier);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//매치정보 전송
					/*
					for(int i = 0 ; i<r.matchList.size() ; i++) {	//검색된 매치 수만큼 반복
						//매치 기본정보 순서  매치번호(0,1,2)/게임길이/은하계정보
						String strMatch = "MATCH$" + i +"&&"+ r.matchDto.get(i).getInfo().getGame_length() + "&&" + r.matchDto.get(i).getInfo().getGame_variation();
						for(int j = 0 ; j < r.matchList.get(i).length() ; j++) {
							String strUser = "USER$" + 
						}
					}
					*/
					//JSON 파일로 보내는걸로 설정
					for(int i = 0 ; i < r.matchObject.size() ; i++ ) {
						try {
							ObjectOutputStream outStream = new ObjectOutputStream(sock.getOutputStream());
							JSONObject tmpObject = r.matchObject.get(i);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					mainMenu.setRequest(r);
					//반복문으로 작성
					
					mainMenu.SearchMenu(); // 페이지 동작
					
				}else { //로그인 실패
					try {
						out.writeUTF("LOGINFAIL$");
					}catch(IOException e) {}
				}
				
			}else if(sign.equals("ENDPAGE")) { //나가기 요구
				try {
					out.writeUTF("ENDPAGE$"); // 시작 페이지 이동
				}catch(IOException e) {}
				return;
				
			}else { // 잘못된 입력 처리
				try {
					out.writeUTF("FAILMSG$");
				}catch(IOException e) {}
			}
			
		}
	}
}
