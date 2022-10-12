package com.somnathshirkule.musicplayer.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class MusicPlayerService {
	Map<Integer, File> musicFiles = new HashMap<>();
	Map<Integer, String> musicFileNames = new HashMap<>();
	
	private void loadMusicFiles() {
		File folder;
		File[] listOfFiles = null;
		try {
			folder = new ClassPathResource("musicfolder").getFile();
			listOfFiles = folder.listFiles();
			var counter = 0;
			for (File file : listOfFiles) {
				int index = counter++;
				musicFiles.put(index, file);
				musicFileNames.put(index, file.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
