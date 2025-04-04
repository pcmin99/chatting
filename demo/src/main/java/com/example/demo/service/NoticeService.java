package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Notice;
import com.example.demo.repository.NoticeRepository;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> getRecentNotices() {
        return noticeRepository.findTop5ByOrderByCreatedAtDesc();
    }
}