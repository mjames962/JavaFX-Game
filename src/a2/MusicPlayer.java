package a2;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Plays sound files in the game.
 * @author Darius Thomas
 * @version 1.0
 */
public class MusicPlayer {
	private Clip musicClip;
	private Clip soundClip;
	private AudioInputStream currentAudioStream;
	private DataLine.Info musicInfo;
	private boolean isMuted = false;
	private String audioPath;
	
	
	/**
	 * Loads the music file.
	 * @param audioFilePath the file path of the music.
	 * @throws LineUnavailableException 
	 * @throws IOException
	 */
	
	public MusicPlayer(String audioFilePath) {
		File musicFile = new File(audioFilePath);
		audioPath = audioFilePath;
		try {
			
			currentAudioStream = 
					AudioSystem.getAudioInputStream(musicFile);
			AudioFormat format = currentAudioStream.getFormat();
			musicInfo = new DataLine.Info(Clip.class, format);
			musicClip = (Clip) AudioSystem.getLine(musicInfo);
		} catch (UnsupportedAudioFileException ex) {
			System.out.println("The specified audio file is not supported.");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			System.out.println("Audio line for playing back is unavailable.");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Error playing the audio file.");
			ex.printStackTrace();
		}
	}

	/**
	 * Plays the music.
	 */
	public void play() {
		try {
			musicClip.open(currentAudioStream);
			if (audioPath.equals(
					"src/a2/resources/Sound bytes/Background music.wav")) {
				musicClip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		musicClip.start();
	}
	
	public Clip getmusicClip() {
		return musicClip;
	}
}