from furhat_remote_api import FurhatRemoteAPI

import os
import pyaudio
import threading
import wave

CHUNK = 1024
FORMAT = pyaudio.paInt16
CHANNELS = 2
RATE = 44100
RECORD_SECONDS = 7  # TODO: change this to record input until user stops speaking like furhat does
WAVE_OUTPUT_FILENAME = "voice_files/output.wav"


def record_save(name):
    print("* recording")

    frames = []

    for i in range(0, int(RATE / CHUNK * RECORD_SECONDS)):
        data = stream.read(CHUNK)
        frames.append(data)

    print("* done recording")

    stream.stop_stream()
    stream.close()
    p.terminate()

    wf = wave.open(WAVE_OUTPUT_FILENAME, 'wb')
    wf.setnchannels(CHANNELS)
    wf.setsampwidth(p.get_sample_size(FORMAT))
    wf.setframerate(RATE)
    wf.writeframes(b''.join(frames))
    wf.close()


if __name__ == '__main__':
    mysp = __import__("my-voice-analysis")

    p = pyaudio.PyAudio()

    stream = p.open(format=FORMAT,
                    channels=CHANNELS,
                    rate=RATE,
                    input=True,
                    frames_per_buffer=CHUNK)

    # Create an instance of the FurhatRemoteAPI class, providing the address of the robot or the SDK running the virtual robot
    furhat = FurhatRemoteAPI("localhost")

    # Get the voices on the robot
    voices = furhat.get_voices()

    # Set the voice of the robot
    furhat.set_voice(name='Matthew')

    # Say "Hi there!"
    furhat.say(text="Hi there! I'm listening.")

    x = threading.Thread(target=record_save, args=(1,))

    x.start()
    # Listen to user speech and return ASR result
    result = furhat.listen()

    x.join()

    furhat.say(text="Now, I'm going to repeat what you just said.")

    furhat.say(text=result.message)

    dirname = os.path.dirname(__file__)
    filename = os.path.join(dirname, 'voice_files')

    p = "output"  # Audio File title
    c = filename  # Path to the Audio_File directory (Python 3.7)

    mysp.mysptotal(p, c)
