package org.nii.niis.niim.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.niim.service.BoardService;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name="boardDAO")
	private BoardDAO boardDAO;
	
	
	@Override
	public Map<String, Object> getBoardMaster(Map<String, Object> param) throws Exception{
		return boardDAO.getBoardMaster(param);
	}
	
	@Override
	public List<Map<String, Object>> getBoardTopList(Map<String, Object> param) throws Exception {
		return boardDAO.getBoardTopList(param);
	}
	
	@Override
	public int getBoardListCnt(Map<String, Object> param) throws Exception {
		return boardDAO.getBoardListCnt(param);
	}
	@Override
	public List<Map<String, Object>> getBoardList(Map<String, Object> param) throws Exception {
		return boardDAO.getBoardList(param);
	}

	@Override
	public Map<String, Object> getBoardDetail(Map<String, Object> param) throws Exception {
		//조회수 처리
		if(param.containsKey("readYn") && "N".equals(param.get("readYn"))){
			boardDAO.updateHit(param);
		}
		return boardDAO.getBoardDetail(param);
	}
	
	@Override
	public List<Map<String, Object>> getBoardFileList(Map<String, Object> param) throws Exception {
		return boardDAO.getBoardFileList(param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> insertBoard(Map<String, Object> param) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		try{
			System.out.println("파일있음");
			String boardSeq = boardDAO.insertBoard(param);
			System.out.println("파일있음파일있음파일있음파일있음");
			List<Map<String, Object>> fileList = (List<Map<String, Object>>)param.get("fileList");
			
			if(fileList != null){
				for(int i=0; i<fileList.size(); i++){
					Map<String, Object> fileMap = fileList.get(i);
					fileMap.put("boardSeq", boardSeq);
					fileMap.put("createUsr", param.get("createUsr"));
					
					System.out.println("파일없");
					boardDAO.insertBoardFile(fileMap);
					System.out.println("파일없음없음없음없음없음없음");
				}
			}
			
			returnMap.put("isSuccess", "1");
		}catch(Exception e){
			e.printStackTrace();
			throw e;	//트랜잭션 rollback 처리를 위해 throw
		}
		
		return returnMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> updateBoard(Map<String, Object> param) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		try{
			int cnt = boardDAO.updateBoard(param);
			
			List<Map<String, Object>> fileList = (List<Map<String, Object>>)param.get("fileList");
			
			if(fileList != null && fileList.size() > 0){
				
				boardDAO.deleteBoardFileAll(param);
				
				for(int i=0; i<fileList.size(); i++){
					Map<String, Object> fileMap = fileList.get(i);
					fileMap.put("createUsr", param.get("lastChangeUsr"));
					
					boardDAO.insertBoardFile(fileMap);
				}
			}
			
			if(cnt == 0){
				returnMap.put("isSuccess", "1");
			}else{
				returnMap.put("isSuccess", "0");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;	//트랜잭션 rollback 처리를 위해 throw
		}
		
		return returnMap;
	}

	@Override
	public Map<String, Object> deleteBoard(Map<String, Object> param) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		try{
			int cnt = boardDAO.deleteBoard(param);
			
			if(cnt == 0){
				returnMap.put("isSuccess", "1");
			}else{
				returnMap.put("isSuccess", "0");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;	//트랜잭션 rollback 처리를 위해 throw
		}
		
		return returnMap;
	}

	@Override
	public Map<String, Object> getAttachFile(Map<String, Object> param) throws Exception {
		return boardDAO.getAttachFile(param);
	}
	
	@Override
	public Map<String, Object> insertBoardPop(Map<String, Object> param) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		try{
			boardDAO.insertBoard(param);
			boardDAO.insertBoardPop(param);
			
			returnMap.put("isSuccess", "1");
		}catch(Exception e){
			e.printStackTrace();
			throw e;	//트랜잭션 rollback 처리를 위해 throw
		}
		
		return returnMap;
	}
	
	@Override
	public Map<String, Object> updateBoardPop(Map<String, Object> param) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		try{
			int cnt = boardDAO.updateBoard(param);
			boardDAO.updateBoardPop(param);
			
			if(cnt == 0){
				returnMap.put("isSuccess", "1");
			}else{
				returnMap.put("isSuccess", "0");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;	//트랜잭션 rollback 처리를 위해 throw
		}
		
		return returnMap;
	}

	@Override
	public String checkUrl() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map = boardDAO.checkUrl(map);
		String value="";
        Iterator<String> keys = map.keySet().iterator();
        while( keys.hasNext() ){
            String key = keys.next();
             value = (String)map.get(key);
            System.out.println("키 : "+key+", 값 : "+value);
        }
        String result = value.substring(value.lastIndexOf("/")+1);
        return  result;
	}
}
