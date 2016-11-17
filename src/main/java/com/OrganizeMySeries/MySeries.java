package com.OrganizeMySeries;

import java.io.File;
import java.util.HashSet;
import java.util.TreeMap;

import org.apache.commons.io.FilenameUtils;

import info.debatty.java.stringsimilarity.Levenshtein;

public class MySeries {

	private HashSet<String> extensions;
	private HashSet<String> videos;
	private HashSet<String> subtitles;
	private TreeMap<Double,String> similarity;
	private Levenshtein levenshtein;
	private int numVideos;
	private int numSubtitles;
	public final static String DIRECTORY = ".";
	public final static String SUBTITLE_FORMAT = "srt";

	MySeries() {
		extensions = new HashSet<String>();
		videos = new HashSet<String>();
		subtitles = new HashSet<String>();
		similarity = new TreeMap<Double,String>();
		levenshtein = new Levenshtein();
		extensions.add("avi");extensions.add("rm");extensions.add("nsv");
    	extensions.add("amv");extensions.add("yuv");extensions.add("svi");
    	extensions.add("asf");extensions.add("flv");extensions.add("f4v");
    	extensions.add("mov");extensions.add("3gp");extensions.add("f4p");
    	extensions.add("qt");extensions.add("3g2");extensions.add("f4a");
    	extensions.add("vob");extensions.add("ogv");extensions.add("f4b");
    	extensions.add("mkv");extensions.add("ogg");extensions.add("wmv");
    	extensions.add("mng");extensions.add("mpg");extensions.add("webm");
    	extensions.add("mp4");extensions.add("mpeg");
    	extensions.add("rmvb");extensions.add("mxf");
    	countVideoFiles();
    	countStrFiles();
	}

	public void renameSubtitle(String video, String sub) {
		File folder = new File(DIRECTORY);
		File rename = new File(video + "." + SUBTITLE_FORMAT);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && FilenameUtils.getExtension(listOfFiles[i].getName()).equalsIgnoreCase(SUBTITLE_FORMAT)
					&& FilenameUtils.getBaseName(listOfFiles[i].getName()).equals(sub)) {
				listOfFiles[i].renameTo(rename);
			}
		}
	}

	private void countStrFiles() {
		File folder = new File(DIRECTORY);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && FilenameUtils.getExtension(listOfFiles[i].getName()).equalsIgnoreCase(SUBTITLE_FORMAT)) {
				this.subtitles.add(FilenameUtils.getBaseName(listOfFiles[i].getName()));
				setNumSubtitles(this.getNumSubtitles()+1);;
			}
		}
	}

	private void countVideoFiles() {
		File folder = new File(DIRECTORY);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()
					&& this.extensions.contains(FilenameUtils.getExtension(listOfFiles[i].getName().toLowerCase()))) {
				this.videos.add(FilenameUtils.getBaseName(listOfFiles[i].getName()));
				setNumVideos(this.getNumVideos()+1);;
			}
		}
	}

	public boolean isVideoFile(String format) {
		if (this.extensions.contains(format))
			return true;
		return false;
	}

	public boolean isStrFile(String format) {
		if (format.equalsIgnoreCase(SUBTITLE_FORMAT))
			return true;
		return false;
	}

	public HashSet<String> getExtensions() {
		return extensions;
	}

	public void setExtensions(HashSet<String> extensions) {
		this.extensions = extensions;
	}

	public HashSet<String> getVideos() {
		return videos;
	}

	public void setVideos(HashSet<String> videos) {
		this.videos = videos;
	}

	public HashSet<String> getSubtitles() {
		return subtitles;
	}

	public void setSubtitles(HashSet<String> subtitles) {
		this.subtitles = subtitles;
	}

	public TreeMap<Double, String> getSimilarity() {
		return similarity;
	}

	public void setSimilarity(TreeMap<Double, String> similarity) {
		this.similarity = similarity;
	}

	public Levenshtein getLevenshtein() {
		return levenshtein;
	}

	public void setLevenshtein(Levenshtein levenshtein) {
		this.levenshtein = levenshtein;
	}

	public int getNumVideos() {
		return numVideos;
	}

	public void setNumVideos(int numVideos) {
		this.numVideos = numVideos;
	}

	public int getNumSubtitles() {
		return numSubtitles;
	}

	public void setNumSubtitles(int numSubtitles) {
		this.numSubtitles = numSubtitles;
	}

}
