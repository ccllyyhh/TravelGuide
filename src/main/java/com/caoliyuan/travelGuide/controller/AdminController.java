package com.caoliyuan.travelGuide.controller;

import com.caoliyuan.travelGuide.domain.NoteModel;
import com.caoliyuan.travelGuide.service.NoteService;
import com.caoliyuan.travelGuide.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AdminController {
		@Autowired
		UserService userService;
		@Autowired
		NoteService noteService;

		@RequestMapping(value = "/admin/getalluser",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
		@ResponseBody
		public String getAllUser(@RequestParam("num") int num,
								 @RequestParam("page") int page,
								 HttpServletRequest request){
				JSONObject rsp = new JSONObject();
				HttpSession session = request.getSession();
				String role = session.getAttribute("role")==null?null:session.getAttribute("role").toString();
				if(role==null||!(role.equals("1"))){
						rsp.put("success",false);
						rsp.put("info","无权限");
						return rsp.toString();
				}else {
						rsp.put("success",true);
						rsp.put("total",userService.getUserNum());
						rsp.put("data",userService.findAllUser(num,page));
						return rsp.toString();
				}

		}

		@RequestMapping(value = "/admin/user",method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
		@ResponseBody
		public String deleteUser(@RequestParam("userid") int id,
								 HttpServletRequest request){
				JSONObject rsp = new JSONObject();
				HttpSession session = request.getSession();
				String role = session.getAttribute("role")==null?null:session.getAttribute("role").toString();
				if(role==null||!(role.equals("1"))){
						rsp.put("success",false);
						rsp.put("info","无权限");
						return rsp.toString();
				}else {
						rsp.put("success",userService.deleteUser(id));
						rsp.put("info","删除成功");
						return rsp.toString();
				}

		}

	@RequestMapping(value = "/admin/getNote", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getNotesRecent(@RequestParam("num") int num,
								 @RequestParam("page") int page,
								 HttpServletRequest request) {
		JSONObject rsp = new JSONObject();
		HttpSession session = request.getSession();
		String role = session.getAttribute("role")==null?null:session.getAttribute("role").toString();
		if(role==null||!(role.equals("1"))){
			rsp.put("success",false);
			rsp.put("info","无权限");
			return rsp.toString();
		}else {
			rsp.put("success", true);
			rsp.put("total",userService.getUserNum());
			rsp.put("data",noteService.getNotes(num, "CREATE_DAY"));
			return rsp.toString();
		}
	}

	@RequestMapping(value = "/admin/note",method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteNote(@RequestParam("noteid") long id,
							 HttpServletRequest request){
		JSONObject rsp = new JSONObject();
		HttpSession session = request.getSession();
		String role = session.getAttribute("role")==null?null:session.getAttribute("role").toString();
		if(role==null||!(role.equals("1"))){
			rsp.put("success",false);
			rsp.put("info","无权限");
			return rsp.toString();
		}else {
			rsp.put("success",noteService.deleteNote(id));
			rsp.put("info","删除成功");
			return rsp.toString();
		}

	}
}
