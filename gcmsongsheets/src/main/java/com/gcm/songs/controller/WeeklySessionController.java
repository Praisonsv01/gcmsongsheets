package com.gcm.songs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gcm.songs.model.PromiseWordEntities;
import com.gcm.songs.model.WeeklySession;
import com.zc.auth.CatalystSDK;
import com.zc.component.files.ZCFile;
import com.zc.component.files.ZCFileDetail;
import com.zc.component.files.ZCFolder;
import com.zc.component.object.ZCObject;
import com.zc.component.object.ZCRowObject;
import com.zc.component.object.ZCTable;
import com.zc.component.zcql.ZCQL;

@RestController
@RequestMapping("/api/v1/")
public class WeeklySessionController {
    private static final String GETWEEKLYSESSIONQUERY = "SELECT * from WeeklySession ORDER BY WeeklySession.CREATEDTIME DESC";

    private static final Logger LOGGER = Logger.getLogger(WeeklySessionController.class.getName());

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("eng_word") MultipartFile engWord,
            @RequestParam("tam_word") MultipartFile tamWord,
            HttpServletRequest request) {
        try {
            // Validate file sizes properly
            double engWordSizeInMB = engWord.getSize() / (1024.0 * 1024);
            double tamWordSizeInMB = tamWord.getSize() / (1024.0 * 1024);

            if (engWordSizeInMB > 50) {
                return ResponseEntity.badRequest().body(Map.of(
                        "error", "EngWord file exceeds 50MB. Please upload a smaller file."));
            }
            if (tamWordSizeInMB > 50) {
                return ResponseEntity.badRequest().body(Map.of(
                        "error", "TamWord file exceeds 50MB. Please upload a smaller file."));
            }

            // Initialize Catalyst SDK
            CatalystSDK.init(request);

            // Convert MultipartFiles to Files
            File convertedEngWord = convertMultiPartToFile(engWord);
            File convertedTamWord = convertMultiPartToFile(tamWord);

            if (!convertedEngWord.exists() || convertedEngWord.length() == 0) {
                throw new IllegalArgumentException(
                        "EngWord file is empty or invalid: " + convertedEngWord.getAbsolutePath());
            }
            if (!convertedTamWord.exists() || convertedTamWord.length() == 0) {
                throw new IllegalArgumentException(
                        "TamWord file is empty or invalid: " + convertedTamWord.getAbsolutePath());
            }

            LOGGER.log(Level.INFO, "Uploading EngWord File: " + convertedEngWord.getAbsolutePath());
            LOGGER.log(Level.INFO, "Uploading TamWord File: " + convertedTamWord.getAbsolutePath());

            // Upload to Catalyst
            ZCFile fileStore = ZCFile.getInstance();
            ZCFolder folder = fileStore.getFolderInstance(12520000000030798L);

            ZCFileDetail engWordFileDetail = folder.uploadFile(convertedEngWord);
            ZCFileDetail tamWordFileDetail = folder.uploadFile(convertedTamWord);

            // Save entity
            PromiseWordEntities promiseWordEntities = new PromiseWordEntities(
                    engWordFileDetail.getFileId().toString(),
                    tamWordFileDetail.getFileId().toString(),
                    engWordFileDetail.getFileName(),
                    tamWordFileDetail.getFileName());
            savePromiseWordEntities(request, promiseWordEntities);

            // Clean up temp files
            convertedEngWord.delete();
            convertedTamWord.delete();

            return ResponseEntity.ok(Map.of(
                    "msg", "Files uploaded and data saved successfully!",
                    "eng_word_id", engWordFileDetail.getFileId().toString(),
                    "eng_word_name", engWordFileDetail.getFileName(),
                    "tam_word_id", tamWordFileDetail.getFileId().toString(),
                    "tam_word_name", tamWordFileDetail.getFileName()));

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "File upload failed", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "File upload failed: " + e.getMessage()));
        }
    }

    // Helper method to convert MultipartFile to File
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }

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

    @GetMapping("/latest-eng-word")
    public ResponseEntity<?> getLatestEngWord(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Request Origin: " + request.getHeader("Origin"));
        return streamFileFromPromiseWord(request, response, true);
    }

    @GetMapping("/latest-tam-word")
    public ResponseEntity<?> getLatestTamWord(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Request Origin: " + request.getHeader("Origin"));
        return streamFileFromPromiseWord(request, response, false);
    }

    private ResponseEntity<?> streamFileFromPromiseWord(HttpServletRequest request, HttpServletResponse response,
            boolean isEngWord) {
        try {
            // Initialize Catalyst SDK
            CatalystSDK.init(request);

            // Fetch the latest record from the PromiseWord table
            String query = "SELECT * FROM PromiseWord ORDER BY CREATEDTIME DESC LIMIT 1";
            ArrayList<ZCRowObject> rowList = ZCQL.getInstance().executeQuery(query);

            if (rowList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No records found");
            }

            ZCRowObject latestRow = rowList.get(0);
            String fileId = isEngWord ? latestRow.get("EngWordId").toString() : latestRow.get("TamWordId").toString();
            String fileName = isEngWord ? latestRow.get("EngWordName").toString()
                    : latestRow.get("TamWordName").toString();

            // Get Catalyst File Store instance
            ZCFile fileStore = ZCFile.getInstance();
            ZCFolder folder = fileStore.getFolderInstance(12520000000030798L);

            // Download file using the ID
            InputStream fileStream = folder.downloadFile(Long.parseLong(fileId));

            if (fileStream == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
            }

            // Stream the file
            response.setContentType(getContentType(fileName));
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            try (InputStream inputStream = fileStream) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
                response.getOutputStream().flush();
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing file");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
            }

            // Prepare JSON response
            String jsonResponse = String.format("{\"fileName\":\"%s\",\"fileId\":%s,\"message\":\"%s\"}",
                    fileName, fileId, "File streamed successfully!");

            return ResponseEntity.status(HttpStatus.CREATED).body(jsonResponse);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch file", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch file: " + e.getMessage());
        }
    }

    // Helper method to determine content type based on file extension
    private String getContentType(String fileName) {
        if (fileName.endsWith(".png")) {
            return "image/png";
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else {
            return "application/octet-stream"; // Default content type
        }
    }

    // Helper method to save InputStream to a temporary file
    private File saveInputStreamToFile(InputStream inputStream, String fileName) throws IOException {
        File tempFile = new File(System.getProperty("java.io.tmpdir"), fileName);
        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return tempFile;
    }

    public String savePromiseWordEntities(HttpServletRequest request, PromiseWordEntities promiseWordEntities)
            throws Exception {
        // Initialize the Catalyst SDK
        CatalystSDK.init(request);

        String engWordId = promiseWordEntities.getEngWordId();
        String tamWordId = promiseWordEntities.getTamWordId();
        String engWordName = promiseWordEntities.getEngWordName();
        String tamWordName = promiseWordEntities.getTamWordName();

        ZCObject object = ZCObject.getInstance();
        ZCTable tab = object.getTable("12520000000030822");
        ZCRowObject row = ZCRowObject.getInstance();
        row.set("EngWordId", engWordId);
        row.set("TamWordId", tamWordId);
        row.set("EngWordName", engWordName);
        row.set("TamWordName", tamWordName);
        tab.insertRow(row);

        System.out.println("Promise word data saved successfully!");

        return "Promise word data saved successfully!";
    }
}
