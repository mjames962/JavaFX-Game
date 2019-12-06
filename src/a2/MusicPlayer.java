package a2;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JOptionPane;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Plays music and sound effects.
 * @author Darius Thomas
 * @version 1.0
 */
public class MusicPlayer {
	
	/**
	 * Plays the audio file.
	 * @param filename the path of the soundbyte
	 */
	public static void playMusic(String filename) {
		InputStream music;
		try {
			music = new FileInputStream(new File(filename));
			AudioStream audio = new AudioStream(music);
			AudioPlayer.player.start(audio);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error");
		}
	}
}