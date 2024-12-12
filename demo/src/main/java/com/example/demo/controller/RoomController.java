package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.ChatRoom;
import com.example.demo.service.ChatService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public ModelAndView getRooms() {
        List<ChatRoom> rooms = chatService.getAllRooms();
        System.out.println("Fetched rooms: " + rooms);  // 디버깅용 로그
        
        ModelAndView mv = new ModelAndView("roomList");
        mv.addObject("rooms", rooms);
        return mv;
    }


    @PostMapping
    public ResponseEntity<ChatRoom> createRoom(@RequestBody ChatRoom chatRoom) {
        System.out.println(chatRoom);
        return ResponseEntity.ok(chatService.createRoom(chatRoom));
    }

    @GetMapping("/{roomId}")
    public ModelAndView getRoom(@PathVariable String roomId) {
        ModelAndView mv = new ModelAndView("chat");
        mv.addObject("room", chatService.getRoom(roomId));
        mv.addObject("roomId", roomId);
        return mv;
    }


    // @GetMapping("/{roomId}/check")
    // @ResponseBody
    // public ResponseEntity<?> checkRoomAvailability(@PathVariable String roomId) {
    //     ChatRoom room = chatRoomService.getRoom(roomId);
    //     if (room == null) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     if (room.isFull()) {
    //         return ResponseEntity.badRequest().body("Room is full");
    //     }
    //     return ResponseEntity.ok().build();
    // }
}
