package com.gcm.songs.controller;

import com.gcm.songs.model.WeeklySession;
import com.zc.auth.CatalystSDK;
import com.zc.component.object.ZCObject;
import com.zc.component.object.ZCRowObject;
import com.zc.component.object.ZCTable;
import com.zc.component.zcql.ZCQL;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/")
public class WeeklySessionController {
    private static final String GETWEEKLYSESSIONQUERY = "SELECT * from WeeklySession ORDER BY WeeklySession.CREATEDTIME DESC";

    @PostMapping("/video-date-url")
    public ResponseEntity<String> saveVideoDateUrl(@RequestBody WeeklySession session, HttpServletRequest request) 
        throws Exception {
        CatalystSDK.init(request);

        String worshipDate = session.getWorshipDate();
        String worshipUrl = session.getWorshipUrl();
        String sermonDate = session.getSermonDate();
        String sermonUrl = session.getSermonUrl();

        System.out.println("Worship Date: " + worshipDate);
        System.out.println("Worship URL: " + worshipUrl);
        System.out.println("Sermon Date: " + sermonDate);
        System.out.println("Sermon URL: " + sermonUrl);

        ZCObject object = ZCObject.getInstance();
        ZCTable tab = object.getTable("12520000000030001");
        ZCRowObject row = ZCRowObject.getInstance();
        row.set("WorshipDate", worshipDate);
        row.set("WorshipUrl", worshipUrl);
        row.set("SermonDate", sermonDate);
        row.set("SermonUrl", sermonUrl);
        tab.insertRow(row);

        System.out.println("Session saved successfully");

        return ResponseEntity.ok("Session saved successfully");
    }

    @GetMapping("/video-date-url")
    public ResponseEntity<Map<String, Object>> getVideoDateUrl(HttpServletRequest request) throws Exception {

        CatalystSDK.init(request);
        
        WeeklySession session = new WeeklySession();
        ArrayList<ZCRowObject> rowList = ZCQL.getInstance().executeQuery(GETWEEKLYSESSIONQUERY);
        ZCRowObject firstRow = rowList.get(0);

        session.setWorshipDate(firstRow.get("WorshipDate").toString());
        session.setWorshipUrl(firstRow.get("WorshipUrl").toString());
        session.setSermonDate(firstRow.get("SermonDate").toString());
        session.setSermonUrl(firstRow.get("SermonUrl").toString());

        System.out.println("Worship Date: " + session.getWorshipDate());
        System.out.println("Worship URL: " + session.getWorshipUrl());
        System.out.println("Sermon Date: " + session.getSermonDate());
        System.out.println("Sermon URL: " + session.getSermonUrl());

        Map<String, Object> response = new HashMap<>();
        response.put("msg", "Session retrieved successfully");
        response.put("session", session);

        return ResponseEntity.ok(response);
    }
}
