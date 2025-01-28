package com.gcm.songs.controller;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcm.songs.service.Service;
import com.zc.auth.CatalystSDK;
import com.zc.component.files.ZCFile;
import com.zc.component.files.ZCFolder;
import com.zc.component.object.ZCObject;
import com.zc.component.object.ZCRowObject;
import com.zc.component.object.ZCTable;

@RestController
public class DownloadController {

	@Autowired
	Service service;

	@GetMapping("/home")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("GCMSONGSHEETS_BACKEND_APP_V3");
	}

	@PostMapping("/save-feedback")
	public ResponseEntity<String> saveFeedback(HttpServletRequest request, @RequestBody Map<String, String> feedback)
			throws Exception {
		// Initialize the Catalyst SDK
		CatalystSDK.init(request);

		// Extract inputs from the request body
		String feedbackText = feedback.get("feedbackText");
		String name = feedback.get("name");
		String emailId = feedback.get("emailId");

		System.out.println("Feedback Text: " + feedbackText);
		System.out.println("Name: " + name);
		System.out.println("Email ID: " + emailId);
		System.out.println("Inside save-feedback");

		// Save the feedback into the Catalyst table
		ZCObject object = ZCObject.getInstance();
		ZCTable tab = object.getTable("12520000000013001");
		ZCRowObject row = ZCRowObject.getInstance();
		row.set("FeedbackText", feedbackText);
		row.set("Name", name);
		row.set("EmailId", emailId);
		tab.insertRow(row);

		System.out.println("Saved feedback data!");

		// Send an email
		service.sendMail("Feedback received from: " + name + " (" + emailId + ")\n\n" + feedbackText);
		System.out.println("After sending mail - from Controller");

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
		case "Neer-siritha-siripukku-enna-porul":
			is = folder.downloadFile(10165000000067021L);
			fileId = "10165000000067021";
			break;

		case "Raajathi-raaja":
			is = folder.downloadFile(10165000000067016L);
			fileId = "10165000000067016";
			break;

		case "Bayapadaathe-sirumandhaye":
			is = folder.downloadFile(10165000000067011L);
			fileId = "10165000000067011";
			break;

		case "Endhan-jebathai-keta-dhevane":
			is = folder.downloadFile(10165000000067005L);
			fileId = "10165000000067005";
			break;

		case "Ennai-nesikum-yesuvae":
			is = folder.downloadFile(10165000000057008L);
			fileId = "10165000000057008";
			break;

		case "Parisutharae-varavendume":
			is = folder.downloadFile(10165000000057002L);
			fileId = "10165000000057002";
			break;

		case "Naan-unnai-aasirvadhithu-perugapanuven":
			is = folder.downloadFile(10165000000048114L);
			fileId = "10165000000048114";
			break;

		case "Needhiyulla-raajave-vaanga":
			is = folder.downloadFile(10165000000048104L);
			fileId = "10165000000048104";
			break;

		case "Naan-edhai-ninaithum-kalanga-maaten":
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

		case "Karthar-en-meiparai-irukindreer":
			is = folder.downloadFile(10165000000048079L);
			fileId = "10165000000048079";
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

		case "En-sondhame":
			is = folder.downloadFile(10165000000048049L);
			fileId = "10165000000048049";
			break;

		case "Ethanaidhaan-idargal-vandhaalum":
			is = folder.downloadFile(10165000000048029L);
			fileId = "10165000000048029";
			break;

		case "Ummai-paadaamal-naan-enna-seiveno":
			is = folder.downloadFile(10165000000048024L);
			fileId = "10165000000048024";
			break;

		case "Ummai-thuthikka-thuthikka":
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

	@GetMapping("download-song-ppt/{song-name}")
	public ResponseEntity<String> downloadPpt(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("song-name") String songName) throws Exception {

		CatalystSDK.init(request);

		ZCFile fileStore = ZCFile.getInstance();
		ZCFolder folder = fileStore.getFolderInstance(10165000000065012L);
		InputStream is = null;
		String fileId = "";

		switch (songName) {
		case "Ummai-thuthikka-thuthikka":
			is = folder.downloadFile(10165000000067135L); // Replace with corresponding PPT file ID if different
			fileId = "10165000000067135";
			break;

		case "Ethanaithaan-idargal-vandhaalum":
			is = folder.downloadFile(10165000000067129L);
			fileId = "10165000000067129";
			break;

		case "Ummai-paadaamal-naan-enna-seiveno":
			is = folder.downloadFile(10165000000067124L);
			fileId = "10165000000067124L";
			break;

		case "Ummil-nilai-nirka":
			is = folder.downloadFile(10165000000067118L);
			fileId = "10165000000067118";
			break;

		case "Neer-siritha-siripukku-enna-porul":
			is = folder.downloadFile(10165000000067113L);
			fileId = "10165000000067113";
			break;

		case "Immatum-kaatheerae-nandri":
			is = folder.downloadFile(10165000000067107L);
			fileId = "10165000000067107";
			break;

		case "En-thevigal-arintha-dheivame":
			is = folder.downloadFile(10165000000067102L);
			fileId = "10165000000067102";
			break;

		case "Naan-unnai-aasirvadhithu-perugapanuven":
			is = folder.downloadFile(10165000000067092L);
			fileId = "10165000000067092";
			break;

		case "En-dhevane-ummai-paadiduven":
			is = folder.downloadFile(10165000000067087L);
			fileId = "10165000000067087";
			break;

		case "Raajathi-raaja":
			is = folder.downloadFile(10165000000067075L);
			fileId = "10165000000067075";
			break;

		case "Bayapadaathe-sirumandhaye":
			is = folder.downloadFile(10165000000067070L);
			fileId = "10165000000067070";
			break;

		case "Thadaigalai-neekubavar":
			is = folder.downloadFile(10165000000067059L);
			fileId = "10165000000067059";
			break;

		case "En-sondhame":
			is = folder.downloadFile(10165000000067054L);
			fileId = "10165000000067054";
			break;

		case "Needhiyulla-raajave-vaanga":
			is = folder.downloadFile(10165000000067048L);
			fileId = "10165000000067048";
			break;

		case "Endhan-jebathai-keta-dhevane":
			is = folder.downloadFile(10165000000067043L);
			fileId = "10165000000067043";
			break;

		case "Setrinindremmai-sirushtithu":
			is = folder.downloadFile(10165000000067038L);
			fileId = "10165000000067038";
			break;

		case "Ennai-nesikum-yesuvae":
			is = folder.downloadFile(10165000000067032L);
			fileId = "10165000000067032";
			break;

		case "Parisutharae-varavendume":
			is = folder.downloadFile(10165000000067026L);
			fileId = "10165000000067026";
			break;

		case "En-vaazhvin-aadhaaram-neerae":
			is = folder.downloadFile(10165000000068010L);
			fileId = "10165000000068010";
			break;

		case "Naan-edhai-ninaithum-kalanga-maaten":
			is = folder.downloadFile(10165000000068015L);
			fileId = "10165000000068015";
			break;

		case "Karthar-en-meiparai-irukindreer":
			is = folder.downloadFile(10165000000068020L);
			fileId = "10165000000068020";
			break;

		// Add more cases here with corresponding file IDs for PPT files

		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
			break;
		}

		if (is == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
		}

		response.setContentType("application/vnd.ms-powerpoint");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + songName + ".ppt\"");

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
				"PPT Downloaded successfully!");

		return ResponseEntity.status(HttpStatus.CREATED).body(jsonResponse);
	}

}
