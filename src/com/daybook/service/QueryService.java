/*
 * This is a QueryService service layer class.
 * This class handles all the functions related to query activity which are: 
 * submit query, view query, reply query etc.
 * 
 * @Author: Ranjit Kaliraj
 * Date: 19th Aug. 2014
 */

package com.daybook.service;

import com.daybook.dao.QueryDao;
import com.daybook.domain.Query;
import com.daybook.dto.QueryDto;

public class QueryService {
	private QueryDao queryDao;
	private Query queryData;
	
	/*
	 * This function adds query information to the database
	 * it takes queryDto object as parameter which contains details of user query information
	 * on successful addition, it sets DTO object's method QueryAddStatus() to true.
	 */
	public void addUserQuery(QueryDto data){
		System.out.println("Service.Query::Starting to add user query");
		queryDao = new QueryDao();
		queryData = new Query();
		
		//adding user info in query data object
		queryData.setUser_Id(data.getUserId());
		queryData.setSubject(data.getSubject());
		queryData.setDetail(data.getDetails());
		
		System.out.println("Service.Query::calling addqueryInfo()");
		queryDao.addQueryInfo(queryData);	//calling dao object
		if (queryData.isQuerySubmited()){
			System.out.println("Service.Query::setting query add status true");
			data.setQueryAddStatus(true);	//setting query add status true
		}
	}
	
	/*
	 * This function set the query detail of user in Query Dto object
	 */
	public void getUserQuery(QueryDto data){
		System.out.println("Service.Query::Starting to get user query");
		queryDao = new QueryDao();
		queryData = new Query();
		
		//adding user info in query data object
		queryData.setUser_Id(data.getUserId());
		
		System.out.println("Service.Query::calling method getQueryDetail()");
		if (queryDao.getQueryDetail(queryData)){
			System.out.println("Service.Query::adding the details in DTO object");
			data.setSubject(queryData.getSubject());
			data.setDetails(queryData.getDetail());
			data.setQueryDate(queryData.getDate());
			data.setView_Status(queryData.isViewStatusTrue());
			data.setAdmin_reply(queryData.getAdmin_Reply());
			data.setReply_Status(queryData.isQueryReplySubmitted());
		}
	}
}
