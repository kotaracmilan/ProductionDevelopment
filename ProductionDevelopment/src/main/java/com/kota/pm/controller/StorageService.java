package com.kota.pm.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

	@Value(value="${com.kota.pm.storage.basedir}")
	private String basePath;
	
	@PostConstruct
	public void init() {
		if(!Files.isDirectory(Paths.get(basePath), LinkOption.NOFOLLOW_LINKS)) {
			try {
				Files.createDirectories(Paths.get(basePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}
	}
	
	public Path saveFile(String path, byte[] data) throws IOException {
		return  Files.write(getPath(path), data);
	}
	
	private Path getPath(String path) {
		return Paths.get(basePath, File.separator, path);
		
	}
}
