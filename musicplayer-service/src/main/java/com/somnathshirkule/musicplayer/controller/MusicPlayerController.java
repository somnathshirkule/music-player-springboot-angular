package com.somnathshirkule.musicplayer.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.somnathshirkule.musicplayer.service.MusicPlayerService;

@RestController
public class MusicPlayerController {
	
	@Autowired
	MusicPlayerService musicPlayerService;
	
	@GetMapping("/api/music/{id}")
	public ResponseEntity<byte[]> getMusicData(@PathVariable String id){
		byte[] musicData = musicPlayerService.getMusicData(id);
		return new ResponseEntity<>(musicData, HttpStatus.OK);
	}
	
	@GetMapping("/api/music-list")
	public ResponseEntity<Map<Integer, String>> getMusicList(){
		Map<Integer, String> musicList = musicPlayerService.getMusicList();
		return new ResponseEntity<>(musicList, HttpStatus.OK);
	}
}
