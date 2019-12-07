package a2;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Plays sound files in the game.
 * @author Darius Thomas
 * @version 1.0
 */
public class MusicPlayer {
	private static boolean muted = false;
	public static final String MUSIC_FOLDER_LOCATION = "src/a2/resources/Sound bytes";
	private Clip musicClip;
	private AudioInputStream currentAudioStream;
	private String audioPath;

	/**
	 * Loads the music file.
	 * @param audioFilePath the file path of the music.
	 */
	
	public MusicPlayer(String audioFilePath) {
	    if (!muted) {
            File musicFile = new File(audioFilePath);
            audioPath = audioFilePath;
            try {

                currentAudioStream =
                        AudioSystem.getAudioInputStream(musicFile);
                AudioFormat format = currentAudioStream.getFormat();
                DataLine.Info musicInfo = new DataLine.Info(Clip.class, format);
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
	}

    /**
     * Returns whether or not the game sounds have been muted.
     * 
     * @return muted True if muted, false otherwise
     */
    public static boolean isMuted() {
        return muted;
    }

    /**
	 * Plays the music.
	 */
	public void play() {
        if (!muted) {
            try {
                musicClip.open(currentAudioStream);
                if (audioPath.equals(
                        MUSIC_FOLDER_LOCATION + "/Background music.wav")) {
                    musicClip.loop(Clip.LOOP_CONTINUOUSLY);
                }
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            musicClip.start();
        }
	}
	
	/**
	 * Gets the music clip.
	 * @return musicClip
	 */
	public Clip getmusicClip() {
		return musicClip;
	}

	/**
	 * Mutes the game if sound is playing, unmutes otherwise.
	 */
	public static void mute() {
	    muted = !muted;
    }
}