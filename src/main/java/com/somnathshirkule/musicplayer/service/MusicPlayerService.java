package com.somnathshirkule.musicplayer.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class MusicPlayerService {
	Map<Integer, File> musicFiles = new HashMap<>();
	Map<Integer, String> musicFileNames = new HashMap<>();
	
	
	
	private static final Logger log = Logger.getAnonymousLogger();  
	
	private void loadMusicFiles(){
		URL url = MusicPlayerService.class.getResource("resources");
		log.info("2");
		log.info("URL::" + url);
		if (url == null) {
		     // error - missing folder
		} else {
		    File dir = null;
			try {
				dir = new File(url.toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			var counter = 0;
		    for (File nextFile : dir.listFiles()) {
		        // Do something with nextFile
		    	int index = counter++;
		    	musicFiles.put(index, nextFile);
				musicFileNames.put(index, nextFile.getName());
		    	log.info(nextFile.getName());
		    }
		}
//		File folder;
//		File[] listOfFiles = null;
//		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//		folder = new File(classloader.getResource("musicfolder").getPath());
//		listOfFiles = folder.listFiles();
//		var counter = 0;
//		for (File file : listOfFiles) {
//			int index = counter++;
//			musicFiles.put(index, file);
//			musicFileNames.put(index, file.getName());
//		}
	}

	@SuppressWarnings("resource")
	public byte[] getMusicData(String id) {
		byte[] data = null;
		try {
			data = new FileInputStream(musicFiles.get(Integer.parseInt(id))).readAllBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public Map<Integer, String> getMusicList() {
		loadMusicFiles();
		return musicFileNames;
	}
}
