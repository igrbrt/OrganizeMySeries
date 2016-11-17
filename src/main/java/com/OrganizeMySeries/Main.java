package com.OrganizeMySeries;

import java.util.Iterator;

import javax.swing.JOptionPane;

/**
 * Program to rename the subtitle with the name closest to the video file.
 * You can use with movies and series.
 * Just copy the program, paste in the same folder the movies/series and run :)
 * 
 * Igor Barreto Rodrigues
 * igrbrt@gmail.com
 * 16/11/2016
 *
 */
public class Main 
{	
	
    public static void main( String[] args )
    {
    	MySeries series = new MySeries();
    	
    	if(series.getNumSubtitles() == 0){
    		JOptionPane.showMessageDialog(null,"Nao existem arquivos .str suficientes para a quantidade de videos","OrganizeMySubtitles", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else{
	    	for (Iterator<String> itVideos = series.getVideos().iterator(); itVideos.hasNext(); ) {
		    	String video = itVideos.next();
		    	for (Iterator<String> itSub = series.getSubtitles().iterator(); itSub.hasNext(); ) {
		    		String sub = itSub.next();
		    		series.getSimilarity().put(series.getLevenshtein().distance(video, sub),sub);
		    	}
		    	series.renameSubtitle(video, series.getSimilarity().firstEntry().getValue());
		    	series.getSimilarity().clear();
		    }
	    	JOptionPane.showMessageDialog(null,"Todos as legendas foram renomeadas com sucesso! Bom seriado/filme :)","OrganizeMySubtitles", JOptionPane.INFORMATION_MESSAGE);
    	}
    }
}
