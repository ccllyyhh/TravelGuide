package com.caoliyuan.travelGuide.controller;

import com.caoliyuan.travelGuide.domain.CommentModel;
import com.caoliyuan.travelGuide.domain.NoteItemModel;
import com.caoliyuan.travelGuide.domain.NoteModel;
import com.caoliyuan.travelGuide.service.NoteService;
import com.caoliyuan.travelGuide.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    @Autowired
    NoteService noteService;


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
    public String getNotes() {
        JSONObject rsp = new JSONObject();
        List<NoteModel> notes = noteService.getNotes(10,"VIEW_NUM");
        JSONArray datas = new JSONArray(notes);
        rsp.put("success", true);
        rsp.put("datas",datas);
        return rsp.toString();
    }

    @RequestMapping(value = "/getNoteDetail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getNoteDetail(
            @RequestParam("noteid") int noteid
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
    public String comment(@RequestParam("noteid") int noteid,
                         @RequestParam("content") String content) {

        JSONObject rsp = new JSONObject();
        int userid = 1;//TODO session
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

    @RequestMapping(value = "/getComment", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String temple(@RequestParam("noteid") int noteid) {

        JSONObject rsp = new JSONObject();
        rsp.put("success", true);
        rsp.put("datas", new JSONArray(noteService.getNoteCommentByid(noteid)));
        return rsp.toString();
    }
}
