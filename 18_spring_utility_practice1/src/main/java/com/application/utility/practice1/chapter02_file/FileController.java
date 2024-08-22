package com.application.utility.practice1.chapter02_file;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {

	@Value("${file.repo.path}")
	private String fileRepositoryPath;
	
	@GetMapping("/main")
	public String main() {
		return "file/fileMain";
	}
	
	@PostMapping("/upload1")
	@ResponseBody // jsScript를 사용하기 위함
	public String upload1(@RequestParam("upFile") MultipartFile upFile) throws IllegalStateException, IOException {
		
		if(!upFile.isEmpty()) {
			
			System.out.println("Uploaded File Name : " + upFile.getOriginalFilename());
			System.out.println("Uploaded File Content Type : " + upFile.getContentType());
			
			File targetFile = new File(fileRepositoryPath + upFile.getOriginalFilename());
			upFile.transferTo(targetFile);
		}
		
		String jsScript = """
				<script>
					alert('Uploading Success');
					location.href = '/file/main';
				</script>
				""";
		
		return jsScript;
	}
	
	@PostMapping("/upload2")
	@ResponseBody
	public String upload2(@RequestParam("files") List<MultipartFile> files) throws IllegalStateException, IOException {
		
		for (MultipartFile file : files) {
			
			if(!file.isEmpty()) {
				if(!file.isEmpty()) {
					
					String originalFileName = file.getOriginalFilename();
					
					UUID uuid = UUID.randomUUID();
					
					String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
					
					String uploadFileName = uuid + extension;
					
					file.transferTo(new File(fileRepositoryPath + uploadFileName));
				}
			}
		}
		
		String jsScript = """
				<script>
					alert('Uploading Success');
					location.href = '/file/main';
				</script>
				""";
		
		return jsScript;
	}
}
