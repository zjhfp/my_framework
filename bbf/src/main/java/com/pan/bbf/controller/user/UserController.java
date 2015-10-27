package com.pan.bbf.controller.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pan.bbf.common.model.JsonResponse;
import com.pan.bbf.service.UserService;
import com.pan.bbf.user.entities.User;

/**
 * 用户相关
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String userList(){
		return "user/list";
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/adduser", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse addUser(@ModelAttribute(value="user") User user){
		JsonResponse jsonResponse = new JsonResponse();
		try{
			if(StringUtils.isBlank(user.getId())){
				userService.insert(user);
			}else{
				userService.updateUser(user);
			}
			jsonResponse.setResult(user.getId());
			jsonResponse.setStatus(HttpStatus.OK.toString());
		}catch(Exception e){
			log.error("添加用户异常",e);
			jsonResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			jsonResponse.setErrorMessage(e.getMessage());
		}
		return jsonResponse;
	}
	
	/**
	 * 通过ID获取用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getuser")
	@ResponseBody
	public JsonResponse getUser(@RequestParam(value="id") String id){
		JsonResponse jsonResponse = new JsonResponse();
		try{
			User user = userService.findById(id);
			jsonResponse.setResult(user);
			jsonResponse.setStatus(HttpStatus.OK.toString());
		}catch(Exception e){
			log.error("用户信息获取异常",e);
			jsonResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			jsonResponse.setErrorMessage(e.getMessage());
		}
		return jsonResponse;
	}
	
	/**
	 * 获取用户列表
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse list(){
		JsonResponse jsonResponse = new JsonResponse();
		try{
			List<User> result = userService.findAllUser();
			jsonResponse.setResult(result);
			jsonResponse.setStatus(HttpStatus.OK.toString());
		}catch(Exception e){
			log.error("用户信息获取异常",e);
			jsonResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			jsonResponse.setErrorMessage(e.getMessage());
		}
		return jsonResponse;
	}
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/del/userid/{userid}",method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse delUser(@PathVariable(value="userid") String userId){
		JsonResponse jsonResponse = new JsonResponse();
		try{
			userService.deleteUser(userId);
			jsonResponse.setStatus(HttpStatus.OK.toString());
		}catch(Exception e){
			log.error("用户信息获取异常",e);
			jsonResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			jsonResponse.setErrorMessage(e.getMessage());
		}
		return jsonResponse;
	}
	
}
