package com.caoliyuan.travelGuide.controller;

import com.caoliyuan.travelGuide.domain.CommentModel;
import com.caoliyuan.travelGuide.domain.NoteItemModel;
import com.caoliyuan.travelGuide.domain.NoteModel;
import com.caoliyuan.travelGuide.domain.UserModel;
import com.caoliyuan.travelGuide.service.NoteService;
import com.caoliyuan.travelGuide.service.UserService;
import com.caoliyuan.travelGuide.util.GenerateID;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class NoteController {

    @Autowired
    NoteService noteService;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/temple2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String temple(@RequestParam("arg1") String arg1,
                         @RequestParam("arg2") String arg2) {

        JSONObject rsp = new JSONObject();
        //TODO 你的业务逻辑
        rsp.put("success", true);
        rsp.put("info", "登录成功");
        return rsp.toString();
    }


    @RequestMapping(value = "/getNotes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getNotes(@RequestParam int num) {
        JSONObject rsp = new JSONObject();
        List<NoteModel> notes = noteService.getNotes(num,"VIEW_NUM");
        JSONArray datas = new JSONArray(notes);
        rsp.put("success", true);
        rsp.put("datas",datas);
        return rsp.toString();
    }

    @RequestMapping(value = "/getNotesRecent", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getNotesRecent(@RequestParam int num) {
        JSONObject rsp = new JSONObject();
        List<NoteModel> notes = noteService.getNotes(num,"CREATE_DATE");
        JSONArray datas = new JSONArray(notes);
        rsp.put("success", true);
        rsp.put("datas",datas);
        return rsp.toString();
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String search(@RequestParam String keyword) {
        JSONObject rsp = new JSONObject();
        List<NoteModel> notes = noteService.searchNotes(keyword);
        JSONArray datas = new JSONArray(notes);
        rsp.put("success", true);
        rsp.put("datas",datas);
        return rsp.toString();
    }

    @RequestMapping(value = "/getNoteDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getNoteDetail(
            @RequestParam("noteid") long noteid
    ) {
        JSONObject rsp = new JSONObject();
        //拿攻略信息
        NoteModel note = noteService.getNoteByid(noteid);
        if(note==null){
            rsp.put("success", false);
            return rsp.toString();
        }
        //阅读数加一
        noteService.viewNumPlusByid(noteid);
        //拿足迹信息
        List<NoteItemModel> steps = noteService.getNoteDetialById(noteid);
        JSONArray datas = new JSONArray(steps);
        rsp.put("success", true);
        rsp.put("note",new JSONObject(note));
        rsp.put("datas",datas);
        return rsp.toString();
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String comment(@RequestParam("noteid") long noteid,
                          @RequestParam("content") String content,
                          HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        JSONObject rsp = new JSONObject();
        HttpSession session = request.getSession();
        if (session.getAttribute("userid")==null) {
            rsp.put("success", false);
            rsp.put("info", "评论失败");
        }
        int userid = (Integer) session.getAttribute("userid");
        boolean success = noteService.addComment(new CommentModel(noteid,userid,content,0));
        if (success){
            rsp.put("success", true);
            rsp.put("info", "评论成功");
        }else {
            rsp.put("success", false);
            rsp.put("info", "评论失败");
        }
        return rsp.toString();
    }

    @RequestMapping(value = "/getComment", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String temple(@RequestParam("noteid") long noteid) {

        JSONObject rsp = new JSONObject();
        JSONArray datas = new JSONArray();
        for (CommentModel c:noteService.getNoteCommentByid(noteid)) {
            JSONObject data = new JSONObject();
            UserModel user = userService.findUserByUserid(c.getUserid());
            if(user!=null){
                data.put("username",user.getNAME());
                data.put("photo",user.getPHOTO());
                data.put("time",c.getTime());
                data.put("content",c.getContent());
                datas.put(data);
            }
        }
        rsp.put("success", true);
        rsp.put("datas", datas);
        return rsp.toString();
    }

    @RequestMapping(value = "/addNote", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addNote(@RequestParam("title") String title,
                          @RequestParam("location") String location,
                          @RequestParam("startday") long startday,
                          @RequestParam("coverurl") String coverurl,
                          HttpServletRequest request) throws ParseException {

        JSONObject rsp = new JSONObject();
        HttpSession session = request.getSession();
        if (session.getAttribute("userid")==null) {
            rsp.put("success", false);
            rsp.put("errorcode",403);
            rsp.put("info", "请先登录");
            return rsp.toString();
        }
        int userid = (Integer) session.getAttribute("userid");
        long noteid = GenerateID.getGeneratID();
        NoteModel noteModel = new NoteModel(
                noteid,
                userid,
                title,
                coverurl,
                0,0,
                new Date(startday),
                location,
                new Date()
        );
        boolean success = noteService.addNote(noteModel);
        if (success){
            rsp.put("success", true);
            rsp.put("info", "创建成功");
            rsp.put("noteid",noteid);
        }else {
            rsp.put("success", false);
            rsp.put("info", "创建失败");
        }
        return rsp.toString();
    }


    @RequestMapping(value = "/addNoteItem", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addNoteItem(@RequestParam("noteid") long noteid,
                          @RequestParam("daynum") int daynum,
                          @RequestParam("imageurl") String imgurl,
                          @RequestParam("content") String content,
                          @RequestParam("time") String time,
                          @RequestParam("location") String location,
                          HttpServletRequest request){

        JSONObject rsp = new JSONObject();
        HttpSession session = request.getSession();
        if (session.getAttribute("userid")==null) {
            rsp.put("success", false);
            rsp.put("errorcode",403);
            rsp.put("info", "请先登录");
            return rsp.toString();
        }
        int userid = (Integer) session.getAttribute("userid");
        NoteItemModel noteItemModel = new NoteItemModel(
                null,noteid,daynum,imgurl,content,time,location
        );
        boolean success = noteService.addNoteItem(noteItemModel);
        if (success){
            rsp.put("success", true);
            rsp.put("info", "创建成功");
            rsp.put("noteid",noteid);
        }else {
            rsp.put("success", false);
            rsp.put("info", "创建失败");
        }
        return rsp.toString();
    }
}
