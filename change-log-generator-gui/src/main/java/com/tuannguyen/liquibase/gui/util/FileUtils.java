package com.tuannguyen.liquibase.gui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {
	public static void emptyDirectory(Path path) throws IOException {
		File directory = path.toFile();
		if (!directory.exists() || !directory.isDirectory()) {
			throw new IOException("Directory " + directory.getAbsolutePath() + " does not exists");
		}
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					emptyDirectory(file.toPath());
				} else {
					file.delete();
				}
			}
		}
	}
}
