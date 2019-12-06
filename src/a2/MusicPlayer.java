package a2;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.sun.media.jfxmedia.AudioClip;

/**
 * Plays sound files in the game.
 * @author Darius Thomas
 * @version 1.0
 */
public class MusicPlayer implements LineListener{

	private static boolean Completed;

	void playMusic(String audioFilePath) 
			throws LineUnavailableException, IOException {
		File musicFile = new File(audioFilePath);

		try {

			AudioInputStream audioStream = 
					AudioSystem.getAudioInputStream(musicFile);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.addLineListener(this);
			audioClip.open(audioStream);
			audioClip.start();

			while (!Completed) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			audioClip.close();

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
	@Override
	public void update(LineEvent event) {
		LineEvent.Type type = event.getType();

		if (type == LineEvent.Type.START) {
			System.out.println("Playback started.");   
		} else if (type == LineEvent.Type.STOP) {
			Completed = true;
			System.out.println("Playback completed.");
		}
	}
	
	/**
	 * Stops the current track.
	 */
	public static void stop() {
		AudioClip.stopAllClips();	
	}
	
	public static void main(String[] args) {
	
	}
}