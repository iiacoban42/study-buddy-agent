package recording;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.Paths;

public class SoundRecorder {

    // record duration, in milliseconds
    static final long RECORD_TIME = 20000; // 20 sec max time

//    static int recordingNumber = 1;
    private String id;

    String fileName;

    String directoryPath;

    // path of the wav file
    File wavFile;

    // format of audio file
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;

    // the line (microphone) from which audio data is captured
    TargetDataLine line;

    public SoundRecorder(String id) {
        this.id = id;
        this.fileName = String.valueOf(id);
        this.directoryPath = Paths.get("").toAbsolutePath() + "\\src\\main\\java\\recording\\";
        this.wavFile = new File(directoryPath + fileName + ".wav");
    }

    /**
     * Defines an audio format
     */
    AudioFormat getAudioFormat() {
        float sampleRate = 44100;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                channels, signed, bigEndian);
        return format;
    }

    /**
     * Captures the sound and record into a WAV file
     */
    void start() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing

            System.out.println("Start capturing...");

            AudioInputStream ais = new AudioInputStream(line);

            System.out.println("Start recording...");

            // start recording
            AudioSystem.write(ais, fileType, wavFile);

        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Closes the target data line to finish capturing and recording
     */
    public void finish() {
        if (wavFile.renameTo(new File(directoryPath + "done\\" + fileName + ".wav")))
            System.out.println(fileName + " is done and moved");
        line.stop();
        line.close();
        System.out.println("Finished");
    }

    public SoundRecorder initialize(String id){
        SoundRecorder recorder = new SoundRecorder(id);
        return recorder;
    }

    /**
     * Entry to run the program
     *
     * @return
     */
    public void startRecording(SoundRecorder recorder) {
        System.out.println("starting to record");

        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(RECORD_TIME);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                recorder.finish();
            }
        });

        stopper.start();

        // start recording
        recorder.start();
    }
}
