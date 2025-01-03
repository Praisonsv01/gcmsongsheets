package com.gcm.songs.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zc.auth.CatalystSDK;
import com.zc.component.files.ZCFile;
import com.zc.component.files.ZCFolder;
import com.zc.component.object.ZCObject;
import com.zc.component.object.ZCRowObject;
import com.zc.component.object.ZCTable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class DownloadController {

	@GetMapping("/home")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("Version-1");
	}

	@GetMapping("/save-feedback")
	public ResponseEntity<String> saveFeedback(@RequestParam("feedbackText") String feedbackText) throws Exception{
		System.out.println("inside save-feedback");
		ZCObject object = ZCObject.getInstance(); 
		ZCTable tab = object.getTable("10165000000063001"); 
		ZCRowObject row = ZCRowObject.getInstance(); 
		row.set("FeedbackText","George Smith"); 
		tab.insertRow(row);
		System.out.println("saved text!");
		
		return ResponseEntity.ok("Feedback saved successfully!");
	}
	
	@GetMapping("download-song-pdf/{song-name}")
	public ResponseEntity<String> downloadPdf(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("song-name") String songName) throws Exception {

		CatalystSDK.init(request);

		ZCFile fileStore = ZCFile.getInstance();
		ZCFolder folder = fileStore.getFolderInstance(10165000000046812L);
		InputStream is = null;
		String fileId = "";

		switch (songName) {
		case "Maruroobamaakidum-dhevane":
			is = folder.downloadFile(10165000000048119L);
			fileId = "10165000000048119";
			break;

		case "Naan-unnai-aasirvadhithu-perugapanuven":
			is = folder.downloadFile(10165000000048114L);
			fileId = "10165000000048114";
			break;

		case "Paadhai-theria-aataipola":
			is = folder.downloadFile(10165000000048109L);
			fileId = "10165000000048109";
			break;

		case "Needhiyulla-raajave-vaanga":
			is = folder.downloadFile(10165000000048104L);
			fileId = "10165000000048104";
			break;

		case "Dhesathai-sutri-vandhom-dhevane":
			is = folder.downloadFile(10165000000048119L);
			fileId = "10165000000048119";
			break;

		case "Naan-edhai-ninaithum-kalangamaaten":
			is = folder.downloadFile(10165000000048094L);
			fileId = "10165000000048094";
			break;

		case "Thadaigalai-neekubavar":
			is = folder.downloadFile(10165000000048089L);
			fileId = "10165000000048089";
			break;

		case "Setrinindremmai-sirushtithu":
			is = folder.downloadFile(10165000000048084L);
			fileId = "10165000000048084";
			break;

		case "Karthar-en-meiparai-irunkindreer":
			is = folder.downloadFile(10165000000048079L);
			fileId = "10165000000048079";
			break;

		case "Ennai-aalum-dheivam":
			is = folder.downloadFile(10165000000048074L);
			fileId = "10165000000048074";
			break;

		case "En-vaazhvin-aadhaaram-neerae":
			is = folder.downloadFile(10165000000048069L);
			fileId = "10165000000048069";
			break;

		case "En-thevigal-arintha-dheivame":
			is = folder.downloadFile(10165000000048064L);
			fileId = "10165000000048064";
			break;

		case "En-dhevane-ummai-paadiduven":
			is = folder.downloadFile(10165000000048059L);
			fileId = "10165000000048059";
			break;

		case "En-sondhame-en-piriyame":
			is = folder.downloadFile(10165000000048049L);
			fileId = "10165000000048049";
			break;

		case "Ezhumbu-ezhumbu-seeyone":
			is = folder.downloadFile(10165000000048039L);
			fileId = "10165000000048039";
			break;

		case "Ethanaidhaan-idargal-vandhaalum":
			is = folder.downloadFile(10165000000048029L);
			fileId = "10165000000048029";
			break;

		case "Ummai-paadamal-naan-enna-seiveno":
			is = folder.downloadFile(10165000000048024L);
			fileId = "10165000000048024";
			break;

		case "Ummai-thuthika-thuthika-endhan-ullam-magizhudhe":
			is = folder.downloadFile(10165000000048019L);
			fileId = "10165000000048019";
			break;

		case "Ummil-nilai-nirka":
			is = folder.downloadFile(10165000000048013L);
			fileId = "10165000000048013";
			break;

		case "Immatum-kaatheerae-nandri":
			is = folder.downloadFile(10165000000048007L);
			fileId = "10165000000048007";
			break;

		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
			break;

		}

		if (is == null)
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + songName + ".pdf\"");

		try (InputStream inputStream = is) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				response.getOutputStream().write(buffer, 0, bytesRead);
			}
			response.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing file");
		}

		String jsonResponse = String.format("{\"songName\":\"%s\",\"fileId\":%s,\"message\":\"%s\"}", songName, fileId,
				"PDF Downloaded successfully!");

		return ResponseEntity.status(HttpStatus.CREATED).body(jsonResponse);
	}
	
}
