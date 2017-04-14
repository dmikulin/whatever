package ecx.mpopijac.restaurants.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileService {
	
	@Autowired
	Environment env;
	
	public String saveFile(MultipartFile image){
		String filename = image.getOriginalFilename();
		String directory = env.getProperty("upload.file.path");
		String filePath = Paths.get("." + File.separator + directory, filename).toString();
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			stream.write(image.getBytes());
			stream.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return filePath;
	}
}
