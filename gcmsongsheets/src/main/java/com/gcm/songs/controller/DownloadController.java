package com.gcm.songs.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zc.auth.CatalystSDK;
import com.zc.component.files.ZCFile;
import com.zc.component.files.ZCFolder;

@RestController
public class DownloadController {

	@GetMapping("/home")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("Version-1");
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

		case "En-thevigal":
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
	
	@GetMapping("download-song-word/{song-name}")
	public ResponseEntity<String> downloadWord(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("song-name") String songName) throws Exception {

		CatalystSDK.init(request);

		ZCFile fileStore = ZCFile.getInstance();
		ZCFolder folder = fileStore.getFolderInstance(10165000000046793L);
		InputStream is = null;
		String fileId = "";

		switch (songName) {
		case "Maruroobamaakidum-dhevane": 
			is = folder.downloadFile(10165000000048215L);
			fileId = "10165000000048215";
			break;

		case "Naan-unnai-aasirvadhithu-perugapanuven": //no
			is = folder.downloadFile(10165000000048114L);
			fileId = "10165000000048114";
			break;

		case "Paadhai-theria-aataipola":
			is = folder.downloadFile(10165000000048210L);
			fileId = "10165000000048210";
			break;

		case "Needhiyulla-raajave-vaanga":
			is = folder.downloadFile(10165000000048190L);
			fileId = "10165000000048190";
			break;

		case "Dhesathai-sutri-vandhom-dhevane": //no
			is = folder.downloadFile(10165000000048119L);
			fileId = "10165000000048119";
			break;

		case "Naan-edhai-ninaithum-kalangamaaten":
			is = folder.downloadFile(10165000000048180L);
			fileId = "10165000000048180";
			break;

		case "Thadaigalai-neekubavar":
			is = folder.downloadFile(10165000000048175L);
			fileId = "10165000000048175";
			break;

		case "Setrinindremmai-sirushtithu":
			is = folder.downloadFile(10165000000048170L);
			fileId = "10165000000048170";
			break;

		case "Karthar-en-meiparai-irunkindreer":
			is = folder.downloadFile(10165000000048165L);
			fileId = "10165000000048165";
			break;

		case "Ennai-aalum-dheivam": //no
			is = folder.downloadFile(10165000000048074L);
			fileId = "10165000000048074";
			break;

		case "En-vaazhvin-aadhaaram-neerae": //no
			is = folder.downloadFile(10165000000048069L);
			fileId = "10165000000048069";
			break;

		case "En-thevigal":
			is = folder.downloadFile(10165000000048160L);
			fileId = "10165000000048160";
			break;

		case "En-dhevane-ummai-paadiduven":
			is = folder.downloadFile(10165000000048155L);
			fileId = "10165000000048155";
			break;

		case "En-sondhame-en-piriyame":
			is = folder.downloadFile(10165000000048150L);
			fileId = "10165000000048150L";
			break;

		case "Ezhumbu-ezhumbu-seeyone":
			is = folder.downloadFile(10165000000048145L);
			fileId = "10165000000048145L";
			break;

		case "Ethanaidhaan-idargal-vandhaalum": //no
			is = folder.downloadFile(10165000000048029L);
			fileId = "10165000000048029";
			break;

		case "Ummai-paadamal-naan-enna-seiveno": //no
			is = folder.downloadFile(10165000000048024L);
			fileId = "10165000000048024";
			break;

		case "Ummai-thuthika-thuthika-endhan-ullam-magizhudhe":
			is = folder.downloadFile(10165000000048140L);
			fileId = "10165000000048140";
			break;

		case "Ummil-nilai-nirka":
			is = folder.downloadFile(10165000000048135L);
			fileId = "10165000000048135";
			break;

		case "Immatum-kaatheerae-nandri":
			is = folder.downloadFile(10165000000048130L);
			fileId = "10165000000048130";
			break;

		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
			break;

		}

		if (is == null)
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + songName + ".docx\"");

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
				"WORD Downloaded successfully!");

		return ResponseEntity.status(HttpStatus.CREATED).body(jsonResponse);
	}
}
