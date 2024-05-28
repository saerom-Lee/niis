/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nii.niis.niim.service.impl;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.nii.niis.niim.service.SearchAreaVO;
import org.nii.niis.niim.service.SpaceSearchService;
import org.springframework.stereotype.Service;

/**  
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : Sample Business Implement Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 * 
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by MOPAS All right reserved.
 */

@Service("niimSpaceSearchService")
public class SpaceSearchServiceImpl implements SpaceSearchService {
	
	/** SpaceSearchDAO */
    @Resource(name="niimSpaceSearchDAO")
    private SpaceSearchDAO spaceSearchDAO;
    
    /**
     * 시도를 조회한다.
     * @exception
     */
    public List<SearchAreaVO> selectAreaListSido(String keyword) throws Exception {
    	return spaceSearchDAO.selectAreaListSido(keyword);
    }

    /**
     * 시군구를 조회한다.
     * @exception
     */
    public List<SearchAreaVO> selectAreaListSigungu(String keyword) throws Exception {
    	return spaceSearchDAO.selectAreaListSigungu(keyword);
    }
    
    /**
     * 읍면동를 조회한다.
     * @exception
     */
    public List<SearchAreaVO> selectAreaListDong(String keyword) throws Exception {
    	return spaceSearchDAO.selectAreaListDong(keyword);
    }
    
    /**
     * 리를 조회한다.
     */
    public List<HashMap<String, Object>> selectAreaListLi(HashMap<String, Object> param) throws Exception {
    	return spaceSearchDAO.selectAreaListLi(param);
    }

}
