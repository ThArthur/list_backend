package com.rokku.list_app.service;

import com.rokku.list_app.repository.UserManhwaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManhwaService {

    @Autowired
    UserManhwaRepository userManhwaRepository;

}